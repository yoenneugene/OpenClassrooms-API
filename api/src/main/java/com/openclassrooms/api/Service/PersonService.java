package com.openclassrooms.api.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.openclassrooms.api.model.Person;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;


import java.io.IOException;
import java.util.List;

@Service
public class PersonService {
    @Value("file:/home/yenx/Documents/projett/p5/api/File/data.json")
    private Resource resource;
    public void addPerson(Person newPerson) {
        MyData data = readDataFromFile();
        data.getPersons().add(newPerson);
        writeDataToFile(data);
    }

    public void updatePerson(Person updatedPerson) {
        MyData data = readDataFromFile();
        List<Person> persons = data.getPersons();
        for (Person person : persons) {
            if (person.getFirstName().equals(updatedPerson.getFirstName()) &&
                    person.getLastName().equals(updatedPerson.getLastName())) {
                // Mettre à jour les champs nécessaires
                person.setAddress(updatedPerson.getAddress());
                person.setPhone(updatedPerson.getPhone());
                person.setCity(updatedPerson.getCity());
                person.setAddress(updatedPerson.getAddress());
                person.setEmail(updatedPerson.getEmail());
                // Autres champs à mettre à jour
                break;
            }
        }
        writeDataToFile(data);
    }

    public void deletePerson(String firstName, String lastName) {
        MyData data = readDataFromFile();
        data.getPersons().removeIf(person -> person.getFirstName().equals(firstName) &&
                person.getLastName().equals(lastName));
        writeDataToFile(data);
    }

    public MyData readDataFromFile() {
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
            // Lire le contenu du fichier JSON
            MyData existingData = objectMapper.readValue(resource.getInputStream(), MyData.class);

            // Ajouter les nouvelles données à celles existantes
            existingData.setPersons(data.getPersons());

            // Écrire les données mises à jour dans le fichier JSON
            objectMapper.writeValue(resource.getFile(), existingData);
        } catch (IOException e) {
            throw new RuntimeException("Failed to write data to JSON file", e);
        }

}}


