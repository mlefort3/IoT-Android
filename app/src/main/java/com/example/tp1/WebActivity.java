package com.example.tp1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;

public class WebActivity extends AppCompatActivity {

    private WebView webview;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        webview = new WebView(this);
        webview.loadUrl("http://iotlab.telecomnancy.eu/mapTN");
        setContentView(webview);
    }
}
