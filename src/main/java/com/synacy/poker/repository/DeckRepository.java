package com.synacy.poker.repository;

import com.synacy.poker.model.card.Card;
import com.synacy.poker.model.card.CardRank;
import com.synacy.poker.model.card.CardSuit;
import com.synacy.poker.model.deck.Deck;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class DeckRepository {
    private final Deck deck = new Deck();

    public void buildDeck() {
        generateCards();
    }

    private void generateCards() {
        CardSuit[] cardSuits = CardSuit.values();

        List<Card> allCards = Arrays.stream(cardSuits)
                .map(this::cardsForSuit)
                .flatMap(List::stream)
                .collect(Collectors.toList());

        deck.addCards(allCards);
    }

    private List<Card> cardsForSuit(CardSuit suit) {
        CardRank[] cardRanks = CardRank.values();

        return Arrays.stream(cardRanks)
                .map(rank -> new Card.Builder()
                        .rank(rank)
                        .suit(suit).build())
                .collect(Collectors.toList());
    }

    public void shuffle() {
        deck.shuffle();
    }

    public Card removeCardFromTop() {
        return this.deck.removeFromTop();
    }

}
