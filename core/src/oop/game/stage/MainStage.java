package oop.game.stage;

import oop.game.assets.Assets;
import oop.game.assets.GameInfo;
import oop.game.controller.PokemonRequestController;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Stack;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

public class MainStage extends Stage {
	private final String[] textButtonName = {"먹이주기", "수련하기", "놀아주기", "똥치우기"};
	private Stack frameTable;
	private Table fieldTable, buttonTable;
	private TextButton[] textButton;
	private PokemonRequestController pokemonRequestController;
	private Texture texture;
	private Label label;
	private GameInfo gameInfo;

	@Override
	public void act() {
		if (gameInfo.getSelectedPokemonInfo() != null) {
			label.setText(gameInfo.getSelectedPokemonInfo().getName());
		}
		if (gameInfo.getSelectedPokemonSprite() != null) {
			texture = gameInfo.getSelectedPokemonSprite();
			SpriteBatch batch = new SpriteBatch();
			batch.begin();
			batch.draw(texture, 190, 190, 350, 350);
			batch.end();
		}
	}

	public MainStage(GameInfo gameInfo) {
		this.gameInfo = gameInfo;
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
		buttonTable.center().bottom().padBottom(10);
	}

	private void makeField() {
		label = new Label("", Assets.skin);
		fieldTable.add(label);
		fieldTable.center().bottom().padBottom(150);
	}
}
