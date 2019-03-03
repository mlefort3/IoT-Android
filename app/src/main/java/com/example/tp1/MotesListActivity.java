package com.example.tp1;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class MotesListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.moteslistwindow);
        Log.d("MotesListActivity", "Activité de liste des motes");

        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        refreshList();

        /*Button b = (Button)findViewById(R.id.button3);
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

        Button web = (Button)findViewById(R.id.moteslist);
        web.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //new FetchTask().execute("http://iotlab.telecomnancy.eu/rest/info/motes");
            }
        });*/

        Button map = (Button)findViewById(R.id.mapbutton);
        map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                switchToMap();
            }
        });
    }

    private void switchToMap() {
        Intent intent = new Intent();
        intent.setClass(this.getApplicationContext(),WebActivity.class);
        this.startActivity(intent);
    }

    public void RefreshMoteList(ArrayList<Mote> moteslist) {
        final ListView listView = (ListView) findViewById(R.id.listview);
        Mote[] motes = moteslist.toArray( new Mote[moteslist.size()]);
        listView.setAdapter(new MoteListAdaptater(this.getApplicationContext(), motes));
    }

    private void refreshList() {

        FetchTask task = new FetchTask(this);
        task.execute("http://iotlab.telecomnancy.eu/rest/info/motes");
    }

    public boolean onOptionsItemSelected(MenuItem item){
        Intent myIntent = new Intent(getApplicationContext(), MainActivity.class);
        startActivityForResult(myIntent, 0);
        return true;
    }
}
