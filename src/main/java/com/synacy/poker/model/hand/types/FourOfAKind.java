package com.synacy.poker.model.hand.types;

import com.synacy.poker.model.card.Card;
import com.synacy.poker.model.hand.Hand;
import com.synacy.poker.model.hand.HandType;

import java.util.List;
import java.util.Objects;

/**
 * @see <a href="https://en.wikipedia.org/wiki/List_of_poker_hands#Four_of_a_kind">What is a Four of a Kind?</a>
 */
public class FourOfAKind implements Hand {

    private final List<Card> fourOfAKindCards;
    private final List<Card> otherCards;

    public FourOfAKind(List<Card> fourOfAKindCards, List<Card> otherCards) {
        this.fourOfAKindCards = fourOfAKindCards;
        this.otherCards = otherCards;
    }

    public HandType getHandType() {
        return HandType.FOUR_OF_A_KIND;
    }

    /**
     * @return Returns the name of the hand plus kicker, e.g. Quads (4) - A High
     */
    @Override
    public String toString() {
        return String.format("Quads (%s) - %s High",
                fourOfAKindCards.get(0).getRank(),
                otherCards.get(0).getRank());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FourOfAKind that = (FourOfAKind) o;
        return Objects.equals(fourOfAKindCards, that.fourOfAKindCards) && Objects.equals(otherCards, that.otherCards);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fourOfAKindCards, otherCards);
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
