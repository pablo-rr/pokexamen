package es.salesianos.model;

import java.util.Random;

public class AbstractPokeball implements Pokeball{
	private int catchChancePercentage;
	private String message;
	
	@Override
	public void catchPokemon(Pokemon pokemon, AbstractTeam team) {
		Random random = new Random();
		int chance = random.nextInt(99)+1;
		if(chance <= catchChancePercentage) {	
			team.addMember(pokemon);
			if(team.getMemberCount() < 6) {
				setMessage("You catched " + pokemon.getName() + "!");
			}else {
				setMessage("Your team is full!");
			}
		}else {
			setMessage("The wild pokemon escaped!");
		}
	}

	public int getCatchChancePercentage() {
		return catchChancePercentage;
	}

	public void setCatchChancePercentage(int catchChancePercentage) {
		this.catchChancePercentage = catchChancePercentage;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	
}
