package oop.test.assets;

import static org.junit.Assert.*;
import oop.game.assets.GameInfo;
import oop.test.test.GdxTestRunner;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(GdxTestRunner.class)
public class GameInfoTest {
	private GameInfo gameInfo;
	@Before
	public void setUp() {
		gameInfo = new GameInfo();
	}

	@Test
	public void testInitialIizer() throws InterruptedException {
		assertEquals(gameInfo.getPokemonList().length, 718);
		assertEquals(gameInfo.getPokemonSpriteList().length, 718);
	}
}
