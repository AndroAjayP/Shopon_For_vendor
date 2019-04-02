package com.example.shopon_4vendor.signup_login;

import android.app.Application;

import com.example.shopon_4vendor.DiscreteScrollViewOptions;


public class App extends Application {

    private static App instance;

    public static App getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        DiscreteScrollViewOptions.init(this);
    }
}
