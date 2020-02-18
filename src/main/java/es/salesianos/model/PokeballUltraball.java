package es.salesianos.model;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component("pokeball")
@Profile("pokeballUltraball")
public class PokeballUltraball extends AbstractPokeball {
	public PokeballUltraball() {
		setCatchChancePercentage(50);
		setTheChosenOne(false);
	}
}

