package oop.game.pokegochi;

import oop.game.stage.MainStage;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class PokeGochi extends ApplicationAdapter {
	private Stage mainStage;

	@Override
	public void create() {
		mainStage = new MainStage();
		Gdx.input.setInputProcessor(mainStage);
	}

	@Override
	public void render() {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		Gdx.gl.glViewport(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		mainStage.getViewport().update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), true);
		mainStage.draw();
		mainStage.act();
	}
}
