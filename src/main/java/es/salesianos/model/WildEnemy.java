package es.salesianos.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("wildEnemy")
public class WildEnemy {

	@Autowired
	private EnemyTeam enemyTeam;
	private Pokemon currentPokemonOut;
	
	public EnemyTeam getTeam() {
		return enemyTeam;
	}

	public void setTeam(EnemyTeam enemyTeam) {
		this.enemyTeam = enemyTeam;
	}

	public Pokemon getCurrentPokemonOut() {
		return currentPokemonOut;
	}

	public void setCurrentPokemonOut(Pokemon currentPokemonOut) {
		this.currentPokemonOut = currentPokemonOut;
	}
}
