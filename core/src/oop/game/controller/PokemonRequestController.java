package oop.game.controller;

import org.apache.http.client.fluent.Request;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Net;
import com.badlogic.gdx.Net.HttpRequest;
import com.badlogic.gdx.Net.HttpResponse;
import com.badlogic.gdx.Net.HttpResponseListener;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;

public class PokemonRequestController implements HttpResponseListener {
	private Texture pokemonSprite;
	private HttpRequest httpRequest;

	public String getPokemonById(int id) {
		String data = "";
		try {
			data = Request.Get("http://pokeapi.co/api/v1/pokemon/" + id).execute().returnContent().asString();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return data;
	}

	public Texture getImage() {
		return pokemonSprite;
	}

	public void makeRequest() {
		String url;
		String httpMethod = Net.HttpMethods.GET;
		url = "http://pokeapi.co/media/img/1.png";
		httpRequest = new HttpRequest(httpMethod);
		httpRequest.setUrl(url);
		Gdx.net.sendHttpRequest(httpRequest, PokemonRequestController.this);
		System.out.println(httpRequest.getUrl());
	}

	private boolean checkIsNull(Texture texture) {
		if (texture != null) {
			return false;
		} else {
			return true;
		}
	}

	@Override
	public void handleHttpResponse(HttpResponse httpResponse) {
		final byte[] rawImageBytes = httpResponse.getResult();
		Gdx.app.postRunnable(new Runnable() {
			public void run() {
				Pixmap pixmap = new Pixmap(rawImageBytes, 0, rawImageBytes.length);
				setPokemonSprite(new Texture(pixmap));
				System.out.println(checkIsNull(pokemonSprite));
			}
		});
	}

	@Override
	public void failed(Throwable t) {
		Gdx.app.log("PokemonRequestController", "request failed");

	}

	@Override
	public void cancelled() {
		// TODO Auto-generated method stub

	}

	public Texture getPokemonSprite() {
		return pokemonSprite;
	}

	public void setPokemonSprite(Texture pokemonSprite) {
		this.pokemonSprite = pokemonSprite;
	}

}
