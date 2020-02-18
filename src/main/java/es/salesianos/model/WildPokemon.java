package es.salesianos.model;

import org.springframework.stereotype.Component;

@Component("wildPokemon")
public class WildPokemon extends Pokemon{
	private String wildName;
	private int wildPower;
	private int wildHealth;
	
	public String getWildName() {
		return wildName;
	}
	
	public int getWildPower() {
		return wildPower;
	}
	
	public int getWildHealth() {
		return wildHealth;
	}
	
	public void setWildName(String wildName) {
		this.wildName = wildName;
	}
	
	public void setWildPower(int wildPower) {
		this.wildPower = wildPower;
	}
	
	public void setWildHealth(int wildHealth) {
		this.wildHealth = wildHealth;
	}
}
