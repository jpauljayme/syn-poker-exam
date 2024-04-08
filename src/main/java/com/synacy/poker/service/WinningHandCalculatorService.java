package com.synacy.poker.service;

import com.synacy.poker.model.hand.Hand;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * A service class used to calculate the winning hand.
 */
@Service
public class WinningHandCalculatorService {
	private Hand winningHand = null;

	/**
	 * @param playerHands
	 * @return The winning {@link Hand} from a list of player hands.
	 */
	public void calculateWinningHand(List<Hand> playerHands) {
			  winningHand = playerHands.stream()
					.max(Comparable::compareTo).get();
	}

	public Hand getWinningHand() {
		return winningHand;
	}

    public boolean isPlayerWinner(Hand playerHand) {
		return winningHand != null
				&& playerHand != null
				&& winningHand.compareTo(playerHand) == 0;
    }
}
