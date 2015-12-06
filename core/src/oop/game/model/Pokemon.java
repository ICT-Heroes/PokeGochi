package oop.game.model;

import java.util.ArrayList;

public class Pokemon {
	private int attack, sp_atk, sp_def, speed, total, catch_rate, defense, egg_cycles, exp, happiness, hp, national_id;
	private String weight, resource_uri, species, created, ev_yield, growth_rate, modified, height, male_female_ratio,
			name, pkdx_id;
	private ArrayList<EggGroup> egg_groups;
	private ArrayList<Ability> abilities;
	private ArrayList<Evolution> evolutions;
	private ArrayList<Move> moves;
	private ArrayList<Sprite> sprites;
	private ArrayList<Type> types;
	private ArrayList<Description> descriptions;
	public int getAttack() {
		return attack;
	}
	public void setAttack(int attack) {
		this.attack = attack;
	}
	public int getSp_atk() {
		return sp_atk;
	}
	public void setSp_atk(int sp_atk) {
		this.sp_atk = sp_atk;
	}
	public int getSp_def() {
		return sp_def;
	}
	public void setSp_def(int sp_def) {
		this.sp_def = sp_def;
	}
	public int getSpeed() {
		return speed;
	}
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public int getCatch_rate() {
		return catch_rate;
	}
	public void setCatch_rate(int catch_rate) {
		this.catch_rate = catch_rate;
	}
	public int getDefense() {
		return defense;
	}
	public void setDefense(int defense) {
		this.defense = defense;
	}
	public int getEgg_cycles() {
		return egg_cycles;
	}
	public void setEgg_cycles(int egg_cycles) {
		this.egg_cycles = egg_cycles;
	}
	public int getExp() {
		return exp;
	}
	public void setExp(int exp) {
		this.exp = exp;
	}
	public int getHappiness() {
		return happiness;
	}
	public void setHappiness(int happiness) {
		this.happiness = happiness;
	}
	public int getHp() {
		return hp;
	}
	public void setHp(int hp) {
		this.hp = hp;
	}
	public int getNational_id() {
		return national_id;
	}
	public void setNational_id(int national_id) {
		this.national_id = national_id;
	}
	public String getWeight() {
		return weight;
	}
	public void setWeight(String weight) {
		this.weight = weight;
	}
	public String getResource_uri() {
		return resource_uri;
	}
	public void setResource_uri(String resource_uri) {
		this.resource_uri = resource_uri;
	}
	public String getSpecies() {
		return species;
	}
	public void setSpecies(String species) {
		this.species = species;
	}
	public String getCreated() {
		return created;
	}
	public void setCreated(String created) {
		this.created = created;
	}
	public String getEv_yield() {
		return ev_yield;
	}
	public void setEv_yield(String ev_yield) {
		this.ev_yield = ev_yield;
	}
	public String getGrowth_rate() {
		return growth_rate;
	}
	public void setGrowth_rate(String growth_rate) {
		this.growth_rate = growth_rate;
	}
	public String getHeight() {
		return height;
	}
	public void setHeight(String height) {
		this.height = height;
	}
	public String getMale_female_ratio() {
		return male_female_ratio;
	}
	public void setMale_female_ratio(String male_female_ratio) {
		this.male_female_ratio = male_female_ratio;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public ArrayList<EggGroup> getEgg_groups() {
		return egg_groups;
	}
	public void setEgg_groups(ArrayList<EggGroup> egg_groups) {
		this.egg_groups = egg_groups;
	}
	public ArrayList<Ability> getAbilities() {
		return abilities;
	}
	public void setAbilities(ArrayList<Ability> abilities) {
		this.abilities = abilities;
	}
	public ArrayList<Evolution> getEvolutions() {
		return evolutions;
	}
	public void setEvolutions(ArrayList<Evolution> evolutions) {
		this.evolutions = evolutions;
	}
	public ArrayList<Move> getMoves() {
		return moves;
	}
	public void setMoves(ArrayList<Move> moves) {
		this.moves = moves;
	}
	public ArrayList<Sprite> getSprites() {
		return sprites;
	}
	public void setSprites(ArrayList<Sprite> sprites) {
		this.sprites = sprites;
	}
	public ArrayList<Type> getTypes() {
		return types;
	}
	public void setTypes(ArrayList<Type> types) {
		this.types = types;
	}
	public ArrayList<Description> getDescriptions() {
		return descriptions;
	}
	public void setDescriptions(ArrayList<Description> descriptions) {
		this.descriptions = descriptions;
	}
	public String getModified() {
		return modified;
	}
	public void setModified(String modified) {
		this.modified = modified;
	}
	public String getPkdx_id() {
		return pkdx_id;
	}
	public void setPkdx_id(String pkdx_id) {
		this.pkdx_id = pkdx_id;
	}

}
