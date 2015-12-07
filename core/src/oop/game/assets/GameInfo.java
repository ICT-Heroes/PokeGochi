package oop.game.assets;

import oop.game.controller.PokemonRequestController;
import oop.game.model.Pokedex;
import oop.game.model.Pokemon;

import com.badlogic.gdx.graphics.Texture;

public class GameInfo {
	public enum GameState {
		MENU, MAIN;
	}
	private GameState gameState;
	private Pokemon searchedPokemonInfo;
	private Texture searchedPokemonSprite;
	private Pokemon[] pokemonList;
	private Texture[] pokemonSpriteList;
	private Pokedex pokedex;

	public GameInfo() {
		setPokemonList(new Pokemon[718]);
		setPokemonSpriteList(new Texture[718]);
		PokemonRequestController pkmRequestController = new PokemonRequestController(this);
		loadPokedex(pkmRequestController);

	}

	private void loadPokedex(PokemonRequestController pkmRequestController) {
		pkmRequestController.requestPokedex();

	}

	public void loadAllPokemon(PokemonRequestController pkmRequestController) {
		for (int i = 0; i < 15; i++) {
			pkmRequestController.requestPokemonById(i);
		}
	}

	public Pokemon getSearchedPokemonInfo() {
		return searchedPokemonInfo;
	}

	public void setSearchedPokemonInfo(Pokemon searchedPokemonInfo) {
		this.searchedPokemonInfo = searchedPokemonInfo;
	}

	public Texture getSearchedPokemonSprite() {
		return searchedPokemonSprite;
	}

	public void setSearchedPokemonSprite(Texture searchedPokemonSprite) {
		this.searchedPokemonSprite = searchedPokemonSprite;
	}

	public GameState getGameState() {
		return gameState;
	}

	public void setGameState(GameState gameState) {
		this.gameState = gameState;
	}
	public Pokemon[] getPokemonList() {
		return pokemonList;
	}
	public void setPokemonList(Pokemon[] pokemonList) {
		this.pokemonList = pokemonList;
	}
	public Texture[] getPokemonSpriteList() {
		return pokemonSpriteList;
	}
	public void setPokemonSpriteList(Texture[] pokemonSpriteList) {
		this.pokemonSpriteList = pokemonSpriteList;
	}

	public Pokedex getPokedex() {
		return pokedex;
	}

	public void setPokedex(Pokedex pokedex) {
		this.pokedex = pokedex;
	}

}
