package com.ondraft.beer;

import com.ondraft.beer.brewery.Brewery;
import com.ondraft.beer.brewery.BreweryClient;
import com.ondraft.beer.brewery.BreweryRestAdapter;
import com.ondraft.beer.brewery.StateNameNumberException;
import com.ondraft.beer.brewery.StateNameTooLongException;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;

import static io.reactivex.internal.operators.flowable.FlowableBlockingSubscribe.subscribe;
import static junit.framework.Assert.assertEquals;
import static org.mockito.Mockito.*;


public class BreweryRestAdapterUnitTest {

    @Mock
    private BreweryClient breweryClient;

    @InjectMocks
    private BreweryRestAdapter breweryRestAdapter;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetBreweries() {

        // Create data that is "supposed" to be returned from the server.
        List<Brewery> expected = new ArrayList<>();

        expected.add(
                Brewery.Builder()
                .withId(123)
                .withName("Brewery")
                .withStreet("Street name")
                .withCity("City")
                .withState("State")
                .withPostalCode("12334567-123456")
                .withPhoneNumber("123456789")
                .withWebsiteUrl("www.something.com")
                .build()
        );

        expected.add(
                Brewery.Builder()
                        .withId(456)
                        .withName("Beer!!!!!")
                        .withStreet("Street name")
                        .withCity("Some City")
                        .withState("Some State")
                        .withPostalCode("12334567-123456")
                        .withPhoneNumber("123456789")
                        .withWebsiteUrl("www.website.com")
                        .build()
        );

        // Prepare to listen for call to BreweryClient Interface
        when(breweryClient.getBreweries())
                .thenReturn(Observable.just(expected));

        // Compare results
        breweryRestAdapter.getBreweries().subscribe(actual -> {
            for (int i = 0; i < actual.size(); i++) {
                assertEquals(expected.get(i), actual.get(i));
            }
        });

    }

    @Test
    public void testGetBreweriesByCity() {

        // Create data that is "supposed" to be returned from the server.
        List<Brewery> expected = new ArrayList<>();

        expected.add(
                Brewery.Builder()
                        .withId(123)
                        .withName("Brewery")
                        .withStreet("Street name")
                        .withCity("City")
                        .withState("State")
                        .withPostalCode("12334567-123456")
                        .withPhoneNumber("123456789")
                        .withWebsiteUrl("www.something.com")
                        .build()
        );

        expected.add(
                Brewery.Builder()
                        .withId(123)
                        .withName("Brewery")
                        .withStreet("Street name")
                        .withCity("City")
                        .withState("State")
                        .withPostalCode("12334567-123456")
                        .withPhoneNumber("123456789")
                        .withWebsiteUrl("www.something.com")
                        .build()
        );

        // Prepare to listen for call to BreweryClient Interface
        when(breweryClient.getBreweriesByCity("Fletcher"))
                .thenReturn(Observable.just(expected));

        // Verify results
        breweryRestAdapter.getBreweriesByCity("Fletcher").subscribe(actual -> {
            for (int i = 0; i < actual.size(); i++) {
                assertEquals(expected.get(i), actual.get(i));
            }
        });


    }


    @Test
    public void testGetBreweriesByState() throws StateNameTooLongException, StateNameNumberException {

        // Create data that is "supposed" to be returned from the server.
        List<Brewery> expected = new ArrayList<>();

        expected.add(
                Brewery.Builder()
                        .withId(123)
                        .withName("Brewery")
                        .withStreet("Street name")
                        .withCity("City")
                        .withState("State")
                        .withPostalCode("12334567-123456")
                        .withPhoneNumber("123456789")
                        .withWebsiteUrl("www.something.com")
                        .build()
        );

        expected.add(
                Brewery.Builder()
                        .withId(123)
                        .withName("Brewery")
                        .withStreet("Street name")
                        .withCity("City")
                        .withState("State")
                        .withPostalCode("12334567-123456")
                        .withPhoneNumber("123456789")
                        .withWebsiteUrl("www.something.com")
                        .build()
        );

        // Prepare to listen for call to BreweryClient Interface
        when(breweryClient.getBreweriesByState("NC"))
                .thenReturn(Observable.just(expected));

        // Verify results
        breweryRestAdapter.getBreweriesByState("NC").subscribe(actual -> {
            for (int i = 0; i < actual.size(); i++) {
                assertEquals(expected.get(i), actual.get(i));
            }
        });

    }

