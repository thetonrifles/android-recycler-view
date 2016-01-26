package com.thetonrifles.recyclerviewsample;

import com.thetonrifles.recyclerviewsample.model.Contact;

import java.util.ArrayList;

public class Contacts {

    public static ArrayList<Contact> getAll() {
        ArrayList<Contact> contacts = new ArrayList<>();
        contacts.add(new Contact("Giorgio", "Bianchi"));
        contacts.add(new Contact("Mario", "Rossi"));
        contacts.add(new Contact("Giuseppe", "Verdi"));
        contacts.add(new Contact("Marco", "Gialli"));
        contacts.add(new Contact("Andrea", "Mainardi"));
        contacts.add(new Contact("Rocco", "Tano"));
        return contacts;
    }

}
