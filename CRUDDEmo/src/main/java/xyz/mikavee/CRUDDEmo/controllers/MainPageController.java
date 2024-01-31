package xyz.mikavee.CRUDDEmo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.mikavee.CRUDDEmo.repositories.TrainerRepository;

@RestController
public class MainPageController {

    private final TrainerRepository trainerRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public MainPageController(TrainerRepository trainerRepository, PasswordEncoder passwordEncoder) {
        this.trainerRepository = trainerRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping
    public ResponseEntity<String> getFrontPageMessage() {
        String message = "Welcome to the main page!";
        return ResponseEntity.ok(message);
    }


}

