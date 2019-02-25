package com.example.tp1;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    //SharedPreferences prefs = getPreferences(Context.MODE_PRIVATE);
    //Boolean atboot = prefs.getBoolean("checked", false);

    public boolean button1clicked = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("MainActivity", "Création de l'activité");


        //stopService(new Intent(this, MainService.class));
        TextView textView5 = (TextView)findViewById(R.id.textView5);
        textView5.setText(" Arrêté");

        /*CheckBox checkBox = (CheckBox)findViewById(R.id.checkbox1);
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    Log.d("MainActivity", "Bouton coché");
                }
                else {
                    Log.d("MainActivity", "Bouton décoché");
                }
            }
        });*/

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
    }

    public void startService() {
        startService(new Intent(this, MainService.class));
    }

    public void stopService() {
        stopService(new Intent(this, MainService.class));
    }

    public void preferences(View view) {
        startActivity(new PreferencesClass().getIntent());
    }
}

