package xyz.mikavee.CRUDDEmo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import xyz.mikavee.CRUDDEmo.entities.Trainer;
import xyz.mikavee.CRUDDEmo.services.TrainerService;

import java.util.List;

@RestController
@RequestMapping("/api/trainers")
public class TrainerController {

    private final TrainerService trainerService;


    @Autowired
    public TrainerController(TrainerService trainerService) {
        this.trainerService = trainerService;
    }



    @PostMapping
    public ResponseEntity<Trainer> createTrainer(@RequestBody Trainer trainer) {
        return trainerService.createTrainer(trainer);
    }

    @GetMapping
    public ResponseEntity<List<Trainer>> getAllTrainers() {
        List<Trainer> trainers = trainerService.getAllTrainers();
        return ResponseEntity.ok(trainers);
    }

    @PostMapping("/{trainerId}/add-pokemon/{pokemonId}")
    public ResponseEntity<String> addPokemonToTrainer(@PathVariable String trainerId, @PathVariable String pokemonId) {

        return trainerService.addPokemonToTrainer(trainerId, pokemonId);
    }

}
