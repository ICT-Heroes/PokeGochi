package oop.game.stage;

import oop.game.assets.Assets;
import oop.game.assets.GameInfo;
import oop.game.controller.PokemonRequestController;
import oop.game.model.Pokemon;
import oop.game.pokegochi.PokeGochi;
import oop.game.screen.MainScreen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Stack;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class SelectStage extends Stage {
	private Stack frameTable;
	private Table startButtonTable, listTable, scrollTable;
	private TextButton[] pokemonButton;
	private TextButton startButton, randomSelectButton;
	private Texture texture;
	private GameInfo gameInfo;
	private ScrollPane buttonScroll;
	private PokemonRequestController pkmRequestController;
	private PokeGochi game;

	public SelectStage(PokeGochi game, GameInfo gameInfo) {
		this.game = game;
		pkmRequestController = new PokemonRequestController(gameInfo);
		this.gameInfo = gameInfo;
		pokemonButton = new TextButton[718];
		frameTable = new Stack();
		scrollTable = new Table();
		frameTable.setWidth(Gdx.graphics.getWidth());
		frameTable.setHeight(Gdx.graphics.getHeight());
		startButtonTable = new Table();
		listTable = new Table();

		makeStartButtonTable();
		makeListTable();
		this.addActor(frameTable);
	}
	public void act(float delta) {
		if (gameInfo.getSelectedPokemonSprite() != null) {
			texture = gameInfo.getSelectedPokemonSprite();
			SpriteBatch batch = new SpriteBatch();
			batch.begin();
			batch.draw(texture, 0, 100, 330, 330);
			batch.end();
		}
		frameTable.act(delta);
	}

	public void makeListTable() {
		for (int i = 0; i < 690; i++) {
			if (gameInfo.getPokedex().getPokemon().get(i) != null) {
				Pokemon pokemon = gameInfo.getPokedex().getPokemon().get(i);
				final String id = pokemon.getResource_uri().split("/")[3];
				String content = "No." + id + " " + pokemon.getName();
				pokemonButton[i] = new TextButton(content, Assets.skin, "red");
				pokemonButton[i].getLabel().setFontScale(0.7f);
				pokemonButton[i].addListener(new ClickListener() {
					public void clicked(InputEvent event, float x, float y) {
						pkmRequestController.requestSpriteImageById(Integer.parseInt(id));
						gameInfo.setSelectedPokemonId(Integer.parseInt(id));
					}
				});
				listTable.add(pokemonButton[i]).width(300).height(50);
				listTable.row();
			}
			listTable.left();
		}
		buttonScroll = new ScrollPane(listTable, Assets.skin, "red");
		buttonScroll.setForceScroll(false, true);
		buttonScroll.setFlickScroll(true);
		buttonScroll.setOverscroll(false, true);
		buttonScroll.setFadeScrollBars(false);
		scrollTable.add(buttonScroll).size(350, 500);
		scrollTable.right().bottom().padBottom(20).padRight(20);
		frameTable.add(scrollTable);
	}

	public void makeStartButtonTable() {
		randomSelectButton = new TextButton("랜덤선택", Assets.skin, "red");
		startButton = new TextButton("시작하기", Assets.skin, "red");
		startButton.addListener(new ClickListener() {
			public void clicked(InputEvent event, float x, float y) {
				if (gameInfo.getSelectedPokemonId() != 0) {
					game.setScreen(new MainScreen(game));
				}
			}
		});
		startButtonTable.add(randomSelectButton);
		startButtonTable.add(startButton);
		startButtonTable.left().bottom().padLeft(20).padBottom(20);
		frameTable.add(startButtonTable);
	}
}
