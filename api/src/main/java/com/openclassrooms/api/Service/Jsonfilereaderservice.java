package com.openclassrooms.api.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jsoniter.JsonIterator;
import com.jsoniter.output.JsonStream;
import com.openclassrooms.api.MyData;
import com.openclassrooms.api.model.Firestation;
import com.openclassrooms.api.model.Medialrecord;
import com.openclassrooms.api.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Map;
@Service
public class Jsonfilereaderservice {

    @Value("classpath:data.json")
    private Resource resource;
    @Autowired
    private MyData myData;

    public MyData readAndPrintJsonFile() {
        ObjectMapper objectMapper = new ObjectMapper();



            try {
                myData = objectMapper.readValue(resource.getInputStream(), MyData.class);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            myData.setPersons(myData.getPersons());
            return myData;


    }
}
