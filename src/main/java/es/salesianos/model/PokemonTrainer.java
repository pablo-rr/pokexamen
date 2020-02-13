package es.salesianos.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("pokemonTrainer")
public class PokemonTrainer{
	private String name;
	@Autowired
	private AbstractTeam team;
	@Autowired
	@Qualifier("pokeball")
	private AbstractPokeball pokeball;
	private boolean gymLeader;
	private Pokemon currentPokemonOut;
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public AbstractTeam getTeam() {
		return team;
	}
	
	public void setTeam(AbstractTeam team) {
		this.team = team;
	}
	
	public AbstractPokeball getPokeball() {
		return pokeball;
	}

	public void setPokeball(AbstractPokeball pokeball) {
		this.pokeball = pokeball;
	}

	public boolean isGymLeader() {
		return gymLeader;
	}
	
	public void setGymLeader(boolean gymLeader) {
		this.gymLeader = gymLeader;
	}

	public Pokemon getCurrentPokemonOut() {
		return currentPokemonOut;
	}

	public void setCurrentPokemonOut(Pokemon currentPokemonOut) {
		this.currentPokemonOut = currentPokemonOut;
	}
}
