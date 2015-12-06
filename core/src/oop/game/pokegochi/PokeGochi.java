package oop.game.pokegochi;

import oop.game.assets.GameInfo;
import oop.game.assets.GameInfo.GameState;
import oop.game.screen.MenuScreen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;

public class PokeGochi extends Game {

	@Override
	public void create() {
		Gdx.input.setCatchBackKey(true);
		GameInfo.setGameState(GameState.MENU);
		setScreen(new MenuScreen(this));
	}
}
