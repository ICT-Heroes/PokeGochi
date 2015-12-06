package oop.test.controller;

import static org.junit.Assert.*;
import oop.game.controller.PokemonRequestController;
import oop.game.controller.PokemonRequestController.MakeType;
import oop.test.test.GdxTestRunner;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(GdxTestRunner.class)
public class PokemonRequestControllerTest {
	private PokemonRequestController pkmRequestController;
	@Before
	public void setUp() {
		pkmRequestController = new PokemonRequestController();
	}

	@Test
	public void testRequestPokemonById() {
		pkmRequestController.requestPokemonById(25);
		assertEquals(pkmRequestController.getMakeType(), MakeType.JSON_DATA);
		assertEquals(pkmRequestController.getHttpRequest().getUrl(), "http://pokeapi.co/api/v1/pokemon/25");
	}

	@Test
	public void requestSpriteImageById() {
		pkmRequestController.requestSpriteImageById(25);
		assertEquals(pkmRequestController.getMakeType(), MakeType.SPRITE_IMAGE);
		assertEquals(pkmRequestController.getHttpRequest().getUrl(), "http://pokeapi.co/media/img/25.png");
	}
}
