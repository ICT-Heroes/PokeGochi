package oop.game.model;

import java.util.ArrayList;

public class Pokedex {
	private String created, modified, name, resource_uri;
	private ArrayList<Pokemon> pokemon;
	public String getCreated() {
		return created;
	}
	public void setCreated(String created) {
		this.created = created;
	}
	public String getModified() {
		return modified;
	}
	public void setModified(String modified) {
		this.modified = modified;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getResource_uri() {
		return resource_uri;
	}
	public void setResource_uri(String resource_uri) {
		this.resource_uri = resource_uri;
	}
	public ArrayList<Pokemon> getPokemon() {
		return pokemon;
	}
	public void setPokemon(ArrayList<Pokemon> pokemon) {
		this.pokemon = pokemon;
	}
}
