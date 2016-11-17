package com.example.micke.lab3;

import android.os.AsyncTask;
import android.util.JsonReader;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by michael on 2016-11-17.
 */

public class SearchOperation extends AsyncTask<String, Void, String> {

    private SearchOperationInterface mListener;

    public SearchOperation(SearchOperationInterface mListener) {
        this.mListener  = mListener;
    }

    @Override
    protected String doInBackground(String... params) {

        URL url = null;
        HttpURLConnection urlConnection = null;
        InputStream in = null;

        byte[] contents = new byte[1024];
        int bytesRead = 0;
        String strFileContents = "";

        try {
            url = new URL(params[0]);
            urlConnection = (HttpURLConnection) url.openConnection();
            in = new BufferedInputStream(urlConnection.getInputStream());

            while((bytesRead = in.read(contents)) != -1) {
                strFileContents += new String(contents, 0, bytesRead);
            }
            in.close();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(urlConnection != null)
                urlConnection.disconnect();
        }

        return strFileContents;
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        JSONObject jsonObject = null;

        try {
            jsonObject = new JSONObject(result);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        if (mListener != null)
            try {
                mListener.putResults(jsonObject.getJSONArray("result"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
    }

    @Override
    protected void onPreExecute() {
    }

    @Override
    protected void onProgressUpdate(Void... values) {
    }


}
