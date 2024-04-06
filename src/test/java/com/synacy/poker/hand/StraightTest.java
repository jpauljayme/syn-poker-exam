package com.synacy.poker.hand;

import com.synacy.poker.model.card.Card;
import com.synacy.poker.model.card.CardRank;
import com.synacy.poker.model.card.CardSuit;
import com.synacy.poker.model.hand.types.Straight;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.assertj.core.api.Assertions.assertThat;
public class StraightTest {

    @Test
    public void toString_withAceHighStraight() {
        List<Card> cards = Arrays.asList(
                new Card.Builder()
                        .rank(CardRank.ACE)
                        .suit(CardSuit.CLUBS)
                        .build(),
                new Card.Builder()
                        .rank(CardRank.KING)
                        .suit(CardSuit.DIAMONDS)
                        .build(),
                new Card.Builder()
                        .rank(CardRank.QUEEN)
                        .suit(CardSuit.SPADES)
                        .build(),
                new Card.Builder()
                        .rank(CardRank.JACK)
                        .suit(CardSuit.CLUBS)
                        .build(),
                new Card.Builder()
                        .rank(CardRank.TEN)
                        .suit(CardSuit.CLUBS)
                        .build()
        );

        Straight straight = new Straight(cards);

        assertEquals("Straight (A High)", straight.toString());
    }

    @Test
    public void toString_withKingHighStraight() {
        List<Card> cards = Arrays.asList(
                new Card.Builder()
                        .rank(CardRank.KING)
                        .suit(CardSuit.CLUBS)
                        .build(),
                new Card.Builder()
                        .rank(CardRank.QUEEN)
                        .suit(CardSuit.DIAMONDS)
                        .build(),
                new Card.Builder()
                        .rank(CardRank.JACK)
                        .suit(CardSuit.SPADES)
                        .build(),
                new Card.Builder()
                        .rank(CardRank.TEN)
                        .suit(CardSuit.CLUBS)
                        .build(),
                new Card.Builder()
                        .rank(CardRank.NINE)
                        .suit(CardSuit.CLUBS)
                        .build()
        );

        Straight straight = new Straight(cards);

        assertEquals("Straight (K High)", straight.toString());
    }

    @Test
    public void toString_withFiveHighStraight() {
        List<Card> cards = Arrays.asList(
                new Card.Builder()
                        .rank(CardRank.FIVE)
                        .suit(CardSuit.CLUBS)
                        .build(),
                new Card.Builder()
                        .rank(CardRank.FOUR)
                        .suit(CardSuit.DIAMONDS)
                        .build(),
                new Card.Builder()
                        .rank(CardRank.THREE)
                        .suit(CardSuit.SPADES)
                        .build(),
                new Card.Builder()
                        .rank(CardRank.TWO)
                        .suit(CardSuit.CLUBS)
                        .build(),
                new Card.Builder()
                        .rank(CardRank.ACE)
                        .suit(CardSuit.CLUBS)
                        .build()
        );

        Straight straight = new Straight(cards);

        assertEquals("Straight (5 High)", straight.toString());
    }

    @Test
    public void givenTwoEqualFiveHighStraight_thenCompareTo_shouldReturnEqual() {
        List<Card> cards = Arrays.asList(
                new Card.Builder()
                        .rank(CardRank.NINE)
                        .suit(CardSuit.CLUBS)
                        .build(),
                new Card.Builder()
                        .rank(CardRank.EIGHT)
                        .suit(CardSuit.CLUBS)
                        .build(),
                new Card.Builder()
                        .rank(CardRank.SEVEN)
                        .suit(CardSuit.CLUBS)
                        .build(),
                new Card.Builder()
                        .rank(CardRank.SIX)
                        .suit(CardSuit.DIAMONDS)
                        .build(),
                new Card.Builder()
                        .rank(CardRank.FIVE)
                        .suit(CardSuit.DIAMONDS)
                        .build()
        );

        List<Card> otherCards = Arrays.asList(
                new Card.Builder()
                        .rank(CardRank.NINE)
                        .suit(CardSuit.SPADES)
                        .build(),
                new Card.Builder()
                        .rank(CardRank.EIGHT)
                        .suit(CardSuit.SPADES)
                        .build(),
                new Card.Builder()
                        .rank(CardRank.SIX)
                        .suit(CardSuit.SPADES)
                        .build(),
                new Card.Builder()
                        .rank(CardRank.SIX)
                        .suit(CardSuit.HEARTS)
                        .build(),
                new Card.Builder()
                        .rank(CardRank.FIVE)
                        .suit(CardSuit.HEARTS)
                        .build()
        );

        Straight straight = new Straight(cards);
        Straight otherStraight = new Straight(otherCards);

        assertThat(straight)
                .isEqualByComparingTo(otherStraight);
    }

    @Test
    public void givenTwoFiveHighStraight_thenCompareTo_shouldReturnPlayerTwo() {
        List<Card> cards = Arrays.asList(
                new Card.Builder()
                        .rank(CardRank.NINE)
                        .suit(CardSuit.CLUBS)
                        .build(),
                new Card.Builder()
                        .rank(CardRank.EIGHT)
                        .suit(CardSuit.CLUBS)
                        .build(),
                new Card.Builder()
                        .rank(CardRank.SEVEN)
                        .suit(CardSuit.CLUBS)
                        .build(),
                new Card.Builder()
                        .rank(CardRank.SIX)
                        .suit(CardSuit.DIAMONDS)
                        .build(),
                new Card.Builder()
                        .rank(CardRank.FIVE)
                        .suit(CardSuit.DIAMONDS)
                        .build()
        );

        List<Card> otherCards = Arrays.asList(
                new Card.Builder()
                        .rank(CardRank.ACE)
                        .suit(CardSuit.SPADES)
                        .build(),
                new Card.Builder()
                        .rank(CardRank.KING)
                        .suit(CardSuit.SPADES)
                        .build(),
                new Card.Builder()
                        .rank(CardRank.QUEEN)
                        .suit(CardSuit.SPADES)
                        .build(),
                new Card.Builder()
                        .rank(CardRank.JACK)
                        .suit(CardSuit.HEARTS)
                        .build(),
                new Card.Builder()
                        .rank(CardRank.TEN)
                        .suit(CardSuit.HEARTS)
                        .build()
        );

        Straight straight = new Straight(cards);
        Straight otherStraight = new Straight(otherCards);

        assertThat(straight).isLessThan(otherStraight);
    }
}