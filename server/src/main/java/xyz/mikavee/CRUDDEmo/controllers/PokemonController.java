package xyz.mikavee.CRUDDEmo.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import xyz.mikavee.CRUDDEmo.entities.Pokemon;
import xyz.mikavee.CRUDDEmo.services.PokemonService;

import java.util.List;

@RestController
@RequestMapping("/api/pokemons")
public class PokemonController {

    private final PokemonService pokemonService;

    @Autowired
    public PokemonController(PokemonService pokemonService) {
        this.pokemonService = pokemonService;
    }

    @PostMapping
    public ResponseEntity<Pokemon> createPokemon(@Valid @RequestBody Pokemon pokemon) {
        return pokemonService.createPokemon(pokemon);
    }

    @GetMapping
    public ResponseEntity<List<Pokemon>> getAllPokemons() {
        return pokemonService.getAllPokemons();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getPokemonById(@PathVariable String id) {
        return pokemonService.getPokemonById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updatePokemon(@PathVariable String id, @RequestBody Pokemon updatedPokemon) {
        return pokemonService.updatePokemon(id, updatedPokemon);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePokemon(@PathVariable String id) {

        return pokemonService.deletePokemon(id);
    }
}
