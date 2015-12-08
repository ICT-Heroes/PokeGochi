package oop.game.pokegochi;

import oop.game.assets.GameInfo;
import oop.game.assets.GameInfo.GameState;
import oop.game.controller.SaveController;
import oop.game.screen.MenuScreen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;

public class PokeGochi extends Game {
	private GameInfo gameInfo;
	private SaveController saveController;

	@Override
	public void create() {
		gameInfo = new GameInfo();
		saveController = new SaveController();
		Gdx.input.setCatchBackKey(true);
		gameInfo.setGameState(GameState.MENU);
		setScreen(new MenuScreen(this));
	}

	public GameInfo getGameInfo() {
		return gameInfo;
	}

	public void setGameInfo(GameInfo gameInfo) {
		this.gameInfo = gameInfo;
	}

	public SaveController getSaveController() {
		return saveController;
	}

	public void setSaveController(SaveController saveController) {
		this.saveController = saveController;
	}
}
