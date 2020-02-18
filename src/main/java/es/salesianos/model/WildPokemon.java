package es.salesianos.model;

import org.springframework.stereotype.Component;

@Component("wildPokemon")
public class WildPokemon extends Pokemon{
	private int captureHealth;
	private boolean capturable;
	
	public int getCaptureHealth() {
		return captureHealth;
	}

	public void setCaptureHealth() {
		captureHealth = getHealth()*25/100;
	}

	public void damage(double d) {
		if(health <= 0) {
			setDead(true);
			setHealth(0);
		}
		if(!isDead()) {
			if(isCapturable()) {
				setCapturable(true);
			}
			health -= d;
		}
	}

	public boolean isCapturable() {
		if(getHealth() <= captureHealth) {
			return true;
		}else {
			return false;
		}
	}

	public void setCapturable(boolean capturable) {
		this.capturable = capturable;
	}
	
}
