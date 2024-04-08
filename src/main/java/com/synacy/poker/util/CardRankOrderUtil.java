package com.synacy.poker.util;

import com.synacy.poker.model.card.Card;

import java.util.Comparator;
import java.util.List;

public class CardRankOrderUtil {

    public static void sortCardsDescending(List<Card> cards) {
        Comparator<Card> cardComparator = Comparator
                .comparing(Card::getRank, Comparator.reverseOrder());
        cards.sort(cardComparator);
    }
}
