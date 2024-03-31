package com.synacy.poker.hand;

import com.synacy.poker.card.Card;
import com.synacy.poker.card.CardRank;
import com.synacy.poker.hand.types.OnePair;
import com.synacy.poker.util.CardRankOrderUtil;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * A service that is used to identify the {@link Hand} given the player's cards and the community
 * cards.
 */
@Component
public class HandIdentifier {

    /**
     * Given the player's cards and the community cards, identifies the player's hand.
     *
     * @param playerCards
     * @param communityCards
     * @return The player's {@link Hand} or `null` if no Hand was identified.
     */
    public Hand identifyHand(List<Card> playerCards, List<Card> communityCards) {

        //TODO: Implement
        if(!playerCards.isEmpty()){

            List<Card> sortedList = new ArrayList<>(communityCards);
            CardRankOrderUtil
                    .sortCardsDescending(sortedList);
            List<Card> view = Collections.unmodifiableList(sortedList);

            Card playerCardOne = playerCards.get(0);
            Card playerCardTwo = playerCards.get(1);

            Map<CardRank, Integer> rankCounts = new HashMap<>();
            for (Card card : communityCards) {
                CardRank rank = card.getRank();
                rankCounts.put(rank,
                        rankCounts.getOrDefault(rank,
                                0) + 1);
            }
            boolean hasOnePairInPlayerCards = hasOnePair(playerCardOne,
                    playerCardTwo);

            boolean hasPairInCommunityCards =

            if(hasOnePairInPlayerCards){
                //We found a pair
                System.out.println("asd");
                List<Card> kickers = Arrays.asList(view.get(0),
                        view.get(1),
                        view.get(2));

                return new OnePair(playerCards,kickers);
            }
        }


        return null;
    }
    private boolean hasOnePair(Card playerCardOne, Card playerCardTwo){
        return playerCardOne.getRank()
                .equals(playerCardTwo.getRank());
    }
    private boolean has
}
