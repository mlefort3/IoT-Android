package com.example.tp1;

import android.util.Log;

import java.sql.Time;
import java.util.Timer;
import java.util.TimerTask;

public class TimerTaskClass extends TimerTask {

    @Override
    public void run() {
        Log.d("NewTask", "Nouvelle tâche");
    }
}
