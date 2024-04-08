package com.synacy.poker.service;

import com.synacy.poker.model.card.Card;
import com.synacy.poker.model.card.CardRank;
import com.synacy.poker.model.card.CardSuit;
import com.synacy.poker.model.hand.Hand;
import com.synacy.poker.model.hand.types.*;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

import static com.synacy.poker.model.card.CardRank.*;

/**
 * A service that is used to identify the {@link Hand} given the player's cards and the community
 * cards.
 */
@Service
public class HandIdentifierService {

    /**
     * Given the player's cards and the community cards, identifies the player's hand.
     *
     * @param playerCards
     * @param communityCards
     * @return The player's {@link Hand} or `null` if no Hand was identified.
     */
    public Hand identifyHand(List<Card> playerCards, List<Card> communityCards) {

        if (!playerCards.isEmpty() && !communityCards.isEmpty()) {


            //Put all cards into one stack
            List<Card> allCards = new ArrayList<>(playerCards);
            allCards.addAll(communityCards);
            Collections.sort(allCards);

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

            List<Card> flushHand = hasFlush(allCards);

            ArrayList<List<Card>> fourOfAKindHand = hasFourOfAKindHand(allCards);

            List<Card> hasStraightFlush = hasStraightFlush(allCards);

            if (!hasStraightFlush.isEmpty()) {
                if(hasStraightFlush.get(0).getRank() == ACE &&
                        hasStraightFlush.get(4).getRank() == TEN ){
                    return new RoyalFlush(hasStraightFlush);
                }else{
                    return new StraightFlush(hasStraightFlush);
                }
            } else if (!fourOfAKindHand.isEmpty()) {
                //Four Of A Kind
                return new FourOfAKind(fourOfAKindHand.get(0), fourOfAKindHand.get(1));
            } else if (!flushHand.isEmpty()) {
                //Flush Hand : Five cards of the same suit
                return new Flush(flushHand);
            } else if (!straightRange.isEmpty()) {
                //Straight Hand
                return new Straight(straightRange);
            } else if (pairs.size() == 1 && threeOfAKind.isPresent()) {

                //Full House Hand
                List<Card> threes = allCards.stream().
                        filter(card -> card.getRank().equals(threeOfAKind.get()))
                        .limit(3)
                        .collect(Collectors.toList());

                List<Card> pair = allCards.stream().
                        filter(card -> card.getRank() == pairs.get(0))
                        .limit(2)
                        .collect(Collectors.toList());

                return new FullHouse(threes, pair);

            } else if (threeOfAKind.isPresent()) {
                List<Card> threes = allCards.stream().
                        filter(card -> card.getRank().equals(threeOfAKind.get()))
                        .collect(Collectors.toList());

                List<Card> otherCards = allCards.stream()
                        .filter(card -> !card.getRank().equals(threeOfAKind.get()))
                        .limit(2)
                        .collect(Collectors.toList());

                return new ThreeOfAKind(threes, otherCards);
            } else if (!pairs.isEmpty()) {
                //ONE PAIR
                if (pairs.size() == 1) {
                    List<Card> pairCards = allCards.stream()
                            .filter(card -> card.getRank().equals(pairs.get(0)))
                            .limit(2)
                            .collect(Collectors.toList());

                    List<Card> kickers = allCards.stream()
                            .filter(card -> !card.getRank().equals(pairs.get(0)))
                            .limit(3)
                            .collect(Collectors.toList());

                    return new OnePair(pairCards, kickers);
                } else {
                    List<Card> firstPair = allCards.stream().
                            filter(card -> card.getRank() == pairs.get(0))
                            .collect(Collectors.toList());
                    List<Card> secondPair = allCards.stream().
                            filter(card -> card.getRank() == pairs.get(1))
                            .collect(Collectors.toList());
                    Card otherCard = allCards
                            .stream()
                            .filter(card -> !(card.getRank().equals(pairs.get(0)) && card.getRank().equals(pairs.get(1))))
                            .findFirst().get();

                    return new TwoPair(firstPair,
                            secondPair,
                            otherCard);
                }
            }else {
                return new HighCard(allCards
                        .stream()
                        .sorted()
                        .limit(5)
                        .collect(Collectors.toList()));
            }
        }else{
            return null;
        }
    }

    private List<Card> hasStraightFlush(List<Card> allCards) {
        HashMap<String, List<Card>> cardFreqSuitMap = new HashMap<>();

        for (Card c : allCards) {
            String suit = c.getSuit().toString();
            cardFreqSuitMap.computeIfAbsent(suit, k -> new ArrayList<>()).add(c);

        }
        for (List<Card> cardsBySuit : cardFreqSuitMap.values()) {
            if (cardsBySuit.size() >= 5) {
                Collections.sort(cardsBySuit);
                for (int i = 0; i <= cardsBySuit.size() - 5; i++) {
                    if (cardsBySuit.get(i).getRank().getNumber() == cardsBySuit.get(i + 1).getRank().getNumber() + 1
                            && cardsBySuit.get(i + 1).getRank().getNumber() == cardsBySuit.get(i + 2).getRank().getNumber() + 1
                            && cardsBySuit.get(i + 2).getRank().getNumber() == cardsBySuit.get(i + 3).getRank().getNumber() + 1
                            && cardsBySuit.get(i + 3).getRank().getNumber() == cardsBySuit.get(i + 4).getRank().getNumber() + 1) {
                        return cardsBySuit.subList(0, 5);
                    }
                }
                int lastElem = cardsBySuit.size() -1;
                if(cardsBySuit.get(0).getRank() == ACE
                    && cardsBySuit.get(lastElem).getRank() == TWO){
                            //Ace Low
                    List < Card > aceLowHand = cardsBySuit.stream()
                                    .filter(card -> card.getRank().getNumber() >= TWO.getNumber()
                                    && card.getRank().getNumber() <= FIVE.getNumber())
                                            .collect(Collectors.toList());
                    aceLowHand.add(cardsBySuit.get(0));
                return aceLowHand;
                }
            }
        }
        return Collections.emptyList();
    }


