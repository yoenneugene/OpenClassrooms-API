package com.openclassrooms.api.Service;

import com.openclassrooms.api.model.*;
import com.openclassrooms.api.model.Person;
import org.springframework.stereotype.Service;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class MyData {
    private List<Person> persons;
    private List<Firestation> firestations;
    private List<Medicalrecord> medicalrecords;







    public List<Firestation> getFirestations() {
        return firestations;
    }

    public void setFirestations(List<Firestation> firestations) {
        this.firestations = firestations;
    }

    public List<Medicalrecord> getMedicalrecords() {
        return medicalrecords;
    }

    public void setMedicalrecords(List<Medicalrecord> medicalrecords) {
        this.medicalrecords = medicalrecords;
    }


    public List<Person> getPersons() {
        return persons;
    }

    public void setPersons(List<Person> persons) {
        this.persons = persons;
    }
    public String getPersonsByStationNumber(String stationNumber) {
        List<String> addresses = firestations.stream()
                .filter(firestation -> firestation.getStation().equals(stationNumber))
                .map(Firestation::getAddress)
                .collect(Collectors.toList());

        // Récupérer les personnes couvertes par la caserne de pompiers donnée
        List<Person> coveredPersons = persons.stream()
                .filter(person -> addresses.contains(person.getAddress()))
                .collect(Collectors.toList());

        // Compter le nombre d'adultes et d'enfants
        long adultsCount = coveredPersons.stream()
                .filter(person -> calculateAge(getBirthdate(person.getFirstName())) >= 18)
                .count();
        long childrenCount = coveredPersons.size() - adultsCount;

        // Construire la liste des personnes couvertes avec les informations demandées
        StringBuilder result = new StringBuilder();
        result.append("Persons covered by firestation ").append(stationNumber).append(":\n");
        for (Person person : coveredPersons) {
            result.append("Name: ").append(person.getFirstName()).append(" ").append(person.getLastName()).append("\n");
            result.append("Address: ").append(person.getAddress()).append("\n");
            result.append("Phone: ").append(person.getPhone()).append("\n").append("\n");
        }
        result.append("Adults: ").append(adultsCount).append("\n");
        result.append("Children: ").append(childrenCount).append("\n");
return result.toString();
    }
    public String getChildAlert(String address) {
        List<Person> householdMembers = persons.stream()
                .filter(person -> person.getAddress().equals(address))
                .collect(Collectors.toList());

        List<String> childNames = new ArrayList<>();
        List<String> otherHouseholdMembers = new ArrayList<>();

        householdMembers.forEach(person -> {int age = calculateAge(getBirthdate(person.getFirstName()));
            if (age <= 18) {
                childNames.add(person.getFirstName() + " " + person.getLastName() + ", Age: " + age);
            } else {
                otherHouseholdMembers.add(person.getFirstName() + " " + person.getLastName());
            }
        });

        StringBuilder result = new StringBuilder();
        if (!childNames.isEmpty()) {
            result.append("Children at this address: ").append(String.join(", ", childNames)).append("\n");
        } else {
            result.append("No children at this address.\n");
        }

        if (!otherHouseholdMembers.isEmpty()) {
            result.append("Other household members: ").append(String.join(", ", otherHouseholdMembers)).append("\n");
        }

        return result.toString();
    }

    private String getBirthdate(String firstName) {
        return medicalrecords.stream()
                .filter(record -> record.getFirstName().equals(firstName))
                .map(Medicalrecord::getBirthdate)
                .findFirst()
                .orElse(null);
    }

    private int calculateAge(String birthdate) {

        if (birthdate == null) {
            return 18; // Valeur par défaut pour une date de naissance inconnue
        }
        LocalDate birth = LocalDate.parse(birthdate, DateTimeFormatter.ofPattern("MM/dd/yyyy"));
        LocalDate now = LocalDate.now();
        return (int) birth.until(now).getYears();
    }
    public String getPhoneNumber(String firestationnumber) {
        List<String> addresses = firestations.stream()
                .filter(firestation -> firestation.getStation().equals(firestationnumber))
                .map(Firestation::getAddress)
                .collect(Collectors.toList());

        List<String> phoneNumbers = persons.stream()
                .filter(person -> addresses.contains(person.getAddress()))
                .map(Person::getPhone)
                .collect(Collectors.toList());


        return phoneNumbers.toString();
    }
    public List<String> getHouseholdsByFirestation(String firestationNumber) {
        // Filtrer les adresses des résidents desservis par la caserne de pompiers correspondante
        List<String> addresses = firestations.stream()
                .filter(firestation -> firestation.getStation().equals(firestationNumber))
                .map(Firestation::getAddress)
                .collect(Collectors.toList());

        // Regrouper les personnes par adresse
        Map<String, List<Person>> personsByAddress = persons.stream()
                .filter(person -> addresses.contains(person.getAddress()))
                .collect(Collectors.groupingBy(Person::getAddress));

        // Construire la liste de tous les foyers desservis
        List<String> households = new ArrayList<>();
        personsByAddress.forEach((address, people) -> {
            StringBuilder householdInfo = new StringBuilder();
            householdInfo.append("Address: ").append(address).append("\n");
            people.forEach(person -> {
                householdInfo.append("Name: ").append(person.getFirstName()).append(" ").append(person.getLastName()).append("\n");
                householdInfo.append("Phone: ").append(person.getPhone()).append("\n");
                // Récupérer les informations médicales de la personne
                List<String> medicalInfo = getMedicalInfo(person.getFirstName());
                householdInfo.append("Medical Information: ").append(String.join(", ", medicalInfo)).append("\n");
                householdInfo.append("\n");
            });
            households.add(householdInfo.toString());
        });

        return households;
    }

    // Méthode pour récupérer les informations médicales d'une personne
    private List<String> getMedicalInfo(String firstName) {
        return medicalrecords.stream()
                .filter(record -> record.getFirstName().equals(firstName))
                .map(record -> "Medications: " + String.join(", ", record.getMedications()) +
                        ", Allergies: " + String.join(", ", record.getAllergies()))
                .collect(Collectors.toList());
    }
    public List<String> getPersonInfo(String firstName, String lastName) {
        // Récupérer toutes les personnes ayant le prénom et le nom fournis
        List<Person> matchingPersons = persons.stream()
                .filter(person -> person.getFirstName().equals(firstName) && person.getLastName().equals(lastName))
                .collect(Collectors.toList());

        // Construire les informations pour chaque personne
        List<String> personInfoList = new ArrayList<>();
        for (Person person : matchingPersons) {
            StringBuilder personInfo = new StringBuilder();
            personInfo.append("Name: ").append(person.getFirstName()).append(" ").append(person.getLastName()).append("\n");
            personInfo.append("Address: ").append(person.getAddress()).append("\n");
            personInfo.append("Age: ").append(calculateAge(getBirthdate(person.getFirstName()))).append("\n");
            personInfo.append("Email: ").append(person.getEmail()).append("\n");

            // Ajouter les antécédents médicaux de la personne
            List<String> medicalInfo = getMedicalInfo(person.getFirstName());
            if (!medicalInfo.isEmpty()) {
                personInfo.append("Medical History: \n");
                for (String info : medicalInfo) {
                    personInfo.append("  - ").append(info).append("\n");
                }
            }
            personInfoList.add(personInfo.toString());
        }

        return personInfoList;
    }
    public String getMailByCity(String city) {
        List<String> Personslist = persons.stream()
                .filter(person -> person.getCity().equals(city))
                .map(Person::getEmail)
                .collect(Collectors.toList());



        return Personslist.toString();
    }
    public String getFireInfo(String address) {
        // Récupérer le numéro de la caserne de pompiers desservant l'adresse donnée
        String firestationNumber = firestations.stream()
                .filter(firestation -> firestation.getAddress().equals(address))
                .map(Firestation::getStation)
                .findFirst()
                .orElse(null);

        // Récupérer les personnes vivant à l'adresse donnée
        List<Person> residents = persons.stream()
                .filter(person -> person.getAddress().equals(address))
                .collect(Collectors.toList());

        // Construire la liste des informations pour chaque personne
        StringBuilder result = new StringBuilder();
        result.append("Residents at address ").append(address).append(":\n");
        for (Person resident : residents) {
            result.append("Name: ").append(resident.getFirstName()).append(" ").append(resident.getLastName()).append("\n");
            result.append("Phone: ").append(resident.getPhone()).append("\n");
            result.append("Age: ").append(calculateAge(getBirthdate(resident.getFirstName()))).append("\n");

            // Ajouter les antécédents médicaux de la personne
            List<String> medicalInfo = getMedicalInfo(resident.getFirstName());
            if (!medicalInfo.isEmpty()) {
                result.append("Medical History: \n");
                for (String info : medicalInfo) {
                    result.append("  - ").append(info).append("\n");
                }
            }
            result.append("\n");
        }

        // Ajouter le numéro de la caserne de pompiers
        if (firestationNumber != null) {
            result.append("Firestation Number: ").append(firestationNumber).append("\n");
        }

        return result.toString();
    }


}






