package es.salesianos.model;

import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component("pokeball")
@Profile("pokeballBasic")
public class PokeballBasic extends AbstractPokeball{
	public PokeballBasic() {
		setCatchChancePercentage(50);
	}
}