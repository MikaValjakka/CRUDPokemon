package xyz.mikavee.CRUDDEmo.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import xyz.mikavee.CRUDDEmo.entities.Pokemon;
import xyz.mikavee.CRUDDEmo.entities.Trainer;
import xyz.mikavee.CRUDDEmo.repositories.TrainerRepository;

import java.util.List;
import java.util.Optional;

@Service
public class TrainerService {

    private final TrainerRepository trainerRepository;
    private final PokemonService pokemonService;
    private final PasswordEncoder passwordEncoder;


    @Autowired
    public TrainerService(TrainerRepository trainerRepository, PokemonService pokemonService, PasswordEncoder passwordEncoder) {
        this.trainerRepository = trainerRepository;
        this.pokemonService = pokemonService;

        this.passwordEncoder = passwordEncoder;
    }



    // CREATE TRAINER -> RETURN RESPONSE ENTITY
    public ResponseEntity<Trainer> createTrainer(Trainer trainer) {

        String hashedPassword = passwordEncoder.encode(trainer.getPassword());
        trainer.setPassword(hashedPassword);
        // Set roles for the trainer
        trainer.setRoles(trainer.getRoles());


        // save the Trainer
        Trainer createdTrainer = trainerRepository.save(trainer);
        // Return ResponseEntity with created Trainer and HttpStatus.CREATED
        return ResponseEntity.status(HttpStatus.CREATED).body(createdTrainer);
    }

    public List<Trainer> getAllTrainers() {
        return trainerRepository.findAll();
    }


    // ADD POKEMON TO TRAINERS POKEMON LIST -> RETURN RESPONSE ENTITY
    public ResponseEntity<String> addPokemonToTrainer(String trainerId, String pokemonId) {
        try {
            Trainer trainer = trainerRepository.findById(trainerId).orElse(null);
            if (trainer == null) {
                return ResponseEntity
                        .status(HttpStatus.NOT_FOUND)
                        .body("Trainer not found");
            }

            ResponseEntity<Pokemon> pokemonResponse = (ResponseEntity<Pokemon>) pokemonService.getPokemonById(pokemonId);
            if (pokemonResponse.getStatusCode() != HttpStatus.OK) {
                return ResponseEntity
                        .status(HttpStatus.NOT_FOUND)
                        .body("Pokemon not found");
            }
            Pokemon pokemon = pokemonResponse.getBody();

            trainer.addPokemon(pokemon);
            trainerRepository.save(trainer);

            //return "Pokemon with ID " + pokemonId + " added to Trainer with ID " + trainerId;
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body("Pokemon");
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("Error occured in connection: " + e.getMessage());
        }
    }

    public Trainer getTrainerById(String id) {
        Optional<Trainer> optionalTrainer = trainerRepository.findById(id);
        return optionalTrainer.orElse(null); // Return null if Trainer not found
    }

    public ResponseEntity<String> updateTrainer(String id, Trainer updatedTrainer) {
        Optional<Trainer> optionalTrainer = trainerRepository.findById(id);

        if (optionalTrainer.isPresent()) {
            Trainer existingTrainer = optionalTrainer.get();
            existingTrainer.setFirstName(updatedTrainer.getFirstName());
            existingTrainer.setLastName(updatedTrainer.getLastName());
            existingTrainer.setAge(updatedTrainer.getAge());
            // Update other fields as needed

            Trainer savedTrainer = trainerRepository.save(existingTrainer);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(savedTrainer.getFirstName() + " was updated");

        } else {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("No Trainer found with ID " + id + ". Update failed.");
        }
    }




}