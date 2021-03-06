package oop.game.assets;

import oop.game.controller.PokemonRequestController;
import oop.game.controller.PokemonRequestController.MakeType;
import oop.game.model.Pokedex;
import oop.game.model.Pokemon;

import com.badlogic.gdx.graphics.Texture;

public class GameInfo {
	public enum GameState {
		MENU, MAIN;
	}
	private GameState gameState;
	private Pokemon selectedPokemonInfo, searchedPokemonInfo, fightPokemonInfo;
	private Texture selectedPokemonSprite, searchedPokemonSprite, fightPokemonSprite;
	private Pokemon[] pokemonList;
	private Texture[] pokemonSpriteList;
	private Pokedex pokedex;
	private int selectedPokemonId, enemyPokemonId, searchedPokemonId;
	private boolean beforeFullLoad;

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
			pkmRequestController.requestPokemonById(i, MakeType.JSON_DATA);
		}
	}

	public Pokemon getSelectedPokemonInfo() {
		return selectedPokemonInfo;
	}

	public void setSelectedPokemonInfo(Pokemon selectedPokemonInfo) {
		this.selectedPokemonInfo = selectedPokemonInfo;
	}

	public Texture getSelectedPokemonSprite() {
		return selectedPokemonSprite;
	}

	public void setSelectedPokemonSprite(Texture selectedPokemonSprite) {
		this.selectedPokemonSprite = selectedPokemonSprite;
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

	public int getSelectedPokemonId() {
		return selectedPokemonId;
	}

	public void setSelectedPokemonId(int selectedPokemonId) {
		this.selectedPokemonId = selectedPokemonId;
	}

	public Texture getFightPokemonSprite() {
		return fightPokemonSprite;
	}

	public void setFightPokemonSprite(Texture fightPokemonSprite) {
		this.fightPokemonSprite = fightPokemonSprite;
	}

	public Pokemon getFightPokemonInfo() {
		return fightPokemonInfo;
	}

	public void setFightPokemonInfo(Pokemon fightPokemonInfo) {
		this.fightPokemonInfo = fightPokemonInfo;
	}

	public int getEnemyPokemonId() {
		return enemyPokemonId;
	}

	public void setEnemyPokemonId(int enemyPokemonId) {
		this.enemyPokemonId = enemyPokemonId;
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

	public int getSearchedPokemonId() {
		return searchedPokemonId;
	}

	public void setSearchedPokemonId(int searchedPokemonId) {
		this.searchedPokemonId = searchedPokemonId;
	}

	public boolean isBeforeFullLoad() {
		return beforeFullLoad;
	}

	public void setBeforeFullLoad(boolean beforeFullLoad) {
		this.beforeFullLoad = beforeFullLoad;
	}

}
