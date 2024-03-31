package com.synacy.poker.hand.types;

import com.synacy.poker.card.Card;
import com.synacy.poker.hand.Hand;
import com.synacy.poker.hand.HandType;
import com.synacy.poker.util.CardRankOrderUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @see <a href="https://en.wikipedia.org/wiki/List_of_poker_hands#High_card">What is a High Card?</a>
 */
public class HighCard extends Hand {

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

}
