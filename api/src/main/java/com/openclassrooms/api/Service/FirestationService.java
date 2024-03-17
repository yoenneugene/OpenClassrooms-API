package com.openclassrooms.api.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.openclassrooms.api.model.Firestation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class FirestationService {

    @Value("file:/home/yenx/Documents/projett/p5/api/File/data.json")
    private Resource resource;

    @Autowired
    private ObjectMapper objectMapper;

    public void addFirestation(Firestation newFirestation) {
        MyData data = readDataFromFile();
        data.getFirestations().add(newFirestation);
        writeDataToFile(data);
    }

    public void updateFirestation(Firestation updatedFirestation) {
        MyData data = readDataFromFile();
        List<Firestation> firestations = data.getFirestations();
        for (Firestation firestation : firestations) {
            if (firestation.getAddress().equals(updatedFirestation.getAddress())) {
                // Mettre à jour les champs nécessaires
                firestation.setStation(updatedFirestation.getStation());
                firestation.setAddress(updatedFirestation.getAddress());
                // Autres champs à mettre à jour
                break;
            }
        }
        writeDataToFile(data);
    }

    public void deleteFirestation(String address) {
        MyData data = readDataFromFile();
        data.getFirestations().removeIf(firestation -> firestation.getAddress().equals(address));
        writeDataToFile(data);
    }

    public MyData readDataFromFile() {
        try {
            return objectMapper.readValue(resource.getInputStream(), MyData.class);
        } catch (IOException e) {
            throw new RuntimeException("Failed to read data from JSON file", e);
        }
    }

    private void writeDataToFile(MyData data) {
        try {
            objectMapper.writeValue(resource.getFile(), data);
        } catch (IOException e) {
            throw new RuntimeException("Failed to write data to JSON file", e);
        }
    }
}

