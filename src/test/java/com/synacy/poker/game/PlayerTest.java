package com.synacy.poker.game;

import com.synacy.poker.model.Player;
import org.junit.Test;

import static org.junit.Assert.*;

public class PlayerTest {

	@Test
	public void getName() {
		Player player = new Player("Name");

		assertEquals("Name", player.getName());
	}

}