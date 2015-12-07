package oop.game.assets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class Assets {
	public static Skin skin = new Skin(Gdx.files.internal("skin/uiskin.json"));
	public static Texture uiLeft = new Texture(Gdx.files.internal("skin/ui-left.png"));
	public static Texture uiLeftDown = new Texture(Gdx.files.internal("skin/ui-left-down.png"));
	public static Texture uiRight = new Texture(Gdx.files.internal("skin/ui-right.png"));
	public static Texture uiRightDown = new Texture(Gdx.files.internal("skin/ui-right-down.png"));
	public static Texture uiCross = new Texture(Gdx.files.internal("skin/ui-cross-button.png"));
	public static Texture uiCrossDown = new Texture(Gdx.files.internal("skin/ui-cross-button-down.png"));
}
