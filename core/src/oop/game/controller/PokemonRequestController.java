package oop.game.controller;

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
		POKEDEX, JSON_DATA, SPRITE_IMAGE;
	}
	public PokemonRequestController(GameInfo gameInfo) {
		this.gameInfo = gameInfo;
	}
	private GameInfo gameInfo;
	private MakeType makeType;
	private HttpRequest httpRequest;
	private int spriteNumber;

	public void requestPokemonById(int id) {
		makeType = MakeType.JSON_DATA;
		makeRequest("http://pokeapi.co/api/v1/pokemon/" + id);
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
	private void makePokemonSprite(byte[] rawImageBytes) {
		Gdx.app.log("PokemonRequestController", "SpriteNumber : " + spriteNumber);
		Pixmap pixmap = new Pixmap(rawImageBytes, 0, rawImageBytes.length);
		if (gameInfo.getPokemonSpriteList()[spriteNumber] == null) {
			gameInfo.getPokemonSpriteList()[spriteNumber] = new Texture(pixmap);
		}
		// gameInfo.setSearchedPokemonSprite(new
		// Texture(pixmap));
	}
	private void makePokemon(String data) {
		Pokemon pokemon = PokemonMakeController.makePokemon(data);
		if (pokemon != null) {
			gameInfo.getPokemonList()[pokemon.getNational_id()] = pokemon;
			Gdx.app.log("PokemonRequestController", "pokemon (" + pokemon.getNational_id() + ") is created");
		}
	}

	private void makePokemonList(String data) {

		Pokedex pokedex = PokemonMakeController.makePokemonList(data);
		gameInfo.setPokedex(pokedex);
	}

	private void makePokemonAndSprite(String data) {
		Pokemon pokemon = PokemonMakeController.makePokemon(data);
		gameInfo.setSearchedPokemonInfo(pokemon);
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
