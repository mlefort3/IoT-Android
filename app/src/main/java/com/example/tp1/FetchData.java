package com.example.tp1;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.os.AsyncTask;
import android.util.JsonReader;
import android.util.Log;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.Timestamp;
import java.util.ArrayList;
import java.util.List;


public class FetchData extends AsyncTask<String, Void, String> {

    public int code;
    MainActivity activity;

    public FetchData(MainActivity activity) {
        this.activity = activity;
    }

    public ArrayList<Data> datalist = new ArrayList<Data>();
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(String... strings) {
        InputStream inputStream = null;
        HttpURLConnection conn = null;

        String stringUrl = "";

        StringBuffer buffer = new StringBuffer();

        for (int i=0;i<5;i++) {
            stringUrl = strings[i];
            System.out.println(stringUrl);
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
                String line;
                while ((line = reader.readLine()) != null) {
                    buffer.append(line);
                    buffer.append("\n");
                }
                //System.out.println(buffer);
                //return new String(buffer);
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
            buffer.append(";");
        }
        return new String(buffer);
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);

        if (s == null) {
            System.out.println("Erreur");
        } else {
            try {
                String[] strSeparated = s.split(";");
                for (String str:strSeparated) {
                    ParseResult(str);
                }
                Data[] array = datalist.toArray(new Data[datalist.size()]);
                activity.RefreshDataList(datalist);
                //ParseResult(s);
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


        while (reader.hasNext()) {
            String name = reader.nextName();
            if (name.equals("data")) {
                ReadDataList(reader);
                //datalist = datalist + ReadDataList(reader);
                System.out.println("on sort bien de la fonction");
            }

            else {
                reader.skipValue();
            }


            System.out.println("Le nom est : "+name);
        }
        reader.endObject();


    }



    private ArrayList<Data> ReadDataList(JsonReader reader) throws IOException {

        ArrayList<Data> listdata = new ArrayList<Data>();
        reader.beginArray();

        while (reader.hasNext()) {
            datalist.add(ReadData(reader));
        }

        reader.endArray();

        return listdata;
    }

    private Data ReadData(JsonReader reader) throws IOException {

        reader.beginObject();


        long timestamp = -1;
        String label = "";
        double value = -1.0;
        String moteAddress = "";

        while (reader.hasNext()) {
            String name = reader.nextName();
            System.out.println("Le nom est : "+name);

            if (name.equals("timestamp")) {
                timestamp = reader.nextLong();
                System.out.println(timestamp);
            } else if (name.equals("label")) {
                label = reader.nextString();
                System.out.println(label);
            } else if (name.equals("value")) {
                value = reader.nextDouble();
                System.out.println(value);
                if(value>1){
                if(label.equals("light1")||label.equals("light2")){
                    System.out.println("OOOOOOOOOOOOOOPPPPPPP");
                    NotificationManager notif= (NotificationManager) activity.getSystemService(Context.NOTIFICATION_SERVICE);
                    Notification notify=new Notification.Builder
                            (activity.getApplicationContext()).setContentTitle("Motes TP").setContentText("Lumière allumée").
                            setContentTitle(label).setSmallIcon(R.drawable.ic_all_it_black_v24dp).build();

                    notify.flags |= Notification.FLAG_AUTO_CANCEL;
                    notif.notify(0, notify);

                    }
                }

            } else if (name.equals("mote")) {
                moteAddress = reader.nextString();
                System.out.println(moteAddress);
            }

            else {
                reader.skipValue();
            }


        }
        reader.endObject();

        return new Data(timestamp, label, value, moteAddress);
    }
}