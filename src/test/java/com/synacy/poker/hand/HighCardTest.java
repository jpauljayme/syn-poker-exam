package com.synacy.poker.hand;

import com.synacy.poker.model.card.Card;
import com.synacy.poker.model.hand.types.HighCard;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static com.synacy.poker.model.card.CardRank.*;
import static com.synacy.poker.model.card.CardSuit.*;
import static org.junit.Assert.assertEquals;
import static org.assertj.core.api.Assertions.assertThat;

public class HighCardTest {

    @Test
    public void toString_withHighCards() {
        List<Card> cards = Arrays.asList(
                new Card.Builder()
                        .rank(ACE)
                        .suit(CLUBS)
                        .build(),
                new Card.Builder()
                        .rank(KING)
                        .suit(DIAMONDS)
                        .build(),
                new Card.Builder()
                        .rank(QUEEN)
                        .suit(SPADES)
                        .build(),
                new Card.Builder()
                        .rank(TWO)
                        .suit(CLUBS)
                        .build(),
                new Card.Builder()
                        .rank(THREE)
                        .suit(HEARTS)
                        .build()
        );

        HighCard highCard = new HighCard(cards);

        assertEquals("A,K,Q,3,2", highCard.toString());
    }

    @Test
    public void givenTwoEqualHighCards_thenCompareTo_shouldReturnEqual() {
        List<Card> cards = Arrays.asList(
                new Card.Builder()
                        .rank(KING)
                        .suit(DIAMONDS)
                        .build(),
                new Card.Builder()
                        .rank(QUEEN)
                        .suit(DIAMONDS)
                        .build(),
                new Card.Builder()
                        .rank(SEVEN)
                        .suit(SPADES)
                        .build(),
                new Card.Builder()
                        .rank(FOUR)
                        .suit(SPADES)
                        .build(),
                new Card.Builder()
                        .rank(THREE)
                        .suit(HEARTS)
                        .build()
        );

        HighCard highCard = new HighCard(cards);

        List<Card> otherCards = Arrays.asList(
                new Card.Builder()
                        .rank(KING)
                        .suit(HEARTS)
                        .build(),
                new Card.Builder()
                        .rank(QUEEN)
                        .suit(HEARTS)
                        .build(),
                new Card.Builder()
                        .rank(SEVEN)
                        .suit(CLUBS)
                        .build(),
                new Card.Builder()
                        .rank(FOUR)
                        .suit(CLUBS)
                        .build(),
                new Card.Builder()
                        .rank(THREE)
                        .suit(DIAMONDS)
                        .build()
        );

        HighCard otherHighCard = new HighCard(otherCards);

        assertThat(highCard)
                .isEqualByComparingTo(otherHighCard);
    }

    @Test
    public void givenTwoHighCards_thenCompareTo_shouldReturnPlayerOne() {
        List<Card> cards = Arrays.asList(
                new Card.Builder()
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
                        .rank(SIX)
                        .suit(SPADES)
                        .build(),
                new Card.Builder()
                        .rank(THREE)
                        .suit(HEARTS)
                        .build()
        );

        HighCard highCard = new HighCard(cards);

        List<Card> otherCards = Arrays.asList(
                new Card.Builder()
                        .rank(KING)
                        .suit(HEARTS)
                        .build(),
                new Card.Builder()
                        .rank(QUEEN)
                        .suit(HEARTS)
                        .build(),
                new Card.Builder()
                        .rank(SEVEN)
                        .suit(CLUBS)
                        .build(),
                new Card.Builder()
                        .rank(FOUR)
                        .suit(CLUBS)
                        .build(),
                new Card.Builder()
                        .rank(THREE)
                        .suit(DIAMONDS)
                        .build()
        );

        HighCard otherHighCard = new HighCard(otherCards);

        assertThat(highCard)
                .isGreaterThan(otherHighCard);
    }

}