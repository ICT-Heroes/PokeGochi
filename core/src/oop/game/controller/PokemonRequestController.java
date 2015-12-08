package oop.game.controller;

import java.util.Random;

import oop.game.assets.GameInfo;
import oop.game.model.Pokedex;
import oop.game.model.Pokemon;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Net;
import com.badlogic.gdx.Net.HttpRequest;
import com.badlogic.gdx.Net.HttpResponse;
import com.badlogic.gdx.Net.HttpResponseListener;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;

public class PokemonRequestController implements HttpResponseListener {
	public enum MakeType {
		POKEDEX, ENEMY_JSON_DATA, JSON_DATA, SPRITE_IMAGE, ENEMY_SPRITE_IMAGE;
	}
	public PokemonRequestController(GameInfo gameInfo) {
		this.gameInfo = gameInfo;
	}
	private GameInfo gameInfo;
	private MakeType makeType;
	private HttpRequest httpRequest;
	private int spriteNumber, enemySpriteNumber;

	public void requestPokemonById(int id) {
		makeType = MakeType.JSON_DATA;
		makeRequest("http://pokeapi.co/api/v1/pokemon/" + id);
	}

	public void requestRandomEnemy() {
		makeType = MakeType.ENEMY_JSON_DATA;
		Random random = new Random();
		random.setSeed(System.currentTimeMillis());
		int randomId = random.nextInt(718) + 1;
		gameInfo.setEnemyPokemonId(randomId);
		makeRequest("http://pokeapi.co/api/v1/pokemon/" + randomId);
	}

	public void requestEnemySpriteImageById(int id) {
		makeType = MakeType.ENEMY_SPRITE_IMAGE;
		enemySpriteNumber = id;
		makeRequest("http://pokeapi.co/media/img/" + id + ".png");
	}

	public void requestPokedex() {
		makeType = MakeType.POKEDEX;
		makeRequest("http://pokeapi.co/api/v1/pokedex/1");
	}

	public void requestSpriteImageById(int id) {
		makeType = MakeType.SPRITE_IMAGE;
		spriteNumber = id;
		makeRequest("http://pokeapi.co/media/img/" + spriteNumber + ".png");
	}

	public void makeRequest(String url) {
		String httpMethod = Net.HttpMethods.GET;
		httpRequest = new HttpRequest(httpMethod);
		httpRequest.setUrl(url);
		Gdx.net.sendHttpRequest(httpRequest, PokemonRequestController.this);
		Gdx.app.log("PokemonRequestController", httpRequest.getUrl());
	}

	@Override
	synchronized public void handleHttpResponse(HttpResponse httpResponse) {
		final String data;
		synchronized (makeType) {
			switch (makeType) {
				case ENEMY_JSON_DATA :
					data = httpResponse.getResultAsString();
					Gdx.app.postRunnable(new Runnable() {
						synchronized public void run() {
							if (!data.equals("")) {
								makeEnemyPokemon(data);
							}
						}
					});
					break;
				case JSON_DATA :
					data = httpResponse.getResultAsString();
					Gdx.app.postRunnable(new Runnable() {
						synchronized public void run() {
							if (!data.equals("")) {
								makePokemon(data);
							}
						}
					});
					break;
				case POKEDEX :
					data = httpResponse.getResultAsString();
					Gdx.app.postRunnable(new Runnable() {
						synchronized public void run() {
							if (!data.equals("")) {
								makePokemonList(data);
							}
						}
					});
					break;
				case ENEMY_SPRITE_IMAGE :
					final byte[] rawEnemyImageBytes = httpResponse.getResult();
					Gdx.app.postRunnable(new Runnable() {
						synchronized public void run() {
							if (rawEnemyImageBytes.length > 0) {
								makeEnemyPokemonSprite(rawEnemyImageBytes);
							}
						}
					});
					break;
				case SPRITE_IMAGE :
					final byte[] rawImageBytes = httpResponse.getResult();
					Gdx.app.postRunnable(new Runnable() {
						synchronized public void run() {
							if (rawImageBytes.length > 0) {
								makePokemonSprite(rawImageBytes);
							}
						}
					});
					break;
			}
		}

	}
	private void makeEnemyPokemonSprite(byte[] rawImageBytes) {
		Gdx.app.log("PokemonRequestController", "SpriteNumber : " + spriteNumber);
		Pixmap pixmap = new Pixmap(rawImageBytes, 0, rawImageBytes.length);
		if (gameInfo.getPokemonSpriteList()[spriteNumber] == null) {
			gameInfo.getPokemonSpriteList()[spriteNumber] = new Texture(pixmap);
			gameInfo.setFightPokemonSprite(new Texture(pixmap));
		}
	}

	private void makePokemonSprite(byte[] rawImageBytes) {
		Gdx.app.log("PokemonRequestController", "SpriteNumber : " + spriteNumber);
		Pixmap pixmap = new Pixmap(rawImageBytes, 0, rawImageBytes.length);
		if (gameInfo.getPokemonSpriteList()[spriteNumber] == null) {
			gameInfo.getPokemonSpriteList()[spriteNumber] = new Texture(pixmap);
			gameInfo.setSelectedPokemonSprite(new Texture(pixmap));
		}
	}

	private void makeEnemyPokemon(String data) {
		Pokemon pokemon = PokemonMakeController.makePokemon(data);
		if (pokemon != null) {
			gameInfo.setFightPokemonInfo(pokemon);
			Gdx.app.log("PokemonRequestController", "pokemon (" + pokemon.getNational_id() + ") is created");
		}
		requestEnemySpriteImageById(pokemon.getNational_id());
	}

	private void makePokemon(String data) {
		Pokemon pokemon = PokemonMakeController.makePokemon(data);
		if (pokemon != null) {
			gameInfo.getPokemonList()[pokemon.getNational_id()] = pokemon;
			gameInfo.setSelectedPokemonInfo(pokemon);
			Gdx.app.log("PokemonRequestController", "pokemon (" + pokemon.getNational_id() + ") is created");
		}
	}

	private void makePokemonList(String data) {

		Pokedex pokedex = PokemonMakeController.makePokemonList(data);
		gameInfo.setPokedex(pokedex);
	}

	private void makePokemonAndSprite(String data) {
		Pokemon pokemon = PokemonMakeController.makePokemon(data);
		gameInfo.setSelectedPokemonInfo(pokemon);
		requestSpriteImageById(pokemon.getNational_id());
	}

	@Override
	public void failed(Throwable t) {
		Gdx.app.log("PokemonRequestController", "request failed");

	}

	@Override
	public void cancelled() {
		// TODO Auto-generated method stub

	}

	public HttpRequest getHttpRequest() {
		return httpRequest;
	}

	public void setHttpRequest(HttpRequest httpRequest) {
		this.httpRequest = httpRequest;
	}

	public MakeType getMakeType() {
		return makeType;
	}

	public void setMakeType(MakeType makeType) {
		this.makeType = makeType;
	}

}
