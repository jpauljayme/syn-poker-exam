package com.synacy.poker.service;

import com.synacy.poker.model.Player;
import com.synacy.poker.model.card.Card;
import com.synacy.poker.model.hand.Hand;
import com.synacy.poker.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

@Service
public class PlayerService {

    private final PlayerRepository playerRepository;
    private List<Player> players;

    private final DeckService deckService;

    private static final int MAX_PLAYER_CARDS = 2;


    @Autowired
    public PlayerService(PlayerRepository playerRepository, DeckService deckService) {
        this.playerRepository = playerRepository;
        this.deckService = deckService;
    }

    @PostConstruct
    void initialize(){
        players = playerRepository.getPlayers();
    }

    public List<Player> getPlayers(){
        return playerRepository.getPlayers();
    }

    public void dealOneCardToEachPlayer() {
        players.forEach(player -> player.addToHand(deckService.removeCardFromTop()));
    }

    public void clearHandForEachPlayer() {
        players.forEach(Player::clearHand);
    }

    public void dealHands() {
        for (int i = 0; i < MAX_PLAYER_CARDS; i++) {
            players.forEach(player -> player.addToHand(deckService.removeCardFromTop()));
        }
    }
}
