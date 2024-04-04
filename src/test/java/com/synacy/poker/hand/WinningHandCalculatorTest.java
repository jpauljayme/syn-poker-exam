package com.synacy.poker.hand;

import com.synacy.poker.card.Card;
import com.synacy.poker.card.CardRank;
import com.synacy.poker.card.CardSuit;
import com.synacy.poker.hand.types.Flush;
import com.synacy.poker.hand.types.FourOfAKind;
import com.synacy.poker.hand.types.StraightFlush;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.*;

import java.util.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class WinningHandCalculatorTest {

    @Autowired
    WinningHandCalculatorService winningHandCalculatorService;

    @Test
    public void givenThreePlayers_thenIdentifyWinningHand_shouldReturnPlayerOneInAListWithStraightFlush() {
        List<Hand> playerHands = new ArrayList<>();
        Hand expectedWinningHand = new StraightFlush(
                Arrays.asList(new Card.Builder()
                                .rank(CardRank.QUEEN)
                                .suit(CardSuit.HEARTS)
                                .build(),
                        new Card.Builder()
                                .rank(CardRank.QUEEN)
                                .suit(CardSuit.HEARTS)
                                .build(),
                        new Card.Builder()
                                .rank(CardRank.JACK)
                                .suit(CardSuit.HEARTS)
                                .build(),
                        new Card.Builder()
                                .rank(CardRank.TEN)
                                .suit(CardSuit.HEARTS)
                                .build(),
                        new Card.Builder()
                                .rank(CardRank.NINE)
                                .suit(CardSuit.HEARTS)
                                .build())
        );
        playerHands.add(expectedWinningHand);
        playerHands.add(new FourOfAKind(
                Arrays.asList(new Card.Builder()
                                .rank(CardRank.TEN)
                                .suit(CardSuit.HEARTS)
                                .build(),
                        new Card.Builder()
                                .rank(CardRank.TEN)
                                .suit(CardSuit.CLUBS)
                                .build(),
                        new Card.Builder()
                                .rank(CardRank.TEN)
                                .suit(CardSuit.DIAMONDS)
                                .build(),
                        new Card.Builder()
                                .rank(CardRank.TEN)
                                .suit(CardSuit.SPADES)
                                .build()),
                Collections.singletonList(new Card.Builder().rank(CardRank.FIVE)
                        .suit(CardSuit.DIAMONDS).build())
        ));
        playerHands.add(new Flush(
                Arrays.asList(new Card.Builder()
                                .rank(CardRank.TEN)
                                .suit(CardSuit.HEARTS)
                                .build(),
                        new Card.Builder()
                                .rank(CardRank.NINE)
                                .suit(CardSuit.HEARTS)
                                .build(),
                        new Card.Builder()
                                .rank(CardRank.SEVEN)
                                .suit(CardSuit.HEARTS)
                                .build(),
                        new Card.Builder()
                                .rank(CardRank.TWO)
                                .suit(CardSuit.HEARTS)
                                .build(),
                        new Card.Builder()
                                .rank(CardRank.FIVE)
                                .suit(CardSuit.HEARTS)
                                .build())
        ));
        Optional<Hand> winningHand = winningHandCalculatorService.calculateWinningHand(playerHands);
        assertThat(winningHand)
                .isPresent()
                .contains(expectedWinningHand);
    }

    @Test
    public void givenThreePlayers_thenIdentifyWinningHand_shouldReturnPlayerOneAndTwoInAListOfStraightFlushAsTie() {
        List<Hand> playerHands = new ArrayList<>();
        Hand expectedWinningHand = new StraightFlush(
                Arrays.asList(new Card.Builder()
                                .rank(CardRank.QUEEN)
                                .suit(CardSuit.HEARTS)
                                .build(),
                        new Card.Builder()
                                .rank(CardRank.JACK)
                                .suit(CardSuit.HEARTS)
                                .build(),
                        new Card.Builder()
                                .rank(CardRank.TEN)
                                .suit(CardSuit.HEARTS)
                                .build(),
                        new Card.Builder()
                                .rank(CardRank.NINE)
                                .suit(CardSuit.HEARTS)
                                .build(),
                        new Card.Builder()
                                .rank(CardRank.EIGHT)
                                .suit(CardSuit.HEARTS)
                                .build())
        );
        playerHands.add(expectedWinningHand);

        Hand expectedWinningHandPLayerTwo = new StraightFlush(
                Arrays.asList(new Card.Builder()
                                .rank(CardRank.QUEEN)
                                .suit(CardSuit.CLUBS)
                                .build(),
                        new Card.Builder()
                                .rank(CardRank.JACK)
                                .suit(CardSuit.CLUBS)
                                .build(),
                        new Card.Builder()
                                .rank(CardRank.TEN)
                                .suit(CardSuit.CLUBS)
                                .build(),
                        new Card.Builder()
                                .rank(CardRank.NINE)
                                .suit(CardSuit.CLUBS)
                                .build(),
                        new Card.Builder()
                                .rank(CardRank.EIGHT)
                                .suit(CardSuit.CLUBS)
                                .build())
        );
        playerHands.add(expectedWinningHandPLayerTwo);

        playerHands.add(new Flush(
                Arrays.asList(new Card.Builder()
                                .rank(CardRank.TEN)
                                .suit(CardSuit.HEARTS)
                                .build(),
                        new Card.Builder()
                                .rank(CardRank.NINE)
                                .suit(CardSuit.HEARTS)
                                .build(),
                        new Card.Builder()
                                .rank(CardRank.SEVEN)
                                .suit(CardSuit.HEARTS)
                                .build(),
                        new Card.Builder()
                                .rank(CardRank.TWO)
                                .suit(CardSuit.HEARTS)
                                .build(),
                        new Card.Builder()
                                .rank(CardRank.FIVE)
                                .suit(CardSuit.HEARTS)
                                .build())
        ));

        Optional<Hand> winningHand = winningHandCalculatorService.calculateWinningHand(playerHands);

        assertThat(winningHand)
                .isPresent()
                .contains(expectedWinningHand);
    }
}