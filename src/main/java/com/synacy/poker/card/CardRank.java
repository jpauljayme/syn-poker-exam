package com.synacy.poker.card;

import java.util.HashMap;
import java.util.Map;

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

	private static final Map<String, CardRank> BY_RANK_LABEL = new HashMap<>();
	private static final Map<Integer, CardRank> BY_RANK_NUMBER = new HashMap<>();

	static {
		for (CardRank cardRank : values()) {
			BY_RANK_LABEL.put(cardRank.label, cardRank);
			BY_RANK_NUMBER.put(cardRank.number, cardRank);
		}
	}

	private final String label;
	private final int number;

	CardRank(String label, int number) {

		this.label = label;
		this.number = number;
	}

	public static CardRank valueOfRankLabel(String label) {
		return BY_RANK_LABEL.get(label);
	}

	public static CardRank valueOfRankNumber(int number) {
		return BY_RANK_NUMBER.get(number);
	}

	@Override
	public String toString() {
		return label;
	}

	public int getNumber() {
		return number;
	}
}
