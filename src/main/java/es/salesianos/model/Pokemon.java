package es.salesianos.model;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.google.gson.stream.JsonReader;

@Component("pokemon")
public class Pokemon {

	private String ID;
	private String name;
	private int health;
	private int maxHealth;
	private int power;
	private boolean dead;
	private boolean wild;
	private boolean currentFighter;
	private String message;
	

	public void heal(int amount) {
		health += amount;
		if (health >= maxHealth) {
			health = maxHealth;
		}

		if (health > 0) {
			setDead(false);
		}
	}
	
	public void damage() {
		health -= Math.random() * power;
		if(health <= 0) {
			setDead(true);
			setHealth(0);
			setCurrentFighter(false);
			if(!isWild()) {
				setMessage(getName() + " fainted! Select a new Pokemon to fight!");
			} else {
				setMessage("The wild " + getName() + " fainted, you win!");
			}
		}
	}
	
	public void revive() {
		if(isDead()) {
			health = maxHealth / 2;
			setDead(false);
		}
	}
	
	public void attack(Pokemon target) {
		if (!isDead()) {
			target.damage(power);
		}
	}

	public int getRandomHealth() {
		return (int) (Math.random() * 180 + 20);
	}

	public int getRandomPower() {
		return (int) (Math.random() * 50 + 10);
	}

	public String getRandomName() {
		List<String> pokemonList = pokemonList();
		int chance = (int) Math.floor(Math.random() * pokemonList.size());
		if (!pokemonList.isEmpty()) {
			name = pokemonList.get(chance);
			return name;
		} else {
			return "This pokemon will be implemented in the next update!";
		}
	}

	public List<String> pokemonList() {
		List<String> nameList = new ArrayList<>();
		try {
			JsonReader reader = new JsonReader(new FileReader("src/main/resources/pokemonList.json"));
			reader.beginArray();
			while (reader.hasNext()) {
				String value = reader.nextString();
				nameList.add(value);
			}
			reader.endArray();
			reader.close();
		} catch (IOException ohNo) {
			ohNo.printStackTrace();
		}
		return nameList;
	}

	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public int getHealth() {
		return health;
	}
	
	public void setHealth(int health) {
		this.health = health;
	}

	public int getMaxHealth() {
		return maxHealth;
	}

	public void setMaxHealth(int maxHealth) {
		this.maxHealth = maxHealth;
	}

	public int getPower() {
		return power;
	}
	
	public void setPower(int power) {
		this.power = power;
	}

	public boolean isDead() {
		return dead;
	}

	public void setDead(boolean dead) {
		this.dead = dead;
	}

	public boolean isWild() {
		return wild;
	}

	public void setWild(boolean status) {
		this.wild = status;
	}

	public boolean isCurrentFighter() {
		return currentFighter;
	}

	public void setCurrentFighter(boolean currentFighter) {
		if(!isDead()) {
			this.currentFighter = currentFighter;
		}
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
