package com.synacy.poker.hand;

import com.synacy.poker.model.card.Card;
import com.synacy.poker.model.card.CardRank;
import com.synacy.poker.model.card.CardSuit;
import com.synacy.poker.model.hand.types.FourOfAKind;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class FourOfAKindTest {

    @Test
    public void toString_withQuadFoursAndAceKicker() {
        List<Card> quads = Arrays.asList(
//                new Card(CardRank.FOUR, CardSuit.CLUBS),
//                new Card(CardRank.FOUR, CardSuit.DIAMONDS),
//                new Card(CardRank.FOUR, CardSuit.SPADES),
//                new Card(CardRank.FOUR, CardSuit.HEARTS)
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
                        .build(),
                new Card.Builder()
                        .rank(CardRank.FOUR)
                        .suit(CardSuit.HEARTS)
                        .build()
        );
        List<Card> kicker = Collections.singletonList(
//                new Card(CardRank.ACE, CardSuit.CLUBS)
                new Card.Builder()
                        .rank(CardRank.ACE)
                        .suit(CardSuit.CLUBS)
                        .build()
        );

        FourOfAKind fourOfAKind = new FourOfAKind(quads, kicker);

        assertEquals("Quads (4) - A High", fourOfAKind.toString());
    }

}