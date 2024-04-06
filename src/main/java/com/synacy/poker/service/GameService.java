package com.synacy.poker.service;

import com.synacy.poker.model.Player;
import com.synacy.poker.model.card.Card;

import com.synacy.poker.model.hand.Hand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * The game engine.
 */
@Service
public class GameService {

//    private List<Player> players;

    private final List<Card> communityCards = new ArrayList<>();

    private final DeckService deckService;
    private final HandIdentifierService handIdentifierService;
    private final WinningHandCalculatorService winningHandCalculatorService;
    private final PlayerService playerService;
    private final CommunityCardsService communityCardsService;

    @Autowired
    public GameService(DeckService deckService,
                       HandIdentifierService handIdentifierService,
                       WinningHandCalculatorService winningHandCalculatorService,
                       PlayerService playerService,
                       CommunityCardsService communityCardsService) {
        this.deckService = deckService;
        this.handIdentifierService = handIdentifierService;
        this.winningHandCalculatorService = winningHandCalculatorService;
        this.playerService = playerService;
        this.communityCardsService = communityCardsService;
    }
    @PostConstruct
    public void init() {
        // Actions to perform after initialization
        startNewGame();
    }

    /**
     * Starts a new game.
     *
     * <h3>The following describes a new game.</h3>
     * <ul>
     * <li>Players' previous hands are cleared</li>
     * <li>Community cards are cleared</li>
     * <li>A new deck is used</li>
     * <li>The deck is shuffled</li>
     * <li>Players' are dealt with new cards.</li>
     * </ul>
     */
    public void startNewGame() {
        playerService.clearHandForEachPlayer();
        communityCardsService.clearCommunityCards();
        deckService.buildDeck();
        deckService.shuffle();
        playerService.dealHands();
    }

    /**
     * The action to take after a new game has been started.
     *
     * <ol>
     * <li>Deal three community cards</li>
     * <li>Deal one community card</li>
     * <li>Deal another community card</li>
     * <li>Determine the winner/s</li>
     * </ol>
     * <p>
     * Dealt community are of course removed from the deck at the time their placed on the table.
     */
    public void nextAction() {
        if (communityCardsService.isCommunityCardsEmpty()) {
            burnCard();
            dealThreeCommunityCards();
        } else if (!communityCardsService.isCommunityCardsFull()) {
            burnCard();
            dealOneCommunityCard();
        }

        if (hasEnded()) {
            identifyWinningHand();
        }
    }

    /**
     * Checks the combination of the players and community cards to identify the winning hand.
     *
     * @see <a href="https://www.youtube.com/watch?v=GAoR9ji8D6A">Poker rules</a>
     */
    public void identifyWinningHand() {
        List<Hand> playerHands = playerService.getPlayers().stream()
                .map(this::identifyPlayerHand)
                .collect(Collectors.toList());
        winningHandCalculatorService.calculateWinningHand(playerHands);
    }

    /**
     * Checks if the player won
     *
     * @param player
     * @return true if the player's hand is equal to the winning hand.
     */
    public boolean checkIfPlayerWon(Player player) {
        Hand playerHand = identifyPlayerHand(player);
        return winningHandCalculatorService.getWinningHand().equals(playerHand);
    }

    /**
     * Identifies the player's hand. A hand is combination of the two cards in the player's
     * possession and the community cards on the table.
     *
     * @param player
     * @return The {@link} of a player, e.g. High Card, One Pair, Straight, etc.
     * @see <a href="https://www.youtube.com/watch?v=GAoR9ji8D6A">Poker rules</a>
     */
    public Hand identifyPlayerHand(Player player) {return handIdentifierService.identifyHand(player.getHand(), communityCardsService.getCommunityCards());}

    /**
     * @return The list of {@link Player}s
     */
    public List<Player> getPlayers() {
        return playerService.getPlayers();
    }

    /**
     * @return The list of community cards {@link Card}
     */
    public List<Card> getCommunityCards() {
        return communityCardsService.getCommunityCards();
    }

    /**
     * @return true if the number of community cards is equal to the maximum community cards allowed.
     */
    public boolean hasEnded() {
//        return communityCards.size() >= MAX_COMMUNITY_CARDS;
        return communityCardsService.isCommunityCardsFull();
    }

    private void dealOneCardToEachPlayer() {
//        players.forEach(player -> player.addToHand(deck.removeFromTop()));
    }

    private void dealThreeCommunityCards() {
        communityCardsService.addCommunityCard(deckService.removeCardFromTop());
        communityCardsService.addCommunityCard(deckService.removeCardFromTop());
        communityCardsService.addCommunityCard(deckService.removeCardFromTop());
    }

    private void dealOneCommunityCard() {
        communityCardsService.addCommunityCard(deckService.removeCardFromTop());
    }

    private void burnCard() {
        deckService.removeCardFromTop();
    }
}
