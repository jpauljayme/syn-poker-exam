package com.synacy.poker.hand;

import com.synacy.poker.model.card.Card;
import com.synacy.poker.model.hand.types.FullHouse;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static com.synacy.poker.model.card.CardRank.*;
import static com.synacy.poker.model.card.CardSuit.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;


public class FullHouseTest {

    @Test
    public void toString_withFullHouse() {
        List<Card> trips = Arrays.asList(
                new Card.Builder()
                        .rank(FOUR)
                        .suit(CLUBS)
                        .build(),
                new Card.Builder()
                        .rank(FOUR)
                        .suit(DIAMONDS)
                        .build(),
                new Card.Builder()
                        .rank(FOUR)
                        .suit(SPADES)
                        .build()
        );
        List<Card> pair = Arrays.asList(
                new Card.Builder()
                        .rank(ACE)
                        .suit(CLUBS)
                        .build(),
                new Card.Builder()
                        .rank(ACE)
                        .suit(HEARTS)
                        .build()
        );

        FullHouse fullHouse = new FullHouse(trips, pair);

        assertEquals("Full House (4,A)", fullHouse.toString());
    }

    @Test
    public void givenTwoEqualFullHouse_thenCompareTo_shouldReturnEqual() {
        List<Card> trips = Arrays.asList(
                new Card.Builder()
                        .rank(KING)
                        .suit(CLUBS)
                        .build(),
                new Card.Builder()
                        .rank(KING)
                        .suit(HEARTS)
                        .build(),
                new Card.Builder()
                        .rank(KING)
                        .suit(DIAMONDS)
                        .build()
        );
        List<Card> pair = Arrays.asList(
                new Card.Builder()
                        .rank(JACK)
                        .suit(CLUBS)
                        .build(),
                new Card.Builder()
                        .rank(JACK)
                        .suit(HEARTS)
                        .build()
        );

        FullHouse fullHouse = new FullHouse(trips, pair);

        List<Card> otherTrips = Arrays.asList(
                new Card.Builder()
                        .rank(KING)
                        .suit(CLUBS)
                        .build(),
                new Card.Builder()
                        .rank(KING)
                        .suit(SPADES)
                        .build(),
                new Card.Builder()
                        .rank(KING)
                        .suit(DIAMONDS)
                        .build()
        );
        List<Card> otherPair = Arrays.asList(
                new Card.Builder()
                        .rank(JACK)
                        .suit(CLUBS)
                        .build(),
                new Card.Builder()
                        .rank(JACK)
                        .suit(SPADES)
                        .build()
        );

        FullHouse otherFullHouse = new FullHouse(otherTrips, otherPair);

        assertThat(fullHouse)
                .isEqualByComparingTo(otherFullHouse);
    }

    @Test
    public void givenTwoFullHouse_thenCompareTo_shouldReturnPlayerOne() {
        List<Card> trips = Arrays.asList(
                new Card.Builder()
                        .rank(EIGHT)
                        .suit(SPADES)
                        .build(),
                new Card.Builder()
                        .rank(EIGHT)
                        .suit(DIAMONDS)
                        .build(),
                new Card.Builder()
                        .rank(EIGHT)
                        .suit(HEARTS)
                        .build()
        );
        List<Card> pair = Arrays.asList(
                new Card.Builder()
                        .rank(SEVEN)
                        .suit(DIAMONDS)
                        .build(),
                new Card.Builder()
                        .rank(SEVEN)
                        .suit(CLUBS)
                        .build()
        );

        FullHouse fullHouse = new FullHouse(trips, pair);

        List<Card> otherTrips = Arrays.asList(
                new Card.Builder()
                        .rank(FOUR)
                        .suit(DIAMONDS)
                        .build(),
                new Card.Builder()
                        .rank(FOUR)
                        .suit(SPADES)
                        .build(),
                new Card.Builder()
                        .rank(FOUR)
                        .suit(CLUBS)
                        .build()
        );
        List<Card> otherPair = Arrays.asList(
                new Card.Builder()
                        .rank(NINE)
                        .suit(DIAMONDS)
                        .build(),
                new Card.Builder()
                        .rank(NINE)
                        .suit(CLUBS)
                        .build()
        );

        FullHouse otherFullHouse = new FullHouse(otherTrips, otherPair);

        assertThat(fullHouse)
                .isGreaterThan(otherFullHouse);
    }
}