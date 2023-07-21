package com.example.testprocessing;

import androidx.appcompat.app.AppCompatActivity;
import androidx.webkit.WebViewAssetLoader;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.google.gson.Gson;

import java.util.ArrayList;

import processing.core.PVector;

public class DesmosWebView extends AppCompatActivity {
    WebView wvDesmos;
    WebSettings websettings;

    @SuppressLint({"SetJavaScriptEnabled", "JavascriptInterface"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_desmos_web_view);

        //Get the drawing ArrayList
        Bundle extra = getIntent().getBundleExtra("extra");
        ArrayList<PVector> drawing = (ArrayList<PVector>) extra.getSerializable("drawing");
        ArrayList<DiscreteFourierTransform.FourierCoefficient> coefficients = DiscreteFourierTransform.DFT(drawing);

        wvDesmos = (WebView) findViewById(R.id.wvDesmos);

        //WebSttings
        websettings = wvDesmos.getSettings();
        websettings.setJavaScriptEnabled(true);
        websettings.setDomStorageEnabled(true);
        //Build the assets loader
        final WebViewAssetLoader assetLoader = new WebViewAssetLoader.Builder()
                .addPathHandler("/assets/", new WebViewAssetLoader.AssetsPathHandler(this))
                .addPathHandler("/res/", new WebViewAssetLoader.ResourcesPathHandler(this))
                .build();

        wvDesmos.setWebViewClient(new LocalContentWebViewClient(assetLoader));

        wvDesmos.addJavascriptInterface(new WebAppInterface(this, coefficients),"WebAppInterface");

        wvDesmos.loadUrl("https://appassets.androidplatform.net/assets/index.html");
    }
}