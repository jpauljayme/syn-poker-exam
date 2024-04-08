package com.synacy.poker.model.hand.types;

import com.synacy.poker.model.card.Card;
import com.synacy.poker.model.hand.Hand;
import com.synacy.poker.model.hand.HandType;

import java.util.List;
import java.util.Objects;

/**
 * @see <a href="https://en.wikipedia.org/wiki/List_of_poker_hands#Straight">What is a Straight?</a>
 */
public class Straight implements Hand {

    private final List<Card> cards;

    public Straight(List<Card> cards) {
        this.cards = cards;
    }

    public HandType getHandType() {
        return HandType.STRAIGHT;
    }

    public List<Card> getCards() {
        return cards;
    }

    /**
     * @return The name of the hand and the high card, e.g. Straight (A High)
     */
    @Override
    public String toString() {
        return String.format("Straight (%s High)",
                cards.get(0).getRank());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Straight straight = (Straight) o;
        return Objects.equals(cards, straight.cards);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cards);
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
        if(o instanceof Straight){
            Straight otherHand = (Straight) o;

            return this.cards.get(0).getRank().compareTo(otherHand.cards.get(0).getRank());
        }else{
            return this.getHandType().compareTo(o.getHandType());
        }
    }
}
