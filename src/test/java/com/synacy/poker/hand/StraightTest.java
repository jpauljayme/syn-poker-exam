package com.synacy.poker.hand;

import com.synacy.poker.model.card.Card;
import com.synacy.poker.model.card.CardRank;
import com.synacy.poker.model.card.CardSuit;
import com.synacy.poker.model.hand.types.Straight;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class StraightTest {

    @Test
    public void toString_withAceHighStraight() {
        List<Card> cards = Arrays.asList(
//                new Card(CardRank.ACE, CardSuit.CLUBS),
//                new Card(CardRank.KING, CardSuit.DIAMONDS),
//                new Card(CardRank.QUEEN, CardSuit.SPADES),
//                new Card(CardRank.JACK, CardSuit.CLUBS),
//                new Card(CardRank.TEN, CardSuit.CLUBS)
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
//                new Card(CardRank.KING, CardSuit.CLUBS),
//                new Card(CardRank.QUEEN, CardSuit.DIAMONDS),
//                new Card(CardRank.JACK, CardSuit.SPADES),
//                new Card(CardRank.TEN, CardSuit.CLUBS),
//                new Card(CardRank.NINE, CardSuit.CLUBS)
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
//                new Card(CardRank.FIVE, CardSuit.CLUBS),
//                new Card(CardRank.FOUR, CardSuit.DIAMONDS),
//                new Card(CardRank.THREE, CardSuit.SPADES),
//                new Card(CardRank.TWO, CardSuit.CLUBS),
//                new Card(CardRank.ACE, CardSuit.CLUBS)
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
}