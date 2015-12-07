package oop.game.stage;

import oop.game.assets.Assets;
import oop.game.assets.GameInfo;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Stack;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

public class SelectStage extends Stage {
	private Stack frameTable;
	private Table imageTable, listTable, scrollTable;
	private TextButton[] pokemonButton;
	private Texture texture;
	private GameInfo gameInfo;
	private ScrollPane buttonScroll;

	public SelectStage(GameInfo gameInfo) {
		this.gameInfo = gameInfo;
		pokemonButton = new TextButton[718];
		frameTable = new Stack();
		scrollTable = new Table();
		frameTable.setWidth(Gdx.graphics.getWidth());
		frameTable.setHeight(Gdx.graphics.getHeight());
		imageTable = new Table();
		listTable = new Table();

		makeImageTable();
		makeListTable();
		this.addActor(frameTable);
	}

	public void act(float delta) {
		if (gameInfo.getSearchedPokemonSprite() != null) {
			texture = gameInfo.getSearchedPokemonSprite();
			SpriteBatch batch = new SpriteBatch();
			batch.begin();
			batch.draw(texture, 190, 190, 350, 350);
			batch.end();
		}
		scrollTable.act(delta);
		frameTable.act(delta);
	}

	public void makeListTable() {
		for (int i = 0; i < 15; i++) {
			if (gameInfo.getPokemonList()[i] != null) {
				String content = "No." + gameInfo.getPokemonList()[i].getNational_id()
						+ gameInfo.getPokemonList()[i].getName();
				pokemonButton[i] = new TextButton(content, Assets.skin, "red");
				listTable.add(pokemonButton[i]).width(350).height(50);
				listTable.row();
			}
		}
		buttonScroll = new ScrollPane(listTable, Assets.skin);
		buttonScroll.setForceScroll(false, true);
		buttonScroll.setFlickScroll(true);
		buttonScroll.setOverscroll(false, true);
		scrollTable.add(buttonScroll).size(350, 580);
		frameTable.add(scrollTable);
	}

	public void makeImageTable() {

	}
}
