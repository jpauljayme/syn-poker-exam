package com.synacy.poker.hand;

import com.synacy.poker.model.card.Card;
import com.synacy.poker.model.hand.types.StraightFlush;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static com.synacy.poker.model.card.CardRank.*;
import static com.synacy.poker.model.card.CardSuit.*;
import static org.junit.Assert.assertEquals;
import static org.assertj.core.api.Assertions.assertThat;

public class StraightFlushTest {

    @Test
    public void toString_withKingHighStraightFlush() {
        List<Card> cards = Arrays.asList(
                new Card.Builder()
                        .rank(KING)
                        .suit(CLUBS)
                        .build(),
                new Card.Builder()
                        .rank(QUEEN)
                        .suit(CLUBS)
                        .build(),
                new Card.Builder()
                        .rank(JACK)
                        .suit(CLUBS)
                        .build(),
                new Card.Builder()
                        .rank(TEN)
                        .suit(CLUBS)
                        .build(),
                new Card.Builder()
                        .rank(NINE)
                        .suit(CLUBS)
                        .build()
        );

        StraightFlush straightFlush = new StraightFlush(cards);

        assertEquals("Straight Flush (K High)", straightFlush.toString());
    }

    @Test
    public void toString_withFiveHighStraightFlush() {
        List<Card> cards = Arrays.asList(
                new Card.Builder()
                        .rank(FIVE)
                        .suit(CLUBS)
                        .build(),
                new Card.Builder()
                        .rank(FOUR)
                        .suit(CLUBS)
                        .build(),
                new Card.Builder()
                        .rank(THREE)
                        .suit(CLUBS)
                        .build(),
                new Card.Builder()
                        .rank(TWO)
                        .suit(CLUBS)
                        .build(),
                new Card.Builder()
                        .rank(ACE)
                        .suit(CLUBS)
                        .build()
        );

        StraightFlush straightFlush = new StraightFlush(cards);

        assertEquals("Straight Flush (5 High)", straightFlush.toString());
    }

    @Test
    public void givenTwoStraightFlush_thenCompareTo_shouldReturnPlayerOne() {
        List<Card> cards = Arrays.asList(
                new Card.Builder()
                        .rank(TEN)
                        .suit(CLUBS)
                        .build(),
                new Card.Builder()
                        .rank(NINE)
                        .suit(CLUBS)
                        .build(),
                new Card.Builder()
                        .rank(EIGHT)
                        .suit(CLUBS)
                        .build(),
                new Card.Builder()
                        .rank(SEVEN)
                        .suit(CLUBS)
                        .build(),
                new Card.Builder()
                        .rank(SIX)
                        .suit(CLUBS)
                        .build()
        );

        StraightFlush straightFlush = new StraightFlush(cards);

        List<Card> otherCards = Arrays.asList(
                new Card.Builder()
                        .rank(EIGHT)
                        .suit(HEARTS)
                        .build(),
                new Card.Builder()
                        .rank(SEVEN)
                        .suit(HEARTS)
                        .build(),
                new Card.Builder()
                        .rank(SIX)
                        .suit(HEARTS)
                        .build(),
                new Card.Builder()
                        .rank(FIVE)
                        .suit(HEARTS)
                        .build(),
                new Card.Builder()
                        .rank(FOUR)
                        .suit(HEARTS)
                        .build()
        );

        StraightFlush otherStraightFlush = new StraightFlush(otherCards);

        assertThat(straightFlush)
                .isGreaterThan(otherStraightFlush);
    }

    @Test
    public void givenTwoEqualStraightFlush_thenCompareTo_shouldReturnEqual() {
        List<Card> cards = Arrays.asList(
                new Card.Builder()
                        .rank(SEVEN)
                        .suit(DIAMONDS)
                        .build(),
                new Card.Builder()
                        .rank(SIX)
                        .suit(DIAMONDS)
                        .build(),
                new Card.Builder()
                        .rank(FIVE)
                        .suit(DIAMONDS)
                        .build(),
                new Card.Builder()
                        .rank(FOUR)
                        .suit(DIAMONDS)
                        .build(),
                new Card.Builder()
                        .rank(THREE)
                        .suit(DIAMONDS)
                        .build()
        );

        StraightFlush straightFlush = new StraightFlush(cards);

        List<Card> otherCards = Arrays.asList(
                new Card.Builder()
                        .rank(SEVEN)
                        .suit(SPADES)
                        .build(),
                new Card.Builder()
                        .rank(SIX)
                        .suit(SPADES)
                        .build(),
                new Card.Builder()
                        .rank(FIVE)
                        .suit(SPADES)
                        .build(),
                new Card.Builder()
                        .rank(FOUR)
                        .suit(SPADES)
                        .build(),
                new Card.Builder()
                        .rank(THREE)
                        .suit(SPADES)
                        .build()
        );

        StraightFlush otherStraightFlush = new StraightFlush(otherCards);

        assertThat(straightFlush)
                .isEqualByComparingTo(otherStraightFlush);
    }
}