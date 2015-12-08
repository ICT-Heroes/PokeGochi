package oop.game.stage;

import oop.game.assets.Assets;
import oop.game.assets.GameInfo;
import oop.game.controller.PokemonRequestController;
import oop.game.pokegochi.PokeGochi;
import oop.game.screen.MonsterBookScreen;
import oop.game.screen.TrainingScreen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Stack;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class MainStage extends Stage {
	private final String[] textButtonName = {"먹이주기", "수련하기", "도감보기", "똥치우기"};
	private Stack frameTable;
	private Table fieldTable, buttonTable, poopTable;
	private TextButton[] textButton;
	private PokemonRequestController pokemonRequestController;
	private Texture texture;
	private Image poopImage;
	private Label label, tmplabel;
	private GameInfo gameInfo;
	private PokeGochi game;
	private Vector2 spritePosition;
	private float angle;
	private int dirty;

	@Override
	public void act() {
		if (gameInfo.getSelectedPokemonInfo() != null) {
			label.setText(gameInfo.getSelectedPokemonInfo().getName());
		}
		if (gameInfo.getSelectedPokemonSprite() != null) {
			if (dirty > 300) {
				// 일정 시간 지나면 dirt 생성
				poopImage = new Image(new Texture(Gdx.files.internal("poop.png")));
				poopTable.add(poopImage);
				poopTable.center().bottom().padLeft(580).padBottom(400);
				frameTable.add(poopTable);
				dirty = 0;
			}
			dirty++;
			moveImage();
			drawSprite(spritePosition.x, spritePosition.y);
		}
	}

	private void moveImage() {
		angle += 0.05f;
		spritePosition.x = (float) (190 + 30 * Math.sin(angle));
	}

	private void drawSprite(float x, float y) {
		texture = gameInfo.getSelectedPokemonSprite();
		SpriteBatch batch = new SpriteBatch();
		batch.begin();
		batch.draw(texture, x, y, 350, 350);
		batch.end();
	}

	public MainStage(PokeGochi game, GameInfo gameInfo) {
		this.gameInfo = gameInfo;
		this.game = game;
		game.getSaveController().save(gameInfo);
		spritePosition = new Vector2(190, 190);
		angle = 0;
		dirty = 0;
		pokemonRequestController = new PokemonRequestController(gameInfo);
		pokemonRequestController.requestSelectedPokemonById(gameInfo.getSelectedPokemonId());
		frameTable = new Stack();
		fieldTable = new Table();
		buttonTable = new Table();
		poopTable = new Table();
		frameTable = new Stack();
		frameTable.setWidth(Gdx.graphics.getWidth());
		frameTable.setHeight(Gdx.graphics.getHeight());

		makeField();
		makeButtons();
		frameTable.add(fieldTable);
		frameTable.add(buttonTable);

		this.addActor(frameTable);
	}

	private void makeButtons() {
		textButton = new TextButton[4];
		for (int i = 0; i < textButtonName.length; i++) {
			textButton[i] = new TextButton(textButtonName[i], Assets.skin, "red");
			textButton[i].setColor(Color.CORAL);
			buttonTable.add(textButton[i]).width(160).height(100).padRight(10);
		}
		textButton[0].addListener(new ClickListener() {
			public void clicked(InputEvent event, float x, float y) {
				feedPokemon();
			}
		});
		textButton[1].addListener(new ClickListener() {
			public void clicked(InputEvent event, float x, float y) {
				game.setScreen(new TrainingScreen(game));
			}
		});
		textButton[2].addListener(new ClickListener() {
			public void clicked(InputEvent event, float x, float y) {
				game.setScreen(new MonsterBookScreen(game));
			}
		});
		textButton[3].addListener(new ClickListener() {
			public void clicked(InputEvent event, float x, float y) {
				if (dirty > 300)
					cleanPokemon();
			}
		});
		buttonTable.center().bottom().padBottom(10);
	}
	private void makeField() {
		label = new Label("", Assets.skin);
		fieldTable.add(label);
		fieldTable.center().bottom().padBottom(150);
	}

	private void feedPokemon() {

	}

	private void cleanPokemon() {

	}
}
