package es.salesianos.model;

import org.springframework.stereotype.Component;

@Component("pokemon")
public class Pokemon{
	private String ID;
	private boolean dead;
	private String message;
	private String name;
	private int power;
	protected int health;
	
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
	
	public void revive() {
		if(isDead()) {
			health = 1;
			setDead(false);
		}
	}
	
	public void attack(TeamPokemon target) {
		if(!isDead()) {			
			target.damage(power);
		}
	}
	
	public void attack(WildPokemon target) {
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

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
