package com.thetonrifles.recyclerviewsample.list;

import com.thetonrifles.recyclerviewsample.model.Contact;

class ContactItem extends ListItem {

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
