package xyz.mikavee.CRUDDEmo.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import xyz.mikavee.CRUDDEmo.entities.Pokemon;
import xyz.mikavee.CRUDDEmo.repositories.PokemonRepository;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

class PokemonServiceTest {
    @Mock
    private PokemonRepository pokemonRepository;

    @InjectMocks
    private PokemonService pokemonService;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    // TESTS -> CREATE POKEMON
    @Test
    void testCreatePokemon() {
        // Arrange
        Pokemon pokemonToCreate = new Pokemon("Pikachu", 5);

        // Mock the save method of the repository to return the created Pokemon
        when(pokemonRepository.save(pokemonToCreate)).thenReturn(pokemonToCreate);

        // Act
        ResponseEntity<Pokemon> responseEntity = pokemonService.createPokemon(pokemonToCreate);

        // Assert
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(pokemonToCreate, responseEntity.getBody());
    }

    // Test for createPokemon with validation error
    // HOX HOX!! Validation is checked in controller!

    // TEST -> DELETE POKEMON
    @Test
    void testDeletePokemonNotFound() {
        // Arrange
        String id = "someId";
        when(pokemonRepository.findById(id)).thenReturn(Optional.empty());

        // Act
        ResponseEntity<String> responseEntity = pokemonService.deletePokemon(id);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        assertEquals("No such Pokemon with id " + id + " could be deleted.", responseEntity.getBody());
    }

    @Test
    void testDeletePokemonFound() {
        // Arrange
        Pokemon bulbasaur = new Pokemon("Bulbasaur", 1);
        when(pokemonRepository.findById(bulbasaur.getId())).thenReturn(Optional.of(bulbasaur));

        // Act
        ResponseEntity<String> responseEntity = pokemonService.deletePokemon(bulbasaur.getId());

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("Bulbasaur with id " + bulbasaur.getId() + " was deleted.", responseEntity.getBody());
    }

    @Test
    void testDeletePokemonError() {
        // Arrange
        Pokemon pokemonToDelete = new Pokemon("Bulbasaur", 1);
        when(pokemonRepository.findById(pokemonToDelete.getId())).thenReturn(Optional.of(pokemonToDelete));
        doThrow(new RuntimeException("Simulated error")).when(pokemonRepository).deleteById(pokemonToDelete.getId());

        // Act
        ResponseEntity<String> responseEntity = pokemonService.deletePokemon(pokemonToDelete.getId());

        // Assert
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
        assertEquals("An unexpected error occurred while deleting the Pokemon.", responseEntity.getBody());
    }

    // TEST -> GET ALL POKEMONS
    @Test
    void testGetAllPokemons() {
        // Arrange
        Pokemon bulbasaur = new Pokemon("Bulbasaur", 1);
        Pokemon charmander = new Pokemon("Charmander", 1);
        Pokemon squirtle = new Pokemon("Squirtle", 1);

        List<Pokemon> pokemonList = Arrays.asList(bulbasaur, charmander, squirtle);

        when(pokemonRepository.findAll()).thenReturn(pokemonList);

        // Act
        ResponseEntity<List<Pokemon>> responseEntity = pokemonService.getAllPokemons();

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(pokemonList, responseEntity.getBody());
    }

    @Test
    void testGetAllPokemonsEmptyList() {
        // Arrange
        when(pokemonRepository.findAll()).thenReturn(Collections.emptyList());

        // Act
        ResponseEntity<List<Pokemon>> responseEntity = pokemonService.getAllPokemons();

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(Collections.emptyList(), responseEntity.getBody());
    }

    @Test
    void testGetAllPokemonsError() {
        // Arrange
        when(pokemonRepository.findAll()).thenThrow(new RuntimeException("Simulated error"));

        // Act
        ResponseEntity<List<Pokemon>> responseEntity = pokemonService.getAllPokemons();

        // Assert
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
        // Add assertions for the error message or any other relevant details
    }

}