package com.example.pdfprime;

import android.app.Application;
import android.content.Context;

/**
 * This class is used to get context from differents points from the app
 */
public class MyApp extends Application {
    private static MyApp instance;

    public static MyApp getInstance() {return instance; }

    public static Context getContext(){ return instance; }

    @Override
    public void onCreate() {
        instance = this;
        super.onCreate();
    }
}
