package com.example.tp1;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import java.util.Timer;

public class MainService extends Service {

    private static Timer timer = new Timer();
    private static TimerTaskClass timertask = new TimerTaskClass();

   /* public void onCreate() {
        // Création du service
        Log.d("Start_Service", "Démarrage du service");
        timer.scheduleAtFixedRate(timertask, 0, 1000);
        timertask.run();
    }

    public void onDestroy() {
        // Destruction du service
        Log.d("Destroy_Service", "Arrêt du service");
    }*/

    public int onStartCommand(Intent intent, int flags, int startId) {
        return START_STICKY;
    }

    public IBinder onBind(Intent arg0) {
        return null;
    }
}