package oop.game.stage;

import java.util.Iterator;

import oop.game.assets.Assets;
import oop.game.assets.GameInfo;
import oop.game.model.Pokemon;
import oop.game.model.Type;
import oop.game.pokegochi.PokeGochi;
import oop.game.screen.MainScreen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Stack;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class TrainingStage extends Stage {
	private PokeGochi game;
	private GameInfo gameInfo;
	private Stack trainFrameTable, bookFrameTable;
	private Table bookTable, topTable, buttonTable;
	private Texture texture;
	private ImageButton escapeButton, leftArrowButton, rightArrowButton;
	private TextButton nameButton, winRateButton, typeButton, heightButton, weightButton;
	private Vector2 spritePosition;
	private Texture character, fightPokemon;
	private TextButton textButton[];
	private Label winrateLabel;
	private int count, winrate;
	private final int fightNumber = 5;
	private boolean fight, win;
	private float angle;
	private final String[] textButtonName = {"싸워보자", "돌아갈래"};

	public void act(float delta) {
		if (gameInfo.getSelectedPokemonSprite() != null) {
			if (fight) {
				moveImage();
			}
			makeFightImage(spritePosition.x, spritePosition.y);
			showBookTable();
		}

	}

	private void moveImage() {
		angle += 0.1f;
		if (angle > 3.14f)
			angle = 0;
		if (count < 1500)
			spritePosition.x = (float) (100 + 30 * Math.sin(angle));
		if (count < 1570)
			count += 10;
	}

	public TrainingStage(final PokeGochi game, GameInfo gameInfo) {
		this.game = game;
		this.gameInfo = gameInfo;
		gameInfo.setFightPokemonId(fightNumber);
		trainFrameTable = new Stack();

		trainFrameTable.setWidth(Gdx.graphics.getWidth() / 2);
		trainFrameTable.setHeight(Gdx.graphics.getHeight());
		bookFrameTable = new Stack();
		bookFrameTable.setWidth(Gdx.graphics.getWidth() / 2);
		bookFrameTable.setHeight(Gdx.graphics.getHeight());
		bookFrameTable.setPosition(340, 0);

		spritePosition = new Vector2(0, 300);
		angle = 0;
		count = 0;
		makeButtons();
		makeFightImage(spritePosition.x, spritePosition.y);

		makeSpriteImage(spritePosition.x, spritePosition.y);
		makeBookTable();
		showBookTable();

		this.addActor(trainFrameTable);
		this.addActor(bookFrameTable);
	}

	private void makeButtons() {
		buttonTable = new Table();
		textButton = new TextButton[2];
		for (int i = 0; i < textButtonName.length; i++) {
			textButton[i] = new TextButton(textButtonName[i], Assets.skin, "red");
			textButton[i].setColor(Color.CORAL);
			buttonTable.add(textButton[i]).width(160).height(100).padRight(10);
		}
		textButton[0].addListener(new ClickListener() {
			public void clicked(InputEvent event, float x, float y) {
				fight = true;
			}
		});
		textButton[1].addListener(new ClickListener() {
			public void clicked(InputEvent event, float x, float y) {
				game.setScreen(new MainScreen(game));
			}
		});
		buttonTable.center().bottom().padBottom(10);
		trainFrameTable.add(buttonTable);
	}

	private void makeFightImage(float x, float y) {
		TextureRegion character = new TextureRegion(gameInfo.getSelectedPokemonSprite());
		TextureRegion fightPokemon = new TextureRegion(gameInfo.getSelectedPokemonSprite());
		SpriteBatch batch = new SpriteBatch();
		batch.begin();
		if (count < 1500) {
			batch.draw(character, 150 - x, y - 100, 200, 200);// , 300, 300, 1,
			batch.draw(fightPokemon, x, y - 100, 200, 200);
		} // 1, 90);
		else {
			if (!win) { // loose
				batch.draw(character, 150 - x, y - 110, 200, 200, 200, 200, 1, 1, count - 1500);
				batch.draw(fightPokemon, x, y - 100, 200, 200);
			} else { // win
				batch.draw(character, 150 - x, y - 100, 200, 200);
				batch.draw(fightPokemon, x, y - 100, 200, 200, 200, 200, 1, 1, count - 1500);
			}
		}
		batch.end();
	}

	private void makeBookTable() {
		escapeButton = new ImageButton(new TextureRegionDrawable(new TextureRegion(Assets.uiCross)),
				new TextureRegionDrawable(new TextureRegion(Assets.uiCrossDown)));
		nameButton = new TextButton("", Assets.skin, "yellow");
		typeButton = new TextButton("", Assets.skin, "yellow");
		heightButton = new TextButton("", Assets.skin, "yellow");
		weightButton = new TextButton("", Assets.skin, "yellow");
		winRateButton = new TextButton("", Assets.skin, "yellow");
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
		bookTable.add(winRateButton).width(350).height(80);
		winRateButton.getLabel().setWrap(true);
		bookFrameTable.add(bookTable);
		bookFrameTable.add(topTable);
	}

	private void makeSpriteImage(float x, float y) {
		texture = gameInfo.getSelectedPokemonSprite();
		SpriteBatch batch = new SpriteBatch();
		batch.begin();
		batch.draw(texture, x, y, 200, 200);
		batch.end();
	}

	private void showBookTable() {
		Pokemon pokemon = gameInfo.getSelectedPokemonInfo();
		if (pokemon != null) {
			nameButton.getLabel().setFontScale(0.7f);
			typeButton.getLabel().setFontScale(0.7f);
			weightButton.getLabel().setFontScale(0.7f);
			heightButton.getLabel().setFontScale(0.7f);
			winRateButton.getLabel().setFontScale(0.7f);

			String name = "No." + pokemon.getNational_id() + " " + pokemon.getName();
			nameButton.setText(name);
			String type = getType(pokemon);
			typeButton.setText(type);
			int height = Integer.parseInt(pokemon.getHeight());
			heightButton.setText("Height : " + (float) (Math.round(height * 100 * height)) / (1000 * height) + "m");
			int weight = Integer.parseInt(pokemon.getWeight());
			weightButton.setText("Weight : " + (float) (Math.round(weight * 10 * weight)) / (100 * weight) + "kg");

			winRateButton.setText("WinRate : " + winrate + "%");
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
