package oop.game.assets;

import oop.game.model.Pokemon;

import com.badlogic.gdx.graphics.Texture;

public class GameInfo {
	public enum GameState {
		MENU, MAIN;
	}
	private static GameState gameState;
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

	public static GameState getGameState() {
		return gameState;
	}

	public static void setGameState(GameState gameState) {
		GameInfo.gameState = gameState;
	}
}
