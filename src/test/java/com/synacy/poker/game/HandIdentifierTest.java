package com.synacy.poker.game;

import com.synacy.poker.card.Card;
import com.synacy.poker.card.CardRank;
import com.synacy.poker.card.CardSuit;
import com.synacy.poker.hand.Hand;
import com.synacy.poker.hand.HandIdentifier;
import com.synacy.poker.hand.types.Flush;
import com.synacy.poker.hand.types.FourOfAKind;
import com.synacy.poker.hand.types.FullHouse;
import com.synacy.poker.hand.types.HighCard;
import com.synacy.poker.hand.types.OnePair;
import com.synacy.poker.hand.types.Straight;
import com.synacy.poker.hand.types.StraightFlush;
import com.synacy.poker.hand.types.ThreeOfAKind;
import com.synacy.poker.hand.types.TwoPair;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class HandIdentifierTest {

	private final HandIdentifier handIdentifier = new HandIdentifier();

	@Test
	public void identifyHand_royalFlush() {
		List<Card> playerCards = Arrays.asList(
//				new Card(CardRank.ACE, CardSuit.SPADES),
//				new Card(CardRank.KING, CardSuit.SPADES)
                new Card.Builder()
                        .rank(CardRank.ACE)
                        .suit(CardSuit.SPADES)
                        .build(),
                new Card.Builder()
                        .rank(CardRank.KING)
                        .suit(CardSuit.SPADES)
                        .build()
		);

		List<Card> communityCards = Arrays.asList(
//				new Card(CardRank.QUEEN, CardSuit.SPADES),
//				new Card(CardRank.JACK, CardSuit.SPADES),
//				new Card(CardRank.TEN, CardSuit.SPADES),
//				new Card(CardRank.NINE, CardSuit.SPADES),
//				new Card(CardRank.ACE, CardSuit.CLUBS)
                new Card.Builder()
                        .rank(CardRank.QUEEN)
                        .suit(CardSuit.SPADES)
                        .build(),
                new Card.Builder()
                        .rank(CardRank.JACK)
                        .suit(CardSuit.SPADES)
                        .build(),
                new Card.Builder()
                        .rank(CardRank.TEN)
                        .suit(CardSuit.SPADES)
                        .build(),
                new Card.Builder()
                        .rank(CardRank.NINE)
                        .suit(CardSuit.SPADES)
                        .build(),
                new Card.Builder()
                        .rank(CardRank.ACE)
                        .suit(CardSuit.CLUBS)
                        .build()
		);

		Hand identifiedHand = handIdentifier.identifyHand(playerCards, communityCards);

		assertTrue(identifiedHand instanceof StraightFlush);
		assertEquals("Royal Flush", identifiedHand.toString());
	}

	@Test
	public void identifyHand_straightFlush() {
		List<Card> playerCards = Arrays.asList(
//				new Card(CardRank.QUEEN, CardSuit.SPADES),
//				new Card(CardRank.JACK, CardSuit.SPADES)
                new Card.Builder()
                        .rank(CardRank.QUEEN)
                        .suit(CardSuit.SPADES)
                        .build(),
                new Card.Builder()
                        .rank(CardRank.JACK)
                        .suit(CardSuit.SPADES)
                        .build()
		);

		List<Card> communityCards = Arrays.asList(
//				new Card(CardRank.TEN, CardSuit.SPADES),
//				new Card(CardRank.NINE, CardSuit.SPADES),
//				new Card(CardRank.EIGHT, CardSuit.SPADES),
//				new Card(CardRank.TWO, CardSuit.SPADES),
//				new Card(CardRank.ACE, CardSuit.CLUBS)
                new Card.Builder()
                        .rank(CardRank.TEN)
                        .suit(CardSuit.SPADES)
                        .build(),
                new Card.Builder()
                        .rank(CardRank.NINE)
                        .suit(CardSuit.SPADES)
                        .build(),
                new Card.Builder()
                        .rank(CardRank.EIGHT)
                        .suit(CardSuit.SPADES)
                        .build(),
                new Card.Builder()
                        .rank(CardRank.TWO)
                        .suit(CardSuit.SPADES)
                        .build(),
                new Card.Builder()
                        .rank(CardRank.ACE)
                        .suit(CardSuit.CLUBS)
                        .build()
		);

		Hand identifiedHand = handIdentifier.identifyHand(playerCards, communityCards);

		assertTrue(identifiedHand instanceof StraightFlush);
		assertEquals("Straight Flush (Q High)", identifiedHand.toString());
	}

	@Test
	public void identifyHand_straightFlush_aceLow() {
		List<Card> playerCards = Arrays.asList(
//				new Card(CardRank.FIVE, CardSuit.SPADES),
//				new Card(CardRank.ACE, CardSuit.SPADES)
                new Card.Builder()
                        .rank(CardRank.FIVE)
                        .suit(CardSuit.SPADES)
                        .build(),
                new Card.Builder()
                        .rank(CardRank.ACE)
                        .suit(CardSuit.SPADES)
                        .build()
		);

		List<Card> communityCards = Arrays.asList(
//				new Card(CardRank.FOUR, CardSuit.SPADES),
//				new Card(CardRank.NINE, CardSuit.SPADES),
//				new Card(CardRank.THREE, CardSuit.SPADES),
//				new Card(CardRank.TWO, CardSuit.SPADES),
//				new Card(CardRank.ACE, CardSuit.CLUBS)
                new Card.Builder()
                        .rank(CardRank.FOUR)
                        .suit(CardSuit.SPADES)
                        .build(),
                new Card.Builder()
                        .rank(CardRank.NINE)
                        .suit(CardSuit.SPADES)
                        .build(),
                new Card.Builder()
                        .rank(CardRank.THREE)
                        .suit(CardSuit.SPADES)
                        .build(),
                new Card.Builder()
                        .rank(CardRank.TWO)
                        .suit(CardSuit.SPADES)
                        .build(),
                new Card.Builder()
                        .rank(CardRank.ACE)
                        .suit(CardSuit.CLUBS)
                        .build()
		);

		Hand identifiedHand = handIdentifier.identifyHand(playerCards, communityCards);

		assertTrue(identifiedHand instanceof StraightFlush);
		assertEquals("Straight Flush (5 High)", identifiedHand.toString());
	}

	@Test
	public void identifyHand_fourOfAKind() {
		List<Card> playerCards = Arrays.asList(
//				new Card(CardRank.EIGHT, CardSuit.SPADES),
//				new Card(CardRank.EIGHT, CardSuit.CLUBS)
                new Card.Builder()
                        .rank(CardRank.EIGHT)
                        .suit(CardSuit.SPADES)
                        .build(),
                new Card.Builder()
                        .rank(CardRank.EIGHT)
                        .suit(CardSuit.CLUBS)
                        .build()
		);

		List<Card> communityCards = Arrays.asList(
//				new Card(CardRank.EIGHT, CardSuit.DIAMONDS),
//				new Card(CardRank.EIGHT, CardSuit.HEARTS),
//				new Card(CardRank.SEVEN, CardSuit.SPADES),
//				new Card(CardRank.SIX, CardSuit.SPADES),
//				new Card(CardRank.TWO, CardSuit.CLUBS)
                new Card.Builder()
                        .rank(CardRank.EIGHT)
                        .suit(CardSuit.DIAMONDS)
                        .build(),
                new Card.Builder()
                        .rank(CardRank.EIGHT)
                        .suit(CardSuit.HEARTS)
                        .build(),
                new Card.Builder()
                        .rank(CardRank.SEVEN)
                        .suit(CardSuit.SPADES)
                        .build(),
                new Card.Builder()
                        .rank(CardRank.SIX)
                        .suit(CardSuit.SPADES)
                        .build(),
                new Card.Builder()
                        .rank(CardRank.TWO)
                        .suit(CardSuit.SPADES)
                        .build()
		);

		Hand identifiedHand = handIdentifier.identifyHand(playerCards, communityCards);

		assertTrue(identifiedHand instanceof FourOfAKind);
		assertEquals("Quads (8) - 7 High", identifiedHand.toString());
	}

	@Test
	public void identifyHand_fullHouse() {
		List<Card> playerCards = Arrays.asList(
//				new Card(CardRank.EIGHT, CardSuit.SPADES),
//				new Card(CardRank.EIGHT, CardSuit.DIAMONDS)
                new Card.Builder()
                        .rank(CardRank.EIGHT)
                        .suit(CardSuit.SPADES)
                        .build(),
                new Card.Builder()
                        .rank(CardRank.EIGHT)
                        .suit(CardSuit.DIAMONDS)
                        .build()
		);

		List<Card> communityCards = Arrays.asList(
//				new Card(CardRank.SEVEN, CardSuit.HEARTS),
//				new Card(CardRank.SEVEN, CardSuit.SPADES),
//				new Card(CardRank.EIGHT, CardSuit.CLUBS),
//				new Card(CardRank.NINE, CardSuit.SPADES),
//				new Card(CardRank.ACE, CardSuit.CLUBS)
                new Card.Builder()
                        .rank(CardRank.SEVEN)
                        .suit(CardSuit.HEARTS)
                        .build(),
                new Card.Builder()
                        .rank(CardRank.SEVEN)
                        .suit(CardSuit.SPADES)
                        .build(),
                new Card.Builder()
                        .rank(CardRank.EIGHT)
                        .suit(CardSuit.CLUBS)
                        .build(),
                new Card.Builder()
                        .rank(CardRank.NINE)
                        .suit(CardSuit.SPADES)
                        .build(),
                new Card.Builder()
                        .rank(CardRank.ACE)
                        .suit(CardSuit.CLUBS)
                        .build()
		);

		Hand identifiedHand = handIdentifier.identifyHand(playerCards, communityCards);

		assertTrue(identifiedHand instanceof FullHouse);
		assertEquals("Full House (8,7)", identifiedHand.toString());
	}

	@Test
	public void identifyHand_flush() {
		List<Card> playerCards = Arrays.asList(
//				new Card(CardRank.SEVEN, CardSuit.SPADES),
//				new Card(CardRank.TWO, CardSuit.SPADES)
                new Card.Builder()
                        .rank(CardRank.SEVEN)
                        .suit(CardSuit.SPADES)
                        .build(),
                new Card.Builder()
                        .rank(CardRank.TWO)
                        .suit(CardSuit.SPADES)
                        .build()
		);

		List<Card> communityCards = Arrays.asList(
//				new Card(CardRank.QUEEN, CardSuit.SPADES),
//				new Card(CardRank.JACK, CardSuit.SPADES),
//				new Card(CardRank.TEN, CardSuit.SPADES),
//				new Card(CardRank.NINE, CardSuit.SPADES),
//				new Card(CardRank.ACE, CardSuit.CLUBS)
                new Card.Builder()
                        .rank(CardRank.QUEEN)
                        .suit(CardSuit.SPADES)
                        .build(),
                new Card.Builder()
                        .rank(CardRank.JACK)
                        .suit(CardSuit.SPADES)
                        .build(),
                new Card.Builder()
                        .rank(CardRank.TEN)
                        .suit(CardSuit.SPADES)
                        .build(),
                new Card.Builder()
                        .rank(CardRank.NINE)
                        .suit(CardSuit.SPADES)
                        .build(),
                new Card.Builder()
                        .rank(CardRank.ACE)
                        .suit(CardSuit.CLUBS)
                        .build()
		);

		Hand identifiedHand = handIdentifier.identifyHand(playerCards, communityCards);

		assertTrue(identifiedHand instanceof Flush);
		assertEquals("Flush (Q High)", identifiedHand.toString());
	}


	@Test
	public void identifyHand_straight_withFiveHighAceLow() {
		List<Card> playerCards = Arrays.asList(
//				new Card(CardRank.ACE, CardSuit.CLUBS),
//				new Card(CardRank.FOUR, CardSuit.DIAMONDS)
                new Card.Builder()
                        .rank(CardRank.ACE)
                        .suit(CardSuit.CLUBS)
                        .build(),
                new Card.Builder()
                        .rank(CardRank.FOUR)
                        .suit(CardSuit.DIAMONDS)
                        .build()
		);

		List<Card> communityCards = Arrays.asList(
//				new Card(CardRank.ACE, CardSuit.HEARTS),
//				new Card(CardRank.QUEEN, CardSuit.DIAMONDS),
//				new Card(CardRank.FIVE, CardSuit.SPADES),
//				new Card(CardRank.THREE, CardSuit.CLUBS),
//				new Card(CardRank.TWO, CardSuit.HEARTS),
//				new Card(CardRank.ACE, CardSuit.HEARTS)
                new Card.Builder()
                        .rank(CardRank.ACE)
                        .suit(CardSuit.HEARTS)
                        .build(),
                new Card.Builder()
                        .rank(CardRank.QUEEN)
                        .suit(CardSuit.DIAMONDS)
                        .build(),
                new Card.Builder()
                        .rank(CardRank.FIVE)
                        .suit(CardSuit.SPADES)
                        .build(),
                new Card.Builder()
                        .rank(CardRank.THREE)
                        .suit(CardSuit.CLUBS)
                        .build(),
                new Card.Builder()
                        .rank(CardRank.TWO)
                        .suit(CardSuit.HEARTS)
                        .build(),
                new Card.Builder()
                        .rank(CardRank.ACE)
                        .suit(CardSuit.HEARTS)
                        .build()
		);

		Hand identifiedHand = handIdentifier.identifyHand(playerCards, communityCards);

		assertTrue(identifiedHand instanceof Straight);
		assertEquals("Straight (5 High)", identifiedHand.toString());
	}
	@Test
	public void identifyHand_straight_withAceHigh() {
		List<Card> playerCards = Arrays.asList(
//				new Card(CardRank.TEN, CardSuit.SPADES),
//				new Card(CardRank.KING, CardSuit.CLUBS)
                new Card.Builder()
                        .rank(CardRank.TEN)
                        .suit(CardSuit.SPADES)
                        .build(),
                new Card.Builder()
                        .rank(CardRank.KING)
                        .suit(CardSuit.CLUBS)
                        .build()
		);

		List<Card> communityCards = Arrays.asList(
//				new Card(CardRank.QUEEN, CardSuit.DIAMONDS),
//				new Card(CardRank.JACK, CardSuit.SPADES),
//				new Card(CardRank.TEN, CardSuit.SPADES),
//				new Card(CardRank.NINE, CardSuit.SPADES),
//				new Card(CardRank.ACE, CardSuit.CLUBS)
                new Card.Builder()
                        .rank(CardRank.QUEEN)
                        .suit(CardSuit.DIAMONDS)
                        .build(),
                new Card.Builder()
                        .rank(CardRank.JACK)
                        .suit(CardSuit.SPADES)
                        .build(),
                new Card.Builder()
                        .rank(CardRank.TEN)
                        .suit(CardSuit.SPADES)
                        .build(),
                new Card.Builder()
                        .rank(CardRank.NINE)
                        .suit(CardSuit.SPADES)
                        .build(),
                new Card.Builder()
                        .rank(CardRank.ACE)
                        .suit(CardSuit.CLUBS)
                        .build()
		);

		Hand identifiedHand = handIdentifier.identifyHand(playerCards, communityCards);

		assertTrue(identifiedHand instanceof Straight);
		assertEquals("Straight (A High)", identifiedHand.toString());
	}

	@Test
	public void identifyHand_straight_withKingHigh() {
		List<Card> playerCards = Arrays.asList(
//				new Card(CardRank.NINE, CardSuit.SPADES),
//				new Card(CardRank.KING, CardSuit.CLUBS)
                new Card.Builder()
                        .rank(CardRank.NINE)
                        .suit(CardSuit.SPADES)
                        .build(),
                new Card.Builder()
                        .rank(CardRank.KING)
                        .suit(CardSuit.CLUBS)
                        .build()
		);

		List<Card> communityCards = Arrays.asList(
//				new Card(CardRank.QUEEN, CardSuit.DIAMONDS),
//				new Card(CardRank.JACK, CardSuit.SPADES),
//				new Card(CardRank.TEN, CardSuit.SPADES),
//				new Card(CardRank.NINE, CardSuit.CLUBS),
//				new Card(CardRank.TWO, CardSuit.CLUBS)
                new Card.Builder()
                        .rank(CardRank.QUEEN)
                        .suit(CardSuit.DIAMONDS)
                        .build(),
                new Card.Builder()
                        .rank(CardRank.JACK)
                        .suit(CardSuit.SPADES)
                        .build(),
                new Card.Builder()
                        .rank(CardRank.TEN)
                        .suit(CardSuit.SPADES)
                        .build(),
                new Card.Builder()
                        .rank(CardRank.NINE)
                        .suit(CardSuit.CLUBS)
                        .build(),
                new Card.Builder()
                        .rank(CardRank.TWO)
                        .suit(CardSuit.CLUBS)
                        .build()
		);

		Hand identifiedHand = handIdentifier.identifyHand(playerCards, communityCards);

		assertTrue(identifiedHand instanceof Straight);
		assertEquals("Straight (K High)", identifiedHand.toString());
	}

	@Test
	public void identifyHand_threeOfAKind() {
		List<Card> playerCards = Arrays.asList(
//				new Card(CardRank.TWO, CardSuit.SPADES),
//				new Card(CardRank.TWO, CardSuit.CLUBS)
                new Card.Builder()
                        .rank(CardRank.TWO)
                        .suit(CardSuit.SPADES)
                        .build(),
                new Card.Builder()
                        .rank(CardRank.TWO)
                        .suit(CardSuit.CLUBS)
                        .build()
		);

		List<Card> communityCards = Arrays.asList(
//				new Card(CardRank.QUEEN, CardSuit.DIAMONDS),
//				new Card(CardRank.JACK, CardSuit.SPADES),
//				new Card(CardRank.TEN, CardSuit.SPADES),
//				new Card(CardRank.NINE, CardSuit.SPADES),
//				new Card(CardRank.TWO, CardSuit.DIAMONDS)
                new Card.Builder()
                        .rank(CardRank.QUEEN)
                        .suit(CardSuit.DIAMONDS)
                        .build(),
                new Card.Builder()
                        .rank(CardRank.JACK)
                        .suit(CardSuit.SPADES)
                        .build(),
                new Card.Builder()
                        .rank(CardRank.TEN)
                        .suit(CardSuit.SPADES)
                        .build(),
                new Card.Builder()
                        .rank(CardRank.NINE)
                        .suit(CardSuit.SPADES)
                        .build(),
                new Card.Builder()
                        .rank(CardRank.TWO)
                        .suit(CardSuit.DIAMONDS)
                        .build()
		);

		Hand identifiedHand = handIdentifier.identifyHand(playerCards, communityCards);

		assertTrue(identifiedHand instanceof ThreeOfAKind);
		assertEquals("Trips (2) - Q,J High", identifiedHand.toString());
	}

	@Test
	public void identifyHand_twoPair() {
		List<Card> playerCards = Arrays.asList(
//				new Card(CardRank.TWO, CardSuit.SPADES),
//				new Card(CardRank.TWO, CardSuit.CLUBS)
                new Card.Builder()
                        .rank(CardRank.TWO)
                        .suit(CardSuit.SPADES)
                        .build(),
                new Card.Builder()
                        .rank(CardRank.TWO)
                        .suit(CardSuit.CLUBS)
                        .build()
		);

		List<Card> communityCards = Arrays.asList(
//				new Card(CardRank.QUEEN, CardSuit.DIAMONDS),
//				new Card(CardRank.JACK, CardSuit.SPADES),
//				new Card(CardRank.TEN, CardSuit.SPADES),
//				new Card(CardRank.NINE, CardSuit.SPADES),
//				new Card(CardRank.NINE, CardSuit.DIAMONDS)
                new Card.Builder()
                        .rank(CardRank.QUEEN)
                        .suit(CardSuit.DIAMONDS)
                        .build(),
                new Card.Builder()
                        .rank(CardRank.JACK)
                        .suit(CardSuit.SPADES)
                        .build(),
                new Card.Builder()
                        .rank(CardRank.TEN)
                        .suit(CardSuit.SPADES)
                        .build(),
                new Card.Builder()
                        .rank(CardRank.NINE)
                        .suit(CardSuit.SPADES)
                        .build(),
                new Card.Builder()
                        .rank(CardRank.NINE)
                        .suit(CardSuit.DIAMONDS)
                        .build()
		);

		Hand identifiedHand = handIdentifier.identifyHand(playerCards, communityCards);

		assertTrue(identifiedHand instanceof TwoPair);
		assertEquals("Two Pair (9,2) - Q High", identifiedHand.toString());
	}

	@Test
	public void identifyHand_onePair() {
		List<Card> playerCards = Arrays.asList(
//				new Card(CardRank.TWO, CardSuit.SPADES),
//				new Card(CardRank.TWO, CardSuit.CLUBS)
                new Card.Builder()
                        .rank(CardRank.TWO)
                        .suit(CardSuit.SPADES)
                        .build(),
                new Card.Builder()
                        .rank(CardRank.TWO)
                        .suit(CardSuit.CLUBS)
                        .build()
		);

		List<Card> communityCards = Arrays.asList(
//				new Card(CardRank.QUEEN, CardSuit.DIAMONDS),
//				new Card(CardRank.JACK, CardSuit.SPADES),
//				new Card(CardRank.TEN, CardSuit.SPADES),
//				new Card(CardRank.THREE, CardSuit.SPADES),
//				new Card(CardRank.SIX, CardSuit.DIAMONDS)
                new Card.Builder()
                        .rank(CardRank.QUEEN)
                        .suit(CardSuit.DIAMONDS)
                        .build(),
                new Card.Builder()
                        .rank(CardRank.JACK)
                        .suit(CardSuit.SPADES)
                        .build(),
                new Card.Builder()
                        .rank(CardRank.TEN)
                        .suit(CardSuit.SPADES)
                        .build(),
                new Card.Builder()
                        .rank(CardRank.THREE)
                        .suit(CardSuit.SPADES)
                        .build(),
                new Card.Builder()
                        .rank(CardRank.SIX)
                        .suit(CardSuit.DIAMONDS)
                        .build()
		);

		Hand identifiedHand = handIdentifier.identifyHand(playerCards, communityCards);

		assertTrue(identifiedHand instanceof OnePair);
		assertEquals("One Pair (2) - Q,J,10 High", identifiedHand.toString());
	}

	@Test
	public void identifyHand_highCard() {
		List<Card> playerCards = Arrays.asList(
//				new Card(CardRank.ACE, CardSuit.SPADES),
//				new Card(CardRank.TWO, CardSuit.CLUBS)
                new Card.Builder()
                        .rank(CardRank.ACE)
                        .suit(CardSuit.SPADES)
                        .build(),
                new Card.Builder()
                        .rank(CardRank.TWO)
                        .suit(CardSuit.CLUBS)
                        .build()
		);

		List<Card> communityCards = Arrays.asList(
//				new Card(CardRank.QUEEN, CardSuit.DIAMONDS),
//				new Card(CardRank.JACK, CardSuit.SPADES),
//				new Card(CardRank.TEN, CardSuit.SPADES),
//				new Card(CardRank.THREE, CardSuit.SPADES),
//				new Card(CardRank.SIX, CardSuit.DIAMONDS)
                new Card.Builder()
                        .rank(CardRank.QUEEN)
                        .suit(CardSuit.DIAMONDS)
                        .build(),
                new Card.Builder()
                        .rank(CardRank.JACK)
                        .suit(CardSuit.SPADES)
                        .build(),
                new Card.Builder()
                        .rank(CardRank.TEN)
                        .suit(CardSuit.SPADES)
                        .build(),
                new Card.Builder()
                        .rank(CardRank.THREE)
                        .suit(CardSuit.SPADES)
                        .build(),
                new Card.Builder()
                        .rank(CardRank.SIX)
                        .suit(CardSuit.DIAMONDS)
                        .build()
		);

		Hand identifiedHand = handIdentifier.identifyHand(playerCards, communityCards);

		assertTrue(identifiedHand instanceof HighCard);
		assertEquals("A,Q,J,10,6", identifiedHand.toString());
	}
}