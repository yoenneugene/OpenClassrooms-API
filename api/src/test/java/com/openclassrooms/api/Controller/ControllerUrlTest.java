package com.openclassrooms.api.Controller;

import com.openclassrooms.api.Service.FirestationService;
import com.openclassrooms.api.Service.MyData;
import com.openclassrooms.api.Service.PersonService;
import com.openclassrooms.api.controller.ControllerUrl;
import com.openclassrooms.api.controller.FireStationController;
import com.openclassrooms.api.model.Firestation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ControllerUrlTest {

    @Mock
    @Autowired
    private PersonService personService;
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ControllerUrl controllerUrl;



    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this); // Initialiser les mocks annotés
    }
    @Test
    public void testAddFirestation() {
        // Préparation des données de test
        Firestation newFirestation = new Firestation();
        newFirestation.setStation("1");
        newFirestation.setAddress("123 Main Street");
        ArrayList<Firestation>firestations=new ArrayList<>();
        firestations.add(newFirestation);
        MyData data =new MyData();
        data.setFirestations(firestations);

        // Appel de la méthode à tester
        String response = controllerUrl.getpersonsbynumbestation("1");
        assertNotNull(response);

    }
    @Test
    public void testGetChild() throws Exception {
        String address = "123 Main Street";
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.get("/childAlert=" + address));
        resultActions.andExpect(status().isOk());
        // Ajoutez d'autres assertions selon les données attendues
    }

    @Test
    public void testGetPhoneNumber() throws Exception {
        String stationNumber = "1";
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.get("/phoneAlertfirestation=" + stationNumber));
        resultActions.andExpect(status().isOk());
        // Ajoutez d'autres assertions selon les données attendues
    }

    @Test
    public void testGetFireAddress() throws Exception {
        String address = "123 Main Street";
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.get("/fireaddress=" + address));
        resultActions.andExpect(status().isOk());
        // Ajoutez d'autres assertions selon les données attendues
    }

    @Test
    public void testGetFireAdress() throws Exception {
        String stationNumber = "1";
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.get("/flood/firestationsstations=" + stationNumber));
        resultActions.andExpect(status().isOk());
        // Ajoutez d'autres assertions selon les données attendues
    }

    @Test
    public void testPersonInfo() throws Exception {
        String firstName = "John";
        String lastName = "Doe";
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.get("/personInfofirstName=" + firstName + "&lastName=" + lastName));
        resultActions.andExpect(status().isOk());
        // Ajoutez d'autres assertions selon les données attendues
    }

    @Test
    public void testGetEmailByCity() throws Exception {
        String city = "New York";
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.get("/communityEmailcity=" + city));
        resultActions.andExpect(status().isOk());
        // Ajoutez d'autres assertions selon les données attendues
    }






























    }


