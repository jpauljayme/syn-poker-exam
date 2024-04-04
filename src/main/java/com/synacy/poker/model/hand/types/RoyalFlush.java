package com.synacy.poker.model.hand.types;

import com.synacy.poker.model.card.Card;
import com.synacy.poker.model.hand.Hand;
import com.synacy.poker.model.hand.HandType;

import java.util.List;

public class RoyalFlush implements Hand {
    final List<Card> cards;

    public RoyalFlush(List<Card> cards) {
        this.cards = cards;
    }

    /**
     * @return
     */
    @Override
    public HandType getHandType() {
        return HandType.ROYAL_FLUSH;
    }

    @Override
    public String toString() {
        return "Royal Flush";
    }

    /**
     * @param o the object to be compared.
     * @return
     */
    @Override
    public int compareTo(Hand o) {
        return 0;
    }
}
