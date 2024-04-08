package com.synacy.poker.hand;

import com.synacy.poker.model.card.Card;
import com.synacy.poker.model.card.CardRank;
import com.synacy.poker.model.card.CardSuit;
import com.synacy.poker.model.hand.types.TwoPair;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.assertj.core.api.Assertions.assertThat;
public class TwoPairTest {

    @Test
    public void toString_withTwoPairsAndAceKicker() {
        List<Card> firstPair = Arrays.asList(
                new Card.Builder()
                        .rank(CardRank.FOUR)
                        .suit(CardSuit.CLUBS)
                        .build(),
                new Card.Builder()
                        .rank(CardRank.FOUR)
                        .suit(CardSuit.DIAMONDS)
                        .build()
        );
        List<Card> secondPair = Arrays.asList(
                new Card.Builder()
                        .rank(CardRank.THREE)
                        .suit(CardSuit.CLUBS)
                        .build(),
                new Card.Builder()
                        .rank(CardRank.TEN)
                        .suit(CardSuit.DIAMONDS)
                        .build()
        );

        TwoPair twoPair = new TwoPair(firstPair,
                secondPair,
                new Card.Builder()
                        .rank(CardRank.ACE)
                        .suit(CardSuit.CLUBS)
                        .build()
        );

        assertEquals("Two Pair (4,3) - A High", twoPair.toString());
    }

    @Test
    public void givenTwoTwoPairsWithEqualRanks_thenCompareTo_shouldReturnEqual() {
        List<Card> firstPair = Arrays.asList(
                new Card.Builder()
                        .rank(CardRank.FOUR)
                        .suit(CardSuit.CLUBS)
                        .build(),
                new Card.Builder()
                        .rank(CardRank.FOUR)
                        .suit(CardSuit.DIAMONDS)
                        .build()
        );
        List<Card> secondPair = Arrays.asList(
                new Card.Builder()
                        .rank(CardRank.THREE)
                        .suit(CardSuit.CLUBS)
                        .build(),
                new Card.Builder()
                        .rank(CardRank.TEN)
                        .suit(CardSuit.DIAMONDS)
                        .build()
        );

        TwoPair twoPairA = new TwoPair(firstPair,
                secondPair,
                new Card.Builder()
                        .rank(CardRank.ACE)
                        .suit(CardSuit.CLUBS)
                        .build()
        );

        TwoPair twoPairB = new TwoPair(firstPair,
                secondPair,
                new Card.Builder()
                        .rank(CardRank.ACE)
                        .suit(CardSuit.CLUBS)
                        .build()
        );

        assertThat(twoPairA)
                .isEqualByComparingTo(twoPairB);
    }

    @Test
    public void givenTwoTwoPairs_thenCompareTo_shouldReturnSecondPair() {
        List<Card> firstPair = Arrays.asList(
                new Card.Builder()
                        .rank(CardRank.FIVE)
                        .suit(CardSuit.CLUBS)
                        .build(),
                new Card.Builder()
                        .rank(CardRank.FIVE)
                        .suit(CardSuit.SPADES)
                        .build()
        );
        List<Card> secondPair = Arrays.asList(
                new Card.Builder()
                        .rank(CardRank.FOUR)
                        .suit(CardSuit.DIAMONDS)
                        .build(),
                new Card.Builder()
                        .rank(CardRank.FOUR)
                        .suit(CardSuit.HEARTS)
                        .build()
        );

        TwoPair twoPairA = new TwoPair(firstPair,
                secondPair,
                new Card.Builder()
                        .rank(CardRank.TEN)
                        .suit(CardSuit.HEARTS)
                        .build()
        );

        List<Card> otherFirstPair = Arrays.asList(
                new Card.Builder()
                        .rank(CardRank.TEN)
                        .suit(CardSuit.DIAMONDS)
                        .build(),
                new Card.Builder()
                        .rank(CardRank.TEN)
                        .suit(CardSuit.SPADES)
                        .build()
        );
        List<Card> otherSecondPair = Arrays.asList(
                new Card.Builder()
                        .rank(CardRank.TWO)
                        .suit(CardSuit.SPADES)
                        .build(),
                new Card.Builder()
                        .rank(CardRank.TWO)
                        .suit(CardSuit.CLUBS)
                        .build()
        );
        TwoPair twoPairB = new TwoPair(otherFirstPair,
                otherSecondPair,
                new Card.Builder()
                        .rank(CardRank.KING)
                        .suit(CardSuit.CLUBS)
                        .build()
        );

        assertThat(twoPairB)
                .isGreaterThan(twoPairA);
    }
}