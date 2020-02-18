package es.salesianos.model;

import org.springframework.stereotype.Component;

@Component("teamPokemon")
public class TeamPokemon extends Pokemon{
	private boolean currentFighter;
	
	public void damage(int ammount) {
		health -= ammount;
		if(health <= 0) {
			setDead(true);
			setHealth(0);
			setCurrentFighter(false);
		}
	}

	public boolean isCurrentFighter() {
		return currentFighter;
	}

	public void setCurrentFighter(boolean currentFighter) {
		this.currentFighter = currentFighter;
	}
}
