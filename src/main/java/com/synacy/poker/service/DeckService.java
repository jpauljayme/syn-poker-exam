package com.synacy.poker.service;

import com.synacy.poker.model.card.Card;
import com.synacy.poker.model.card.CardRank;
import com.synacy.poker.model.card.CardSuit;
import com.synacy.poker.model.deck.Deck;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * A service class used to build a {@link Deck}
 */
@Service
public class DeckService {

	private final Deck deck = new Deck();

	/**
	 * Builds a complete {@link Deck} without Jokers. Does not shuffle the deck.
	 */
	public void buildDeck() {
		deck.addCards(generateCards());
	}

	private List<Card> generateCards() {
		CardSuit[] cardSuits = CardSuit.values();

		return Arrays.stream(cardSuits)
				.map(this::cardsForSuit)
				.flatMap(List::stream)
				.collect(Collectors.toList());
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
