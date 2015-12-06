package oop.game.stage;

import oop.game.assets.Assets;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Stack;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class MenuStage extends Stage {
	private TextButton textButton[];
	private Table textButtonTable;
	private Stack frameTable;
	private Label label;
	private final String[] textButtonContent = {"새로하자", "이어하자", "누가만듦", "그만할래"};

	public MenuStage() {
		frameTable = new Stack();
		makeLogo();
		makeButtons();
	}

	private void makeLogo() {
		label = new Label("포켓고치", Assets.skin);
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
		this.addActor(frameTable);
		textButton[0].addListener(new ClickListener() {

		});
	}
}
