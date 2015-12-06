package oop.game.model;

public class Move {
	private String learn_type;
	private String name;
	private String resource_uri;
	private int level;
	public String getLearn_type() {
		return learn_type;
	}
	public void setLearn_type(String learn_type) {
		this.learn_type = learn_type;
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
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
}
