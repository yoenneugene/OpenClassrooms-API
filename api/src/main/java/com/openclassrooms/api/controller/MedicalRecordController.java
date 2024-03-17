package com.openclassrooms.api.controller;

import com.openclassrooms.api.Service.MedicalrecordService;
import com.openclassrooms.api.model.Medicalrecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class MedicalRecordController {
    @Autowired
    private MedicalrecordService medicalRecordService;

    @PostMapping("/medicalrecord")
    public ResponseEntity<String> addMedicalRecord(@RequestBody Medicalrecord newMedicalRecord) {
        medicalRecordService.addMedicalRecord(newMedicalRecord);
        return ResponseEntity.status(HttpStatus.CREATED).body("Medical record added successfully");
    }

    @PutMapping("/medicalrecord/{firstName}/{lastName}")
    public ResponseEntity<String> updateMedicalRecord(@PathVariable String firstName, @PathVariable String lastName,
                                                      @RequestBody Medicalrecord updatedMedicalRecord) {
        updatedMedicalRecord.setFirstName(firstName);
        updatedMedicalRecord.setLastName(lastName);
        medicalRecordService.updateMedicalRecord(updatedMedicalRecord);
        return ResponseEntity.ok("Medical record updated successfully");
    }

    @DeleteMapping("/medicalrecord/{firstName}/{lastName}")
    public ResponseEntity<String> deleteMedicalRecord(@PathVariable String firstName, @PathVariable String lastName) {
        medicalRecordService.deleteMedicalRecord(firstName, lastName);
        return ResponseEntity.ok("Medical record deleted successfully");
    }
}


