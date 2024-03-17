package com.openclassrooms.api.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;

@Service
public class Jsonfilereaderservice {

    @Value("file:/home/yenx/Documents/projett/p5/api/File/data.json")
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
