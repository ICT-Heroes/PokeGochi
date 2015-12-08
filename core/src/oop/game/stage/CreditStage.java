package oop.game.stage;

import oop.game.assets.Assets;
import oop.game.assets.GameInfo;
import oop.game.pokegochi.PokeGochi;
import oop.game.screen.MenuScreen;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Stack;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class CreditStage extends Stage {
	private PokeGochi game;
	private Stack frameTable;
	private Table labelTable;
	private Label nameLabel, hoonLabel, yeonLabel;
	private ImageButton escapeButton;
	public CreditStage(PokeGochi game, GameInfo gameInfo) {
		this.game = game;
		frameTable = new Stack();
		labelTable = new Table();

		makeLabel();
	}

	private void makeLabel() {
		nameLabel = new Label("[ 어떤 잉여가 만들었을까? ]", Assets.skin);
		hoonLabel = new Label("김대훈 (중앙대학교 사회학과11)", Assets.skin);
		hoonLabel.setScale(0.7f);
		yeonLabel = new Label("김수연 (중앙대학교 컴퓨터공학부12)", Assets.skin);
		yeonLabel.setScale(0.7f);
		labelTable.add(nameLabel).padBottom(30);
		labelTable.row();
		labelTable.add(hoonLabel);
		labelTable.row();
		labelTable.add(yeonLabel).padBottom(50);
		labelTable.row();
		escapeButton = new ImageButton(new TextureRegionDrawable(new TextureRegion(Assets.uiCross)),
				new TextureRegionDrawable(new TextureRegion(Assets.uiCrossDown)));
		labelTable.add(escapeButton);

		escapeButton.addListener(new ClickListener() {
			public void clicked(InputEvent event, float x, float y) {
				game.setScreen(new MenuScreen(game));
			}
		});
		frameTable.add(labelTable);
		frameTable.setPosition(300, 300);
		this.addActor(frameTable);
	}
}
