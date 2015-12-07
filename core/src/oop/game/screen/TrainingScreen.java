package oop.game.screen;

import oop.game.pokegochi.PokeGochi;
import oop.game.stage.TrainingStage;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;

public class TrainingScreen implements Screen {
	private PokeGochi game;
	private TrainingStage trainingStage;

	public TrainingScreen(PokeGochi game) {
		this.game = game;
	}

	@Override
	public void show() {
		trainingStage = new TrainingStage(game, game.getGameInfo());
		Gdx.input.setInputProcessor(trainingStage);
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		Gdx.gl.glClearColor(94 / 255f, 94 / 255f, 94 / 255f, 1);
		trainingStage.draw();
		trainingStage.act(delta);
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
