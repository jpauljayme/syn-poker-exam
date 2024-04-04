package com.synacy.poker.hand;

import com.synacy.poker.card.Card;
import com.synacy.poker.card.CardRank;
import com.synacy.poker.card.CardSuit;
import com.synacy.poker.hand.types.TwoPair;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class TwoPairTest {

    @Test
    public void toString_withTwoPairsAndAceKicker() {
        List<Card> firstPair = Arrays.asList(
//                new Card(CardRank.FOUR, CardSuit.CLUBS),
//                new Card(CardRank.FOUR, CardSuit.DIAMONDS)
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
//                new Card(CardRank.THREE, CardSuit.CLUBS),
//                new Card(CardRank.THREE, CardSuit.DIAMONDS)
                new Card.Builder()
                        .rank(CardRank.THREE)
                        .suit(CardSuit.CLUBS)
                        .build(),
                new Card.Builder()
                        .rank(CardRank.TEN)
                        .suit(CardSuit.DIAMONDS)
                        .build()
        );
        List<Card> kicker = Arrays.asList(
//                new Card(CardRank.ACE, CardSuit.CLUBS)
                new Card.Builder()
                        .rank(CardRank.ACE)
                        .suit(CardSuit.CLUBS)
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

}