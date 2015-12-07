package oop.game.screen;

import oop.game.pokegochi.PokeGochi;
import oop.game.stage.FightStage;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;

public class FightScreen implements Screen {
	private PokeGochi game;
	private FightStage fightStage;

	public FightScreen(PokeGochi game) {
		this.game = game;
	}

	@Override
	public void show() {
		fightStage = new FightStage(game, game.getGameInfo());
		Gdx.input.setInputProcessor(fightStage);
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		Gdx.gl.glClearColor(94 / 255f, 94 / 255f, 94 / 255f, 1);
		fightStage.draw();
		fightStage.act(delta);
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
