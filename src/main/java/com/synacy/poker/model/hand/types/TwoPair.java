package com.synacy.poker.model.hand.types;

import com.synacy.poker.model.card.Card;
import com.synacy.poker.model.hand.Hand;
import com.synacy.poker.model.hand.HandType;

import java.util.List;
import java.util.Objects;

/**
 * @see <a href="https://en.wikipedia.org/wiki/List_of_poker_hands#Two_pair">What is a Two Pair?</a>
 */
public class TwoPair implements Hand {

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TwoPair twoPair = (TwoPair) o;
        return Objects.equals(firstPairCards, twoPair.firstPairCards) && Objects.equals(secondPairCards, twoPair.secondPairCards) && Objects.equals(otherCard, twoPair.otherCard);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstPairCards, secondPairCards, otherCard);
    }

    /**
     * @param otherHand the object to be compared.
     * @return
     */
    @Override
    public int compareTo(Hand otherHand) {
        int isEqualHand = this.getHandType().compareTo(otherHand.getHandType());
//        if(otherHand instanceof TwoPair){
//            //Equal Rank
//            //Evaluate per pair, then kicker
//            this.firstPairCards.a
//            return
//        }else{
//            return isEqualHand;
//        }
        return  0;
    }
}
