package oop.game.screen;

import oop.game.pokegochi.PokeGochi;
import oop.game.stage.CreditStage;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class CreditScreen implements Screen {

	private Stage creditStage;
	private PokeGochi game;

	public CreditScreen(PokeGochi game) {
		this.game = game;
	}

	@Override
	public void show() {
		creditStage = new CreditStage(game, game.getGameInfo());
		Gdx.input.setInputProcessor(creditStage);

	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		Gdx.gl.glClearColor(94 / 255f, 94 / 255f, 94 / 255f, 1);
		creditStage.draw();
		creditStage.act();
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
