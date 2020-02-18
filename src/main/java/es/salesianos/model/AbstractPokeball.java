package es.salesianos.model;

import java.util.Random;

public class AbstractPokeball implements Pokeball {

	private int catchChancePercentage;
	private String message;
	private boolean theChosenOne;
	
	@Override
	public void catchPokemon(Pokemon pokemon, AbstractTeam team) {
		Random random = new Random();
		int chance = random.nextInt(99) + 1;
		if (pokemon.getHealth() != 0) {
			if (pokemon.isLessThan25Percent() || isTheChosenOne()) {
				if (chance <= catchChancePercentage) {
					team.addMember(pokemon);
					if (team.getMemberCount() < 6) {
						setMessage("You catched " + pokemon.getName() + "!");
					} else {
						setMessage("Your team is full!");
					}
				} else {
					setMessage("The wild pokemon escaped!");
				}
			} else {
				setMessage("You need to lower its life to have a chance");
			}
		} else {
			setMessage("This pokemon is dead, you can't catch it!");
		}
	}

	public boolean isTheChosenOne() {
		return theChosenOne;
	}

	public void setTheChosenOne(boolean theChosenOne) {
		this.theChosenOne = theChosenOne;
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
