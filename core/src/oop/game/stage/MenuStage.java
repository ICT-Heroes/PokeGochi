package oop.game.stage;

import oop.game.assets.Assets;
import oop.game.assets.GameInfo;
import oop.game.pokegochi.PokeGochi;
import oop.game.screen.MainScreen;
import oop.game.screen.SelectScreen;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Stack;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class MenuStage extends Stage {
	private PokeGochi game;
	private TextButton textButton[];
	private Table textButtonTable;
	private Stack frameTable;
	private Label label;
	private GameInfo gameInfo;
	private final String[] textButtonContent = {"새로하자", "이어하자", "누가만듦", "그만할래"};

	public MenuStage(PokeGochi game, GameInfo gameInfo) {
		this.game = game;
		this.gameInfo = gameInfo;
		frameTable = new Stack();
		makeLogo();
		makeButtons();
		this.addActor(frameTable);
	}

	private void makeLogo() {
		label = new Label("PokeGochi!", Assets.skin);
		label.scaleBy(3.0f);
		Table labelTable = new Table();
		labelTable.add(label);
		labelTable.center().bottom().padLeft(580).padBottom(400);
		frameTable.add(labelTable);
	}

	private void makeButtons() {
		textButtonTable = new Table();
		textButton = new TextButton[4];
		for (int i = 0; i < textButton.length; i++) {
			textButton[i] = new TextButton(textButtonContent[i], Assets.skin, "red");
			textButtonTable.add(textButton[i]).width(170).height(50).padBottom(20);
			textButtonTable.row();
		}
		frameTable.add(textButtonTable);
		textButtonTable.padLeft(580).padBottom(200);

		textButton[0].addListener(new ClickListener() {
			public void clicked(InputEvent event, float x, float y) {
				if (gameInfo.getPokedex() != null) {
					game.setScreen(new SelectScreen(game));
				}
			}
		});

		textButton[1].addListener(new ClickListener() {
			public void clicked(InputEvent event, float x, float y) {
				if (gameInfo.getPokedex() != null) {
					if (game.getSaveController().isLoadable()) {
						game.getSaveController().load(game);
						game.setScreen(new MainScreen(game));
					}
				}
			}
		});
	}
}
