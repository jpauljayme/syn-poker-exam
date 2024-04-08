package com.synacy.poker.model.hand.types;

import com.synacy.poker.model.card.Card;
import com.synacy.poker.model.hand.Hand;
import com.synacy.poker.model.hand.HandType;

import java.util.List;
import java.util.Objects;

/**
 * @see <a href="https://en.wikipedia.org/wiki/List_of_poker_hands#One_pair">What is a One Pair?</a>
 */
public class OnePair implements Hand {

    private final List<Card> pairCards;
    private final List<Card> otherCards;

    public OnePair(List<Card> pairCards, List<Card> otherCards) {
        this.pairCards = pairCards;
        this.otherCards = otherCards;
    }

    public HandType getHandType() {
        return HandType.ONE_PAIR;
    }

    /**
     * @return The name of the hand plus kickers ordered by descending rank, e.g. One Pair (2) - A,K,Q High,
     * or the name of the hand and rank if there are no community cards yet in play, e.g. One Pair (2)
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("One Pair (2)");

        if (!otherCards.isEmpty()) {
            builder.append(" - ");
            for (int i = 0; i < otherCards.size(); i++) {
                Card c = otherCards.get(i);
                if (i != 0) {
                    builder.append(",").append(c.getRank());
                } else {
                    builder.append(c.getRank());
                }
            }
            builder.append(" High");
        }

        return builder.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OnePair onePair = (OnePair) o;
        return Objects.equals(pairCards, onePair.pairCards) && Objects.equals(otherCards, onePair.otherCards);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pairCards, otherCards);
    }

    /**
     * @param o the object to be compared.
     * @return integer value of equality, 0 if two hands are equal,
     * less than 1 if this hand is lesser than o,
     * greater than 1 if this hand is greater than o
     */
    @Override
    public int compareTo(Hand o) {
        if (o instanceof OnePair) {
            OnePair otherHand = (OnePair) o;
            int comparePair = this.pairCards.get(0).getRank().compareTo(otherHand.pairCards.get(0).getRank());
            if (comparePair != 0) {
                return comparePair;
            } else {
                //Same pair rank, then let's compare kickers descending order
                int kickerOne = this.otherCards.get(0).getRank().
                        compareTo(otherHand.otherCards.get(0).getRank());
                if (kickerOne != 0) {
                    return kickerOne;
                } else {
                    int kickerTwo = this.otherCards.get(1).getRank().
                            compareTo(otherHand.otherCards.get(1).getRank());
                    if (kickerTwo != 0) {
                        return kickerTwo;
                    } else {
                        return this.otherCards.get(2).getRank().
                                compareTo(otherHand.otherCards.get(2).getRank());
                    }
                }
            }
        } else {
            return this.getHandType().compareTo(o.getHandType());
        }
    }
}
