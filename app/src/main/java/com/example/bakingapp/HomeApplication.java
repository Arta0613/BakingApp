package com.example.bakingapp;

import android.app.Application;

import androidx.annotation.NonNull;

import com.example.bakingapp.di.AppContainer;

public class HomeApplication extends Application {

    @NonNull
    private final AppContainer appContainer = new AppContainer();

    @NonNull
    public final AppContainer getAppContainer() {
        return appContainer;
    }
}