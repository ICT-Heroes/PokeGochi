package oop.test.tester;

import java.util.ArrayList;
import java.util.Date;

import oop.game.model.Ability;
import oop.game.model.Description;
import oop.game.model.EggGroup;
import oop.game.model.Pokemon2;
import oop.game.model.Type;

@SuppressWarnings("unused")
public class TestPokemon {

	public static void main(String[] args) {
		System.out.println("Yo");
		System.out.println(isAllPokemonBasics());
	}

	// ** LONG TESTS **

	// All Pokemon Tests

	private static long getAllPokemonTime() {
		long sTime = System.nanoTime();
		for (int i = 1; i <= 718; i++) {
			Pokemon2 p = new Pokemon2(i);
		}
		long eTime = System.nanoTime();

		return eTime - sTime;
	}

	private static boolean isAllPokemonName() {
		for (int i = 1; i <= 718; i++) {
			Pokemon2 p = new Pokemon2(i);
			String n = p.getName();
		}
		return true;
	}

	private static boolean isAllPokemonAbility() {
		for (int i = 1; i <= 718; i++) {
			Pokemon2 p = new Pokemon2(i);
			ArrayList<Ability> a = p.getAbilities();
		}
		return true;
	}

	private static boolean isAllPokemonDescription() {
		for (int i = 1; i <= 718; i++) {
			Pokemon2 p = new Pokemon2(i);
			if (p.hasDescription()) {
				ArrayList<Description> d = p.getDescriptions();
			}
		}
		return true;
	}

	private static boolean isAllPokemonEggGroup() {
		for (int i = 1; i <= 718; i++) {
			Pokemon2 p = new Pokemon2(i);
			ArrayList<EggGroup> g = p.getEggGroup();
		}
		return true;
	}

	private static boolean isAllPokemonEvolutions() {
		for (int i = 1; i <= 718; i++) {
			Pokemon2 p = new Pokemon2(i);
			if (p.hasEvolution()) {
				ArrayList<Pokemon2> e = p.getEvolutions();
			}
		}
		return true;
	}

	private static boolean isAllPokemonTypes() {
		for (int i = 1; i <= 718; i++) {
			Pokemon2 p = new Pokemon2(i);
			ArrayList<Type> t = p.getTypes();
		}
		return true;
	}

	// CHECK ALL
	private static boolean allPokemonCheck() {
		if (isAllPokemonName() && isAllPokemonAbility() && isAllPokemonDescription() && isAllPokemonEggGroup()
				&& isAllPokemonEvolutions() && isAllPokemonTypes()) {
			return true;
		} else {
			return false;
		}
	}

	private static boolean isAllPokemonBasics() {
		for (int id = 1; id <= 718; id++) {
			Pokemon2 p = new Pokemon2(id);
			String n = p.getEVYield();
			n = p.getGrowthRate();
			n = p.getHeight();
			n = p.getMFRatio();
			n = p.getSpecies();
			n = p.getURI();
			n = p.getWeight();

			int i = p.getAttack();
			i = p.getCatchRate();
			i = p.getDefense();
			i = p.getEggCycle();
			i = p.getEvolvesAt();
			i = p.getExp();
			i = p.getHappiness();
			i = p.getHP();
			i = p.getID();
			i = p.getSpAttack();
			i = p.getSpDefense();
			i = p.getSpeed();
			i = p.getTotal();

			Date d = p.getCreated();
			d = p.getModified();
			System.out.println(p.toString());

		}
		return true;
	}

	// ** QUICK TESTS **

	// Single Pokemon Tests

	private static long getPokemonTime(int ID) {
		long sTime = System.nanoTime();
		Pokemon2 p = new Pokemon2(ID);
		long eTime = System.nanoTime();

		return eTime - sTime;
	}

	private static boolean isPokemonName(int ID) {
		Pokemon2 p = new Pokemon2(ID);
		String n = p.getName();
		return true;
	}

	private static boolean isPokemonAbility(int ID) {
		Pokemon2 p = new Pokemon2(ID);
		ArrayList<Ability> a = p.getAbilities();
		return true;
	}

	private static boolean isPokemonDescription(int ID) {
		Pokemon2 p = new Pokemon2(ID);
		if (p.hasDescription()) {
			ArrayList<Description> d = p.getDescriptions();
		}
		return true;
	}

	private static boolean isPokemonEggGroup(int ID) {
		Pokemon2 p = new Pokemon2(ID);
		ArrayList<EggGroup> g = p.getEggGroup();
		return true;
	}

	private static boolean isPokemonEvolutions(int ID) {
		Pokemon2 p = new Pokemon2(ID);
		if (p.hasEvolution()) {
			ArrayList<Pokemon2> e = p.getEvolutions();
		}
		return true;
	}

	private static boolean isPokemonTypes(int ID) {
		Pokemon2 p = new Pokemon2(ID);
		ArrayList<Type> t = p.getTypes();
		return true;
	}

	// CHECK ALL
	private static boolean pokemonCheck(int ID) {
		if (isPokemonName(ID) && isPokemonAbility(ID) && isPokemonDescription(ID) && isPokemonEggGroup(ID)
				&& isPokemonEvolutions(ID) && isPokemonTypes(ID)) {
			return true;
		} else {
			return false;
		}
	}

	// Test Basic Properties
	private static boolean isPokemonBasics(int ID) {
		Pokemon2 p = new Pokemon2(ID);
		String n = p.getEVYield();
		n = p.getGrowthRate();
		n = p.getHeight();
		n = p.getMFRatio();
		n = p.getSpecies();
		n = p.getURI();
		n = p.getWeight();

		int i = p.getAttack();
		i = p.getCatchRate();
		i = p.getDefense();
		i = p.getEggCycle();
		i = p.getEvolvesAt();
		i = p.getExp();
		i = p.getHappiness();
		i = p.getHP();
		i = p.getID();
		i = p.getSpAttack();
		i = p.getSpDefense();
		i = p.getSpeed();
		i = p.getTotal();

		Date d = p.getCreated();
		d = p.getModified();

		return true;

	}

}
