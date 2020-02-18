package es.salesianos.model;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component("pokeball")
@Profile("pokeballSuperball")
public class PokeballSuperball extends AbstractPokeball {
	public PokeballSuperball() {
		setCatchChancePercentage(25);
		setTheChosenOne(false);
	}
}