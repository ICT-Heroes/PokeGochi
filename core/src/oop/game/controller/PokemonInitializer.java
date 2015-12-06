package oop.game.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import oop.game.model.Pokemon;

import org.json.JSONArray;
import org.json.JSONObject;

import com.badlogic.gdx.Gdx;

public class PokemonInitializer {
	private Pokemon initializePokemon(String data) {
		Pokemon pokemon = new Pokemon();
		if (data.equals("")) {
			Gdx.app.log("PokemonInitializer", "Pokemon not found");
		} else {
			JSONObject root = parseJson(data);
			try {
				// Define properties
				// Json Parsing

				Created = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse(root.getString("created")
						.substring(0, 19));
				Modified = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse(root.getString("modified").substring(0,
						19));

				/*
				 * Instead of initializing the ArrayList<Pokemon> at every
				 * Pokemon constructor, a list of IDs is initialized. This is
				 * not so much a problem with the Pokemon class as it is in
				 * classes such as Type, which would recursively continue
				 * initializing infinite Types due to the cyclical nature of
				 * types and their weaknessess/super-effectiveness
				 */

				// Abilities ArrayList Defining
				Abilities = new ArrayList<Integer>();
				JSONArray abilityNode = root.getJSONArray("abilities");
				for (int i = 0; i < abilityNode.length(); i++) {
					String abilityURI = abilityNode.getJSONObject(i).getString("resource_uri");
					abilityURI = abilityURI.substring(16);
					abilityURI = abilityURI.replace("/", "");

					Abilities.add(Integer.parseInt(abilityURI));
				}
				if (Abilities.isEmpty()) {
					Abilities.add(null);
				}

				// Descriptions ArrayList Defining
				Descriptions = new ArrayList<Integer>();
				JSONArray descriptionNode = root.getJSONArray("descriptions");
				for (int i = 0; i < descriptionNode.length(); i++) {
					String descriptionURI = descriptionNode.getJSONObject(i).getString("resource_uri");
					descriptionURI = descriptionURI.substring(20);
					descriptionURI = descriptionURI.replace("/", "");

					Descriptions.add(Integer.parseInt(descriptionURI));
				}

				// Evolutions ArrayList Defining
				Evolutions = new ArrayList<Integer>();
				JSONArray evolutionNode = root.getJSONArray("evolutions");
				for (int i = 0; i < evolutionNode.length(); i++) {
					String evolutionURI = evolutionNode.getJSONObject(i).getString("resource_uri");
					evolutionURI = evolutionURI.substring(16);
					evolutionURI = evolutionURI.replace("/", "");

					if (evolutionNode.getJSONObject(i).getString("method").equals("level_up")) {
						EvolvesAt = evolutionNode.getJSONObject(i).getInt("level");
					} else
						EvolvesAt = -1;

					Evolutions.add(Integer.parseInt(evolutionURI));
				}

				// EggGroups ArrayList Defining
				EggGroups = new ArrayList<Integer>();
				JSONArray eggNode = root.getJSONArray("egg_groups");
				for (int i = 0; i < eggNode.length(); i++) {
					String eggURI = eggNode.getJSONObject(i).getString("resource_uri");
					eggURI = eggURI.substring(12);
					eggURI = eggURI.replace("/", "");

					EggGroups.add(Integer.parseInt(eggURI));
				}

				// Moves ArrayList Defining
				Moves = new ArrayList<Integer>();
				LearnTypes = new ArrayList<String>();
				JSONArray moveNode = root.getJSONArray("moves");
				for (int i = 0; i < moveNode.length(); i++) {
					String moveURI = moveNode.getJSONObject(i).getString("resource_uri");
					moveURI = moveURI.substring(13);
					moveURI = moveURI.replace("/", "");
					LearnTypes.add(moveNode.getJSONObject(i).getString("learn_type"));

					Moves.add(Integer.parseInt(moveURI));
				}

				// Types ArrayList Defining
				Types = new ArrayList<Integer>();
				JSONArray typeNode = root.getJSONArray("types");
				for (int i = 0; i < typeNode.length(); i++) {
					String typesURI = typeNode.getJSONObject(i).getString("resource_uri");
					typesURI = typesURI.substring(13);
					typesURI = typesURI.replace("/", "");

					Types.add(Integer.parseInt(typesURI));
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	protected String get(String urlAddress) {
		String data = "";
		try {
			data = Request.Get("http://pokeapi.co/api/v1/" + urlAddress).execute().returnContent().asString();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return data;
	}

	private JSONObject parseJson(String data) {
		JSONObject root;
		try {
			root = new JSONObject(data);
			return root;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}
}
