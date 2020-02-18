package es.salesianos.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import es.salesianos.model.AbstractPokeball;
import es.salesianos.model.AbstractTeam;
import es.salesianos.model.EnemyTeam;
import es.salesianos.model.Pokemon;
import es.salesianos.model.PokemonTrainer;
import es.salesianos.model.WildEnemy;

@Controller
public class IndexController {

	private static Logger log = LogManager.getLogger(IndexController.class);

	@Autowired
	private PokemonTrainer pokemonTrainer;
	@Autowired
	private WildEnemy wildEnemy;
	@Autowired
	private AbstractTeam team;
	@Autowired
	private EnemyTeam enemyTeam;
	@Autowired
	private Pokemon pokemon;
	@Autowired
	private Pokemon wildPokemon;
	@Autowired
	private Pokemon currentWildPokemon;
	@Autowired
	private AbstractPokeball pokeball;

	public void addAllObjects(ModelAndView modelAndView) {
		modelAndView.addObject("pokemonTrainer", this.pokemonTrainer);
		modelAndView.addObject("wildEnemy", this.wildEnemy);
		modelAndView.addObject("team", this.team);
		modelAndView.addObject("enemyTeam", this.enemyTeam);
		modelAndView.addObject("pokemon", this.pokemon);
		modelAndView.addObject("wildPokemon", this.wildPokemon);
		modelAndView.addObject("currentWildPokemon", this.currentWildPokemon);
		modelAndView.addObject("pokeball", this.pokeball);
	}
	
	private void addPageDataPokemon(Pokemon pokemonForm) {
		if (!StringUtils.isEmpty(pokemonForm.getName())) {
			pokemon.setName(pokemonForm.getName());
		}
		
		if (!StringUtils.isEmpty(pokemonForm.getPower())) {
			pokemon.setPower(pokemonForm.getPower());
		}
		
		if (!StringUtils.isEmpty(pokemonForm.getHealth())) {
			pokemon.setHealth(pokemonForm.getHealth());
		}

		if (!StringUtils.isEmpty(pokemonForm.getMaxHealth())) {
			pokemon.setMaxHealth(pokemonForm.getMaxHealth());
		}

		if (!StringUtils.isEmpty(pokemonForm.isCurrentFighter())) {
			pokemon.setCurrentFighter(false);
		}
	}
	
	private void addPageDataWildPokemon(ModelMap model, Pokemon wildPokemon) {

		wildPokemon.setMaxHealth(wildPokemon.getRandomHealth());
		wildPokemon.setHealth(wildPokemon.getMaxHealth());
		wildPokemon.setPower(wildPokemon.getRandomPower());

		model.addAttribute("pokemonWildName", wildPokemon.getRandomName());
		model.addAttribute("pokemonWildHealth", wildPokemon.getHealth());
		model.addAttribute("pokemonWildMaxHealth", wildPokemon.getMaxHealth());
		model.addAttribute("pokemonWildPower", wildPokemon.getPower());

		currentWildPokemon.setName(wildPokemon.getName());
		currentWildPokemon.setHealth(wildPokemon.getHealth());
		currentWildPokemon.setMaxHealth(wildPokemon.getMaxHealth());
		currentWildPokemon.setPower(wildPokemon.getPower());
		currentWildPokemon.setCurrentFighter(true);
		currentWildPokemon.setWild(true);

		wildEnemy.getTeam().addMember(currentWildPokemon);
	}
	
	private void addWildPokemon(Pokemon wildPokemon) {

		wildPokemon.setName(currentWildPokemon.getName());
		wildPokemon.setMaxHealth(currentWildPokemon.getHealth());
		wildPokemon.setHealth(currentWildPokemon.getMaxHealth());
		wildPokemon.setPower(currentWildPokemon.getPower());
	}

	private void removeWildPokemon() {

		wildEnemy.getTeam().removeMember(currentWildPokemon.getName());
	}

	@GetMapping("/")
	public ModelAndView index(Model model) {
		ModelAndView modelAndView = new ModelAndView("index");
		addAllObjects(modelAndView);
		log.debug(modelAndView);
		return modelAndView;
	}

	@PostMapping("/pokemonEvent")
	public ModelAndView pokemonInsert(@ModelAttribute("pokemon") Pokemon poke) {	
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
		for(Pokemon pokemon : team.getMembers()) {
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
				pokemon.heal(20);
				pokemon.setMessage(pokemon.getName() + " was healed!");
			}
		}
	}
	
	@GetMapping("insertWildPokemon")
	public ModelAndView wildPokemonInsert(ModelMap model, @ModelAttribute("wildPokemon") Pokemon poke) {
		ModelAndView modelAndView = new ModelAndView("index");
		addPageDataWildPokemon(model, poke);
		addAllObjects(modelAndView);
		return modelAndView;
	}

	@PostMapping("/battle{pokeAtk}-{wildAtk}")
	public ModelAndView battle(@PathVariable("pokeAtk") int pokeAtk, @PathVariable("wildAtk") int wildAtk) {
		ModelAndView modelAndView = new ModelAndView("index");
		pokeFight(pokeAtk, wildAtk);
		addAllObjects(modelAndView);
		return modelAndView;
	}

	private void pokeFight(int pokeAtk, int wildAtk) {
		for(Pokemon poke : team.getMembers()) {
			if(poke.isCurrentFighter() && !poke.isDead()) {
				poke.damage((int) (Math.random() * wildAtk));
			}
		}
		
		for (Pokemon wildPoke : enemyTeam.getMembers()) {
			if (!team.getCurrentPokemon().isDead() && !wildPoke.isDead()) {
				wildEnemy.getTeam().getCurrentPokemon().damage((int) (Math.random() * pokeAtk));
			}
		}
	}

	@PostMapping("/catchWildPokemon")
	public ModelAndView catchWildPokemon(@ModelAttribute("wildPokemon") Pokemon wildPokemon) {
		ModelAndView modelAndView = new ModelAndView("index");
		addWildPokemon(wildPokemon);
		pokeball.catchPokemon(wildPokemon, pokemonTrainer.getTeam());
		removeWildPokemon();
		addAllObjects(modelAndView);
		return modelAndView;
	}
}
