package com.synacy.poker.game;

import com.synacy.poker.model.card.Card;
import com.synacy.poker.model.card.CardRank;
import com.synacy.poker.model.card.CardSuit;
import com.synacy.poker.model.hand.Hand;
import com.synacy.poker.service.HandIdentifierService;
import com.synacy.poker.model.hand.types.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static com.synacy.poker.model.card.CardRank.*;
import static com.synacy.poker.model.card.CardSuit.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.assertj.core.api.Assertions.assertThat;
@SpringBootTest
@RunWith(SpringRunner.class)
public class HandIdentifierServiceTest {

	@Autowired
	private HandIdentifierService handIdentifierService;

	@Test
	public void identifyHand_royalFlush() {
		List<Card> playerCards = Arrays.asList(
                new Card.Builder()
                        .rank(ACE)
                        .suit(SPADES)
                        .build(),
                new Card.Builder()
                        .rank(KING)
                        .suit(SPADES)
                        .build()
		);

		List<Card> communityCards = Arrays.asList(
                new Card.Builder()
                        .rank(QUEEN)
                        .suit(SPADES)
                        .build(),
                new Card.Builder()
                        .rank(JACK)
                        .suit(SPADES)
                        .build(),
                new Card.Builder()
                        .rank(TEN)
                        .suit(SPADES)
                        .build(),
                new Card.Builder()
                        .rank(NINE)
                        .suit(SPADES)
                        .build(),
                new Card.Builder()
                        .rank(ACE)
                        .suit(CLUBS)
                        .build()
		);

		Hand identifiedHand = handIdentifierService.identifyHand(playerCards, communityCards);

		assertTrue(identifiedHand instanceof RoyalFlush);
		assertEquals("Royal Flush", identifiedHand.toString());
	}

	@Test
	public void identifyHand_straightFlush() {
		List<Card> playerCards = Arrays.asList(
                new Card.Builder()
                        .rank(QUEEN)
                        .suit(SPADES)
                        .build(),
                new Card.Builder()
                        .rank(JACK)
                        .suit(SPADES)
                        .build()
		);

		List<Card> communityCards = Arrays.asList(
                new Card.Builder()
                        .rank(TEN)
                        .suit(SPADES)
                        .build(),
                new Card.Builder()
                        .rank(NINE)
                        .suit(SPADES)
                        .build(),
                new Card.Builder()
                        .rank(EIGHT)
                        .suit(SPADES)
                        .build(),
                new Card.Builder()
                        .rank(TWO)
                        .suit(SPADES)
                        .build(),
                new Card.Builder()
                        .rank(ACE)
                        .suit(CLUBS)
                        .build()
		);

		Hand identifiedHand = handIdentifierService.identifyHand(playerCards, communityCards);

		assertTrue(identifiedHand instanceof StraightFlush);
		assertEquals("Straight Flush (Q High)", identifiedHand.toString());
	}

	@Test
	public void identifyHand_straightFlush_aceLow() {
		List<Card> playerCards = Arrays.asList(
                new Card.Builder()
                        .rank(FIVE)
                        .suit(SPADES)
                        .build(),
                new Card.Builder()
                        .rank(ACE)
                        .suit(SPADES)
                        .build()
		);

		List<Card> communityCards = Arrays.asList(
                new Card.Builder()
                        .rank(FOUR)
                        .suit(SPADES)
                        .build(),
                new Card.Builder()
                        .rank(NINE)
                        .suit(SPADES)
                        .build(),
                new Card.Builder()
                        .rank(THREE)
                        .suit(SPADES)
                        .build(),
                new Card.Builder()
                        .rank(TWO)
                        .suit(SPADES)
                        .build(),
                new Card.Builder()
                        .rank(ACE)
                        .suit(CLUBS)
                        .build()
		);

		Hand identifiedHand = handIdentifierService.identifyHand(playerCards, communityCards);

		assertTrue(identifiedHand instanceof StraightFlush);
		assertEquals("Straight Flush (5 High)", identifiedHand.toString());
	}

