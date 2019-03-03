package com.example.tp1;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.widget.CheckBox;
import android.widget.CompoundButton;

public class PreferencesActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.preferences_activity);
        Log.d("Preferences Activity", "Activité des préférences");

        final SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        boolean atboot = prefs.getBoolean("checked", false);

        //stopService(new Intent(this, MainService.class));

        final CheckBox checkBox = (CheckBox)findViewById(R.id.bootcheck);
        if (atboot) {
            checkBox.setChecked(true);
        }
        else {
            checkBox.setChecked(false);
        }


        SharedPreferences.Editor editor = prefs.edit();

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
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    /*public boolean onOptionsItemSelected(MenuItem item){
        Intent myIntent = new Intent(getApplicationContext(), MainActivity.class);
        startActivityForResult(myIntent, 0);
        return true;
    }*/
}
