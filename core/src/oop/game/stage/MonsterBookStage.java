package oop.game.stage;

import java.util.Iterator;

import oop.game.assets.Assets;
import oop.game.assets.GameInfo;
import oop.game.controller.PokemonRequestController;
import oop.game.model.Pokemon;
import oop.game.model.Type;
import oop.game.pokegochi.PokeGochi;
import oop.game.screen.MainScreen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Stack;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class MonsterBookStage extends Stage {
	private PokeGochi game;
	private GameInfo gameInfo;
	private Stack frameTable;
	private Table bookTable, topTable;
	private Texture texture;
	private ImageButton escapeButton, leftArrowButton, rightArrowButton;
	private TextButton nameButton, evolutionButton, typeButton, heightButton, weightButton;
	private Vector2 spritePosition;
	private float angle;
	private int waitFlagCount;
	private PokemonRequestController pkmRequestController;
	private boolean waitFlag;

	public void act(float delta) {
		if (gameInfo.getSearchedPokemonSprite() != null) {
			moveImage();
			makeSpriteImage(spritePosition.x, spritePosition.y);
		}
		waitFlagCount++;
		if (waitFlagCount > 300) {
			waitFlag = true;
		}
		showBookTable();
	}

	private void moveImage() {
		angle += 0.04f;
		spritePosition.y = (float) (220 + 20 * Math.sin(angle));
	}

	public MonsterBookStage(PokeGochi game, GameInfo gameInfo) {
		this.game = game;
		this.gameInfo = gameInfo;
		this.pkmRequestController = new PokemonRequestController(gameInfo);
		waitFlag = true;
		waitFlagCount = 0;
		frameTable = new Stack();
		frameTable.setWidth(Gdx.graphics.getWidth() / 2);
		frameTable.setHeight(Gdx.graphics.getHeight());
		spritePosition = new Vector2(35, 220);
		if (gameInfo.getSearchedPokemonSprite() != null) {
			makeSpriteImage(spritePosition.x, spritePosition.y);
		}
		makeBookTable();
		showBookTable();

		this.addActor(frameTable);
	}

	private void makeBookTable() {

		escapeButton = new ImageButton(new TextureRegionDrawable(new TextureRegion(Assets.uiCross)),
				new TextureRegionDrawable(new TextureRegion(Assets.uiCrossDown)));
		nameButton = new TextButton("", Assets.skin, "yellow");
		typeButton = new TextButton("", Assets.skin, "yellow");
		heightButton = new TextButton("", Assets.skin, "yellow");
		weightButton = new TextButton("", Assets.skin, "yellow");
		evolutionButton = new TextButton("", Assets.skin, "yellow");
		leftArrowButton = new ImageButton(new TextureRegionDrawable(new TextureRegion(Assets.uiLeft)),
				new TextureRegionDrawable(new TextureRegion(Assets.uiLeftDown)));
		rightArrowButton = new ImageButton(new TextureRegionDrawable(new TextureRegion(Assets.uiRight)),
				new TextureRegionDrawable(new TextureRegion(Assets.uiRightDown)));

		topTable = new Table();
		topTable.left().top().padLeft(20).padTop(30);
		topTable.add(leftArrowButton).width(50);
		topTable.add(nameButton).width(250);
		topTable.add(rightArrowButton).width(50);

		bookTable = new Table();
		bookTable.left().top().padLeft(20).padTop(350);
		bookTable.add(typeButton).width(350);;
		bookTable.row();
		bookTable.add(heightButton).width(350);
		bookTable.row();
		bookTable.add(weightButton).width(350);
		bookTable.row();
		bookTable.add(evolutionButton).width(350).height(80);
		evolutionButton.getLabel().setWrap(true);
		bookTable.add(escapeButton);
		leftArrowButton.addListener(new ClickListener() {
			public void clicked(InputEvent event, float x, float y) {
				if (gameInfo.getSearchedPokemonInfo().getNational_id() > 1) {
					if (waitFlag) {
						pkmRequestController.searchPokemonById(gameInfo.getSearchedPokemonInfo().getNational_id() - 1);
						waitFlag = false;
					}

				}

			}
		});

		rightArrowButton.addListener(new ClickListener() {
			public void clicked(InputEvent event, float x, float y) {
				if (gameInfo.getSearchedPokemonInfo().getNational_id() < 718) {
					if (waitFlag) {
						pkmRequestController.searchPokemonById(gameInfo.getSearchedPokemonInfo().getNational_id() + 1);
						waitFlag = false;
					}
				}
			}
		});

		escapeButton.addListener(new ClickListener() {
			public void clicked(InputEvent event, float x, float y) {
				game.setScreen(new MainScreen(game));
			}
		});
		frameTable.add(bookTable);
		frameTable.add(topTable);
	}

	private void makeSpriteImage(float x, float y) {
		texture = gameInfo.getSearchedPokemonSprite();
		SpriteBatch batch = new SpriteBatch();
		batch.begin();
		batch.draw(texture, x, y, 300, 300);
		batch.end();
	}

	private void showBookTable() {
		Pokemon pokemon = gameInfo.getSearchedPokemonInfo();
		if (pokemon != null) {
			nameButton.getLabel().setFontScale(0.7f);
			typeButton.getLabel().setFontScale(0.7f);
			weightButton.getLabel().setFontScale(0.7f);
			heightButton.getLabel().setFontScale(0.7f);
			evolutionButton.getLabel().setFontScale(0.7f);

			String name = "No." + pokemon.getNational_id() + " " + pokemon.getName();
			nameButton.setText(name);
			String type = getType(pokemon);
			typeButton.setText(type);
			int height = Integer.parseInt(pokemon.getHeight());
			heightButton.setText("Height : " + (float) (Math.round(height * 100 * height)) / (1000 * height) + "m");
			int weight = Integer.parseInt(pokemon.getWeight());
			weightButton.setText("Weight : " + (float) (Math.round(weight * 10 * weight)) / (100 * weight) + "kg");
			if (!pokemon.getEvolutions().isEmpty()) {
				evolutionButton.setText("Evolution : " + pokemon.getEvolutions().get(0).getTo());
			}
		}
	}
	private String getType(Pokemon pokemon) {
		String type = "Type : ";
		for (Iterator<Type> pit = pokemon.getTypes().iterator(); pit.hasNext();) {
			type += pit.next().getName();
			if (pit.hasNext()) {
				type += ", ";
			}
		}
		return type;
	}
}
