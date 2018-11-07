package com.ondraft.beer.brewery;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Interface implemented by Retrofit to make REST API calls.
 */
public interface BreweryClient {

    /**
     * Method to be implemented by Retrofit to make REST API
     * call to Open Brewery DB server
     * @return general list of breweries from Open Brewery DB
     */
    @GET
    Observable<List<Brewery>> getBreweries();

    /**
     * Method to be implemented by Retrofit to make REST API
     * call to Open Brewery DB server
     * @param city - parameter to search breweries by
     * @return list of breweries from Open Brewery DB filtered by city
     */
    @GET
    Observable<List<Brewery>> getBreweriesByCity(@Query("by_city") String city);

    /**
     * Method to be implemented by Retrofit to make REST API
     * call to Open Brewery DB server
     * @param state - parameter to search breweries by
     * @return list of breweries from Open Brewery DB filtered by state
     */
    @GET
    Observable<List<Brewery>> getBreweriesByState(@Query("by_state") String state);

    /**
     * Method to be implemented by Retrofit to make REST API
     * call to Open Brewery DB server
     * @param name - parameter to search breweries by
     * @return list of breweries from Open Brewery DB filtered by name
     */
    @GET
    Observable<List<Brewery>> getBreweriesByName(@Query("by_name") String name);

    /**
     * Method to be implemented by Retrofit to make REST API
     * call to Open Brewery DB server
     * @param id - unique id of the brewery to be returned
     * @return single instance of the Brewery class by id
     */
    @GET("{id}")
    Observable<Brewery> getBreweryById(@Path("id") long id);
}