    @Test(expected = StateNameTooLongException.class)
    public void testGetBreweriesByStateInvalidState_NameTooLong() throws StateNameTooLongException, StateNameNumberException {

        // Create data that is "supposed" to be returned from the server.
        List<Brewery> expected = new ArrayList<>();

        expected.add(
                Brewery.Builder()
                        .withId(123)
                        .withName("Brewery")
                        .withStreet("Street name")
                        .withCity("City")
                        .withState("State")
                        .withPostalCode("12334567-123456")
                        .withPhoneNumber("123456789")
                        .withWebsiteUrl("www.something.com")
                        .build()
        );

        expected.add(
                Brewery.Builder()
                        .withId(123)
                        .withName("Brewery")
                        .withStreet("Street name")
                        .withCity("City")
                        .withState("State")
                        .withPostalCode("12334567-123456")
                        .withPhoneNumber("123456789")
                        .withWebsiteUrl("www.something.com")
                        .build()
        );

        // Prepare to listen for call to BreweryClient Interface
        when(breweryClient.getBreweriesByState("Long State Name"))
                .thenReturn(Observable.just(expected));

        // Call function
        breweryRestAdapter.getBreweriesByState("Long State Name");

        // Verify that the API call was not made
        verify(breweryClient, times(0))
                .getBreweriesByState("Long State Name");
    }

    @Test(expected = StateNameNumberException.class)
    public void testGetBreweriesByStateInvalidState_Numbers() throws StateNameTooLongException, StateNameNumberException {

        // Create data that is "supposed" to be returned from the server.
        List<Brewery> expected = new ArrayList<>();

        expected.add(
                Brewery.Builder()
                        .withId(123)
                        .withName("Brewery")
                        .withStreet("Street name")
                        .withCity("City")
                        .withState("State")
                        .withPostalCode("12334567-123456")
                        .withPhoneNumber("123456789")
                        .withWebsiteUrl("www.something.com")
                        .build()
        );

        expected.add(
                Brewery.Builder()
                        .withId(123)
                        .withName("Brewery")
                        .withStreet("Street name")
                        .withCity("City")
                        .withState("State")
                        .withPostalCode("12334567-123456")
                        .withPhoneNumber("123456789")
                        .withWebsiteUrl("www.something.com")
                        .build()
        );

        // Prepare to listen for call to BreweryClient Interface
        when(breweryClient.getBreweriesByState("1234567"))
                .thenReturn(Observable.just(expected));

        // Call function
        breweryRestAdapter.getBreweriesByState("1234567");

        // Verify that the API call was not made
        verify(breweryClient, times(0)).getBreweriesByState("1234567");
    }

    @Test
    public void testGetBreweriesByName() {

        // Create data that is "supposed" to be returned from the server.
        List<Brewery> expected = new ArrayList<>();

        expected.add(
                Brewery.Builder()
                        .withId(123)
                        .withName("Brewery")
                        .withStreet("Street name")
                        .withCity("City")
                        .withState("State")
                        .withPostalCode("12334567-123456")
                        .withPhoneNumber("123456789")
                        .withWebsiteUrl("www.something.com")
                        .build()
        );

        expected.add(
                Brewery.Builder()
                        .withId(123)
                        .withName("Brewery")
                        .withStreet("Street name")
                        .withCity("City")
                        .withState("State")
                        .withPostalCode("12334567-123456")
                        .withPhoneNumber("123456789")
                        .withWebsiteUrl("www.something.com")
                        .build()
        );

        // Prepare to listen for call to BreweryClient Interface
        when(breweryClient.getBreweriesByName("Sample"))
                .thenReturn(Observable.just(expected));

        // Verify results
        breweryRestAdapter.getBreweriesByName("Sample").subscribe(actual -> {
            for (int i = 0; i < actual.size(); i++) {
                assertEquals(expected.get(i), actual.get(i));
            }
        });
    }

    @Test
    public void testGetBreweryById() {

        // Create data that is "supposed" to be returned from the server.
        Brewery expected = Brewery.Builder()
                .withId(123)
                .withName("Brewery")
                .withStreet("Street name")
                .withCity("City")
                .withState("State")
                .withPostalCode("12334567-123456")
                .withPhoneNumber("123456789")
                .withWebsiteUrl("www.something.com")
                .build();

        // Prepare to listen for call to BreweryClient Interface
        when(breweryClient.getBreweryById(12345678L))
                .thenReturn(Observable.just(expected));

        breweryRestAdapter.getBreweryById(12345678L)
                .subscribe(actual -> assertEquals(expected, actual));

    }

}
