package com.openclassrooms.api.Controller;


import com.openclassrooms.api.controller.PersonController;

import com.openclassrooms.api.model.Person;
import com.openclassrooms.api.Service.PersonService;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class PersonControllerTest {

    @Mock
    @Autowired
    private PersonService personServiceMock;

    @Autowired
    private PersonController personController;
    @Mock
    private Resource resourceMock;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);


    }

    @Test
    public void testAddPerson() {
        // Préparation des données de test
        Person newPerson = new Person();
        newPerson.setFirstName("John");
        newPerson.setLastName("Doe");
        newPerson.setAddress("123 Main Street");
        newPerson.setCity("New York");
        newPerson.setZip("10001");
        newPerson.setPhone("123-456-7890");
        newPerson.setEmail("john.doe@example.com");




        // Appeler la méthode à tester
        ResponseEntity<String> response = personController.addPerson(newPerson);

        // Vérifier le comportement
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("Person added successfully", response.getBody());

    }
    @Test
    public void testUpdatePerson() {
        // Préparation des données de test
        String firstName = "John";
        String lastName = "Doe";
        Person updatedPerson = new Person();
        updatedPerson.setFirstName(firstName);
        updatedPerson.setLastName(lastName);
        updatedPerson.setAddress("456 Oak Street");
        updatedPerson.setCity("New York");
        updatedPerson.setZip("10001");
        updatedPerson.setPhone("987-654-3210");
        updatedPerson.setEmail("john.doe@example.com");




        // Appeler la méthode à tester
        ResponseEntity<String> response = personController.updatePerson(firstName, lastName, updatedPerson);

        // Vérifier le comportement
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Person updated successfully", response.getBody());

    }

    @Test
    public void testDeletePerson() {

        String firstName = "John";
        String lastName = "Doe";



        // Appeler la méthode à tester
        ResponseEntity<String> response = personController.deletePerson(firstName, lastName);

        // Vérifier le comportement
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Person deleted successfully", response.getBody());

    }
}