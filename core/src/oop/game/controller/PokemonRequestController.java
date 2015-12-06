package oop.game.controller;

import oop.game.assets.GameInfo;
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
		JSON_DATA, SPRITE_IMAGE;
	}
	private MakeType makeType;
	private HttpRequest httpRequest;

	public void requestPokemonById(int id) {
		makeType = MakeType.JSON_DATA;
		makeRequest("http://pokeapi.co/api/v1/pokemon/" + id);
	}

	public void requestSpriteImageById(int id) {
		makeType = MakeType.SPRITE_IMAGE;
		makeRequest("http://pokeapi.co/media/img/" + id + ".png");
	}

	public void makeRequest(String url) {
		String httpMethod = Net.HttpMethods.GET;
		httpRequest = new HttpRequest(httpMethod);
		httpRequest.setUrl(url);
		Gdx.net.sendHttpRequest(httpRequest, PokemonRequestController.this);
		Gdx.app.log("PokemonRequestController", httpRequest.getUrl());
	}

	@Override
	public void handleHttpResponse(HttpResponse httpResponse) {
		switch (makeType) {
			case JSON_DATA :
				final String data = httpResponse.getResultAsString();
				Gdx.app.postRunnable(new Runnable() {
					public void run() {
						makePokemon(data);
					}
				});
				break;
			case SPRITE_IMAGE :
				final byte[] rawImageBytes = httpResponse.getResult();
				Gdx.app.postRunnable(new Runnable() {
					public void run() {
						Pixmap pixmap = new Pixmap(rawImageBytes, 0, rawImageBytes.length);
						GameInfo.setSearchedPokemonSprite(new Texture(pixmap));
					}
				});
				break;

		}
	}

	private void makePokemon(String data) {
		Pokemon pokemon = PokemonMakeController.makePokemon(data);
		GameInfo.setSearchedPokemonInfo(pokemon);
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
