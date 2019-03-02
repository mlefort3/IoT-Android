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

    Timer timer = new Timer();

    public void onCreate(){
        final Handler handler = new Handler();
        TimerTask task = new TimerTask(){
            public void run() {
                handler.post(new Runnable() {
                    public void run() {
                        new FetchTask().execute("http://iotlab.telecomnancy.eu/rest/data/1/light1/last");
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

