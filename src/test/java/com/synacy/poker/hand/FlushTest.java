package com.synacy.poker.hand;

import com.synacy.poker.model.card.Card;
import com.synacy.poker.model.card.CardRank;
import com.synacy.poker.model.card.CardSuit;
import com.synacy.poker.model.hand.types.Flush;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static com.synacy.poker.model.card.CardRank.*;
import static org.junit.Assert.assertEquals;
import static org.assertj.core.api.Assertions.assertThat;

public class FlushTest {

    @Test
    public void toString_withAceHighFlush() {
        List<Card> cards = Arrays.asList(
                new Card.Builder()
                        .rank(ACE)
                        .suit(CardSuit.CLUBS)
                        .build(),
                new Card.Builder()
                        .rank(KING)
                        .suit(CardSuit.CLUBS)
                        .build(),
                new Card.Builder()
                        .rank(QUEEN)
                        .suit(CardSuit.CLUBS)
                        .build(),
                new Card.Builder()
                        .rank(SEVEN)
                        .suit(CardSuit.CLUBS)
                        .build(),
                new Card.Builder()
                        .rank(TWO)
                        .suit(CardSuit.CLUBS)
                        .build()
        );

        Flush flush = new Flush(cards);

        assertEquals("Flush (A High)", flush.toString());
    }

    @Test
    public void toString_withKingHighFlush() {
        List<Card> cards = Arrays.asList(
                new Card.Builder()
                        .rank(KING)
                        .suit(CardSuit.CLUBS)
                        .build(),
                new Card.Builder()
                        .rank(SEVEN)
                        .suit(CardSuit.CLUBS)
                        .build(),
                new Card.Builder()
                        .rank(SIX)
                        .suit(CardSuit.CLUBS)
                        .build(),
                new Card.Builder()
                        .rank(SEVEN)
                        .suit(CardSuit.CLUBS)
                        .build(),
                new Card.Builder()
                        .rank(TEN)
                        .suit(CardSuit.CLUBS)
                        .build(),
                new Card.Builder()
                        .rank(NINE)
                        .suit(CardSuit.CLUBS)
                        .build()
                );

        Flush flush = new Flush(cards);

        assertEquals("Flush (K High)", flush.toString());
    }

    @Test
    public void giveTwoEqualFlush_thenCompareTo_shouldReturnEqual() {
        List<Card> cards = Arrays.asList(
                new Card.Builder()
                        .rank(KING)
                        .suit(CardSuit.CLUBS)
                        .build(),
                new Card.Builder()
                        .rank(SEVEN)
                        .suit(CardSuit.CLUBS)
                        .build(),
                new Card.Builder()
                        .rank(SIX)
                        .suit(CardSuit.CLUBS)
                        .build(),
                new Card.Builder()
                        .rank(SEVEN)
                        .suit(CardSuit.CLUBS)
                        .build(),
                new Card.Builder()
                        .rank(TEN)
                        .suit(CardSuit.CLUBS)
                        .build(),
                new Card.Builder()
                        .rank(NINE)
                        .suit(CardSuit.CLUBS)
                        .build()
        );

        List<Card> otherCards = Arrays.asList(
                new Card.Builder()
                        .rank(KING)
                        .suit(CardSuit.HEARTS)
                        .build(),
                new Card.Builder()
                        .rank(SEVEN)
                        .suit(CardSuit.HEARTS)
                        .build(),
                new Card.Builder()
                        .rank(SIX)
                        .suit(CardSuit.HEARTS)
                        .build(),
                new Card.Builder()
                        .rank(SEVEN)
                        .suit(CardSuit.HEARTS)
                        .build(),
                new Card.Builder()
                        .rank(TEN)
                        .suit(CardSuit.HEARTS)
                        .build(),
                new Card.Builder()
                        .rank(NINE)
                        .suit(CardSuit.HEARTS)
                        .build()
        );
        Flush flush = new Flush(cards);
        Flush otherFlush = new Flush(otherCards);

        assertThat(flush)
                .isEqualByComparingTo(otherFlush);

    }

    @Test
    public void giveTwoFlush_thenCompareTo_shouldReturnPlayerOne() {
        List<Card> cards = Arrays.asList(
                new Card.Builder()
                        .rank(KING)
                        .suit(CardSuit.DIAMONDS)
                        .build(),
                new Card.Builder()
                        .rank(JACK)
                        .suit(CardSuit.DIAMONDS)
                        .build(),
                new Card.Builder()
                        .rank(NINE)
                        .suit(CardSuit.DIAMONDS)
                        .build(),
                new Card.Builder()
                        .rank(SIX)
                        .suit(CardSuit.DIAMONDS)
                        .build(),
                new Card.Builder()
                        .rank(FOUR)
                        .suit(CardSuit.DIAMONDS)
                        .build()
        );

        List<Card> otherCards = Arrays.asList(
                new Card.Builder()
                        .rank(QUEEN)
                        .suit(CardSuit.CLUBS)
                        .build(),
                new Card.Builder()
                        .rank(JACK)
                        .suit(CardSuit.CLUBS)
                        .build(),
                new Card.Builder()
                        .rank(SEVEN)
                        .suit(CardSuit.CLUBS)
                        .build(),
                new Card.Builder()
                        .rank(SIX)
                        .suit(CardSuit.CLUBS)
                        .build(),
                new Card.Builder()
                        .rank(FIVE)
                        .suit(CardSuit.CLUBS)
                        .build()
        );
        Flush flush = new Flush(cards);
        Flush otherFlush = new Flush(otherCards);

        assertThat(flush)
                .isGreaterThan(otherFlush);

    }
}