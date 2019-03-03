package com.example.tp1;
import android.app.Application;
import android.os.AsyncTask;
import android.util.JsonReader;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Array;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class FetchTask extends AsyncTask<String, Void, String> {

    public int code;
    MotesListActivity activity;

    public FetchTask(MotesListActivity activity) {
        this.activity = activity;
    }

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
                //System.out.println(buffer);
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
            try {
                ParseResult(s);
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println(s);

        }
    }

    public void ParseResult(String toparse) throws IOException {
        JsonReader reader = null;

        InputStream is = new ByteArrayInputStream( toparse.getBytes(  ) );
        try {
            reader = new JsonReader(new InputStreamReader(is, "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        List<String> test = new ArrayList<String>();
        int id = -1;
        String ipaddress = "";
        String macaddress = "";
        double lon = -1;
        double lat = -1;

        reader.beginObject();

        ArrayList<Mote> moteslist = new ArrayList<Mote>();

        while (reader.hasNext()) {
            String name = reader.nextName();
            if (name.equals("motesNb")) {
                id = reader.nextInt();
                System.out.println(id);
            } else if (name.equals("sender")) {
                moteslist = ReadMoteList(reader);
                System.out.println("on sort bien de la fonction");
            }

            else {
                reader.skipValue();
            }


            System.out.println("Le nom est : "+name);
        }
        reader.endObject();

        Mote[] array = moteslist.toArray(new Mote[moteslist.size()]);
        activity.RefreshMoteList(moteslist);
    }



    private ArrayList<Mote> ReadMoteList(JsonReader reader) throws IOException {

        ArrayList<Mote> moteslist = new ArrayList<Mote>();
        reader.beginArray();

        while (reader.hasNext()) {
            moteslist.add(ReadMote(reader));
        }

        reader.endArray();

        return moteslist;
    }

    private Mote ReadMote(JsonReader reader) throws IOException {

        reader.beginObject();


        int id = -1;
        String ipaddress = "";
        String macaddress = "";
        Double lat = -1.0;
        Double lon = -1.0;

        while (reader.hasNext()) {
            String name = reader.nextName();
            System.out.println("Le nom est : "+name);

            if (name.equals("id")) {
                id = reader.nextInt();
                System.out.println(id);
            } else if (name.equals("ipv6")) {
                ipaddress = reader.nextString();
                System.out.println(ipaddress);
            } else if (name.equals("mac")) {
                macaddress = reader.nextString();
                System.out.println(macaddress);
            } else if (name.equals("lat")) {
                lat = reader.nextDouble();
                System.out.println(lat);
            } else if (name.equals("lon")) {
                lon = reader.nextDouble();
                System.out.println(lon);
            }

            else {
                reader.skipValue();
            }


        }
        reader.endObject();

        return new Mote(id, ipaddress, macaddress, lat, lon);
    }
}