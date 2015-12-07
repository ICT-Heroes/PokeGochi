package oop.game.controller;

import oop.game.model.Pokemon;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Json;

public class PokemonMakeController {
	@SuppressWarnings("unused")
	public static Pokemon makePokemon(String data) {
		Pokemon pokemon = new Pokemon();
		if (data.equals("")) {
			Gdx.app.log("PokemonMakeController", "Pokemon not found");
			return null;
		} else {
			pokemon = new Json().fromJson(Pokemon.class, data);
			return pokemon;
		}
	}
}
