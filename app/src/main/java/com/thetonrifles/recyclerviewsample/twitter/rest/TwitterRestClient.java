package com.thetonrifles.recyclerviewsample.twitter.rest;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class TwitterRestClient extends BasicRestClient {

    public static final String LOG_TAG = "TWITTER";

    private static final String URL_BASE = "https://api.twitter.com";

    private static final String URL_OAUTH = URL_BASE + "/oauth2/token";
    private static final String URL_TWEETS = URL_BASE + "/1.1/statuses/user_timeline.json";

    private static final String CONSUMER_KEY = "";
    private static final String CONSUMER_SECRET = "";

    public TwitterRestClient(Context context) {
        super(context);
    }

    public void loadTweets(String name, HttpGetRequestListener listener) {
        getAsync(URL_TWEETS + "?count=10&screen_name=" + name, listener);
    }

    /**
     * Wrap the whole flow for executing an async request
     * to Twitter, starting from token retrieval till
     * final request execution.
     */
    private void getAsync(final String path, final HttpGetRequestListener listener) {
        (new AsyncTask<Void, Void, HttpResponse>() {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                if (listener != null) {
                    listener.onPrepare();
                }
            }

            @Override
            protected HttpResponse doInBackground(Void... voids) {
                HttpResponse response = null;
                String token = getBearerToken();
                // valid token?
                if (!TextUtils.isEmpty(token)) {
                    // let's perform request
                    HttpHeader[] headers = {
                            new HttpHeader("Authorization", "Bearer " + token),
                            new HttpHeader("Content-Type", "application/json")
                    };
                    response = getSync(path, headers);
                }
                return response;
            }

            @Override
            protected void onPostExecute(HttpResponse response) {
                super.onPostExecute(response);
                if (listener != null) {
                    if (response.isSuccess()) {
                        listener.onSuccess(response.getJson());
                    } else {
                        listener.onFailure(response.getException());
                    }
                }
            }

        }).execute();
    }

    /**
     * Retrieves Twitter token for executing
     * API requests. In case token was already
     * requested in past, it is loaded from
     * shared preferences.
     */
    public String getBearerToken() {
        // trying to get token from shared preferences
        String token = getBearerTokenFromStorage();
        // not in storage?
        if (!TextUtils.isEmpty(token)) {
            Log.d(LOG_TAG, "Got token from local storage: " + token);
        } else {
            // let's proceed by calling service
            String bearer = getBasicToken();
            if (bearer != null) {
                HttpHeader[] headers = {
                        new HttpHeader("Authorization", "Basic " + bearer),
                        new HttpHeader("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8")
                };
                HttpResponse response = postSync(URL_OAUTH, "grant_type=client_credentials", headers);
                if (response != null && response.isSuccess()) {
                    String json = response.getJson();
                    BearerTokenWrapper wrapper = (new Gson()).fromJson(json, BearerTokenWrapper.class);
                    Log.d(LOG_TAG, "Got token: " + wrapper.token);
                    token = wrapper.token;
                    // let's save token in storage for future usages
                    saveBearerToken(token);
                    Log.d(LOG_TAG, "Token saved in local storage");
                }
            }
        }
        return token;
    }

    /**
     * Build basic token to be used for requesting
     * bearer token to Twitter services.
     */
    private String getBasicToken() {
        if (TextUtils.isEmpty(CONSUMER_KEY) || TextUtils.isEmpty(CONSUMER_SECRET)) {
            Log.e(LOG_TAG, "consumer key and/or secret are undefined");
            return null;
        }
        try {
            String twitterKeySecret = URLEncoder.encode(CONSUMER_KEY, "UTF-8") +
                    ":" + URLEncoder.encode(CONSUMER_SECRET, "UTF-8");
            return Base64.encodeToString(twitterKeySecret.getBytes(), Base64.NO_WRAP);
        } catch (UnsupportedEncodingException ex) {
            Log.e(LOG_TAG, ex.getMessage());
            return null;
        }
    }

    /**
     * Retrieves token from shared preferences,
     * eventually returning null if not available.
     */
    private String getBearerTokenFromStorage() {
        SharedPreferences prefs = getContext().getSharedPreferences("twitter", Context.MODE_PRIVATE);
        return prefs.getString("token", null);
    }

    /**
     * Store token in shared preferences.
     */
    private void saveBearerToken(String token) {
        SharedPreferences prefs = getContext().getSharedPreferences("twitter", Context.MODE_PRIVATE);
        prefs.edit().putString("token", token).apply();
    }

    private static class BearerTokenWrapper implements Serializable {

        private static final long serialVersionUID = 1L;

        @SerializedName("access_token")
        private String token;

    }

}
