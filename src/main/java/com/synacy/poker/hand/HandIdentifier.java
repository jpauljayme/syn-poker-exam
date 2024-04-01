package com.synacy.poker.hand;

import com.synacy.poker.card.Card;
import com.synacy.poker.card.CardRank;
import com.synacy.poker.hand.types.*;
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
        if (!playerCards.isEmpty() && !communityCards.isEmpty()) {
            List<Card> sortedList = new ArrayList<>(communityCards);
            CardRankOrderUtil
                    .sortCardsDescending(sortedList);
            List<Card> view = Collections.unmodifiableList(sortedList);

            //Put all cards into one stack
            List<Card> allCards = new ArrayList<>(playerCards);
            allCards.addAll(communityCards);
//            List<Card> sortedAllCards = new ArrayList<>(communityCards);
//            CardRankOrderUtil
//                    .sortCardsDescending(sortedAllCards);
//            List<Card> viewAllCards= Collections.unmodifiableList(sortedAllCards);
//
//
//            Map<CardRank, Integer> sortedRankCounts = new HashMap<>();
//            for (Card card : sortedAllCards) {
//                CardRank rank = card.getRank();
//                sortedRankCounts.put(rank,
//                        sortedRankCounts.getOrDefault(rank, 0) + 1);
//            }


            Map<CardRank, Integer> rankCounts = new HashMap<>();
            for (Card card : allCards) {
                CardRank rank = card.getRank();
                rankCounts.put(rank,
                        rankCounts.getOrDefault(rank, 0) + 1);
            }

            Map<Integer, Integer> rankNumberCounts = new HashMap<>();

            for (Card card : allCards) {
                CardRank rank = card.getRank();
                rankNumberCounts.put(rank.getNumber(),
                        rankNumberCounts.getOrDefault(rank.getNumber(),
                                0) + 1);
            }
            List<CardRank> pairs = countFrequencyOfPairs(rankCounts);

            Optional<CardRank> threeOfAKind = countFrequencyThreeOfAKind(rankCounts);

            List<Card> straightRange = hasStraight(allCards);


             //Straight Hand
            if(!straightRange.isEmpty()){

                return new Straight(straightRange);

            } else if (pairs.size() == 1 && threeOfAKind.isPresent()) {

                //Full House Hand

                List<Card> threes = allCards.stream().
                        filter(card -> card.getRank().equals(threeOfAKind.get()))
                        .collect(Collectors.toList());

                List<Card> pair = allCards.stream().
                        filter(card -> card.getRank() == pairs.get(0))
                        .collect(Collectors.toList());

                return new FullHouse(threes, pair);

            } else if (threeOfAKind.isPresent()) {
                List<Card> threes = allCards.stream().
                        filter(card -> card.getRank().equals(threeOfAKind.get()))
                        .collect(Collectors.toList());

                List<Card> otherCards = Arrays.asList(view.get(0), view.get(1));

                return new ThreeOfAKind(threes, otherCards);
            } else if (!pairs.isEmpty()) {
                //ONE PAIR
                if (pairs.size() == 1) {
                    List<Card> kickers = Arrays.asList(view.get(0),
                            view.get(1),
                            view.get(2));

                    return new OnePair(playerCards, kickers);
                } else {

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

    private List<CardRank> countFrequencyOfPairs(Map<CardRank, Integer> rankCounts) {
        return rankCounts.entrySet().stream()
                .filter(entry -> entry.getValue() == 2)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }

    private Optional<CardRank> countFrequencyThreeOfAKind(Map<CardRank, Integer> rankCounts) {
        return rankCounts.entrySet().stream()
                .filter(entry -> entry.getValue() == 3)
                .map(Map.Entry::getKey)
                .findFirst();
    }

    private List<Card> hasStraight(List<Card>  cards){
        HashMap<Integer, Card> cardMap = new HashMap<>();
        for(Card c : cards){
            int rankNumber = c.getRank().getNumber();
            if(!cardMap.containsKey(rankNumber)){
                cardMap.put(rankNumber, c);
            }
        }

        List<Integer> straightNumbers = new ArrayList<>(5);
        int lastRank = 0;
        boolean isAceLow = false;
        for (int start = 2; start <= 14; start ++) {  // Loop from 2 (lowest rank) to Ace (high rank)
            boolean isStraight = true;

            for (int end = start; end < start + 5; end++) {  // Check for consecutive ranks in a sequence of 5
                Card endCard = cardMap.getOrDefault(end, null);
                if (endCard == null) {
                    isStraight = false;
                    break;
                }else{

                    lastRank = end;
                    if(start == 2 && end == 5
                            && cardMap.containsKey(CardRank.ACE.getNumber())){
                        isAceLow = true;
                        break;
                    }
                }


            }
            if (isStraight) {
                final int finalStart = start;
                int finalLastRank = lastRank;
                if(start + 4 == 14){

                    List<Card> collect = cardMap.entrySet()
                            .stream()
                            .filter(entry -> entry.getKey() >= finalStart && entry.getKey() <= finalLastRank)
                            .map(Map.Entry::getValue)
                            .collect(Collectors.toList());

                    return collect;
                }else if(isAceLow){
                    List<Card> orig = cardMap.entrySet()
                            .stream()
                            .filter(entryCard -> entryCard.getKey() == CardRank.ACE.getNumber() || (entryCard.getKey() >= finalStart && entryCard.getKey() <= finalLastRank))
                            .map(Map.Entry::getValue)
                            .collect(Collectors.toList());
                    //Swap
                    List<Card> straightAceLow = new ArrayList<>();
                    int aceIndex = 4;
                    straightAceLow.add(0, orig.get(aceIndex) );
                    straightAceLow.addAll(orig.subList(0, aceIndex));
                    Collections.reverse(straightAceLow);
                    return straightAceLow;
                }



            }
        }
        return Collections.emptyList();
    }
}

