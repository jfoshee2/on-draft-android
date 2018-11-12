package com.ondraft.beer;

import android.app.Activity;

import com.ondraft.beer.brewery.BreweryRestAdapter;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    void injectActivity(MainActivity activity);
}
