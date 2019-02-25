package com.example.tp1;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class FetchTask extends AsyncTask<String, Void, String> {

    public int code;
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(String... strings) {
        InputStream inputStream = null;
        HttpURLConnection conn = null;

        String stringUrl = strings[0];
        System.out.println(strings[0]);
        try {
            URL url = new URL(stringUrl);
            conn = (HttpURLConnection) url.openConnection();
            conn.connect();
            int response = conn.getResponseCode();
            Log.d("serverCode", "Code de réponse du serveur : "+response);
            code = response;
            if (response != 200) {
                return null;
            }

            inputStream = conn.getInputStream();
            if (inputStream == null) {
                return null;
            }

            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
            BufferedReader reader = new BufferedReader(inputStreamReader);
            StringBuffer buffer = new StringBuffer();
            String line;
            while ((line = reader.readLine()) != null) {
                buffer.append(line);
                buffer.append("\n");
            }
                System.out.println(buffer);
            return new String(buffer);
        } catch (IOException e) {
            System.out.println("Erreur : "+e);
            return null;
        } finally {
            if (conn != null) {
                conn.disconnect();
            }
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException ignored) {
                }
            }
        }
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);

        if (s == null) {
            System.out.println("Erreur");
        } else {
            System.out.println(s);
        }
    }
}