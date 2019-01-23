package com.example.idel2101.datingmadeovereasy;

import android.app.Application;

import com.backendless.Backendless;

public class EggApplication extends Application {
    public static final String APPLICATON_ID = "DFC3BBB7-51E6-DBDF-FF95-2910EA68D800";
    public static final String API_KEY = "C23043C9-B5C5-2EBF-FFBC-158A32F24600";
    public static final String SERVER_URL = "https://api.backendless.com";

    @Override
    public void onCreate() {
        super.onCreate();
        Backendless.setUrl(SERVER_URL);
        Backendless.initApp(getApplicationContext(), APPLICATON_ID, API_KEY);
    }
}
