package es.salesianos.model;

import org.springframework.stereotype.Component;

@Component("wildPokemon")
public class WildPokemon extends Pokemon{
	private int captureHealth;
	
	public int getCaptureHealth() {
		return captureHealth;
	}

	public void setCaptureHealth() {
		captureHealth = getHealth()*25/100;
	}

	public void damage(int ammount) {
		health -= ammount;
		if(health <= 0) {
			setDead(true);
			setHealth(0);
		}
	}
	
}
