package oop.game.stage;

import oop.game.assets.Assets;
import oop.game.assets.GameInfo;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Stack;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

public class SelectStage {
	private Stack frameTable;
	private Table imageTable, listTable;
	private Label label;
	private Texture texture;

	public void act() {
		if (GameInfo.getSearchedPokemonInfo() != null) {
			label.setText(GameInfo.getSearchedPokemonInfo().getName());
		}
		if (GameInfo.getSearchedPokemonSprite() != null) {
			texture = GameInfo.getSearchedPokemonSprite();
			SpriteBatch batch = new SpriteBatch();
			batch.begin();
			batch.draw(texture, 190, 190, 350, 350);
			batch.end();
		}
	}
	public SelectStage() {
		frameTable = new Stack();
		imageTable = new Table();
		listTable = new Table();

		makeImageTable();
		makeListTable();
	}

	public void makeListTable() {
		label = new Label("", Assets.skin);
	}

	public void makeImageTable() {

	}
}
