package oop.game.stage;

import oop.game.assets.Assets;
import oop.game.assets.GameInfo;
import oop.game.controller.PokemonRequestController;
import oop.game.pokegochi.PokeGochi;
import oop.game.screen.MonsterBookScreen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Stack;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class MainStage extends Stage {
	private final String[] textButtonName = {"먹이주기", "수련하기", "도감보기", "똥치우기"};
	private Stack frameTable;
	private Table fieldTable, buttonTable;
	private TextButton[] textButton;
	private PokemonRequestController pokemonRequestController;
	private Texture texture;
	private Label label;
	private GameInfo gameInfo;
	private PokeGochi game;
	private Vector2 spritePosition;
	private float angle;

	@Override
	public void act() {

		if (gameInfo.getSelectedPokemonInfo() != null) {
			label.setText(gameInfo.getSelectedPokemonInfo().getName());
		}
		if (gameInfo.getSelectedPokemonSprite() != null) {
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
		spritePosition = new Vector2(190, 190);
		angle = 0;
		pokemonRequestController = new PokemonRequestController(gameInfo);
		pokemonRequestController.requestPokemonById(gameInfo.getSelectedPokemonId());
		frameTable = new Stack();
		fieldTable = new Table();
		buttonTable = new Table();
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

		textButton[2].addListener(new ClickListener() {
			public void clicked(InputEvent event, float x, float y) {
				game.setScreen(new MonsterBookScreen(game));
			}
		});
		buttonTable.center().bottom().padBottom(10);
	}
	private void makeField() {
		label = new Label("", Assets.skin);
		fieldTable.add(label);
		fieldTable.center().bottom().padBottom(150);
	}
}
