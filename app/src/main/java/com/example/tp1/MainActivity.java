package com.example.tp1;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.DownloadListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
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

import static android.content.Intent.ACTION_SEND;

public class MainActivity extends AppCompatActivity {


    //SharedPreferences prefs = getPreferences(Context.MODE_PRIVATE);
    //Boolean atboot = prefs.getBoolean("checked", false);

    public boolean button1clicked = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //startService(new Intent(this, ServiceGet.class));
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("MainActivity", "Création de l'activité");

        final SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        boolean atboot = prefs.getBoolean("checked", false);

        //stopService(new Intent(this, MainService.class));
        TextView textView5 = (TextView)findViewById(R.id.textView5);
        textView5.setText("Arrêté");

        final CheckBox checkBox = (CheckBox)findViewById(R.id.checkBox);
        if (atboot) {
            checkBox.setChecked(true);
        }
        else {
            checkBox.setChecked(false);
        }

        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                SharedPreferences.Editor editor = prefs.edit();

                if (isChecked) {
                    Log.d("MainActivity", "Bouton coché");
                    editor.putBoolean("checked", true);
                    editor.commit();
                }
                else {
                    Log.d("MainActivity", "Bouton décoché");
                    editor.putBoolean("checked", false);
                    editor.commit();
                }
            }
        });

        Button b = (Button)findViewById(R.id.button3);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!button1clicked) {
                    startService();
                    button1clicked=true;
                    TextView textView5 = (TextView)findViewById(R.id.textView5);
                    textView5.setText(" En cours");
                }
                else {
                    stopService();
                    button1clicked=false;
                    TextView textView5 = (TextView)findViewById(R.id.textView5);
                    textView5.setText(" Arrêté");
                }
            }
        });

        Button web = (Button)findViewById(R.id.webbutton);
        web.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //new FetchTask().execute("http://iotlab.telecomnancy.eu/rest/data/1/light1/last");
                Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);
               String[] recipients = new String[]{"yann.ricci@gmail.com", "",};
               emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL, recipients);
               emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Test");
               emailIntent.putExtra(android.content.Intent.EXTRA_TEXT, "Message");
               // emailIntent.setType("text/plain");
                emailIntent.setData(Uri.parse("mailto:"));
                emailIntent.setType("text/plain");
                startActivity(Intent.createChooser(emailIntent, "Send mail..."));
               // finish();

            }
        });
    }

    public void startService() {
        startService(new Intent(this, MainService.class));
    }

    public void stopService() {
        stopService(new Intent(this, MainService.class));
    }

    public void throwAlarm(int code) {
        Toast.makeText(getApplicationContext(), "Hello", Toast.LENGTH_LONG).show();
    }
}

