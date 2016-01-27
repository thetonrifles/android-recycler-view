package com.thetonrifles.recyclerviewsample.list;

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
