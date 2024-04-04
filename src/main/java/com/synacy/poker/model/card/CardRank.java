package com.synacy.poker.model.card;

/**
 * The rank of a {@link Card} from <em>2</em> to <em>Ace</em>. No jokers.
 */
public enum CardRank {

	TWO("2", 2),
	THREE("3", 3),
	FOUR("4", 4),
	FIVE("5", 5),
	SIX("6", 6),
	SEVEN("7", 7),
	EIGHT("8", 8),
	NINE("9", 9),
	TEN("10", 10),
	JACK("J", 11),
	QUEEN("Q", 12),
	KING("K", 13),
	ACE("A", 14); //LOW OR HIGH..

	private final String label;
	private final int number;

	CardRank(String label, int number) {

		this.label = label;
		this.number = number;
	}

	@Override
	public String toString() {
		return label;
	}

	public int getNumber() {
		return number;
	}
}
