package com.thetonrifles.recyclerviewsample.twitter.rest;

public abstract class HttpPostRequestListener {

    public void onPrepare() {
    }

    abstract public void onSuccess(String result);

    abstract public void onFailure(Exception ex);

}