package com.thetonrifles.recyclerviewsample.twitter.rest;

import android.content.Context;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

class BasicRestClient {

    public static final String LOG_TAG = "REST";

    private static final int CONNECTION_TIMEOUT = (int) TimeUnit.SECONDS.toMillis(15);

    private static final int READ_TIMEOUT = (int) TimeUnit.SECONDS.toMillis(15);

    private enum HttpMethod {

        GET,
        POST

    }

    private Context mContext;

    public BasicRestClient(Context context) {
        mContext = context;
    }

    protected Context getContext() {
        return mContext;
    }

    /**
     * Implementation of sync get request.
     */
    protected HttpResponse getSync(String address, HttpHeader... headers) {
        return request(HttpMethod.GET, address, null, headers);
    }

    /**
     * Implementation of sync post request.
     */
    protected HttpResponse postSync(String address, String entity, HttpHeader... headers) {
        return request(HttpMethod.POST, address, entity, headers);
    }

    public HttpResponse request(HttpMethod method, String address, String entity, HttpHeader... headers) {
        HttpURLConnection conn = null;
        InputStream in = null;
        OutputStream out = null;
        HttpResponse response = null;
        try {
            // building api url
            URL url = new URL(address);
            Log.i(LOG_TAG, method + " URL " + url.toString());
            // establishing connection with server
            conn = (HttpURLConnection) url.openConnection();
            // defining connection params
            conn.setReadTimeout(READ_TIMEOUT);
            conn.setConnectTimeout(CONNECTION_TIMEOUT);
            conn.setRequestMethod(method.toString());
            conn.setDoInput(true);
            // building headers
            for (HttpHeader header : headers) {
                conn.setRequestProperty(header.getKey(), header.getValue());
            }
            if (method == HttpMethod.POST) {
                conn.setDoOutput(true);
                // building payload
                Log.i(LOG_TAG, method + " PAY " + entity);
                // writing payload
                out = conn.getOutputStream();
                BufferedWriter writer = new BufferedWriter(
                        new OutputStreamWriter(out, "UTF-8"));
                writer.write(entity);
                writer.flush();
                writer.close();
                out.close();
            }
            // getting response
            if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
                in = new BufferedInputStream(conn.getInputStream());
                // building json string from stream
                StringBuilder sb = new StringBuilder();
                int b;
                while ((b = in.read()) != -1) {
                    sb.append((char) b);
                }
                String json = sb.toString().replace("\n","");
                Log.d(LOG_TAG, method + " RES " + json);
                response = HttpResponse.success(json);
                in.close();
            } else {
                response = HttpResponse.failure(new Exception(conn.getResponseMessage()));
            }
        } catch (MalformedURLException ex) {
            Log.e(LOG_TAG, "malformed url");
            Log.e(LOG_TAG, ex.getMessage(), ex);
            response = HttpResponse.failure(ex);
        } catch (IOException ex) {
            Log.e(LOG_TAG, "I/O exception");
            Log.e(LOG_TAG, ex.getMessage(), ex);
            response = HttpResponse.failure(ex);
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException ex) {
                }
            }
            if (out != null) {
                try {
                    out.close();
                } catch (IOException ex) {
                }
            }
            if (conn != null) {
                conn.disconnect();
            }
        }
        return response;
    }

}