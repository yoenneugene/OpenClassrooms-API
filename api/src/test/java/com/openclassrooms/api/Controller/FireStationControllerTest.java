package com.openclassrooms.api.Controller;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.openclassrooms.api.Service.FirestationService;
import com.openclassrooms.api.controller.FireStationController;
import com.openclassrooms.api.model.Firestation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class FireStationControllerTest {

    @Mock
    @Autowired
    private FirestationService firestationServiceMock;

    @Autowired
    private FireStationController fireStationController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this); // Initialiser les mocks annotés
    }
    @Test
    public void testAddFirestation() {
        // Préparation des données de test
        Firestation newFirestation = new Firestation();
        newFirestation.setStation("1");
        newFirestation.setAddress("123 Main Street");



        // Appel de la méthode à tester
        ResponseEntity<String> response = fireStationController.addFirestation(newFirestation);

        // Vérification du comportement
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("Firestation added successfully", response.getBody());
    }
    @Test
    public void testUpdateFirestation() {
        // Préparation des données de test
        String address = "123 Main Street";
        Firestation updatedFirestation = new Firestation();
        updatedFirestation.setAddress(address);
        // Définir le comportement du mock


        // Appeler la méthode à tester
        ResponseEntity<String> response = fireStationController.updateFirestation(address, updatedFirestation);

        // Vérifier le comportement
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Firestation updated successfully", response.getBody());

    }

    @Test
    public void testDeleteFirestation() {
        // Préparation des données de test
        String address = "123 Main Street";


        ResponseEntity<String> response = fireStationController.deleteFirestation(address);

        // Vérifier le comportement
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Firestation deleted successfully", response.getBody());

    }
}

