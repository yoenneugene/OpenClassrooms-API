package com.openclassrooms.api.service;

import com.openclassrooms.api.Service.MyData;
import com.openclassrooms.api.model.Firestation;
import com.openclassrooms.api.model.Medicalrecord;
import com.openclassrooms.api.model.Person;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MyDataTest {

    @Test
    public void testGetPersonsByStationNumber() {
        // Créer des données de test
        MyData myData = new MyData();
        List<Person> persons = new ArrayList<>();
        Person person1 = new Person();
        person1.setFirstName("John");
        person1.setLastName("Doe");
        person1.setAddress("123 Main Street");
        person1.setPhone("123-456-7890");
        persons.add(person1);
        myData.setPersons(persons);

        List<Firestation> firestations = new ArrayList<>();
        Firestation firestation = new Firestation();
        firestation.setStation("1");
        firestation.setAddress("123 Main Street");
        firestations.add(firestation);
        myData.setFirestations(firestations);

        List<Medicalrecord> medicalrecords = new ArrayList<>();
        Medicalrecord medicalRecord = new Medicalrecord();
        medicalRecord.setFirstName("John");
        medicalRecord.setLastName("Doe");
        medicalRecord.setBirthdate("02/18/2000");

        ArrayList<String> medications = new ArrayList<>();
        medications.add("Medication1");
        medications.add("Medication2");
        medicalRecord.setMedications(medications);

        ArrayList<String> allergies = new ArrayList<>();
        allergies.add("Allergy1");
        allergies.add("Allergy2");
        medicalRecord.setAllergies(allergies);
        medicalrecords.add(medicalRecord);
        myData.setMedicalrecords(medicalrecords);







        // Appeler la méthode à tester
        String actualResult = myData.getPersonsByStationNumber("1");

        // Vérifier le résultat
        assertNotNull(actualResult);
    }
    @Test
    public void testGetChildAlert() {
        // Créer des données de test
        MyData myData = new MyData();
        List<Person> persons = new ArrayList<>();
        Person child = new Person();
        child.setFirstName("Alice");
        child.setLastName("Doe");
        child.setAddress("123 Main Street");
        child.setPhone("123-456-7890");
        persons.add(child);
        myData.setPersons(persons);

        // Ajouter des informations médicales pour l'enfant
        List<Medicalrecord> medicalrecords = new ArrayList<>();
        Medicalrecord medicalRecord = new Medicalrecord();
        medicalRecord.setFirstName("Alice");
        medicalRecord.setLastName("Doe");
        medicalRecord.setBirthdate("03/04/2010"); // Date de naissance d'Alice
        ArrayList<String> medications = new ArrayList<>();
        medications.add("Medication1");
        medications.add("Medication2");
        medicalRecord.setMedications(medications);
        ArrayList<String> allergies = new ArrayList<>();
        allergies.add("Allergy1");
        allergies.add("Allergy2");
        medicalRecord.setAllergies(allergies);
        medicalrecords.add(medicalRecord);
        myData.setMedicalrecords(medicalrecords);

        // Appeler la méthode à tester
        String actualResult = myData.getChildAlert("123 Main Street");

        // Vérifier le résultat
        assertNotNull(actualResult);
    }
    @Test
    public void testGetPhoneNumber() {
        // Créer des données de test
        MyData myData = new MyData();

        // Ajouter des stations de pompiers avec des adresses
        List<Firestation> firestations = new ArrayList<>();
        Firestation firestation = new Firestation();
        firestation.setStation("1");
        firestation.setAddress("123 Main Street");
        firestations.add(firestation);
        myData.setFirestations(firestations);

        // Ajouter des personnes avec des numéros de téléphone
        List<Person> persons = new ArrayList<>();
        Person person1 = new Person();
        person1.setFirstName("John");
        person1.setLastName("Doe");
        person1.setAddress("123 Main Street");
        person1.setPhone("123-456-7890");
        persons.add(person1);
        myData.setPersons(persons);

        // Appeler la méthode à tester
        String actualResult = myData.getPhoneNumber("1");

        // Vérifier le résultat
        assertNotNull(actualResult);
    }
    @Test
    public void testGetFireAddress() {
        // Créer des données de test
        MyData myData = new MyData();

        // Ajouter des stations de pompiers avec des adresses
        List<Firestation> firestations = new ArrayList<>();
        Firestation firestation = new Firestation();
        firestation.setStation("1");
        firestation.setAddress("123 Main Street");
        firestations.add(firestation);
        myData.setFirestations(firestations);

        // Ajouter des personnes avec des informations
        List<Person> persons = new ArrayList<>();
        Person person1 = new Person();
        person1.setFirstName("John");
        person1.setLastName("Doe");
        person1.setAddress("123 Main Street");
        person1.setPhone("123-456-7890");
        persons.add(person1);
        myData.setPersons(persons);

        // Ajouter un enregistrement médical pour la personne
        List<Medicalrecord> medicalrecords = new ArrayList<>();
        Medicalrecord medicalRecord = new Medicalrecord();
        medicalRecord.setFirstName("John");
        medicalRecord.setLastName("Doe");
        medicalRecord.setBirthdate("02/18/2000");

        ArrayList<String> medications = new ArrayList<>();
        medications.add("Medication1");
        medications.add("Medication2");
        medicalRecord.setMedications(medications);

        ArrayList<String> allergies = new ArrayList<>();
        allergies.add("Allergy1");
        allergies.add("Allergy2");
        medicalRecord.setAllergies(allergies);
        medicalrecords.add(medicalRecord);
        myData.setMedicalrecords(medicalrecords);

        // Appeler la méthode à tester
        String actualResult = myData.getFireInfo("123 Main Street");

        // Vérifier le résultat
        assertNotNull(actualResult);
    }
    @Test
    public void testPersonInfo() {
        // Créer des données de test
        MyData myData = new MyData();

        // Ajouter une personne avec des informations
        List<Person> persons = new ArrayList<>();
        Person person1 = new Person();
        person1.setFirstName("John");
        person1.setLastName("Doe");
        person1.setAddress("123 Main Street");
        person1.setPhone("123-456-7890");
        persons.add(person1);
        myData.setPersons(persons);

        // Ajouter un enregistrement médical pour la personne
        List<Medicalrecord> medicalrecords = new ArrayList<>();
        Medicalrecord medicalRecord = new Medicalrecord();
        medicalRecord.setFirstName("John");
        medicalRecord.setLastName("Doe");
        medicalRecord.setBirthdate("02/18/2000");

        ArrayList<String> medications = new ArrayList<>();
        medications.add("Medication1");
        medications.add("Medication2");
        medicalRecord.setMedications(medications);

        ArrayList<String> allergies = new ArrayList<>();
        allergies.add("Allergy1");
        allergies.add("Allergy2");
        medicalRecord.setAllergies(allergies);
        medicalrecords.add(medicalRecord);
        myData.setMedicalrecords(medicalrecords);

        // Appeler la méthode à tester
        String actualResult = myData.getPersonInfo("John", "Doe").toString();

        // Vérifier le résultat
        assertNotNull(actualResult);
    }
    @Test
    public void testGetEmailByCity() {
        // Créer des données de test
        MyData myData = new MyData();
        List<Person> persons = new ArrayList<>();

        // Ajouter des personnes avec des adresses e-mail pour une ville spécifique
        Person person1 = new Person();
        person1.setFirstName("John");
        person1.setLastName("Doe");
        person1.setCity("New York");
        person1.setEmail("john@example.com");
        persons.add(person1);

        Person person2 = new Person();
        person2.setFirstName("Alice");
        person2.setLastName("Smith");
        person2.setCity("New York");
        person2.setEmail("alice@example.com");
        persons.add(person2);

        // Ajouter des personnes avec des adresses e-mail pour une autre ville
        Person person3 = new Person();
        person3.setFirstName("Bob");
        person3.setLastName("Johnson");
        person3.setCity("Los Angeles");
        person3.setEmail("bob@example.com");
        persons.add(person3);

        myData.setPersons(persons);

        // Appeler la méthode à tester
        String actualResult = myData.getMailByCity("New York");

        // Vérifier le résultat
        String expectedResult = "[john@example.com, alice@example.com]";
        assertEquals(expectedResult, actualResult);
    }


}

