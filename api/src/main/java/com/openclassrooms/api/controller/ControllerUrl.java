package com.openclassrooms.api.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.openclassrooms.api.Service.PersonService;
import com.openclassrooms.api.Service.MyData;
import com.openclassrooms.api.Service.Jsonfilereaderservice;
import com.openclassrooms.api.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ControllerUrl {

    @Autowired
    private Jsonfilereaderservice jsonfilereaderservice;
    @Autowired
    private PersonService personService;




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
            MyData data = personService.readDataFromFile();

            return data.getPersonInfo(firstName,lastName).toString();
        }
    @GetMapping("/communityEmailcity={city}")
    public String getEmailByCity(@PathVariable String city) {
        MyData data = jsonfilereaderservice.readAndPrintJsonFile();

        return data.getMailByCity(city);
    }


}
