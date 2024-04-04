package com.synacy.poker.hand.types;

import com.synacy.poker.hand.Hand;
import com.synacy.poker.hand.HandType;

public class RoyalFlush extends Hand {
    /**
     * @return
     */
    @Override
    public HandType getHandType() {
        return HandType.ROYAL_FLUSH;
    }
}
