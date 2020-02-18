package es.salesianos.model;

import java.util.Random;

public class AbstractPokeball implements Pokeball{
	private int catchChancePercentage;
	private String message;
	private int chance;
	
	public void chances() {
		
	}
	
	@Override
	public void catchPokemon(TeamPokemon pokemon, AbstractTeam team) {
		Random random = new Random();
		chance = random.nextInt(99)+1;
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
	
	public void catchPokemon(String name, int power, int health, int captureHealth, AbstractTeam team) {
		Random random = new Random();
		chance = random.nextInt(99)+1;
		if(chance <= catchChancePercentage && health <= captureHealth) {	
			team.addMember(name, power, health);
			if(team.getMemberCount() < 6) {
				setMessage("You catched " + name + "!");
			}else {
				setMessage("Your team is full!");
			}
		}else {
			setMessage("The wild pokemon escaped!");
		}
	}
	
	@Override
	public void catchPokemon(WildPokemon pokemon, AbstractTeam team) {
		Random random = new Random();
		chance = random.nextInt(99)+1;
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
