package com.synacy.poker.game;

import com.synacy.poker.model.Player;
import com.synacy.poker.model.card.Card;
import com.synacy.poker.model.card.CardRank;
import com.synacy.poker.model.card.CardSuit;
import com.synacy.poker.service.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static com.synacy.poker.model.card.CardRank.FIVE;
import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.then;

@SpringBootTest
@RunWith(SpringRunner.class)
public class GameServiceTest {
    @Mock
    DeckService deckService;
    @Mock
    private HandIdentifierService handIdentifierService;
    @Mock
    private WinningHandCalculatorService winningHandCalculatorService;
    @Mock
    private PlayerService playerService;
    @Mock
    private CommunityCardsService communityCardsService;
    @Mock
    Player playerOne = new Player("Alex");
    @Mock
    Player playerTwo = new Player("Bob");
    @Mock
    Player playerThree = new Player("Jane");

    @InjectMocks
    GameService gameService;

    @Test
    public void afterConstructorInit_eachPlayerHasTwoCards() {
        List<Card> playerCards = Arrays.asList(new Card.Builder().rank(CardRank.TWO).suit(CardSuit.CLUBS).build(),
                new Card.Builder().rank(FIVE).suit(CardSuit.HEARTS).build());

        given(playerOne.getHand()).willReturn(playerCards);
        given(playerThree.getHand()).willReturn(playerCards);
        given(playerTwo.getHand()).willReturn(playerCards);

        given(playerService.getPlayers())
                .willReturn(Arrays.asList(playerOne,
                        playerTwo,
                        playerThree));

        for(Player p : gameService.getPlayers()){
            List<Card> hand = p.getHand();
            then(p).should().getHand();
            assertThat(hand)
                    .hasSize(2);

        }
    }

    @Test
    public void nextAction_dealCommunityCards() {

        gameService.nextAction();
        assertEquals("Deal three community cards at the start", 3, gameService.getCommunityCards().size());

        gameService.nextAction();
        assertEquals("Expecting four community cards", 4, gameService.getCommunityCards().size());

        gameService.nextAction();
        assertEquals("Expecting 5 community cards", 5, gameService.getCommunityCards().size());
    }
}
