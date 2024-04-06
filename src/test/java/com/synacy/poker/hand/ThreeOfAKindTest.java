package com.synacy.poker.hand;

import com.synacy.poker.model.card.Card;
import com.synacy.poker.model.card.CardRank;
import com.synacy.poker.model.card.CardSuit;
import com.synacy.poker.model.hand.types.ThreeOfAKind;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.assertj.core.api.Assertions.assertThat;
public class ThreeOfAKindTest {

    @Test
    public void toString_withTripFoursAndKickers() {
        List<Card> trips = Arrays.asList(
                new Card.Builder()
                        .rank(CardRank.FOUR)
                        .suit(CardSuit.CLUBS)
                        .build(),
                new Card.Builder()
                        .rank(CardRank.FOUR)
                        .suit(CardSuit.DIAMONDS)
                        .build(),
                new Card.Builder()
                        .rank(CardRank.FOUR)
                        .suit(CardSuit.SPADES)
                        .build()
        );
        List<Card> kickers = Arrays.asList(
                new Card.Builder()
                        .rank(CardRank.ACE)
                        .suit(CardSuit.CLUBS)
                        .build(),
                new Card.Builder()
                        .rank(CardRank.TWO)
                        .suit(CardSuit.CLUBS)
                        .build()
        );

        ThreeOfAKind threeOfAKind = new ThreeOfAKind(trips, kickers);

        assertEquals("Trips (4) - A,2 High", threeOfAKind.toString());
    }

    @Test
    public void givenTwoEqualTripleHand_thenCompareTo_shouldReturnEqual() {
        List<Card> trips = Arrays.asList(
                new Card.Builder()
                        .rank(CardRank.FOUR)
                        .suit(CardSuit.CLUBS)
                        .build(),
                new Card.Builder()
                        .rank(CardRank.FOUR)
                        .suit(CardSuit.DIAMONDS)
                        .build(),
                new Card.Builder()
                        .rank(CardRank.FOUR)
                        .suit(CardSuit.SPADES)
                        .build()
        );
        List<Card> kickers = Arrays.asList(
                new Card.Builder()
                        .rank(CardRank.ACE)
                        .suit(CardSuit.CLUBS)
                        .build(),
                new Card.Builder()
                        .rank(CardRank.TWO)
                        .suit(CardSuit.CLUBS)
                        .build()
        );

        ThreeOfAKind threeOfAKindFirst = new ThreeOfAKind(trips, kickers);
        ThreeOfAKind threeOfAKindSecond = new ThreeOfAKind(trips, kickers);

        assertThat(threeOfAKindFirst)
                .isEqualByComparingTo(threeOfAKindSecond);
    }

    @Test
    public void givenTwoTripleHand_thenCompareTo_shouldReturnPlayerTwo() {
        List<Card> trips = Arrays.asList(
                new Card.Builder()
                        .rank(CardRank.THREE)
                        .suit(CardSuit.DIAMONDS)
                        .build(),
                new Card.Builder()
                        .rank(CardRank.THREE)
                        .suit(CardSuit.SPADES)
                        .build(),
                new Card.Builder()
                        .rank(CardRank.THREE)
                        .suit(CardSuit.CLUBS)
                        .build()
        );
        List<Card> kickers = Arrays.asList(
                new Card.Builder()
                        .rank(CardRank.KING)
                        .suit(CardSuit.SPADES)
                        .build(),
                new Card.Builder()
                        .rank(CardRank.TWO)
                        .suit(CardSuit.SPADES)
                        .build()
        );
        ThreeOfAKind threeOfAKindFirst = new ThreeOfAKind(trips, kickers);

        List<Card> otherTrips = Arrays.asList(
                new Card.Builder()
                        .rank(CardRank.SIX)
                        .suit(CardSuit.HEARTS)
                        .build(),
                new Card.Builder()
                        .rank(CardRank.SIX)
                        .suit(CardSuit.DIAMONDS)
                        .build(),
                new Card.Builder()
                        .rank(CardRank.SIX)
                        .suit(CardSuit.SPADES)
                        .build()
        );
        List<Card> otherKickers = Arrays.asList(
                new Card.Builder()
                        .rank(CardRank.QUEEN)
                        .suit(CardSuit.CLUBS)
                        .build(),
                new Card.Builder()
                        .rank(CardRank.FOUR)
                        .suit(CardSuit.SPADES)
                        .build()
        );
        ThreeOfAKind threeOfAKindSecond = new ThreeOfAKind(otherTrips, otherKickers);

        assertThat(threeOfAKindSecond)
                .isGreaterThan(threeOfAKindFirst);
    }

    @Test
    public void givenTwoTripleHand_thenCompareTo_shouldReturnPlayerOne() {
        List<Card> trips = Arrays.asList(
                new Card.Builder()
                        .rank(CardRank.JACK)
                        .suit(CardSuit.DIAMONDS)
                        .build(),
                new Card.Builder()
                        .rank(CardRank.JACK)
                        .suit(CardSuit.SPADES)
                        .build(),
                new Card.Builder()
                        .rank(CardRank.JACK)
                        .suit(CardSuit.CLUBS)
                        .build()
        );
        List<Card> kickers = Arrays.asList(
                new Card.Builder()
                        .rank(CardRank.KING)
                        .suit(CardSuit.SPADES)
                        .build(),
                new Card.Builder()
                        .rank(CardRank.TWO)
                        .suit(CardSuit.SPADES)
                        .build()
        );
        ThreeOfAKind threeOfAKindFirst = new ThreeOfAKind(trips, kickers);

        List<Card> otherTrips = Arrays.asList(
                new Card.Builder()
                        .rank(CardRank.SIX)
                        .suit(CardSuit.HEARTS)
                        .build(),
                new Card.Builder()
                        .rank(CardRank.SIX)
                        .suit(CardSuit.DIAMONDS)
                        .build(),
                new Card.Builder()
                        .rank(CardRank.SIX)
                        .suit(CardSuit.SPADES)
                        .build()
        );
        List<Card> otherKickers = Arrays.asList(
                new Card.Builder()
                        .rank(CardRank.QUEEN)
                        .suit(CardSuit.CLUBS)
                        .build(),
                new Card.Builder()
                        .rank(CardRank.FOUR)
                        .suit(CardSuit.SPADES)
                        .build()
        );
        ThreeOfAKind threeOfAKindSecond = new ThreeOfAKind(otherTrips, otherKickers);

        assertThat(threeOfAKindFirst)
                .isGreaterThan(threeOfAKindSecond);
    }
}