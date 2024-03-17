package com.openclassrooms.api.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.openclassrooms.api.model.Medicalrecord;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class MedicalrecordService {
    @Value("file:/home/yenx/Documents/projett/p5/api/File/data.json")
    private Resource resource;

    public void addMedicalRecord(Medicalrecord newMedicalRecord) {
        MyData data = readDataFromFile();
        data.getMedicalrecords().add(newMedicalRecord);
        writeDataToFile(data);
    }

    public void updateMedicalRecord(Medicalrecord updatedMedicalRecord) {
        MyData data = readDataFromFile();
        for (Medicalrecord record : data.getMedicalrecords()) {
            if (record.getFirstName().equals(updatedMedicalRecord.getFirstName()) &&
                    record.getLastName().equals(updatedMedicalRecord.getLastName())) {
                // Mettre à jour les champs nécessaires
                record.setBirthdate(updatedMedicalRecord.getBirthdate());
                record.setMedications(updatedMedicalRecord.getMedications());
                record.setAllergies(updatedMedicalRecord.getAllergies());
               record.setMedications(updatedMedicalRecord.getMedications());
                break;
            }
        }
        writeDataToFile(data);
    }

    public void deleteMedicalRecord(String firstName, String lastName) {
        MyData data = readDataFromFile();
        data.getMedicalrecords().removeIf(record -> record.getFirstName().equals(firstName) &&
                record.getLastName().equals(lastName));
        writeDataToFile(data);
    }

    private MyData readDataFromFile() {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(resource.getInputStream(), MyData.class);
        } catch (IOException e) {
            throw new RuntimeException("Failed to read data from JSON file", e);
        }
    }

    private void writeDataToFile(MyData data) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            objectMapper.writeValue(resource.getFile(), data);
        } catch (IOException e) {
            throw new RuntimeException("Failed to write data to JSON file", e);
        }
    }

}
