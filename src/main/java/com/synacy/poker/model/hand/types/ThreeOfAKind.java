package com.synacy.poker.model.hand.types;

import com.synacy.poker.model.card.Card;
import com.synacy.poker.model.hand.Hand;
import com.synacy.poker.model.hand.HandType;

import java.util.List;
import java.util.Objects;

/**
 * @see <a href="https://en.wikipedia.org/wiki/List_of_poker_hands#Three_of_a_kind">What is a Three of a Kind?</a>
 */
public class ThreeOfAKind implements Hand {

    private List<Card> threeOfAKindCards;
    private List<Card> otherCards;

    public ThreeOfAKind(List<Card> threeOfAKindCards, List<Card> otherCards) {
        this.threeOfAKindCards = threeOfAKindCards;
        this.otherCards = otherCards;
    }

    public HandType getHandType() {
        return HandType.THREE_OF_A_KIND;
    }

    /**
     * @return The name of the hand plus kickers in descending rank, e.g. Trips (4) - A,2 High
     */
    @Override
    public String toString() {

        return String.format("Trips (%s) - %s,%s High",
                threeOfAKindCards.get(0).getRank(),
                otherCards.get(0).getRank(),
                otherCards.get(1).getRank()
                );
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ThreeOfAKind that = (ThreeOfAKind) o;
        return Objects.equals(threeOfAKindCards, that.threeOfAKindCards) && Objects.equals(otherCards, that.otherCards);
    }

    @Override
    public int hashCode() {
        return Objects.hash(threeOfAKindCards, otherCards);
    }

    /**
     * @param o the Hand to be compared.
     * @return 0 if the two hands are equal, a negative integer
     * if this hand is lesser than the other hand, and a
     * positive integer greater than 0 when
     * the other hand is greater than this hand
     */
    @Override
    public int compareTo(Hand o) {
        if(o instanceof ThreeOfAKind){
            ThreeOfAKind otherHand = (ThreeOfAKind) o;

            int compareTripletRank = this.threeOfAKindCards.get(0).getRank()
                    .compareTo(otherHand.threeOfAKindCards.get(0).getRank());

            if(compareTripletRank != 0){
                return  compareTripletRank;
            }else{
                //Equal, compare by highest kicker, then lowest
                int compareFirstKicker = this.otherCards.get(0).getRank().compareTo(otherHand.otherCards.get(0).getRank());
                if(compareFirstKicker != 0){
                    return compareFirstKicker;
                }else{
                    return this.otherCards.get(1).getRank().compareTo(otherHand.otherCards.get(1).getRank());
                }
            }
        }else{
            return this.getHandType().compareTo(o.getHandType());
        }
    }
}
