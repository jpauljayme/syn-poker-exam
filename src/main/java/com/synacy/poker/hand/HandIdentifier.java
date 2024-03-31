package com.synacy.poker.hand;

import com.synacy.poker.card.Card;
import com.synacy.poker.card.CardRank;
import com.synacy.poker.hand.types.OnePair;
import com.synacy.poker.hand.types.TwoPair;
import com.synacy.poker.util.CardRankOrderUtil;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

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
        if(!playerCards.isEmpty() && !communityCards.isEmpty()){
            List<Card> sortedList = new ArrayList<>(communityCards);
            CardRankOrderUtil
                    .sortCardsDescending(sortedList);
            List<Card> view = Collections.unmodifiableList(sortedList);

            //Put all cards into one stack
            List<Card> allCards = new ArrayList<>(playerCards);
            allCards.addAll(communityCards);

            Card playerCardOne = playerCards.get(0);
            Card playerCardTwo = playerCards.get(1);

            Map<CardRank, Integer> rankCounts = new HashMap<>();
            for (Card card : allCards) {
                CardRank rank = card.getRank();
                rankCounts.put(rank,
                        rankCounts.getOrDefault(rank,
                                0) + 1);
            }

            List<CardRank> pairs = countFrequencyOfPairs(rankCounts);

            //We found pair/pairs
            if(!pairs.isEmpty()){

                //ONE PAIR
              if(pairs.size() == 1){
                  List<Card> kickers = Arrays.asList(view.get(0),
                          view.get(1),
                          view.get(2));

                  return new OnePair(playerCards,kickers);
              }else {

                  List<Card> firstPair = allCards.stream().
                          filter(card -> card.getRank() == pairs.get(0))
                          .collect(Collectors.toList());
                  List<Card> secondPair = allCards.stream().
                          filter(card -> card.getRank() == pairs.get(1))
                          .collect(Collectors.toList());
                  return new TwoPair(firstPair,
                          secondPair,
                          view.get(0));
              }
            }
        }
        return null;
    }

    private List<CardRank> countFrequencyOfPairs(Map<CardRank, Integer> rankCounts){
        return rankCounts.entrySet().stream()
                .filter(entry -> entry.getValue() == 2)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }
    private List<CardRank> countFrequencyThreeOfAKind(Map<CardRank, Integer> rankCounts){
        return rankCounts.entrySet().stream()
                .filter(entry -> entry.getValue() == 3)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }

}