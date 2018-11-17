package com.ondraft.beer;

import com.ondraft.beer.brewery.BreweryDetailActivity;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    void injectActivity(MainActivity activity);

    void injectActivity(BreweryDetailActivity activity);
}
