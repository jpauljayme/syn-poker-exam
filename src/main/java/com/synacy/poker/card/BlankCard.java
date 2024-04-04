package com.synacy.poker.card;

/**
 * A blank card. Also, the back side of the card.
 */
public class BlankCard extends Card {

	private BlankCard(Builder builder) {
		super(builder);
	}

	/**
	 *
	 * @return The CSS class <code>card-back</code>
	 */
	@Override
	public String styleClass() {
		return "card-back";
	}

	@Override
	public String toString() {
		return "&nbsp;";
	}

	public static class Builder extends Card.Builder {

		public Builder description(String description) {
			return this;
		}

		@Override
		public BlankCard build() {
			return new BlankCard(this);
		}
	}
}
