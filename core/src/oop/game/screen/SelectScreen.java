package oop.game.screen;

import oop.game.pokegochi.PokeGochi;
import oop.game.stage.SelectStage;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class SelectScreen implements Screen {
	private PokeGochi pokeGochi;
	private Stage selectStage;

	public SelectScreen(PokeGochi pokeGochi) {
		this.pokeGochi = pokeGochi;
	}
	@Override
	public void show() {
		selectStage = new SelectStage(pokeGochi.getGameInfo());

	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		Gdx.gl.glClearColor(0, 0, 0, 1);
		selectStage.draw();
		selectStage.act();
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
