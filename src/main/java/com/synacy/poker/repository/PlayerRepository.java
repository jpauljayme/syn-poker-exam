package com.synacy.poker.repository;

import com.synacy.poker.model.Player;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class PlayerRepository {
    private final List<Player> players = new ArrayList<>();

    public PlayerRepository() {
        players.add(new Player("Alex"));
        players.add(new Player("Bob"));
        players.add(new Player("Jane"));
    }

    public List<Player> getPlayers() {
        return players;
    }
}
