package es.salesianos.controller;

import java.util.List;
import java.util.Random;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import es.salesianos.model.PokemonTrainer;
import es.salesianos.model.Pokemon;
import es.salesianos.model.Team;
import es.salesianos.model.TeamPokemon;
import es.salesianos.model.WildPokemon;
import es.salesianos.model.AbstractPokeball;
import es.salesianos.model.AbstractTeam;
import es.salesianos.model.PokemonTrainer;

@Controller
public class IndexController {

	private static Logger log = LogManager.getLogger(IndexController.class);

	@Autowired
	private PokemonTrainer pokemonTrainer;
	@Autowired
	private TeamPokemon pokemon;
	@Autowired
	private WildPokemon wildPokemon;
	@Autowired
	private AbstractTeam team;
	@Autowired
	private AbstractPokeball pokeball;
	
	public void addAllObjects(ModelAndView modelAndView) {
		modelAndView.addObject("pokemonTrainer", this.pokemonTrainer);
		modelAndView.addObject("pokemon", this.pokemon);
		modelAndView.addObject("wildPokemon", this.wildPokemon);
		modelAndView.addObject("team", this.team);
		modelAndView.addObject("pokeball", this.pokeball);
	}
	
	private void addPageDataTrainer(PokemonTrainer pokemonTrainerForm) {
		if (!StringUtils.isEmpty(pokemonTrainerForm.getName())) {
			pokemonTrainer.setName(pokemonTrainerForm.getName());
		}
		
		if (!StringUtils.isEmpty(pokemonTrainerForm.isGymLeader())) {
			pokemonTrainer.setGymLeader(pokemonTrainerForm.isGymLeader());
		}
	}
	
	private void addPageDataPokemon(TeamPokemon pokemonForm) {
		if (!StringUtils.isEmpty(pokemonForm.getName())) {
			pokemon.setName(pokemonForm.getName());
		}
		
		if (!StringUtils.isEmpty(pokemonForm.getPower())) {
			pokemon.setPower(pokemonForm.getPower());
		}
		
		if (!StringUtils.isEmpty(pokemonForm.getHealth())) {
			pokemon.setHealth(pokemonForm.getHealth());
		}

		if (!StringUtils.isEmpty(pokemonForm.isCurrentFighter())) {
			pokemon.setCurrentFighter(false);
		}
	}
	
	private void addPageDataWildPokemon(WildPokemon pokemonForm) {
		if (!StringUtils.isEmpty(pokemonForm.getName())) {
			wildPokemon.setName(pokemonForm.getName());
		}

		if (!StringUtils.isEmpty(pokemonForm.getPower())) {
			wildPokemon.setPower(pokemonForm.getPower());
		}
		
		if (!StringUtils.isEmpty(pokemonForm.getHealth())) {
			wildPokemon.setHealth(pokemonForm.getHealth());
		}
		
		wildPokemon.setCaptureHealth();
	}
	
	
	@GetMapping("/")
	public ModelAndView index(Model model) {
		ModelAndView modelAndView = new ModelAndView("index");
		addAllObjects(modelAndView);
		log.debug(modelAndView);
		return modelAndView;
	}
	
	@PostMapping("/insertTrainer")
	public ModelAndView pokemonTrainerInsert(@ModelAttribute("pokemonTrainer") PokemonTrainer pokeTrainer) {
		log.debug("pokemonTrainerInsert:" + this.pokemonTrainer.toString());		
		ModelAndView modelAndView = new ModelAndView("index");	
		addPageDataTrainer(pokeTrainer);
		addAllObjects(modelAndView);
		return modelAndView;
	}
	
//	@PostMapping(path = "/pokemonEvent", params = {"fight"})
	@PostMapping("/wildPokemon")
	public ModelAndView wildPokemon(@ModelAttribute("pokemon") WildPokemon poke) {	
		ModelAndView modelAndView = new ModelAndView("index");
		addPageDataWildPokemon(poke);
		addAllObjects(modelAndView);
		return modelAndView;
	}


//	@PostMapping(path = "/pokemonEvent", params = {"capture"})
	@PostMapping("/pokemonInsert")
	public ModelAndView pokemonInsert(@ModelAttribute("pokemon") TeamPokemon poke) {	
		ModelAndView modelAndView = new ModelAndView("index");
		addPageDataPokemon(poke);
		pokeball.catchPokemon(poke, pokemonTrainer.getTeam());
		addAllObjects(modelAndView);
		return modelAndView;
	}
	
	@PostMapping("/changePokemon{id}")
	public ModelAndView changePokemon(@PathVariable("id") String id) {	
		ModelAndView modelAndView = new ModelAndView("index");
		currentPokemonChange(id);
		addAllObjects(modelAndView);
		return modelAndView;
	}

	private void currentPokemonChange(String id) {
		for(TeamPokemon pokemon : team.getMembers()) {
			pokemon.setCurrentFighter(false);
			if(pokemon.getID().equals(id)) {
				pokemon.setCurrentFighter(true);
				pokemon.setMessage(pokemon.getName() + " joined the battle!");
			}
		}
	}
	
	@PostMapping("/healPokemon{id}")
	public ModelAndView healPokemon(@PathVariable("id") String id) {	
		ModelAndView modelAndView = new ModelAndView("index");
		heal(id);
		addAllObjects(modelAndView);
		return modelAndView;
	}
	
	private void heal(String id) {
		for(Pokemon pokemon : team.getMembers()) {
			if(pokemon.getID().equals(id)) {
				pokemon.heal(2);
				pokemon.setMessage(pokemon.getName() + " was healed!");
			}
		}
	}
	
	@PostMapping("/battle{pokeAtk}-{wildAtk}")
	public ModelAndView battle(@PathVariable("pokeAtk") int pokeAtk, @PathVariable("wildAtk") int wildAtk) {
		ModelAndView modelAndView = new ModelAndView("index");
		pokeFight(pokeAtk, wildAtk);
		addAllObjects(modelAndView);
		return modelAndView;
	}

	private void pokeFight(int pokeAtk, int wildAtk) {
		for(TeamPokemon poke : team.getMembers()) {
			if(poke.isCurrentFighter() && !poke.isDead()) {
				poke.damage(wildAtk);
			}
		}
		if(!team.getCurrentPokemon().isDead()) {
			wildPokemon.damage(pokeAtk);
		}
	}
	
	@PostMapping("/wildCapture{wildName}-{wildPwr}-{wildHp}-{wildCapHp}")
	public ModelAndView wildCapture(@PathVariable("wildName") String wildName, @PathVariable("wildPwr") int wildPwr, @PathVariable("wildHp") int wildHp, @PathVariable("wildCapHp") int wildCapHp) {
		ModelAndView modelAndView = new ModelAndView("index");
		pokeball.catchPokemon(wildName, wildPwr, wildHp, wildCapHp, team);
		addAllObjects(modelAndView);
		return modelAndView;
	}
}
