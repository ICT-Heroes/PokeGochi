package oop.game.screen;

import oop.game.pokegochi.PokeGochi;
import oop.game.stage.MenuStage;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class MenuScreen extends ScreenAdapter {
	private Stage menuStage;
	private PokeGochi game;

	public MenuScreen(PokeGochi game) {
		this.game = game;
	}

	@Override
	public void show() {
		menuStage = new MenuStage();
		Gdx.input.setInputProcessor(menuStage);
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		Gdx.gl.glClearColor(0, 0, 0, 1);
		menuStage.draw();
		menuStage.act();
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub

	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}
}
