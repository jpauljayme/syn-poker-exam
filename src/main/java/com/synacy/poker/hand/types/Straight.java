package com.synacy.poker.hand.types;

import com.synacy.poker.card.Card;
import com.synacy.poker.card.CardRank;
import com.synacy.poker.hand.Hand;
import com.synacy.poker.hand.HandType;
import com.synacy.poker.util.CardRankOrderUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @see <a href="https://en.wikipedia.org/wiki/List_of_poker_hands#Straight">What is a Straight?</a>
 */
public class Straight extends Hand {

    private List<Card> cards;

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
        // Create a sorted copy of the list without modifying the original list
        List<Card> sortedList = new ArrayList<>(cards);
        CardRankOrderUtil
                .sortCardsDescending(sortedList);
        List<Card> view = Collections.unmodifiableList(sortedList);
        boolean isAcePresent = view
                .stream()
                .anyMatch( card ->
                card.getRank() == CardRank.ACE);
        if(isAcePresent){
            boolean isKingPresent = view.stream().
                    anyMatch(card -> card.getRank() == CardRank.KING);
            if(isKingPresent){
                return String.format("Straight (%s High)",
                        view.get(0).getRank());
                //Ace High
            }else{
                //Ace Low
                return String.format("Straight (%s High)",
                        view.get(1).getRank());
            }
        }else{
            return String.format("Straight (%s High)",
                    view.get(0).getRank());
        }

    }

}
