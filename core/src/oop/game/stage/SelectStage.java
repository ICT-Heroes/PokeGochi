package oop.game.stage;

import oop.game.assets.Assets;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class SelectStage extends Stage {
	private TextButton textButton[];
	private final String[] textButtonContent = {"새로하기", "이어하기", "도감보기", "종료하기"};
	public SelectStage() {
		makeButtons();
	}

	private void makeButtons() {
		textButton = new TextButton[4];
		for (int i = 0; i < textButton.length; i++) {
			textButton[i] = new TextButton(textButtonContent[i], Assets.skin, "red");
		}
		textButton[0].addListener(new ClickListener() {

		});
	}
}
