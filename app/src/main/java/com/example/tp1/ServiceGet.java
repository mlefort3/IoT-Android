package com.example.tp1;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;

import java.util.Timer;
import java.util.TimerTask;


public class ServiceGet extends Service {
    public ServiceGet(){


    }
    MainActivity activity;
    Timer timer = new Timer();

    public void onCreate(){
        final Handler handler = new Handler();
        TimerTask task = new TimerTask(){
            public void run() {
                handler.post(new Runnable() {
                    public void run() {
                    //                     new FetchTask().execute("http://iotlab.telecomnancy.eu/rest/data/1/light1/last");
                        FetchData taskdata = new FetchData(activity);
                        //taskdata.execute("http://iotlab.telecomnancy.eu/rest/data/1/temperature/last",
                        //"http://iotlab.telecomnancy.eu/rest/data/1/humidity/last");
                        taskdata.execute(new String[]{"http://iotlab.telecomnancy.eu/rest/data/1/temperature/last",
                                "http://iotlab.telecomnancy.eu/rest/data/1/humidity/last",
                                "http://iotlab.telecomnancy.eu/rest/data/1/battery_voltage/last",
                                "http://iotlab.telecomnancy.eu/rest/data/1/light1/last",
                                "http://iotlab.telecomnancy.eu/rest/data/1/light2/last"});
                        new FetchTask(new MotesListActivity()).execute("http://iotlab.telecomnancy.eu/rest/data/1/light1/last");
                    }
                });
            }
        };
        timer.schedule(task,0,1000);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    public void onDestroy(){
        timer.cancel();
    }
}

