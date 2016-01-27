package com.thetonrifles.recyclerviewsample.model;

public class Contact {

    private String name;
    private String surname;

    public Contact(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || !(o instanceof Contact)) {
            return false;
        }
        Contact other = (Contact) o;
        return name.equals(other.getName()) &&
                surname.equals(other.getSurname());
    }

}
