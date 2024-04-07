package com.synacy.poker.service;

import com.synacy.poker.model.card.Card;
import com.synacy.poker.model.deck.Deck;
import com.synacy.poker.repository.DeckRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * A service class used to build a {@link Deck}
 */
@Service
public class DeckService {
	private final DeckRepository deckRepository;

	@Autowired
    public DeckService(DeckRepository deckRepository) {
        this.deckRepository = deckRepository;
    }

    /**
	 * Builds a complete {@link Deck} without Jokers. Does not shuffle the deck.
	 */
	public void buildDeck() {
		deckRepository.buildDeck();
	}

	public void shuffle() {
		deckRepository.shuffle();
	}

	public Card removeCardFromTop() {
		return deckRepository.removeCardFromTop();
	}

}
