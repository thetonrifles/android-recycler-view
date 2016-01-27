package com.thetonrifles.recyclerviewsample.twitter.rest;

import java.io.Serializable;

class HttpResponse implements Serializable {

    private static final long serialVersionUID = 1L;

    private boolean mSuccess;

    private String mJson;

    private Exception mException;

    public static HttpResponse success(String json) {
        return new HttpResponse(json);
    }

    public static HttpResponse failure(Exception ex) {
        return new HttpResponse(ex);
    }

    private HttpResponse(String json) {
        mSuccess = true;
        mJson = json;
    }

    private HttpResponse(Exception ex) {
        mSuccess = false;
        mException = ex;
    }

    public boolean isSuccess() {
        return mSuccess;
    }

    public Exception getException() {
        return mException;
    }

    public String getJson() {
        return mJson;
    }

}