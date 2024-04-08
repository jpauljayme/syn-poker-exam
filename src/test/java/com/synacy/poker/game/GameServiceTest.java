package com.synacy.poker.game;

import com.synacy.poker.model.Player;
import com.synacy.poker.model.card.Card;
import com.synacy.poker.model.card.CardRank;
import com.synacy.poker.model.hand.Hand;
import com.synacy.poker.model.hand.types.HighCard;
import com.synacy.poker.model.hand.types.OnePair;
import com.synacy.poker.model.hand.types.Straight;
import com.synacy.poker.repository.PlayerRepository;
import com.synacy.poker.service.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.synacy.poker.model.card.CardRank.*;
import static com.synacy.poker.model.card.CardSuit.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.*;

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
    Player playerOne;
    @Mock
    Player playerTwo;
    @Mock
    Player playerThree;
    @Mock
    Card communityCardOne;
    @Mock
    Card communityCardFive;
    @Mock
    Card communityCardTwo;
    @Mock
    Card communityCardThree;
    @Mock
    Card communityCardFour;
    @Mock
    List<Card> communityCards;

    @InjectMocks
    GameService gameService;

    @Mock
    PlayerRepository playerRepository;

    @Mock
    List<Hand> playerHands;

    @Test
    public void afterConstructorInit_eachPlayerHasTwoCards() {
        List<Card> playerCards = Arrays.asList(new Card.Builder().rank(CardRank.TWO).suit(CLUBS).build(),
                new Card.Builder().rank(FIVE).suit(HEARTS).build());

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
    public void test_checkIfPlayerWon(){

        List<Card> playerTwoCards = Arrays.asList(new Card.Builder()
                        .rank(CardRank.TWO)
                        .suit(DIAMONDS)
                        .build(),
                new Card.Builder()
                        .rank(NINE)
                        .suit(SPADES)
                        .build());

        List<Card> playerOneCards = Arrays.asList(new Card.Builder()
                        .rank(EIGHT)
                        .suit(CLUBS)
                        .build(),
                new Card.Builder()
                        .rank(KING)
                        .suit(DIAMONDS)
                        .build());

        List<Card> playerThreeCards = Arrays.asList(new Card.Builder()
                        .rank(THREE)
                        .suit(HEARTS)
                        .build(),
                new Card.Builder()
                        .rank(TEN)
                        .suit(DIAMONDS)
                        .build());

        given(playerOne.getHand()).willReturn(playerOneCards);
        given(playerThree.getHand()).willReturn(playerThreeCards);
        given(playerTwo.getHand()).willReturn(playerTwoCards);

        given(playerService.getPlayers())
                .willReturn(Arrays.asList(playerOne,
                        playerTwo,
                        playerThree));

        communityCards = Arrays.asList(communityCardOne,
                communityCardTwo,
                communityCardThree,
                communityCardFour,
                communityCardFive);

        willDoNothing().given(playerService).clearHandForEachPlayer();
        willDoNothing().given(deckService).buildDeck();
        willDoNothing().given(deckService).shuffle();
        willDoNothing().given(playerService).dealHands();
        given(communityCardsService.getCommunityCards())
                .willReturn(communityCards);

        List<Hand> playerHands = new ArrayList<>();
        Hand playerTwoHand = new OnePair(
                Arrays.asList(new Card.Builder()
                                .rank(NINE)
                                .suit(SPADES)
                                .build(),
                        new Card.Builder()
                                .rank(NINE)
                                .suit(HEARTS)
                                .build()),
                Arrays.asList(
                        new Card.Builder()
                                .rank(QUEEN)
                                .suit(DIAMONDS)
                                .build(),
                        new Card.Builder()
                                .rank(TEN)
                                .suit(SPADES)
                                .build(),
                        new Card.Builder()
                                .rank(FIVE)
                                .suit(HEARTS)
                                .build())
        );

        Hand playerThreeHand = new OnePair(
                Arrays.asList(new Card.Builder()
                                .rank(TEN)
                                .suit(DIAMONDS)
                                .build(),
                        new Card.Builder()
                                .rank(TEN)
                                .suit(SPADES)
                                .build()),
                Arrays.asList(new Card.Builder()
                                .rank(QUEEN)
                                .suit(DIAMONDS)
                                .build(),
                        new Card.Builder()
                                .rank(NINE)
                                .suit(HEARTS)
                                .build(),
                        new Card.Builder()
                                .rank(FIVE)
                                .suit(HEARTS)
                                .build()
                ));

        Hand playerOneHand = new HighCard(
                Arrays.asList(new Card.Builder()
                                .rank(KING)
                                .suit(DIAMONDS)
                                .build(),
                        new Card.Builder()
                                .rank(QUEEN)
                                .suit(DIAMONDS)
                                .build(),
                        new Card.Builder()
                                .rank(TEN)
                                .suit(SPADES)
                                .build(),
                        new Card.Builder()
                                .rank(NINE)
                                .suit(HEARTS)
                                .build(),
                        new Card.Builder()
                                .rank(EIGHT)
                                .suit(CLUBS)
                                .build())
        );

        given(communityCardsService.isCommunityCardsEmpty())
                .willReturn(false);
        given(communityCardsService.isCommunityCardsFull())
                .willReturn(true);
        given(handIdentifierService.identifyHand(playerOneCards,
                communityCards)).willReturn(playerOneHand);
        given(handIdentifierService.identifyHand(playerTwoCards,
                communityCards)).willReturn(playerTwoHand);
        given(handIdentifierService.identifyHand(playerThreeCards,
                communityCards)).willReturn(playerThreeHand);

        willDoNothing().given(winningHandCalculatorService)
                .calculateWinningHand(playerHands);

        given(winningHandCalculatorService.isPlayerWinner(playerOneHand))
                .willReturn(false);
        given(winningHandCalculatorService.isPlayerWinner(playerTwoHand))
                .willReturn(true);
        given(winningHandCalculatorService.isPlayerWinner(playerThreeHand))
                .willReturn(false);
        gameService.nextAction();
        boolean p3Check = gameService.checkIfPlayerWon(playerThree);
        boolean p2Check = gameService.checkIfPlayerWon(playerTwo);
        boolean p1Check = gameService.checkIfPlayerWon(playerOne);
        assertThat(p2Check)
                .isTrue();
        assertThat(p1Check)
                .isFalse();
        assertThat(p3Check)
                .isFalse();
    }

    @Test
    public void givenTwoStraightHandAndHighHand_thenCheckIfPlayerWon_shouldReturnTie(){

        List<Card> playerTwoCards = Arrays.asList(new Card.Builder()
                        .rank(SEVEN)
                        .suit(SPADES)
                        .build(),
                new Card.Builder()
                        .rank(SIX)
                        .suit(HEARTS)
                        .build());

        List<Card> playerOneCards = Arrays.asList(new Card.Builder()
                        .rank(SIX)
                        .suit(CLUBS)
                        .build(),
                new Card.Builder()
                        .rank(SEVEN)
                        .suit(CLUBS)
                        .build());

        List<Card> playerThreeCards = Arrays.asList(new Card.Builder()
                        .rank(KING)
                        .suit(DIAMONDS)
                        .build(),
                new Card.Builder()
                        .rank(QUEEN)
                        .suit(DIAMONDS)
                        .build());

        given(playerOne.getHand()).willReturn(playerOneCards);
        given(playerThree.getHand()).willReturn(playerThreeCards);
        given(playerTwo.getHand()).willReturn(playerTwoCards);

        given(playerService.getPlayers())
                .willReturn(Arrays.asList(playerOne,
                        playerTwo,
                        playerThree));

        communityCards = Arrays.asList(communityCardOne,
                communityCardTwo,
                communityCardThree,
                communityCardFour,
                communityCardFive);

        willDoNothing().given(playerService).clearHandForEachPlayer();
        willDoNothing().given(deckService).buildDeck();
        willDoNothing().given(deckService).shuffle();
        willDoNothing().given(playerService).dealHands();
        given(communityCardsService.getCommunityCards())
                .willReturn(communityCards);

        List<Hand> playerHands = new ArrayList<>();
        Hand playerTwoHand = new Straight(
                Arrays.asList(new Card.Builder()
                                .rank(SEVEN)
                                .suit(SPADES)
                                .build(),
                        new Card.Builder()
                                .rank(SIX)
                                .suit(HEARTS)
                                .build(),
                        new Card.Builder()
                                .rank(FIVE)
                                .suit(CLUBS)
                                .build(),
                        new Card.Builder()
                                .rank(FOUR)
                                .suit(DIAMONDS)
                                .build(),
                        new Card.Builder()
                                .rank(THREE)
                                .suit(DIAMONDS)
                                .build()
        ));

        Hand playerThreeHand = new HighCard(
                Arrays.asList(new Card.Builder()
                                .rank(KING)
                                .suit(DIAMONDS)
                                .build(),
                        new Card.Builder()
                                .rank(QUEEN)
                                .suit(DIAMONDS)
                                .build(),
                new Card.Builder()
                                .rank(NINE)
                                .suit(CLUBS)
                                .build(),
                        new Card.Builder()
                                .rank(SEVEN)
                                .suit(HEARTS)
                                .build(),
                        new Card.Builder()
                                .rank(FIVE)
                                .suit(CLUBS)
                                .build()
                ));

        Hand playerOneHand = new HighCard(
                Arrays.asList(new Card.Builder()
                                .rank(SEVEN)
                                .suit(CLUBS)
                                .build(),
                        new Card.Builder()
                                .rank(SIX)
                                .suit(CLUBS)
                                .build(),
                        new Card.Builder()
                                .rank(FIVE)
                                .suit(CLUBS)
                                .build(),
                        new Card.Builder()
                                .rank(FOUR)
                                .suit(DIAMONDS)
                                .build(),
                        new Card.Builder()
                                .rank(THREE)
                                .suit(DIAMONDS)
                                .build())
        );

        given(communityCardsService.isCommunityCardsEmpty())
                .willReturn(false);
        given(communityCardsService.isCommunityCardsFull())
                .willReturn(true);
        given(handIdentifierService.identifyHand(playerOneCards,
                communityCards)).willReturn(playerOneHand);
        given(handIdentifierService.identifyHand(playerTwoCards,
                communityCards)).willReturn(playerTwoHand);
        given(handIdentifierService.identifyHand(playerThreeCards,
                communityCards)).willReturn(playerThreeHand);

        willDoNothing().given(winningHandCalculatorService)
                .calculateWinningHand(playerHands);

        given(winningHandCalculatorService.isPlayerWinner(playerOneHand))
                .willReturn(false);
        given(winningHandCalculatorService.isPlayerWinner(playerTwoHand))
                .willReturn(true);
        given(winningHandCalculatorService.isPlayerWinner(playerThreeHand))
                .willReturn(false);
        gameService.nextAction();
        boolean p3Check = gameService.checkIfPlayerWon(playerThree);
        boolean p2Check = gameService.checkIfPlayerWon(playerTwo);
        boolean p1Check = gameService.checkIfPlayerWon(playerOne);
        assertThat(p2Check)
                .isTrue();
        assertThat(p1Check)
                .isFalse();
        assertThat(p3Check)
                .isFalse();
    }
}
