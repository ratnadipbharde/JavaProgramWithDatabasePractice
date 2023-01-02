package org.example;

public class Contact {
String firstName;
String lastName;
String city;
String state;
String phoneNumber;
String email;

    public Contact() {

    }

    public Contact(String firstName, String lastName, String city, String state, String phoneNumber, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.city = city;
        this.state = state;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "\nfirstName='" + firstName +
                "\nlastName=" + lastName +
                "\ncity='" + city +
                "\nState='" + state +
                "\nPhone Number='" + phoneNumber +
                "\nEmail='" + email +
                "\n";
    }
}
