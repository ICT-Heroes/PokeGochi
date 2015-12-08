package oop.game.controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import oop.game.assets.GameInfo;
import oop.game.pokegochi.PokeGochi;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;

public class SaveController {
	private final static String SAVE_PATH = "save/";
	private Kryo kryoSerializer;
	public SaveController() {
		kryoSerializer = new Kryo();
		kryoSerializer.register(GameInfo.class);
	}

	public void save(GameInfo gameInfo) {

		FileHandle handle;
		handle = Gdx.files.local(SAVE_PATH + "save_data");
		Output output;
		try {
			Gdx.app.log("SaveController", "save - " + handle.file().getParentFile().getAbsolutePath());
			if (!handle.file().getParentFile().exists()) {
				handle.file().getParentFile().mkdirs();
				handle.file().createNewFile();
			}
			Gdx.app.log("SaveController", "저장 : " + handle.file().getAbsolutePath());
			output = new Output(new FileOutputStream(handle.file()));
			kryoSerializer.writeObject(output, gameInfo);
			output.flush();
			output.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Gdx.app.log("SaveController", "저장작업완료");
	}

	public boolean isLoadable() {
		return Gdx.files.local(SAVE_PATH + "save_data").exists();
	}

	public void load(PokeGochi game) {
		FileHandle handle = Gdx.files.local(SAVE_PATH + "save_data");
		Input input;
		try {
			input = new Input(new FileInputStream(handle.file()));
			GameInfo gameInfo = kryoSerializer.readObject(input, GameInfo.class);
			gameInfo.setSelectedPokemonSprite(null);
			gameInfo.setBeforeFullLoad(true);
			game.setGameInfo(gameInfo);
			input.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}
