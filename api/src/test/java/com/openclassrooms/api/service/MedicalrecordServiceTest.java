package com.openclassrooms.api.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.openclassrooms.api.Service.MedicalrecordService;
import com.openclassrooms.api.Service.MyData;
import com.openclassrooms.api.model.Medicalrecord;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class MedicalrecordServiceTest {

    @Autowired
    @Mock
    private MedicalrecordService medicalrecordService;
    @Mock
    private Resource resourceMock;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }


    @Test
    public void testAddMedicalRecord() {
        // Préparation des données de test
        Medicalrecord newMedicalRecord = new Medicalrecord();
        newMedicalRecord.setFirstName("test");
        newMedicalRecord.setLastName("toto");
        newMedicalRecord.setBirthdate("02/18/2000");
        MyData myData = new MyData();
        ArrayList<Medicalrecord> medicalrecords = new ArrayList<>();
        medicalrecords.add(newMedicalRecord);
        myData.setMedicalrecords(medicalrecords);
        // Appel de la méthode à tester
        medicalrecordService.addMedicalRecord(newMedicalRecord);

        // Vérification que le dossier médical a été ajouté

        boolean recordAdded = myData.getMedicalrecords().stream()
                .anyMatch(record -> record.getFirstName().equals("test") && record.getLastName().equals("toto"));
        assertTrue(recordAdded);
    }

    @Test
    public void testUpdateMedicalRecord() {
        // Préparation des données de test
        Medicalrecord updatedMedicalRecord = new Medicalrecord();
        updatedMedicalRecord.setFirstName("test");
        updatedMedicalRecord.setLastName("toto");
        updatedMedicalRecord.setBirthdate("02/18/2000");

        MyData myData = new MyData();
        ArrayList<Medicalrecord> medicalrecords = new ArrayList<>();
        medicalrecords.add(updatedMedicalRecord);
        myData.setMedicalrecords(medicalrecords);

        // Appel de la méthode à tester
        medicalrecordService.updateMedicalRecord(updatedMedicalRecord);

        // Vérification que le dossier médical a été mis à jour
        boolean recordUpdated = myData.getMedicalrecords().stream()
                .anyMatch(record -> record.getFirstName().equals("test") &&
                        record.getLastName().equals("toto") &&
                        record.getBirthdate().equals("02/18/2000"));
        assertTrue(recordUpdated);
    }

    @Test
    public void testDeleteMedicalRecord() {
        // Préparation des données de test
        Medicalrecord medicalRecordToDelete = new Medicalrecord();
        medicalRecordToDelete.setFirstName("testd");
        medicalRecordToDelete.setLastName("testd");

        MyData myData = new MyData();
        ArrayList<Medicalrecord> medicalrecords = new ArrayList<>();
        medicalrecords.add(medicalRecordToDelete);
        myData.setMedicalrecords(medicalrecords);

        // Appel de la méthode à tester
        medicalrecordService.deleteMedicalRecord("testd", "testd");

        // Vérification que le dossier médical a été supprimé
        boolean recordDeleted = myData.getMedicalrecords().stream()
                .noneMatch(record -> record.getFirstName().equals("testd") &&
                        record.getLastName().equals("testd"));
        assertFalse(recordDeleted);
    }
}



