package com.ondraft.beer.brewery;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import retrofit2.Retrofit;

/**
 * TODO: Write JavaDoc for this class
 */
public class BreweryRestAdapter {

    private final BreweryClient breweryClient;

    @Inject
    public BreweryRestAdapter(BreweryClient breweryClient) {
        this.breweryClient = breweryClient;
    }

    public Observable<List<Brewery>> getBreweries() {
        return breweryClient.getBreweries();
    }

    public Observable<List<Brewery>> getBreweriesByCity(String city) {
        return breweryClient.getBreweriesByCity(city);
    }

    public Observable<List<Brewery>> getBreweriesByState(String state)
            throws StateNameTooLongException, StateNameNumberException {

        if (state.matches("[0-9]+")) {
            throw new StateNameNumberException();
        }

        if (state.length() > 2) {
            throw new StateNameTooLongException();
        }

        return breweryClient.getBreweriesByState(state);
    }

    public Observable<List<Brewery>> getBreweriesByName(String name) {
        return breweryClient.getBreweriesByName(name);
    }

    public Observable<Brewery> getBreweryById(long id) {
        return breweryClient.getBreweryById(id);
    }
}
