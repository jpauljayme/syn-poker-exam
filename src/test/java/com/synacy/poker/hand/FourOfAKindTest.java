package com.synacy.poker.hand;

import com.synacy.poker.model.card.Card;
import com.synacy.poker.model.card.CardRank;
import com.synacy.poker.model.card.CardSuit;
import com.synacy.poker.model.hand.types.FourOfAKind;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static com.synacy.poker.model.card.CardRank.*;
import static com.synacy.poker.model.card.CardSuit.*;
import static org.junit.Assert.assertEquals;
import static org.assertj.core.api.Assertions.assertThat;

public class FourOfAKindTest {

    @Test
    public void toString_withQuadFoursAndAceKicker() {
        List<Card> quads = Arrays.asList(
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
                        .build(),
                new Card.Builder()
                        .rank(FOUR)
                        .suit(HEARTS)
                        .build()
        );
        List<Card> kicker = Collections.singletonList(
                new Card.Builder()
                        .rank(ACE)
                        .suit(CLUBS)
                        .build()
        );

        FourOfAKind fourOfAKind = new FourOfAKind(quads, kicker);

        assertEquals("Quads (4) - A High", fourOfAKind.toString());
    }

    @Test
    public void givenTwoEqualFourOfAKind_thenCompareTo_shouldReturnEqual() {
        List<Card> quads = Arrays.asList(
                new Card.Builder()
                        .rank(FOUR)
                        .suit(CLUBS)
                        .build(),
                new Card.Builder()
                        .rank(FOUR)
                        .suit(SPADES)
                        .build(),
                new Card.Builder()
                        .rank(FOUR)
                        .suit(DIAMONDS)
                        .build(),
                new Card.Builder()
                        .rank(FOUR)
                        .suit(HEARTS)
                        .build()
        );
        Card kicker =
                new Card.Builder()
                        .rank(NINE)
                        .suit(CLUBS)
                        .build();

        FourOfAKind fourOfAKind = new FourOfAKind(quads, Collections.singletonList(kicker));

        List<Card> otherQuads = Arrays.asList(
                new Card.Builder()
                        .rank(FOUR)
                        .suit(CLUBS)
                        .build(),
                new Card.Builder()
                        .rank(FOUR)
                        .suit(SPADES)
                        .build(),
                new Card.Builder()
                        .rank(FOUR)
                        .suit(DIAMONDS)
                        .build(),
                new Card.Builder()
                        .rank(FOUR)
                        .suit(HEARTS)
                        .build()
        );
        Card otherKicker =
                new Card.Builder()
                        .rank(NINE)
                        .suit(DIAMONDS)
                        .build();

        FourOfAKind otherFourOfAKind = new FourOfAKind(otherQuads, Collections.singletonList(otherKicker));

        assertThat(fourOfAKind)
                .isEqualByComparingTo(otherFourOfAKind);
    }

    @Test
    public void givenTwoFourOfAKind_thenCompareTo_shouldReturnPlayerOne() {
        List<Card> quads = Arrays.asList(
                new Card.Builder()
                        .rank(KING)
                        .suit(SPADES)
                        .build(),
                new Card.Builder()
                        .rank(KING)
                        .suit(HEARTS)
                        .build(),
                new Card.Builder()
                        .rank(KING)
                        .suit(CLUBS)
                        .build(),
                new Card.Builder()
                        .rank(KING)
                        .suit(DIAMONDS)
                        .build()
        );
        Card kicker =
                new Card.Builder()
                        .rank(THREE)
                        .suit(HEARTS)
                        .build();

        FourOfAKind fourOfAKind = new FourOfAKind(quads, Collections.singletonList(kicker));

        List<Card> otherQuads = Arrays.asList(
                new Card.Builder()
                        .rank(SEVEN)
                        .suit(HEARTS)
                        .build(),
                new Card.Builder()
                        .rank(SEVEN)
                        .suit(DIAMONDS)
                        .build(),
                new Card.Builder()
                        .rank(SEVEN)
                        .suit(SPADES)
                        .build(),
                new Card.Builder()
                        .rank(SEVEN)
                        .suit(CLUBS)
                        .build()
        );
        Card otherKicker =
                new Card.Builder()
                        .rank(QUEEN)
                        .suit(HEARTS)
                        .build();

        FourOfAKind otherFourOfAKind = new FourOfAKind(otherQuads, Collections.singletonList(otherKicker));

        assertThat(fourOfAKind)
                .isGreaterThan(otherFourOfAKind);
    }

    @Test
    public void givenTwoEqualExceptKickerFourOfAKind_thenCompareTo_shouldReturnPlayerOne() {
        List<Card> quads = Arrays.asList(
                new Card.Builder()
                        .rank(SEVEN)
                        .suit(HEARTS)
                        .build(),
                new Card.Builder()
                        .rank(SEVEN)
                        .suit(DIAMONDS)
                        .build(),
                new Card.Builder()
                        .rank(SEVEN)
                        .suit(SPADES)
                        .build(),
                new Card.Builder()
                        .rank(SEVEN)
                        .suit(CLUBS)
                        .build()
        );
        Card kicker =
                new Card.Builder()
                        .rank(QUEEN)
                        .suit(HEARTS)
                        .build();

        FourOfAKind fourOfAKind = new FourOfAKind(quads, Collections.singletonList(kicker));

        List<Card> otherQuads = Arrays.asList(
                new Card.Builder()
                        .rank(SEVEN)
                        .suit(HEARTS)
                        .build(),
                new Card.Builder()
                        .rank(SEVEN)
                        .suit(DIAMONDS)
                        .build(),
                new Card.Builder()
                        .rank(SEVEN)
                        .suit(SPADES)
                        .build(),
                new Card.Builder()
                        .rank(SEVEN)
                        .suit(CLUBS)
                        .build()
        );
        Card otherKicker =
                new Card.Builder()
                        .rank(TEN)
                        .suit(CLUBS)
                        .build();

        FourOfAKind otherFourOfAKind = new FourOfAKind(otherQuads, Collections.singletonList(otherKicker));

        assertThat(fourOfAKind)
                .isGreaterThan(otherFourOfAKind);
    }
}