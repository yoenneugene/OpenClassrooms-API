package com.openclassrooms.api;

import com.openclassrooms.api.model.Firestation;
import com.openclassrooms.api.model.Person;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class MyDataTest {
   @Autowired
   private static MyData data ;
    @Mock
    private static Person person;
    @Mock
    private List<Person> persons;
    @Mock
    private static Firestation firestation;
    @Mock
    private List<Firestation> firestations;

    @Test
    void setPersons() {
    }

    @Test
    void getPersonsByStationNumber() {
        List<Person> persons = new ArrayList<>();
        Person person = new Person("sqddqsd" , "sqdqds","adress" ,"dsqds",
                "adress", "sdq" ,"dsq");
        Person person2 = new Person("sqddqsds" , "sqdqds","dsqd", "dsqds",
                "sdqdq", "sdq" ,"dsq");
        persons.add(person);
        persons.add(person2);
        List<Firestation> firestations = new ArrayList<>();
        Firestation firestation1= new Firestation("sqddqsd" , "sqdqds");
        Firestation firestation2 = new Firestation("adress" , "1");
        firestations.add(firestation1);
        firestations.add(firestation2);

        MyData myData =new MyData();
        myData.setPersons(persons);
        myData.setFirestations(firestations);
        myData.getPersonsByStationNumber(("1"));

    }
}