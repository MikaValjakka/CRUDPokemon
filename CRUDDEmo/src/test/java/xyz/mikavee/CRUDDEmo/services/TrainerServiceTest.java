package xyz.mikavee.CRUDDEmo.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import xyz.mikavee.CRUDDEmo.entities.Trainer;
import xyz.mikavee.CRUDDEmo.repositories.TrainerRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class TrainerServiceTest {

    @Mock
    private TrainerRepository trainerRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private TrainerService trainerService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    // TESTS -> CREATE TRAINER
    @Test
    void testCreateTrainer(){
        // Arrange
        Trainer trainerToCreate = new Trainer("Ash","Ketchum","masterTrainer12",12, "1234");
        when(passwordEncoder.encode(any(CharSequence.class))).thenReturn("hashedPassword");
        when(trainerRepository.save(trainerToCreate)).thenReturn(trainerToCreate);

        // Act
        ResponseEntity<Trainer> responseEntity = trainerService.createTrainer(trainerToCreate);

        // Assert
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(trainerToCreate, responseEntity.getBody());
    }


}