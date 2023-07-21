package com.example.testprocessing;

import android.content.Context;
import android.webkit.JavascriptInterface;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;

import processing.core.PVector;

public class WebAppInterface {
    Context mContext;
    ArrayList<DiscreteFourierTransform.FourierCoefficient> coefficients;

    /** Instantiate the interface and set the context */
    WebAppInterface(Context c, ArrayList<DiscreteFourierTransform.FourierCoefficient> coefficients) {
        mContext = c;
        this.coefficients = coefficients;
    }

    /** Show a toast from the web page */
    @JavascriptInterface
    public void showToast(String toast) {
        Toast.makeText(mContext, toast, Toast.LENGTH_SHORT).show();
    }

    @JavascriptInterface
    public String getCoefficients(){
        Gson gson = new Gson();
        return gson.toJson(this.coefficients);
    }
}
