package com.openclassrooms.api.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.openclassrooms.api.Service.Jsonfilereaderservice;
import com.openclassrooms.api.Service.MyData;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest

public class JsonfilereaderServiceTest {
    @InjectMocks
    private Jsonfilereaderservice jsonfilereaderservice;

    @Mock
    private Resource resource;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testReadAndPrintJsonFile() throws IOException, IOException {
        // Mock des données JSON
        String json = "{\"persons\": [{\"firstName\": \"John\", \"lastName\": \"Doe\"}]}";

        // Création d'une implémentation mock de Resource qui retourne un InputStream contenant les données JSON
        Resource mockResource = new ByteArrayResource(json.getBytes());

        // Création d'une instance de Jsonfilereaderservice
        Jsonfilereaderservice jsonfilereaderservice = new Jsonfilereaderservice();

        // Injection du mock de Resource dans l'instance de Jsonfilereaderservice
        jsonfilereaderservice.setResource(mockResource);

        // Appel de la méthode à tester
        MyData actualData = jsonfilereaderservice.readAndPrintJsonFile();

        // Configuration de l'objet ObjectMapper pour désérialiser les données JSON
        ObjectMapper objectMapper = new ObjectMapper();
        MyData expectedData = objectMapper.readValue(json, MyData.class);

        // Vérification que les données retournées sont celles attendues
        assertEquals(expectedData.getPersons().size(), actualData.getPersons().size());
        assertEquals(expectedData.getPersons().get(0).getFirstName(), actualData.getPersons().get(0).getFirstName());
        assertEquals(expectedData.getPersons().get(0).getLastName(), actualData.getPersons().get(0).getLastName());
    }
}

