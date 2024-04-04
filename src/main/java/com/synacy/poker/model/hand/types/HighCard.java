package com.synacy.poker.model.hand.types;

import com.synacy.poker.model.card.Card;
import com.synacy.poker.model.hand.Hand;
import com.synacy.poker.model.hand.HandType;
import com.synacy.poker.util.CardRankOrderUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * @see <a href="https://en.wikipedia.org/wiki/List_of_poker_hands#High_card">What is a High Card?</a>
 */
public class HighCard implements Hand {

    private List<Card> cards;

    public HighCard(List<Card> cards) {
        this.cards = cards;
    }

    public HandType getHandType() {
        return HandType.HIGH_CARD;
    }

    /**
     * @return The cards ordered by descending rank, e.g. A,K,Q,3,2
     */
    @Override
    public String toString() {
        List<Card> sortedList = new ArrayList<>(cards);
        CardRankOrderUtil
                .sortCardsDescending(sortedList);
        List<Card> view = Collections.unmodifiableList(sortedList);

        StringBuilder sb = new StringBuilder();
        for(Card c : view){
            sb.append(c.getRank()).append(",");
        }
        sb.delete(sb.length()-1, sb.length());
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HighCard highCard = (HighCard) o;
        return Objects.equals(cards, highCard.cards);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cards);
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
