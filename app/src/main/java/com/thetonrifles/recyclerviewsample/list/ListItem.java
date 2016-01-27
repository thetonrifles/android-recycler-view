package com.thetonrifles.recyclerviewsample.list;

abstract class ListItem {

    public static final int EMPTY_TYPE = 0;
    public static final int CONTACT_TYPE = 1;

    abstract public int getType();

}
