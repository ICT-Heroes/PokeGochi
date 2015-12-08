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
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Stack;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class TrainingStage extends Stage {
	private PokeGochi game;
	private GameInfo gameInfo;
	private Stack trainFrameTable, bookFrameTable;
	private Table bookTable, topTable, buttonTable;
	private Texture texture;
	private Label winOrLoseLabel;
	private TextButton nameButton, enemyButton, winRateButton, typeButton, heightButton, weightButton;
	private Vector2 spritePosition;
	private TextureRegion character, fightPokemon;
	private TextButton textButton[];
	private int count, winrate, looserate, mAttack, mDefense, eAttack, eDefense, randomrate;
	private boolean fight, win, endGame;
	private float angle;
	private final String[] textButtonName = {"싸워보자", "도망가자"};
	private PokemonRequestController pkmRequestController;

	public void act(float delta) {
		if (gameInfo.getSelectedPokemonSprite() != null) {
			if (fight) {
				moveImage();
			}
			if (gameInfo.getFightPokemonSprite() != null) {
				makeFightImage(spritePosition.x, spritePosition.y);
			}
			showBookTable();
		}

		if (gameInfo.getFightPokemonSprite() != null) {
			texture = gameInfo.getFightPokemonSprite();
			SpriteBatch batch = new SpriteBatch();
			batch.begin();
			batch.draw(texture, 400, 200, 300, 300);
			batch.end();
		}
		if (endGame) {
			if (win) {
				winOrLoseLabel = new Label("You win!", Assets.skin);
				winOrLoseLabel.setScale(0.7f);
				Stack labelStack = new Stack();
				labelStack.setPosition(40, 100);
				labelStack.add(winOrLoseLabel);
				this.addActor(labelStack);
			} else {
				winOrLoseLabel = new Label("You Lose!", Assets.skin);
				winOrLoseLabel.setScale(0.7f);
				Stack labelStack = new Stack();
				labelStack.setPosition(40, 100);
				labelStack.add(winOrLoseLabel);
				this.addActor(labelStack);
			}
		}

	}

	private void moveImage() {
		angle += 0.1f;
		if (angle > 3.14f)
			angle = 0;
		if (count < 900)
			spritePosition.x = (float) (100 + 30 * Math.sin(angle));
		if (count < 970)
			count += 10;
		else
			textButton[1].setText("돌아가자");
	}

	public TrainingStage(final PokeGochi game, GameInfo gameInfo) {
		this.game = game;
		this.gameInfo = gameInfo;
		this.pkmRequestController = new PokemonRequestController(gameInfo);
		createFightPokemon();
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
		randomrate = (int) (Math.random() * 10);

		makeButtons();
		if (gameInfo.getFightPokemonSprite() != null) {
			makeFightImage(spritePosition.x, spritePosition.y);
		}

		makeSpriteImage(spritePosition.x, spritePosition.y);
		makeBookTable();
		showBookTable();

		this.addActor(trainFrameTable);
		this.addActor(bookFrameTable);
	}

	private void createFightPokemon() {
		pkmRequestController.requestRandomEnemy();
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
		character = new TextureRegion(gameInfo.getSelectedPokemonSprite());
		fightPokemon = new TextureRegion(gameInfo.getFightPokemonSprite());
		SpriteBatch batch = new SpriteBatch();
		batch.begin();
		if (count < 900) {
			batch.draw(fightPokemon, 150 - x, y - 100, 200, 200);
			batch.draw(character, x, y - 100, 200, 200);
		} // 1, 90);
		else {
			if (!win) { // loose
				batch.draw(fightPokemon, 150 - x, y - 100, 200, 200);
				batch.draw(character, x, y - 100, 200, 200, 200, 200, 1, 1, count - 900);
				endGame = true;
			} else { // win
				batch.draw(fightPokemon, 150 - x, y - 110, 200, 200, 200, 200, 1, 1, count - 900);
				batch.draw(character, x, y - 100, 200, 200);
				endGame = true;
			}
		}
		batch.end();
	}

	private void makeBookTable() {
		enemyButton = new TextButton("상대", Assets.skin, "red");
		nameButton = new TextButton("", Assets.skin, "red");
		typeButton = new TextButton("", Assets.skin, "red");
		heightButton = new TextButton("", Assets.skin, "red");
		weightButton = new TextButton("", Assets.skin, "red");
		winRateButton = new TextButton("", Assets.skin, "red");

		topTable = new Table();
		topTable.left().top().padLeft(20).padTop(30);
		topTable.add(enemyButton).width(100);
		topTable.add(nameButton).width(250);

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
		batch.draw(texture, x, y, 400, 200);
		batch.end();
	}

	private void showBookTable() {
		Pokemon pokemon = gameInfo.getFightPokemonInfo();
		if (pokemon != null) {
			enemyButton.getLabel().setFontScale(0.7f);
			nameButton.getLabel().setFontScale(0.7f);
			typeButton.getLabel().setFontScale(0.7f);
			weightButton.getLabel().setFontScale(0.7f);
			heightButton.getLabel().setFontScale(0.7f);
			winRateButton.getLabel().setFontScale(0.7f);

			String name = pokemon.getName();
			nameButton.setText(name);
			String type = getType(pokemon);
			typeButton.setText(type);
			int height = Integer.parseInt(pokemon.getHeight());
			heightButton.setText("Height : " + (float) (Math.round(height * 100 * height)) / (1000 * height) + "m");
			int weight = Integer.parseInt(pokemon.getWeight());
			weightButton.setText("Weight : " + (float) (Math.round(weight * 10 * weight)) / (100 * weight) + "kg");

			mAttack = gameInfo.getSelectedPokemonInfo().getAttack();
			mDefense = gameInfo.getSelectedPokemonInfo().getDefense();
			eAttack = gameInfo.getFightPokemonInfo().getAttack();
			eDefense = gameInfo.getFightPokemonInfo().getAttack();
			// ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
			// 내 포켓몬과 상대 포켓몬의 Type에 따른 weakness, no effect, ineffective,
			// supereffective값 필요//
			// ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////

			winrate = (mAttack - eDefense);
			looserate = (eAttack - mDefense);

			if (winrate >= looserate) {
				win = true;
				winRateButton.setText("Advantageous");
			} else {
				if (randomrate > 7)
					win = true;
				else
					win = false;
				winRateButton.setText("Disadvantageous");
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
