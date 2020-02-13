package es.salesianos.model;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component("pokeball")
@Profile("pokeballMasterball")
public class PokeballMasterball extends AbstractPokeball{
	public PokeballMasterball() {
		setCatchChancePercentage(99);
	}
}
