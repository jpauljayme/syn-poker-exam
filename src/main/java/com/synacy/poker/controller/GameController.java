package com.synacy.poker.controller;

import com.synacy.poker.model.Player;
import com.synacy.poker.model.card.BlankCard;
import com.synacy.poker.model.card.Card;
import com.synacy.poker.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Iterator;
import java.util.List;

@Controller
public class GameController {

	private final GameService gameService;

	@Autowired
	public GameController(GameService game) {
		this.gameService = game;
	}

	@GetMapping("/")
	public String index(Model model) {
		model.addAttribute("game", gameService);

		int i = 1;
		for (Player p : gameService.getPlayers()) {
			model.addAttribute("player" + i, p);
			i++;
		}

		Iterator<Card> communityCardIterator = gameService.getCommunityCards().iterator();
		for (int communityCardNumber = 1; communityCardNumber <= 5; communityCardNumber++) {
			model.addAttribute("communityCard" + communityCardNumber, fetchNextCommunityCard(communityCardIterator));
		}

		return "index";
	}

	private Card fetchNextCommunityCard(Iterator<Card> communityCardIterator) {
		if (communityCardIterator.hasNext()) {
			return communityCardIterator.next();
		} else {
			return new BlankCard.Builder().build();
		}
	}

	@GetMapping("/nextAction")
	public String nextAction() {
		if (gameService.hasEnded()) {
			gameService.startNewGame();
		} else {
			gameService.nextAction();
		}

		return "redirect:/";
	}

}