    private List<Card> hasFlush(List<Card> cards) {
        HashMap<CardSuit, Integer> cardSuitMap = new HashMap<>();
        for (Card c : cards) {
            CardSuit suit = c.getSuit();
            cardSuitMap.put(suit,
                    cardSuitMap.getOrDefault(suit, 0) + 1);
        }

        Optional<CardSuit> hasFlushSuit = cardSuitMap.entrySet()
                .stream()
                .filter(cardSuitEntry -> cardSuitEntry.getValue() >= 5)
                .map(Map.Entry::getKey)
                .findFirst();

        if (hasFlushSuit.isPresent()) {
            CardSuit flushSuit = hasFlushSuit.get();
            return cards.stream()
                    .filter(card -> card.getSuit().compareTo(flushSuit) == 0)
                    .limit(5)
                    .sorted()
                    .collect(Collectors.toList());
        }

        return Collections.emptyList();
    }

    private ArrayList<List<Card>> hasFourOfAKindHand(List<Card> allCards) {
        HashMap<Integer, Integer> cardRankFrequencyMap = new HashMap<>();
        for (Card c : allCards) {
            int rank = c.getRank().getNumber();
            cardRankFrequencyMap.put(rank,
                    cardRankFrequencyMap.getOrDefault(rank, 0) + 1);
        }
        Optional<Integer> hasFreqRank = cardRankFrequencyMap.entrySet()
                .stream()
                .filter(cardRankIntegerEntry -> cardRankIntegerEntry.getValue() == 4)
                .map(Map.Entry::getKey)
                .findFirst();

        if (hasFreqRank.isPresent()) {
            int fourOfAKindRank = hasFreqRank.get();

            List<Card> kickerCard = new ArrayList<>(1);

            List<Card> fourOfAKindCards = new ArrayList<>(4);

            for(Card c : allCards){
                if(fourOfAKindCards.size() == 4 && kickerCard.size() == 1){
                    break;
                }else if (c.getRank().getNumber() == fourOfAKindRank ) {
                    fourOfAKindCards.add(c);
                } else {
                    kickerCard.add(c);
                }
            }
            ArrayList<List<Card>> hand = new ArrayList<>();
            hand.add(fourOfAKindCards);
            hand.add(kickerCard);
            return hand;
        } else {
            return new ArrayList<>(Collections.emptyList());
        }
    }

    private List<CardRank> countFrequencyOfPairs(Map<CardRank, Integer> rankCounts) {
        return rankCounts.entrySet().stream()
                .filter(entry -> entry.getValue() == 2)
                .map(Map.Entry::getKey)
                .sorted((Comparator.reverseOrder()))
                .collect(Collectors.toList());
    }

    private Optional<CardRank> countFrequencyThreeOfAKind(Map<CardRank, Integer> rankCounts) {
        return rankCounts.entrySet().stream()
                .filter(entry -> entry.getValue() == 3)
                .map(Map.Entry::getKey)
                .findFirst();
    }

    private List<Card> hasStraight(List<Card> cards) {
        HashMap<Integer, Card> cardByRankMap = new HashMap<>();
        for (Card c : cards) {
            int rankNumber = c.getRank().getNumber();
            if (!cardByRankMap.containsKey(rankNumber)) {
                cardByRankMap.put(rankNumber, c);
            }
        }

        for (int i = 14; i - 5 >= 2; i--) {  // Loop from 2 (lowest rank) to Ace (high rank)
            if ( cardByRankMap.containsKey(i)
                && cardByRankMap.containsKey(i - 1)
                    && cardByRankMap.containsKey(i - 2)
                    && cardByRankMap.containsKey(i - 3)
                    && cardByRankMap.containsKey(i - 4)) {

                int finalRank = i;
                return cardByRankMap.
                        entrySet()
                        .stream()
                        .filter(entry -> entry.getKey() <= finalRank && entry.getKey() >= finalRank -4)
                        .map(Map.Entry::getValue)
                        .sorted()
                        .collect(Collectors.toList());
            }
        }

        if( cardByRankMap.containsKey(FIVE.getNumber())
                && cardByRankMap.containsKey(FOUR.getNumber())
                && cardByRankMap.containsKey(THREE.getNumber())
                && cardByRankMap.containsKey(TWO.getNumber())
                && cardByRankMap.containsKey(ACE.getNumber())) {
            //Ace Low
            List<Card> aceLowHand = new ArrayList<>();
            aceLowHand.add(cardByRankMap.get(FIVE.getNumber()));
            aceLowHand.add(cardByRankMap.get(FOUR.getNumber()));
            aceLowHand.add(cardByRankMap.get(THREE.getNumber()));
            aceLowHand.add(cardByRankMap.get(TWO.getNumber()));
            aceLowHand.add(cardByRankMap.get(ACE.getNumber()));
            return aceLowHand;
        }

        return Collections.emptyList();
    }
}

