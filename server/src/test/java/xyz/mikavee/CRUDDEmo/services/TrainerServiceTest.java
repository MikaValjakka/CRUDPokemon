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

        // Creating a mock Trainer object to be used for testing.
        Trainer trainerToCreate = new Trainer("Ash","Ketchum","masterTrainer12",12, "1234");

        // Mocking the behavior of the password encoder to return a fixed value when called with any CharSequence.
        when(passwordEncoder.encode(any(CharSequence.class))).thenReturn("hashedPassword");

        // Mocking the behavior of the trainer repository to return the same trainer object when saved.
        when(trainerRepository.save(trainerToCreate)).thenReturn(trainerToCreate);

        // Act

        // Calling the method under test with the created Trainer object.
        ResponseEntity<Trainer> responseEntity = trainerService.createTrainer(trainerToCreate);

        // Assert

        // Verifying that the HTTP status code of the response is HttpStatus.CREATED.
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());

        // Verifying that the response body contains the same Trainer object that was created.
        assertEquals(trainerToCreate, responseEntity.getBody());
    }


}