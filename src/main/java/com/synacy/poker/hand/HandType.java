package com.synacy.poker.hand;

/**
 * An enum of {@link Hand}s
 *
 * <ul>
 * <li>High Card</li>
 * <li>One Pair</li>
 * <li>Two Pair</li>
 * <li>Three of a Kind</li>
 * <li>Straight</li>
 * <li>Flush</li>
 * <li>Full House</li>
 * <li>Four of a Kind</li>
 * <li>Straight Flush</li>
 * <li>Royal Flush</li>
 * </ul>
 */
public enum HandType {

    HIGH_CARD("High Card", 1),
    ONE_PAIR("One Pair",2),
    TWO_PAIR("Two Pair",3),
    THREE_OF_A_KIND("Three of a Kind",4),
    STRAIGHT("Straight",5),
    FLUSH("Flush", 6),
    FULL_HOUSE("Full House",7),
    FOUR_OF_A_KIND("Four of a Kind", 8),
    STRAIGHT_FLUSH("Straight Flush",9),
    ROYAL_FLUSH("Royal Flush", 10);

    private final String label;
    private final int number;

    HandType(String label, int number) {
        this.label = label;
        this.number = number;
    }

    public int getNumber() {
        return number;
    }

    public String getLabel() {
        return label;
    }

    @Override
    public String toString() {
        return "HandType{" +
                "label='" + label + '\'' +
                ", number=" + number +
                '}';
    }
}
