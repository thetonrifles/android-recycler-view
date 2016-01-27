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
    private static final String URL_TWEETS = URL_BASE + "/1.1/statuses/home_timeline.json";

    private static final String CONSUMER_KEY = "R5qVY6gziGpuR4au4figRQ";
    private static final String CONSUMER_SECRET = "ViYC1i3w30vDAO7L3C9z46pOBrQTNgMeUJlZJ490";
    private static final String ACCESS_TOKEN = "304297244-1XbtcCitPYnFivZ0VcpZh7CfUU107VTzUQyqdgOK";
    private static final String ACCESS_TOKEN_SECRET = "EyTuKffxLdWNwJT3ESCVV27glXUSsgvwQAneZk2rJUZ5n";

    public TwitterRestClient(Context context) {
        super(context);
    }


    public void loadTweets(final HttpGetRequestListener listener) {
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
                String token = getToken();
                // valid token?
                if (!TextUtils.isEmpty(token)) {
                    // let's perform request
                    HttpHeader[] headers = {
                            new HttpHeader("Authorization", "Basic " + token),
                            new HttpHeader("Content-Type", "application/json")
                    };
                    response = getSync(URL_TWEETS, headers);
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

//        conn.setRequestProperty("Content-Type", "application/json");
        //;(new Gson()).toJson(object);
    }

    /**
     * Retrieves Twitter token for executing
     * API requests. In case token was already
     * requested in past, it is loaded from
     * shared preferences.
     */
    public String getToken() {
        // trying to get token from shared preferences
        String token = getTokenFromStorage();
        // not in storage?
        if (TextUtils.isEmpty(token)) {
            // let's proceed by calling service
            String bearer = getBearer();
            if (bearer != null) {
                HttpHeader[] headers = {
                        new HttpHeader("Authorization", "Basic " + bearer),
                        new HttpHeader("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8")
                };
                HttpResponse response = postSync(URL_OAUTH, "grant_type=client_credentials", headers);
                if (response != null && response.isSuccess()) {
                    String json = response.getJson();
                    TokenWrapper wrapper = (new Gson()).fromJson(json, TokenWrapper.class);
                    Log.d(LOG_TAG, "Got token: " + wrapper.token);
                    token = wrapper.token;
                    // let's save token in storage for future usages
                    saveToken(token);
                }
            }
        }
        return token;
    }

    /**
     * Build bearer to be used for requesting
     * token to Twitter services.
     */
    private String getBearer() {
        try {
            String twitterUrlApiKey = URLEncoder.encode(CONSUMER_KEY, "UTF-8");
            String twitterUrlApiSecret = URLEncoder.encode(CONSUMER_SECRET, "UTF-8");
            String twitterKeySecret = twitterUrlApiKey + ":" + twitterUrlApiSecret;
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
    private String getTokenFromStorage() {
        SharedPreferences prefs = getContext().getSharedPreferences("twitter", Context.MODE_PRIVATE);
        return prefs.getString("token", null);
    }

    /**
     * Store token in shared preferences.
     */
    private void saveToken(String token) {
        SharedPreferences prefs = getContext().getSharedPreferences("twitter", Context.MODE_PRIVATE);
        prefs.edit().putString("token", token).apply();
    }

    private static class TokenWrapper implements Serializable {

        private static final long serialVersionUID = 1L;

        @SerializedName("access_token")
        private String token;

    }

}
