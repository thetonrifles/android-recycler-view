package com.thetonrifles.recyclerviewsample.twitter.rest;

public abstract class HttpGetRequestListener {

    public void onPrepare() {
    }

    abstract public void onSuccess(String result);

    abstract public void onFailure(Exception ex);

}