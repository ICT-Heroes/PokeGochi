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
	private Table fieldTable, buttonTable, poopTable, foodTable, meat1, meat2, meat3;
	private TextButton[] textButton;
	private PokemonRequestController pokemonRequestController;
	private Texture texture;
	private Image poopImage, foodImage, food1, food2, food3;
	private Label label;
	private GameInfo gameInfo;
	private PokeGochi game;
	private Vector2 spritePosition;
	private float angle;
	private int dirty, food;
	private boolean feed;

	@Override
	public void act() {
		if (gameInfo.getSelectedPokemonInfo() != null) {
			label.setText(gameInfo.getSelectedPokemonInfo().getName());
		}
		if (gameInfo.getSelectedPokemonSprite() != null) {
			if (dirty > 300) {
				// 일정 시간 지나면 dirt 생성
				poopImage = new Image(new Texture(Gdx.files.internal("poop.png")));
				poopImage.setScale(0.15f, 0.15f);
				poopTable.add(poopImage);
				poopTable.center().bottom().padLeft(400).padBottom(250);
				frameTable.add(poopTable);
			}
			else
				dirty++;
			
			if(feed) {
				food++;
				dirty--;
				
				if(food > 100) {
					meat3.clear();
					
					if(food>150) {
						meat2.clear();
						
						if(food>200) {
							meat1.clear();
						}
					}
				}
				
				if(food > 250) {
					foodTable.clear();
					feed = false;
					food = 0;
				}
			}
			
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
		food = 0;
		feed = false;
		pokemonRequestController = new PokemonRequestController(gameInfo);
		pokemonRequestController.requestSelectedPokemonById(gameInfo.getSelectedPokemonId());
		frameTable = new Stack();
		fieldTable = new Table();
		buttonTable = new Table();
		poopTable = new Table();
		foodTable = new Table();
		meat1 = new Table();
		meat2 = new Table();
		meat3 = new Table();
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
				feed = true;
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
		foodImage = new Image(new Texture(Gdx.files.internal("food_bowl.png")));
		food1 = new Image(new Texture(Gdx.files.internal("meatball.png")));
		food2 = new Image(new Texture(Gdx.files.internal("meatball.png")));
		food3 = new Image(new Texture(Gdx.files.internal("meatball.png")));
		foodImage.setScale(0.2f, 0.2f);
		food1.setScale(0.3f, 0.3f);
		food2.setScale(0.3f, 0.3f);
		food3.setScale(0.3f, 0.3f);
		foodTable.add(foodImage);
		foodTable.center().bottom().padLeft(200).padBottom(250);
		meat1.add(food1);
		meat1.center().bottom().padLeft(-70).padBottom(260);
		meat2.add(food2);
		meat2.center().bottom().padLeft(10).padBottom(260);
		meat3.add(food3);
		meat3.center().bottom().padLeft(-10).padBottom(275);
		frameTable.add(foodTable);
		frameTable.add(meat1);
		frameTable.add(meat2);
		frameTable.add(meat3);
	}

	private void cleanPokemon() {
		dirty = 0;
		poopTable.clear();
	}
}