	@Test
	public void identifyHand_fourOfAKind() {
		List<Card> playerCards = Arrays.asList(
                new Card.Builder()
                        .rank(EIGHT)
                        .suit(SPADES)
                        .build(),
                new Card.Builder()
                        .rank(EIGHT)
                        .suit(CLUBS)
                        .build()
		);

		List<Card> communityCards = Arrays.asList(
                new Card.Builder()
                        .rank(EIGHT)
                        .suit(DIAMONDS)
                        .build(),
                new Card.Builder()
                        .rank(EIGHT)
                        .suit(HEARTS)
                        .build(),
                new Card.Builder()
                        .rank(SEVEN)
                        .suit(SPADES)
                        .build(),
                new Card.Builder()
                        .rank(SIX)
                        .suit(SPADES)
                        .build(),
                new Card.Builder()
                        .rank(TWO)
                        .suit(SPADES)
                        .build()
		);

		Hand identifiedHand = handIdentifierService.identifyHand(playerCards, communityCards);

		assertTrue(identifiedHand instanceof FourOfAKind);
		assertEquals("Quads (8) - 7 High", identifiedHand.toString());
	}

	@Test
	public void identifyHand_fullHouse() {
		List<Card> playerCards = Arrays.asList(
                new Card.Builder()
                        .rank(EIGHT)
                        .suit(SPADES)
                        .build(),
                new Card.Builder()
                        .rank(EIGHT)
                        .suit(DIAMONDS)
                        .build()
		);

		List<Card> communityCards = Arrays.asList(
                new Card.Builder()
                        .rank(SEVEN)
                        .suit(HEARTS)
                        .build(),
                new Card.Builder()
                        .rank(SEVEN)
                        .suit(SPADES)
                        .build(),
                new Card.Builder()
                        .rank(EIGHT)
                        .suit(CLUBS)
                        .build(),
                new Card.Builder()
                        .rank(NINE)
                        .suit(SPADES)
                        .build(),
                new Card.Builder()
                        .rank(ACE)
                        .suit(CLUBS)
                        .build()
		);

		Hand identifiedHand = handIdentifierService.identifyHand(playerCards, communityCards);

		assertTrue(identifiedHand instanceof FullHouse);
		assertEquals("Full House (8,7)", identifiedHand.toString());
	}

	@Test
	public void identifyHand_flush() {
		List<Card> playerCards = Arrays.asList(
                new Card.Builder()
                        .rank(SEVEN)
                        .suit(SPADES)
                        .build(),
                new Card.Builder()
                        .rank(TWO)
                        .suit(SPADES)
                        .build()
		);

		List<Card> communityCards = Arrays.asList(
                new Card.Builder()
                        .rank(QUEEN)
                        .suit(SPADES)
                        .build(),
                new Card.Builder()
                        .rank(JACK)
                        .suit(SPADES)
                        .build(),
                new Card.Builder()
                        .rank(TEN)
                        .suit(SPADES)
                        .build(),
                new Card.Builder()
                        .rank(NINE)
                        .suit(SPADES)
                        .build(),
                new Card.Builder()
                        .rank(ACE)
                        .suit(CLUBS)
                        .build()
		);

		Hand identifiedHand = handIdentifierService.identifyHand(playerCards, communityCards);

		assertTrue(identifiedHand instanceof Flush);
		assertEquals("Flush (Q High)", identifiedHand.toString());
	}


	@Test
	public void identifyHand_straight_withFiveHighAceLow() {
		List<Card> playerCards = Arrays.asList(
                new Card.Builder()
                        .rank(ACE)
                        .suit(CLUBS)
                        .build(),
                new Card.Builder()
                        .rank(FOUR)
                        .suit(DIAMONDS)
                        .build()
		);

		List<Card> communityCards = Arrays.asList(
                new Card.Builder()
                        .rank(ACE)
                        .suit(HEARTS)
                        .build(),
                new Card.Builder()
                        .rank(QUEEN)
                        .suit(DIAMONDS)
                        .build(),
                new Card.Builder()
                        .rank(FIVE)
                        .suit(SPADES)
                        .build(),
                new Card.Builder()
                        .rank(THREE)
                        .suit(CLUBS)
                        .build(),
                new Card.Builder()
                        .rank(TWO)
                        .suit(HEARTS)
                        .build(),
                new Card.Builder()
                        .rank(ACE)
                        .suit(HEARTS)
                        .build()
		);

		Hand identifiedHand = handIdentifierService.identifyHand(playerCards, communityCards);

		assertTrue(identifiedHand instanceof Straight);
		assertEquals("Straight (5 High)", identifiedHand.toString());
	}
	@Test
	public void identifyHand_straight_withAceHigh() {
		List<Card> playerCards = Arrays.asList(
                new Card.Builder()
                        .rank(TEN)
                        .suit(SPADES)
                        .build(),
                new Card.Builder()
                        .rank(KING)
                        .suit(CLUBS)
                        .build()
		);

		List<Card> communityCards = Arrays.asList(
                new Card.Builder()
                        .rank(QUEEN)
                        .suit(DIAMONDS)
                        .build(),
                new Card.Builder()
                        .rank(JACK)
                        .suit(SPADES)
                        .build(),
                new Card.Builder()
                        .rank(TEN)
                        .suit(SPADES)
                        .build(),
                new Card.Builder()
                        .rank(NINE)
                        .suit(SPADES)
                        .build(),
                new Card.Builder()
                        .rank(ACE)
                        .suit(CLUBS)
                        .build()
		);

		Hand identifiedHand = handIdentifierService.identifyHand(playerCards, communityCards);

		assertTrue(identifiedHand instanceof Straight);
		assertEquals("Straight (A High)", identifiedHand.toString());
	}

