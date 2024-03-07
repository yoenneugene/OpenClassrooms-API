package com.openclassrooms.api.model;

public record Personn() {
    private static String firstName;
    private static String lastName;
    private static String address;
    private static String city;
    private static String zip;
    private static String phone;
    private static String email;

    public static String getFirstName() {
        return firstName;
    }

    public static void setFirstName(String firstName) {
        Personn.firstName = firstName;
    }

    public static String getLastName() {
        return lastName;
    }

    public static void setLastName(String lastName) {
        Personn.lastName = lastName;
    }

    public static String getAddress() {
        return address;
    }

    public static void setAddress(String address) {
        Personn.address = address;
    }

    public static String getCity() {
        return city;
    }

    public static void setCity(String city) {
        Personn.city = city;
    }

    public static String getZip() {
        return zip;
    }

    public static void setZip(String zip) {
        Personn.zip = zip;
    }

    public static String getPhone() {
        return phone;
    }

    public static void setPhone(String phone) {
        Personn.phone = phone;
    }

    public static String getEmail() {
        return email;
    }

    public static void setEmail(String email) {
        Personn.email = email;
    }
}
