package com.synacy.poker.model.hand.types;

import com.synacy.poker.model.card.Card;
import com.synacy.poker.model.card.CardRank;
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
     * @param o the object to be compared.
     * @return
     */
    @Override
    public int compareTo(Hand o) {
        if (o instanceof TwoPair) {
            TwoPair otherHand = (TwoPair) o;

            CardRank thisFirstPairRank = this.firstPairCards.get(0).getRank();
            CardRank thisSecondPairRank = this.secondPairCards.get(0).getRank();

            CardRank thisHighestRank = thisFirstPairRank.compareTo(thisSecondPairRank) >= 1 ? thisFirstPairRank : thisSecondPairRank;
            CardRank thisSecondHighestRank = thisFirstPairRank.equals(thisHighestRank) ? thisSecondPairRank : thisFirstPairRank;

            CardRank otherFirstPairRank = otherHand.firstPairCards.get(0).getRank();
            CardRank otherSecondPairRank = otherHand.secondPairCards.get(0).getRank();

            CardRank otherHighestRank = otherFirstPairRank.compareTo(otherSecondPairRank) >= 1 ? otherFirstPairRank : otherSecondPairRank;
            CardRank otherSecondHighestRank = otherFirstPairRank.equals(otherHighestRank) ? otherSecondPairRank : otherFirstPairRank;

            int compareHighestPair = thisHighestRank.compareTo(otherHighestRank);
            if(compareHighestPair != 0){
                return  compareHighestPair;
            }else{
                int compareSecondHighestPair = thisSecondHighestRank.compareTo(otherSecondHighestRank);
                if(compareSecondHighestPair != 0){
                    return compareHighestPair;
                }else{
                    //Compare othercard
                    return this.otherCard.compareTo(otherHand.otherCard);
                }
            }
        }else{
            return this.getHandType().compareTo(o.getHandType());
        }
    }
}
