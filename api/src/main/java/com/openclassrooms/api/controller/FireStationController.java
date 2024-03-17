package com.openclassrooms.api.controller;

import com.openclassrooms.api.Service.FirestationService;
import com.openclassrooms.api.model.Firestation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class FireStationController {

    @Autowired
    private FirestationService firestationService;

    @PostMapping("/firestation")
    public ResponseEntity<String> addFirestation(@RequestBody Firestation newFirestation) {
        firestationService.addFirestation(newFirestation);
        return ResponseEntity.status(HttpStatus.CREATED).body("Firestation added successfully");
    }

    @PutMapping("/firestation/{address}")
    public ResponseEntity<String> updateFirestation(@PathVariable String address, @RequestBody Firestation updatedFirestation) {
        updatedFirestation.setAddress(address);
        firestationService.updateFirestation(updatedFirestation);
        return ResponseEntity.ok("Firestation updated successfully");
    }

    @DeleteMapping("/firestation/{address}")
    public ResponseEntity<String> deleteFirestation(@PathVariable String address) {
        firestationService.deleteFirestation(address);
        return ResponseEntity.ok("Firestation deleted successfully");
    }
}
