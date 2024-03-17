package com.openclassrooms.api.controller;

import com.openclassrooms.api.Service.PersonService;
import com.openclassrooms.api.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class PersonController {

    @Autowired
    private PersonService personService;

    @PostMapping("/person")
    public ResponseEntity<String> addPerson(@RequestBody Person newPerson) {
        personService.addPerson(newPerson);
        return ResponseEntity.status(HttpStatus.CREATED).body("Person added successfully");
    }

    @PutMapping("/person/{firstName}/{lastName}")
    public ResponseEntity<String> updatePerson(@PathVariable String firstName, @PathVariable String lastName,
                                               @RequestBody Person updatedPerson) {
        updatedPerson.setFirstName(firstName);
        updatedPerson.setLastName(lastName);
        personService.updatePerson(updatedPerson);
        return ResponseEntity.ok("Person updated successfully");
    }

    @DeleteMapping("/person/{firstName}/{lastName}")
    public ResponseEntity<String> deletePerson(@PathVariable String firstName, @PathVariable String lastName) {
        personService.deletePerson(firstName, lastName);
        return ResponseEntity.ok("Person deleted successfully");
    }
}

