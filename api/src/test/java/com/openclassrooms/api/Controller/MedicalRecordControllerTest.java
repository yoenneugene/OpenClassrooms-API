package com.openclassrooms.api.Controller;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.openclassrooms.api.Service.MedicalrecordService;
import com.openclassrooms.api.controller.MedicalRecordController;
import com.openclassrooms.api.model.Medicalrecord;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class MedicalRecordControllerTest {

    @Mock
    @Autowired
    private MedicalrecordService medicalRecordServiceMock;

    @Autowired
    private MedicalRecordController medicalRecordController;
    @Mock
    private Resource resourceMock;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        // Initialiser les mocks annotés
    }

    @Test
    public void testAddMedicalRecord() {
        // Préparation des données de test
        Medicalrecord newMedicalRecord = new Medicalrecord();
        newMedicalRecord.setFirstName("John");
        newMedicalRecord.setLastName("Doe");
        newMedicalRecord.setBirthdate("02/18/2000");

        // Ajouter des médicaments et des allergies
        ArrayList<String> medications = new ArrayList<>();
        medications.add("Medication1");
        medications.add("Medication2");
        newMedicalRecord.setMedications( medications);

        ArrayList<String> allergies = new ArrayList<>();
        allergies.add("Allergy1");
        allergies.add("Allergy2");
        newMedicalRecord.setAllergies(allergies);

        // Appeler la méthode à tester
        ResponseEntity<String> response = medicalRecordController.addMedicalRecord(newMedicalRecord);

        // Vérifier le comportement
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("Medical record added successfully", response.getBody());
    }


    @Test
    public void testUpdateMedicalRecord() {
        // Préparation des données de test
        String firstName = "John";
        String lastName = "Doe";
        Medicalrecord updatedMedicalRecord = new Medicalrecord();
        updatedMedicalRecord.setFirstName(firstName);
        updatedMedicalRecord.setLastName(lastName);
        // Définir le comportement du mock


        // Appeler la méthode à tester
        ResponseEntity<String> response = medicalRecordController.updateMedicalRecord(firstName, lastName, updatedMedicalRecord);

        // Vérifier le comportement
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Medical record updated successfully", response.getBody());

    }

    @Test
    public void testDeleteMedicalRecord() {
        // Préparation des données de test
        String firstName = "John";
        String lastName = "Doe";

        // Appeler la méthode à tester
        ResponseEntity<String> response = medicalRecordController.deleteMedicalRecord(firstName, lastName);

        // Vérifier le comportement
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Medical record deleted successfully", response.getBody());

    }
}

