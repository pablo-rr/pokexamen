package es.salesianos.model;

import org.springframework.stereotype.Component;

@Component("pokemon")
//public class Pokemon extends AbstractPokeball{
public class Pokemon{
	private String ID;
	private String name;
	private int health;
	private int power;
	private boolean dead;
	private boolean wild;
	private boolean currentFighter;
	private String message;
	
	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}

	public void heal(int ammount) {
		health += ammount;
		if(getHealth() > 0) {
			setDead(false);
		}
	}
	
	public void damage(int ammount) {
		health -= ammount;
		if(health <= 0) {
			setDead(true);
			setHealth(0);
			setCurrentFighter(false);
			if(!isWild()) {
				setMessage(getName() + " fainted! Select a new Pokemon to fight!");
			}else {
				setMessage("The wild " + getName() + " fainted, you win!");
			}
		}
	}
	
	public void revive() {
		if(isDead()) {
			health = 1;
			setDead(false);
		}
	}
	
	public void attack(Pokemon target) {
		if(!isDead()) {			
			target.damage(power);
		}
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
