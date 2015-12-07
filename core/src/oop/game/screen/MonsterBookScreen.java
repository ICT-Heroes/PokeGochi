package oop.game.screen;

import oop.game.pokegochi.PokeGochi;
import oop.game.stage.MonsterBookStage;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class MonsterBookScreen implements Screen {
	private PokeGochi game;
	private Stage monsterBookStage;

	public MonsterBookScreen(PokeGochi game) {
		this.game = game;
	}

	@Override
	public void show() {
		monsterBookStage = new MonsterBookStage(game, game.getGameInfo());
		Gdx.input.setInputProcessor(monsterBookStage);
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		Gdx.gl.glClearColor(94 / 255f, 94 / 255f, 94 / 255f, 1);
		monsterBookStage.draw();
		monsterBookStage.act();
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
