package oop.test.controller;

import static org.junit.Assert.*;
import oop.game.assets.GameInfo;
import oop.game.controller.PokemonRequestController;
import oop.game.controller.PokemonRequestController.MakeType;
import oop.test.test.GdxTestRunner;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(GdxTestRunner.class)
public class PokemonRequestControllerTest {
	private PokemonRequestController pkmRequestController;
	private GameInfo gameInfo;
	@Before
	public void setUp() {
		pkmRequestController = new PokemonRequestController(gameInfo);
	}

	@Test
	public void testRequestPokemonById() {
		for (int i = 0; i < 718; i++) {
			pkmRequestController.requestPokemonById(i, MakeType.JSON_DATA);
			assertEquals(pkmRequestController.getMakeType(), MakeType.JSON_DATA);
			assertEquals(pkmRequestController.getHttpRequest().getUrl(), "http://pokeapi.co/api/v1/pokemon/" + i);
		}
	}

	@Test
	public void testRequestSpriteImageById() {
		for (int i = 0; i < 718; i++) {
			pkmRequestController.requestSpriteImageById(i);
			assertEquals(pkmRequestController.getMakeType(), MakeType.SPRITE_IMAGE);
			assertEquals(pkmRequestController.getHttpRequest().getUrl(), "http://pokeapi.co/media/img/" + i + ".png");
		}
	}
}
