package com.example.tp1;

import android.os.AsyncTask;
import android.util.Log;
import com.example.tp1.JSSEProvider;

public class FetchTaskMail extends AsyncTask<String, Void, Void> {
    private String nom;

    public FetchTaskMail(String name){
        this.nom=name;
    }
    protected void onPreExecute(){
        super.onPreExecute();
    }

    @Override
    protected Void doInBackground(String... strings) {

        GMailSender sender = new GMailSender("JavaMail.TP@gmail.com", "V3ry_strong_pw");
        try {
            sender.sendMail("TP Androïd", "La lumière "+nom+" est alumée" , "maxence.lefort@telecomnancy.net", "m.lefort3@gmail.com");
            Log.d("MainActivity","envoie?");
        }
        catch (Exception e) {
            Log.e("SendMail", e.getMessage(), e);
        }
        return null;
    }


}
