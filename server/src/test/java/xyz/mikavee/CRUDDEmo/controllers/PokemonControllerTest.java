package xyz.mikavee.CRUDDEmo.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import xyz.mikavee.CRUDDEmo.entities.Pokemon;
import xyz.mikavee.CRUDDEmo.services.PokemonService;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class PokemonControllerTest {

    @Mock
    private PokemonService pokemonService;

    @InjectMocks
    private PokemonController pokemonController;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(pokemonController).build();
    }

    @Test
    void testGetAllPokemons() throws Exception {
        // Arrange
        Pokemon bulbasaur = new Pokemon("Bulbasaur", 1);
        Pokemon charmander = new Pokemon("Charmander", 1);
        Pokemon squirtle = new Pokemon("Squirtle", 1);

        List<Pokemon> pokemonList = Arrays.asList(bulbasaur, charmander, squirtle);

        when(pokemonService.getAllPokemons()).thenReturn(new ResponseEntity<>(pokemonList, HttpStatus.OK));

        // Act & Assert
        mockMvc.perform(MockMvcRequestBuilders.get("/api/pokemons")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").isArray())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value("Bulbasaur"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].name").value("Charmander"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[2].name").value("Squirtle"));
    }
}