package com.example.tp1;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.webkit.DownloadListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    //SharedPreferences prefs = getPreferences(Context.MODE_PRIVATE);
    //Boolean atboot = prefs.getBoolean("checked", false);

    public boolean button1clicked = false;


    public Data[] datalist;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("MainActivity", "Création de l'activité");

        final SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        boolean atboot = prefs.getBoolean("checked", false);


        //stopService(new Intent(this, MainService.class));

        if (atboot) {
            refreshList();
        }

        //final ListView listView = (ListView) findViewById(R.id.listview_main);
        //listView.setAdapter(new MoteListAdaptater(this.getApplicationContext(), this.moteList));


        Button web = (Button)findViewById(R.id.moteslist);
        web.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                switchToMoteList();
            }
        });

        Button preferences = (Button)findViewById(R.id.preferencesbutton);
        preferences.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                switchToPreferences();
            }
        });

        Button refresh = (Button)findViewById(R.id.refresh);
        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                refreshList();
            }
        });
    }

    private void switchToPreferences() {
        Intent intent = new Intent();
        intent.setClass(this.getApplicationContext(),PreferencesActivity.class);
        this.startActivity(intent);
    }

    private void refreshList() {

        FetchData taskdata = new FetchData(this);
        //taskdata.execute("http://iotlab.telecomnancy.eu/rest/data/1/temperature/last",
                //"http://iotlab.telecomnancy.eu/rest/data/1/humidity/last");
        taskdata.execute(new String[]{"http://iotlab.telecomnancy.eu/rest/data/1/temperature/last",
                "http://iotlab.telecomnancy.eu/rest/data/1/humidity/last",
                "http://iotlab.telecomnancy.eu/rest/data/1/battery_voltage/last",
                "http://iotlab.telecomnancy.eu/rest/data/1/light1/last",
                "http://iotlab.telecomnancy.eu/rest/data/1/light2/last"});
    }

    public void startService() {
        startService(new Intent(this, MainService.class));
    }

    public void stopService() {
        stopService(new Intent(this, MainService.class));
    }

    public void switchToMoteList() {
        Intent intent = new Intent();
        intent.setClass(this,MotesListActivity.class);
        startActivity(intent);
    }

    public void throwAlarm(int code) {
        Toast.makeText(getApplicationContext(), "Hello", Toast.LENGTH_LONG).show();
    }

    public void RefreshDataList(ArrayList<Data> datalist) {
        final ListView listView = (ListView) findViewById(R.id.datalist);
        Data[] datas = datalist.toArray( new Data[datalist.size()]);
        this.datalist = datas;
        listView.setAdapter(new DataListAdaptater(this.getApplicationContext(), datas));
    }

    public static void setMoteList(Mote[] motes) {
        //changeMoteList(motes);
    }
 }