	@Test
	public void identifyHand_straight_withKingHigh() {
		List<Card> playerCards = Arrays.asList(
                new Card.Builder()
                        .rank(NINE)
                        .suit(SPADES)
                        .build(),
                new Card.Builder()
                        .rank(KING)
                        .suit(CLUBS)
                        .build()
		);

		List<Card> communityCards = Arrays.asList(
                new Card.Builder()
                        .rank(QUEEN)
                        .suit(DIAMONDS)
                        .build(),
                new Card.Builder()
                        .rank(JACK)
                        .suit(SPADES)
                        .build(),
                new Card.Builder()
                        .rank(TEN)
                        .suit(SPADES)
                        .build(),
                new Card.Builder()
                        .rank(NINE)
                        .suit(CLUBS)
                        .build(),
                new Card.Builder()
                        .rank(TWO)
                        .suit(CLUBS)
                        .build()
		);

		Hand identifiedHand = handIdentifierService.identifyHand(playerCards, communityCards);

		assertTrue(identifiedHand instanceof Straight);
		assertEquals("Straight (K High)", identifiedHand.toString());
	}

	@Test
	public void identifyHand_threeOfAKind() {
		List<Card> playerCards = Arrays.asList(
                new Card.Builder()
                        .rank(TWO)
                        .suit(SPADES)
                        .build(),
                new Card.Builder()
                        .rank(TWO)
                        .suit(CLUBS)
                        .build()
		);

		List<Card> communityCards = Arrays.asList(
                new Card.Builder()
                        .rank(QUEEN)
                        .suit(DIAMONDS)
                        .build(),
                new Card.Builder()
                        .rank(JACK)
                        .suit(SPADES)
                        .build(),
                new Card.Builder()
                        .rank(TEN)
                        .suit(SPADES)
                        .build(),
                new Card.Builder()
                        .rank(NINE)
                        .suit(SPADES)
                        .build(),
                new Card.Builder()
                        .rank(TWO)
                        .suit(DIAMONDS)
                        .build()
		);

		Hand identifiedHand = handIdentifierService.identifyHand(playerCards, communityCards);

		assertTrue(identifiedHand instanceof ThreeOfAKind);
		assertEquals("Trips (2) - Q,J High", identifiedHand.toString());
	}

	@Test
	public void identifyHand_twoPair() {
		List<Card> playerCards = Arrays.asList(
                new Card.Builder()
                        .rank(TWO)
                        .suit(SPADES)
                        .build(),
                new Card.Builder()
                        .rank(TWO)
                        .suit(CLUBS)
                        .build()
		);

		List<Card> communityCards = Arrays.asList(
                new Card.Builder()
                        .rank(QUEEN)
                        .suit(DIAMONDS)
                        .build(),
                new Card.Builder()
                        .rank(JACK)
                        .suit(SPADES)
                        .build(),
                new Card.Builder()
                        .rank(TEN)
                        .suit(SPADES)
                        .build(),
                new Card.Builder()
                        .rank(NINE)
                        .suit(SPADES)
                        .build(),
                new Card.Builder()
                        .rank(NINE)
                        .suit(DIAMONDS)
                        .build()
		);

		Hand identifiedHand = handIdentifierService.identifyHand(playerCards, communityCards);

		assertTrue(identifiedHand instanceof TwoPair);
		assertEquals("Two Pair (9,2) - Q High", identifiedHand.toString());
	}

