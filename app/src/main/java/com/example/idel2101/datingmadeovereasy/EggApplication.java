package com.example.idel2101.datingmadeovereasy;

import android.app.Application;

import com.backendless.Backendless;

public class EggApplication extends Application {
    public static final String APPLICATION_ID = "26E1C796-1F43-E60B-FF11-6C5BBFAD5700";
    public static final String API_KEY = "A6D0355A-D14A-A15B-FF0B-79980DCE6100";
    public static final String SERVER_URL = "https://api.backendless.com";

    @Override
    public void onCreate() {
        super.onCreate();
       Backendless.setUrl(SERVER_URL);
        Backendless.initApp(getApplicationContext(), APPLICATION_ID, API_KEY);
    }
}
