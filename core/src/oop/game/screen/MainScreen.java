package oop.game.screen;

import oop.game.pokegochi.PokeGochi;
import oop.game.stage.MainStage;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class MainScreen implements Screen {
	private PokeGochi game;
	private Stage mainStage;

	public MainScreen(PokeGochi game) {
		this.game = game;
	}

	@Override
	public void show() {
		mainStage = new MainStage(game, game.getGameInfo());
		Gdx.input.setInputProcessor(mainStage);
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		mainStage.draw();
		mainStage.act();

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
		mainStage.dispose();

	}

}
