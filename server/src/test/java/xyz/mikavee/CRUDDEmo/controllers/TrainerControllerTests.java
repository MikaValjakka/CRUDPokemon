package xyz.mikavee.CRUDDEmo.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import xyz.mikavee.CRUDDEmo.services.TrainerService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class TrainerControllerTests {


    @Mock
    private TrainerService trainerService;

    @InjectMocks
    private TrainerController trainerController;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(trainerController).build();
    }

    @Test
    void testDeleteTrainer_AdminRole_Success() {
        // Arrange
        String trainerId = "someId";
        ResponseEntity<String> expectedResponse = ResponseEntity.ok("Trainer with ID " + trainerId + " Deleted");

        // Mock the service method to return the expected response
        when(trainerService.deleteTrainer(trainerId)).thenReturn(expectedResponse);

        // Act
        ResponseEntity<String> responseEntity = trainerController.deleteTrainer(trainerId);

        // Assert
        assertEquals(expectedResponse.getStatusCode(), responseEntity.getStatusCode());
        assertEquals(expectedResponse.getBody(), responseEntity.getBody());

        // Verify that the service method was called with the correct trainer ID
        verify(trainerService, times(1)).deleteTrainer(trainerId);
    }
}
