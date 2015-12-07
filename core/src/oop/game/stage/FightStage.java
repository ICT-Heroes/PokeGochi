package oop.game.stage;

import oop.game.assets.Assets;
import oop.game.assets.GameInfo;
import oop.game.controller.PokemonRequestController;
import oop.game.pokegochi.PokeGochi;
import oop.game.screen.FightScreen;
import oop.game.screen.MainScreen;
import oop.game.screen.MonsterBookScreen;
import oop.game.screen.TrainingScreen;

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
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class FightStage extends Stage {
	private PokeGochi game;
	private GameInfo gameInfo;
	private Label winrateLabel;
	private Stack frameTable;
	private Table buttonTable;
	private TextButton textButton[];
	private Vector2 spritePosition;
	private Texture character, fightPokemon;
	private float angle;
	private int count, fightid, winrate;
	private boolean fight, win;
	private final String[] textButtonName = {"싸워보자", "그만둘래"};
	private PokemonRequestController pokemonRequestController;

	public void act(float delta) {
		if (gameInfo.getSelectedPokemonSprite() != null) {
			if(fight) {
				moveImage();		
			}
			makeFightImage(spritePosition.x, spritePosition.y);		
		}
	}

	private void moveImage() {
		angle += 0.1f;
		if (angle > 3.14f)
			angle = 0;
		if (count < 900)
			spritePosition.x = (float) (300 + 30 * Math.sin(angle));
		if (count < 970)
			count += 10;
	}

	public FightStage(final PokeGochi game, GameInfo gameInfo) {
		this.game = game;
		this.gameInfo = gameInfo;
		angle = 0;
		count = 0;
		// fightid randomly generate
		fightid = 5;
		fight = false;
		win = false;
		// 싸울 포켓몬의 공격력 받아와서 winrate 계산
		winrate = 0;
		frameTable = new Stack();
		buttonTable = new Table();
		frameTable.setWidth(Gdx.graphics.getWidth() / 2);
		frameTable.setHeight(Gdx.graphics.getHeight());
		spritePosition = new Vector2(300, 300);
		//makeFightImage(spritePosition.x, spritePosition.y);
		gameInfo.setFightPokemonId(fightid);
		//gameInfo.setFightPokemonSprite(pokemonRequestController.requestSpriteImageById(fightid));

		winrateLabel = new Label("승률 : "+ winrate + "%", Assets.skin);
		winrateLabel.scaleBy(3.0f);
		Table labelTable = new Table();
		labelTable.add(winrateLabel);
		labelTable.center().bottom().padLeft(580).padBottom(40);
		frameTable.add(labelTable);
		
		makeButtons();
		frameTable.add(buttonTable);
		this.addActor(frameTable);
	}

	private void makeFightImage(float x, float y) {
		TextureRegion character = new TextureRegion(gameInfo.getSelectedPokemonSprite());
		TextureRegion fightPokemon = new TextureRegion(gameInfo.getSelectedPokemonSprite());
		//TextureRegion fightPokemon = new TextureRegion(gameInfo.getFightPokemonSprite());
		SpriteBatch batch = new SpriteBatch();
		batch.begin();
		if(count < 900) { // 승부 중
			batch.draw(character, 350 - x, y - 100, 300, 300);
			batch.draw(fightPokemon, x, y - 100, 300, 300);
		}
		else { 
			if(!win) { // loose
				batch.draw(character,  100 - x, y - 110, 300, 300, 300, 300, 1, 1, count-900);
				batch.draw(fightPokemon, x, y - 100, 300, 300);
			}
			else { // win
				batch.draw(character, 350 - x, y - 100, 300, 300);
				batch.draw(fightPokemon, x, y-100, 300, 300, 300, 300, 1, 1, count - 900);
			}
		}
		batch.end();
	}
	
	private void makeButtons() {
		textButton = new TextButton[2];
		for (int i = 0; i < textButtonName.length; i++) {
			textButton[i] = new TextButton(textButtonName[i], Assets.skin, "red");
			textButton[i].setColor(Color.CORAL);
			buttonTable.add(textButton[i]).width(160).height(100).padRight(10);
		}
		textButton[0].addListener(new ClickListener() {
			public void clicked(InputEvent event, float x, float y){
				fight = true;
			}
		});
		textButton[1].addListener(new ClickListener() {
			public void clicked(InputEvent event, float x, float y) {
				game.setScreen(new MainScreen(game));
			}
		});
		buttonTable.center().bottom().padBottom(10);
	}
}
