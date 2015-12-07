package oop.game.stage;

import oop.game.assets.GameInfo;
import oop.game.pokegochi.PokeGochi;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Stack;

public class TrainingStage extends Stage {
	private PokeGochi game;
	private GameInfo gameInfo;
	private Stack frameTable;
	private Vector2 spritePosition;
	private Texture character, fightPokemon;
	private float angle;
	private int count;

	public void act(float delta) {
		if (gameInfo.getSelectedPokemonSprite() != null) {
			moveImage();
			makeFightImage(spritePosition.x, spritePosition.y);
		}
	}

	private void moveImage() {
		angle += 0.1f;
		if (angle > 3.14f)
			angle = 0;
		if (count < 150)
		spritePosition.x = (float) (300 + 30 * Math.sin(angle));
		count++;
	}

	public TrainingStage(PokeGochi game, GameInfo gameInfo) {
		this.game = game;
		this.gameInfo = gameInfo;
		angle = 0;
		count = 0;
		frameTable = new Stack();
		frameTable.setWidth(Gdx.graphics.getWidth() / 2);
		frameTable.setHeight(Gdx.graphics.getHeight());
		spritePosition = new Vector2(300, 300);
		makeFightImage(spritePosition.x, spritePosition.y);

		this.addActor(frameTable);
	}

	private void makeFightImage(float x, float y) {
		TextureRegion character = new TextureRegion(gameInfo.getSelectedPokemonSprite());
		TextureRegion fightPokemon = new TextureRegion(gameInfo.getSelectedPokemonSprite());
		SpriteBatch batch = new SpriteBatch();
		batch.begin();
		if(count < 150)
			batch.draw(character, 350 - x, y - 100, 300, 300);//, 300, 300, 1, 1, 90);
		else
			batch.draw(character,  100 - x, y - 90, 300, 300, 300, 300, 1, 1, 90);
		batch.draw(fightPokemon, x, y - 100, 300, 300);
		batch.end();
	}
}
