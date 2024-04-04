package com.synacy.poker.hand.types;

import com.synacy.poker.card.Card;
import com.synacy.poker.hand.Hand;
import com.synacy.poker.hand.HandType;

import java.util.List;
import java.util.Objects;

/**
 * @see <a href="https://en.wikipedia.org/wiki/List_of_poker_hands#One_pair">What is a One Pair?</a>
 */
public class OnePair extends Hand {

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

        //TODO: Implement one pair, probably need to
        StringBuilder builder = new StringBuilder();
        builder.append("One Pair (2)");

        if(!otherCards.isEmpty()){
            builder.append(" - ");
            for(int i = 0 ; i < otherCards.size() ; i++){
                Card c = otherCards.get(i);
                if(i != 0){
                    builder.append(",").append(c.getRank());
                }else{
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
}
