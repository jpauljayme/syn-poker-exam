package com.synacy.poker.service.game;

import com.synacy.poker.model.Player;
import com.synacy.poker.model.card.Card;
import com.synacy.poker.model.deck.Deck;
import com.synacy.poker.model.deck.DeckBuilder;
import com.synacy.poker.model.hand.Hand;
import com.synacy.poker.model.hand.HandIdentifier;
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

    private final List<Player> players = new ArrayList<>();

    private final List<Card> communityCards = new ArrayList<>();

    private final DeckBuilder deckBuilder;
    private final HandIdentifier handIdentifier;
    private final WinningHandCalculatorService winningHandCalculator;

    private Deck deck;

    private Hand winningHand = null;

    private static final int MAX_PLAYER_CARDS = 2;
    private static final int MAX_COMMUNITY_CARDS = 5;
    private boolean isGameInProgress = false;

    @Autowired
    public GameService(DeckBuilder deckBuilder,
                       HandIdentifier handIdentifier,
                       WinningHandCalculatorService winningHandCalculator) {
        this.deckBuilder = deckBuilder;
        this.handIdentifier = handIdentifier;
        this.winningHandCalculator = winningHandCalculator;
//        players.add(new Player("Alex"));
//        players.add(new Player("Bob"));
//        players.add(new Player("Jane"));
//        startNewGame();
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
        isGameInProgress = true;
        if(players.isEmpty()){
            players.add(new Player("Alex"));
            players.add(new Player("Bob"));
            players.add(new Player("Jane"));
        }
        players.forEach(Player::clearHand);
        communityCards.clear();

        deck = deckBuilder.buildDeck();
        deck.shuffle();

        dealHands();
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
        if (communityCards.isEmpty()) {
            burnCard();
            dealThreeCommunityCards();
        } else if (communityCards.size() < MAX_COMMUNITY_CARDS) {
            burnCard();
            dealOneCommunityCard();
        }

        if (hasEnded()) {
            System.out.println("Has ended");
            identifyWinningHand();
        }
    }

    /**
     * Checks the combination of the players and community cards to identify the winning hand.
     *
     * @see <a href="https://www.youtube.com/watch?v=GAoR9ji8D6A">Poker rules</a>
     */
    public void identifyWinningHand() {
        System.out.println("In identify winning hand");
        List<Hand> playerHands = players.stream()
                .map(this::identifyPlayerHand)
                .collect(Collectors.toList());
        Optional<Hand> optionalHand=  winningHandCalculator.calculateWinningHand(playerHands);
        winningHand = optionalHand.orElse(null);
    }

    /**
     * Checks if the player won
     *
     * @param player
     * @return true if the player's hand is equal to the winning hand.
     */
    public boolean checkIfPlayerWon(Player player) {
        System.out.println("Check if player won : " + player.getName());
        Hand playerHand = identifyPlayerHand(player);
        System.out.println("Player hand" + playerHand);
        System.out.println("Winning Hand: " + winningHand);
        return winningHand != null && winningHand.equals(playerHand);
    }

    /**
     * Identifies the player's hand. A hand is combination of the two cards in the player's
     * possession and the community cards on the table.
     *
     * @param player
     * @return The {@link} of a player, e.g. High Card, One Pair, Straight, etc.
     * @see <a href="https://www.youtube.com/watch?v=GAoR9ji8D6A">Poker rules</a>
     */
    public Hand identifyPlayerHand(Player player) {
        List<Card> playerCards = player.getHand();
        return handIdentifier.identifyHand(playerCards, communityCards);
    }

    /**
     * @return The list of {@link Player}s
     */
    public List<Player> getPlayers() {
        return players;
    }

    /**
     * @return The list of community cards {@link Card}
     */
    public List<Card> getCommunityCards() {
        return communityCards;
    }

    /**
     * @return true if the number of community cards is equal to the maximum community cards allowed.
     */
    public boolean hasEnded() {
        return communityCards.size() >= MAX_COMMUNITY_CARDS;
    }

    private void dealHands() {
        for (int i = 0; i < MAX_PLAYER_CARDS; i++) {
            dealOneCardToEachPlayer();
        }
    }

    private void dealOneCardToEachPlayer() {
        players.forEach(player -> player.addToHand(deck.removeFromTop()));
    }

    private void dealThreeCommunityCards() {
        communityCards.add(deck.removeFromTop());
        communityCards.add(deck.removeFromTop());
        communityCards.add(deck.removeFromTop());
    }

    private void dealOneCommunityCard() {
        communityCards.add(deck.removeFromTop());
    }

    private void burnCard() {
        deck.removeFromTop();
    }

    public boolean isGameInProgress() {
        return isGameInProgress;
    }
}
