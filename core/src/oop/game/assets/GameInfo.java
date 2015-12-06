package oop.game.assets;

import oop.game.model.Pokemon;

import com.badlogic.gdx.graphics.Texture;

public class GameInfo {
	private static Pokemon searchedPokemonInfo;
	private static Texture searchedPokemonSprite;

	public static Pokemon getSearchedPokemonInfo() {
		return searchedPokemonInfo;
	}

	public static void setSearchedPokemonInfo(Pokemon searchedPokemonInfo) {
		GameInfo.searchedPokemonInfo = searchedPokemonInfo;
	}

	public static Texture getSearchedPokemonSprite() {
		return searchedPokemonSprite;
	}

	public static void setSearchedPokemonSprite(Texture searchedPokemonSprite) {
		GameInfo.searchedPokemonSprite = searchedPokemonSprite;
	}
}
