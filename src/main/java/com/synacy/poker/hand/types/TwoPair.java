package com.synacy.poker.hand.types;

import com.synacy.poker.card.Card;
import com.synacy.poker.hand.Hand;
import com.synacy.poker.hand.HandType;

import java.util.List;

/**
 * @see <a href="https://en.wikipedia.org/wiki/List_of_poker_hands#Two_pair">What is a Two Pair?</a>
 */
public class TwoPair extends Hand {

    private final List<Card> firstPairCards;
    private final List<Card> secondPairCards;
    private final Card otherCard;

    public TwoPair(List<Card> firstPairCards, List<Card> secondPairCards, Card otherCard) {
        this.firstPairCards = firstPairCards;
        this.secondPairCards = secondPairCards;
        this.otherCard = otherCard;
    }

    public HandType getHandType() {
        return HandType.TWO_PAIR;
    }

    /**
     * @return The name of the hand with kicker ranked in descending order, e.g. Two Pair (4,3) - A High
     */
    @Override
    public String toString() {


        return String.format("Two Pair (%s,%s) - %s High",
                firstPairCards.get(0).getRank(),
                secondPairCards.get(0).getRank(),
                otherCard.getRank());
    }

}
