package com.synacy.poker.game;

import com.synacy.poker.deck.DeckBuilder;
import com.synacy.poker.hand.HandIdentifier;
import com.synacy.poker.hand.WinningHandCalculatorService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

public class GameTest {

    @Autowired
    GameService gameService;
    @Test
    public void afterConstructorInit_eachPlayerHasTwoCards() {
        DeckBuilder deckBuilder = new DeckBuilder();
        HandIdentifier handIdentifier = mock(HandIdentifier.class);
        WinningHandCalculatorService winningHandCalculator = mock(WinningHandCalculatorService.class);

        GameService game = new GameService(deckBuilder, handIdentifier, winningHandCalculator);

        assertPlayersHaveTwoCardsEach(game);
    }

    @Test
    public void startNewGame_eachPlayerHasTwoCards() {
        DeckBuilder deckBuilder = new DeckBuilder();
        HandIdentifier handIdentifier = mock(HandIdentifier.class);
        WinningHandCalculatorService winningHandCalculator = mock(WinningHandCalculatorService.class);

        GameService game = new GameService(deckBuilder, handIdentifier, winningHandCalculator);

        assertPlayersHaveTwoCardsEach(game);
    }

    private void assertPlayersHaveTwoCardsEach(GameService game) {
        game.getPlayers().forEach(player ->
                assertEquals("Players should have 2 cards each",
                        2,
                        player.getHand().size()));
    }

//    @Test
//    public void nextAction_dealCommunityCards() {
//        DeckBuilder deckBuilder = new DeckBuilder();
//        HandIdentifier handIdentifier = mock(HandIdentifier.class);
//        WinningHandCalculatorService winningHandCalculator = mock(WinningHandCalculatorService.class);
//
//        GameService game = new GameService(deckBuilder, handIdentifier, winningHandCalculator);
//
//        game.nextAction();
//        assertEquals("Deal three community cards at the start", 3, game.getCommunityCards().size());
//
//        game.nextAction();
//        assertEquals("Expecting four community cards", 4, game.getCommunityCards().size());
//
//        game.nextAction();
//        assertEquals("Expecting 5 community cards", 5, game.getCommunityCards().size());
//    }
}
