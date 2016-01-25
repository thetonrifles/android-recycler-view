package com.thetonrifles.recyclerviewsample.adapter;

import com.thetonrifles.recyclerviewsample.model.Contact;

public class ContactItem extends ListItem {

    private Contact contact;

    public ContactItem(Contact contact) {
        this.contact = contact;
    }

    public Contact getContact() {
        return contact;
    }

    @Override
    public int getType() {
        return CONTACT_TYPE;
    }

}
