package es.salesianos.model;

public interface Pokeball {
	public void catchPokemon(TeamPokemon pokemon, AbstractTeam team);
	public void catchPokemon(WildPokemon pokemon, AbstractTeam team);
}
