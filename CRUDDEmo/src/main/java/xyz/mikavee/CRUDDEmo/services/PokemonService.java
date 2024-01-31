package xyz.mikavee.CRUDDEmo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import xyz.mikavee.CRUDDEmo.entities.Pokemon;
import xyz.mikavee.CRUDDEmo.repositories.PokemonRepository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class PokemonService {

    private final PokemonRepository pokemonRepository;

    @Autowired
    public PokemonService(PokemonRepository pokemonRepository) {
        this.pokemonRepository = pokemonRepository;
    }

    public ResponseEntity<Pokemon> createPokemon(Pokemon pokemon) {

        // Save the Pokemon
        Pokemon createdPokemon = pokemonRepository.save(pokemon);

        // Return ResponseEntity with the created Pokemon and HttpStatus.CREATED
        return ResponseEntity.status(HttpStatus.CREATED).body(createdPokemon);

    }

    public ResponseEntity<List<Pokemon>> getAllPokemons() {
        try {
            List<Pokemon> pokemons = pokemonRepository.findAll();
            return ResponseEntity.ok(pokemons);
        } catch (Exception e) {
            // Log the exception for debugging purposes
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Collections.emptyList()); // You can customize the error response as needed
        }
    }


    public ResponseEntity<?> getPokemonById(String id) {
        Optional<Pokemon> optionalPokemon = pokemonRepository.findById(id);

        return optionalPokemon
                .map(pokemon -> ResponseEntity.ok(pokemon))
                .orElse(ResponseEntity.notFound().build());
    }


    public ResponseEntity<String> updatePokemon(String id, Pokemon updatedPokemon) {
        Optional<Pokemon> optionalPokemon = pokemonRepository.findById(id);

        if (optionalPokemon.isPresent()) {
            Pokemon existingPokemon = optionalPokemon.get();
            existingPokemon.setName(updatedPokemon.getName());
            existingPokemon.setLevel(updatedPokemon.getLevel());
            // Update other fields as needed
            Pokemon savedPokemon = pokemonRepository.save(existingPokemon);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(savedPokemon.getName() + " was updated");

        } else {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("No Pokemon found with ID " + id + ". Update failed.");
        }
    }

    public ResponseEntity<String> deletePokemon(String id) {
        // commented out code is for test of unexpected error and that it is handled.
        //boolean simulateError = true;

        try {
            Optional<Pokemon> optionalPokemon = pokemonRepository.findById(id);
            /*if(simulateError) {
                throw new RuntimeException("Simulated error");
            }
            */
            if (optionalPokemon.isPresent()) {

                Pokemon pokemonToBeDeleted = optionalPokemon.get();
                pokemonRepository.deleteById(id);
                return ResponseEntity
                        .status(HttpStatus.OK)
                        .body(pokemonToBeDeleted.getName() + " with id " + id + " was deleted.");
            } else {
                return ResponseEntity
                        .status(HttpStatus.NOT_FOUND)
                        .body("No such Pokemon with id " + id + " could be deleted.");
            }
        } catch (Exception e) {
            // Log the exception for debugging purposes
            e.printStackTrace();
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An unexpected error occurred while deleting the Pokemon.");
        }
    }
}