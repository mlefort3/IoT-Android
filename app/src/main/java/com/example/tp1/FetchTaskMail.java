package com.example.tp1;

import android.os.AsyncTask;
import android.util.Log;

public class FetchTaskMail extends AsyncTask<String, Void, Void> {

    protected void onPreExecute(){
        super.onPreExecute();
    }

    @Override
    protected Void doInBackground(String... strings) {
        GMailSender sender = new GMailSender("yann.ricci@gmail.com", "My_1mp0rtant_mail");
        try {
            sender.sendMail("Test", "body", "yann.ricci@telecomnancy.net", "yann.ricci@gmail.com");
            Log.d("MainActivity","envoie?");
        }
        catch (Exception e) {
            Log.e("SendMail", e.getMessage(), e);
        }
        return null;
    }


}