	@Test
	public void identifyHand_onePair() {
		List<Card> playerCards = Arrays.asList(
                new Card.Builder()
                        .rank(TWO)
                        .suit(SPADES)
                        .build(),
                new Card.Builder()
                        .rank(TWO)
                        .suit(CLUBS)
                        .build()
		);

		List<Card> communityCards = Arrays.asList(
                new Card.Builder()
                        .rank(QUEEN)
                        .suit(DIAMONDS)
                        .build(),
                new Card.Builder()
                        .rank(JACK)
                        .suit(SPADES)
                        .build(),
                new Card.Builder()
                        .rank(TEN)
                        .suit(SPADES)
                        .build(),
                new Card.Builder()
                        .rank(THREE)
                        .suit(SPADES)
                        .build(),
                new Card.Builder()
                        .rank(SIX)
                        .suit(DIAMONDS)
                        .build()
		);

		Hand identifiedHand = handIdentifierService.identifyHand(playerCards, communityCards);

		assertTrue(identifiedHand instanceof OnePair);
		assertEquals("One Pair (2) - Q,J,10 High", identifiedHand.toString());
	}

	@Test
	public void identifyHand_highCard() {
		List<Card> playerCards = Arrays.asList(
                new Card.Builder()
                        .rank(ACE)
                        .suit(SPADES)
                        .build(),
                new Card.Builder()
                        .rank(TWO)
                        .suit(CLUBS)
                        .build()
		);

		List<Card> communityCards = Arrays.asList(
                new Card.Builder()
                        .rank(QUEEN)
                        .suit(DIAMONDS)
                        .build(),
                new Card.Builder()
                        .rank(JACK)
                        .suit(SPADES)
                        .build(),
                new Card.Builder()
                        .rank(TEN)
                        .suit(SPADES)
                        .build(),
                new Card.Builder()
                        .rank(THREE)
                        .suit(SPADES)
                        .build(),
                new Card.Builder()
                        .rank(SIX)
                        .suit(DIAMONDS)
                        .build()
		);

		Hand identifiedHand = handIdentifierService.identifyHand(playerCards, communityCards);

		assertTrue(identifiedHand instanceof HighCard);
		assertEquals("A,Q,J,10,6", identifiedHand.toString());
	}

	@Test
	public void identifyHand_highCard_2() {
		List<Card> playerCards = Arrays.asList(
				new Card.Builder()
						.rank(EIGHT)
						.suit(CLUBS)
						.build(),
				new Card.Builder()
						.rank(KING)
						.suit(DIAMONDS)
						.build()
		);

		List<Card> communityCards = Arrays.asList(
				new Card.Builder()
						.rank(NINE)
						.suit(HEARTS)
						.build(),
				new Card.Builder()
						.rank(FIVE)
						.suit(HEARTS)
						.build(),
				new Card.Builder()
						.rank(TEN)
						.suit(SPADES)
						.build(),
				new Card.Builder()
						.rank(QUEEN)
						.suit(DIAMONDS)
						.build(),
				new Card.Builder()
						.rank(FOUR)
						.suit(SPADES)
						.build()
		);

		Hand identifiedHand = handIdentifierService.identifyHand(playerCards, communityCards);

		assertTrue(identifiedHand instanceof HighCard);
		assertEquals("K,Q,10,9,8", identifiedHand.toString());
	}


	@Test
	public void identifyHand_threeOfAKind_twoPlayers() {
		List<Card> playerTwoCards = Arrays.asList(
				new Card.Builder()
						.rank(SEVEN)
						.suit(SPADES)
						.build(),
				new Card.Builder()
						.rank(SIX)
						.suit(DIAMONDS)
						.build()
		);

		List<Card> playerThreeCards = Arrays.asList(
				new Card.Builder()
						.rank(SEVEN)
						.suit(DIAMONDS)
						.build(),
				new Card.Builder()
						.rank(TEN)
						.suit(SPADES)
						.build()
		);

		List<Card> communityCards = Arrays.asList(
				new Card.Builder()
						.rank(SEVEN)
						.suit(HEARTS)
						.build(),
				new Card.Builder()
						.rank(EIGHT)
						.suit(CLUBS)
						.build(),
				new Card.Builder()
						.rank(THREE)
						.suit(DIAMONDS)
						.build(),
				new Card.Builder()
						.rank(SEVEN)
						.suit(CLUBS)
						.build(),
				new Card.Builder()
						.rank(TWO)
						.suit(CLUBS)
						.build()
		);

		Hand playerTwoHand = handIdentifierService.identifyHand(playerTwoCards, communityCards);
		Hand playerThreeHand = handIdentifierService.identifyHand(playerThreeCards, communityCards);

		assertThat(playerTwoHand)
				.isInstanceOf(ThreeOfAKind.class);
		assertThat(playerThreeHand)
				.isInstanceOf(ThreeOfAKind.class);

		assertThat(playerThreeHand)
				.isGreaterThan(playerTwoHand);
	}
}