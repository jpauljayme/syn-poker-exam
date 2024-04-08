package com.synacy.poker.hand;

import com.synacy.poker.model.card.Card;
import com.synacy.poker.model.card.CardRank;
import com.synacy.poker.model.card.CardSuit;
import com.synacy.poker.model.hand.types.RoyalFlush;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.assertj.core.api.Assertions.assertThat;


public class RoyalFlushTest {
    @Test
    public void toString_withRoyalFlush() {
        List<Card> cards = Arrays.asList(
                new Card.Builder()
                        .rank(CardRank.ACE)
                        .suit(CardSuit.CLUBS)
                        .build(),
                new Card.Builder()
                        .rank(CardRank.KING)
                        .suit(CardSuit.CLUBS)
                        .build(),
                new Card.Builder()
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
                        .build()
        );

        RoyalFlush royalFlush = new RoyalFlush(cards);

        assertEquals("Royal Flush", royalFlush.toString());
    }

    @Test
    public void givenTwoRoyalFlush_thenCompareTo_shouldReturnEqual() {
        List<Card> cards = Arrays.asList(
                new Card.Builder()
                        .rank(CardRank.ACE)
                        .suit(CardSuit.CLUBS)
                        .build(),
                new Card.Builder()
                        .rank(CardRank.KING)
                        .suit(CardSuit.CLUBS)
                        .build(),
                new Card.Builder()
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
                        .build()
        );

        RoyalFlush royalFlush = new RoyalFlush(cards);

        List<Card> otherCards = Arrays.asList(
                new Card.Builder()
                        .rank(CardRank.ACE)
                        .suit(CardSuit.HEARTS)
                        .build(),
                new Card.Builder()
                        .rank(CardRank.KING)
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
                        .build()
        );

        RoyalFlush otherRoyalFlush = new RoyalFlush(otherCards);

       assertThat(royalFlush)
               .isEqualByComparingTo(otherRoyalFlush);
    }
}
