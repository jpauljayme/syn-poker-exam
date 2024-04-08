package com.synacy.poker.service;

import com.synacy.poker.model.card.Card;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommunityCardsService {

    private final List<Card> communityCards = new ArrayList<>();
    private static final int MAX_COMMUNITY_CARDS = 5;


    public void addCommunityCard(Card card) {
        communityCards.add(card);
    }

    public List<Card> getCommunityCards() {
        return communityCards;
    }

    public boolean isCommunityCardsEmpty(){return communityCards.isEmpty();}

    public boolean isCommunityCardsFull() {
        return communityCards.size() >= MAX_COMMUNITY_CARDS;
    }

    public void clearCommunityCards() {
        this.communityCards.clear();
    }
}
