package com.synacy.poker.model.hand.types;

import com.synacy.poker.model.card.Card;
import com.synacy.poker.model.hand.Hand;
import com.synacy.poker.model.hand.HandType;

import java.util.List;
import java.util.Objects;

/**
 * @see <a href="https://en.wikipedia.org/wiki/List_of_poker_hands#Flush">What is a flush?</a>
 */
public class Flush implements Hand {

    private final List<Card> cards;

    public Flush(List<Card> cards) {
        this.cards = cards;
    }

    public HandType getHandType() {
        return HandType.FLUSH;
    }

    /**
     * @return
     */

    public List<Card> getCards() {
        return cards;
    }

    /**
     * @return Returns the name of the hand and the highest card, e.g. Flush (K High)
     */
    @Override
    public String toString() {

        return String.format("Flush (%s High)",
                cards.get(0).getRank());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Flush flush = (Flush) o;
        return Objects.equals(cards, flush.cards);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cards);
    }

    /**
     * @param o the object to be compared.
     * @return Compares this hand with the specified other hand
     * for order. Returns a negative integer, zero, or a
     * positive integer as this object is less than, equal to,
     * or greater than the specified object.
     */
    @Override
    public int compareTo(Hand o) {
        if(o instanceof Flush){
            Flush otherHand = (Flush) o;
            int compareHighestRank = this.cards.get(0).getRank().compareTo(otherHand.cards.get(0).getRank());
            if(compareHighestRank != 0){
                return compareHighestRank;
            }else{
                int compareSecondHighestRank = this.cards.get(1).getRank().compareTo(otherHand.cards.get(1).getRank());

                if(compareSecondHighestRank != 0){
                    return compareSecondHighestRank;
                }else{
                    int compareThirdHighestRank = this.cards.get(2).getRank().compareTo(otherHand.cards.get(2).getRank());

                    if(compareThirdHighestRank != 0){
                        return  compareThirdHighestRank;
                    }else{
                        int compareFourthHighestRank = this.cards.get(3).getRank()
                                .compareTo(otherHand.cards.get(3).getRank());
                        if(compareFourthHighestRank != 0){
                            return compareFourthHighestRank;
                        }else{
                            return this.cards.get(4).getRank()
                                    .compareTo(otherHand.cards.get(4).getRank());
                        }
                    }
                }
            }
        }
        return this.getHandType().compareTo(o.getHandType());
    }
}
