package com.openclassrooms.api.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.openclassrooms.api.MyData;
import com.openclassrooms.api.Service.Jsonfilereaderservice;
import com.openclassrooms.api.model.Person;
import com.openclassrooms.api.model.Personn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class PersonController {

    @Autowired
    private Jsonfilereaderservice jsonfilereaderservice;


    @GetMapping("/persons1")
    public String getpersons() {
        MyData data = jsonfilereaderservice.readAndPrintJsonFile();
        List<Person> persons = data.getPersons();
        ObjectMapper objectMapper = new ObjectMapper();

        if (persons == null || persons.isEmpty()) {
            return "No persons found";
        }


        String json = null;
        try {
            json = objectMapper.writeValueAsString(persons);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return json;


    }

    @GetMapping("/firestationstationNumber={stationNumber}")
    public String getpersonsbynumbestation(@PathVariable String stationNumber) {
        MyData data = jsonfilereaderservice.readAndPrintJsonFile();
        return data.getPersonsByStationNumber(stationNumber);


    }

    @GetMapping("/childAlert={address}")
    public String getchild(@PathVariable String address) {
        MyData data = jsonfilereaderservice.readAndPrintJsonFile();

        return data.getChildAlert(address);
    }

    @GetMapping("/phoneAlertfirestation={stationNumber}")
    public String getPhonNumber(@PathVariable String stationNumber) {
        MyData data = jsonfilereaderservice.readAndPrintJsonFile();

        return data.getPhoneNumber(stationNumber);

    }
    @GetMapping ("/fireaddress={address}")
    public String getFireAddress(@PathVariable String address) {
        MyData data = jsonfilereaderservice.readAndPrintJsonFile();

        return data.getFireInfo(address);
    }


    @GetMapping("/flood/firestationsstations={stationNumber}")
    public String getFireAdress(@PathVariable String stationNumber) {
        MyData data = jsonfilereaderservice.readAndPrintJsonFile();

        return data.getHouseholdsByFirestation(stationNumber).toString();
    }

        @GetMapping("/personInfofirstName={firstName}&lastName={lastName}")
        public String personinfo(@PathVariable String firstName ,@PathVariable String lastName) {
            MyData data = jsonfilereaderservice.readAndPrintJsonFile();

            return data.getPersonInfo(firstName,lastName).toString();
        }
    @GetMapping("/communityEmailcity={city}")
    public String getEmailByCity(@PathVariable String city) {
        MyData data = jsonfilereaderservice.readAndPrintJsonFile();

        return data.getMailByCity(city);
    }


}
