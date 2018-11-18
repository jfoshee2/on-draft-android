package com.ondraft.beer;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.SearchView;

import com.ondraft.beer.brewery.Brewery;
import com.ondraft.beer.brewery.BreweryArrayAdapter;
import com.ondraft.beer.brewery.BreweryDetailActivity;
import com.ondraft.beer.brewery.BreweryRestAdapter;
import com.ondraft.beer.brewery.StateNameNumberException;
import com.ondraft.beer.brewery.StateNameTooLongException;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

import static com.ondraft.beer.SearchFilter.*;

public class MainActivity extends AppCompatActivity {

    @Inject
    BreweryRestAdapter breweryRestAdapter;

    private ListView breweryListView;
    private Intent breweryDetailIntent;

    private SearchFilter filter = NAME; // Default search filter by name of brewery
    private View view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SearchView searchView = findViewById(R.id.brewery_search_view);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        view = findViewById(R.id.main_activity_view);

        breweryListView = findViewById(R.id.brewery_list_view);
        breweryDetailIntent = new Intent(this, BreweryDetailActivity.class);

        DaggerApplicationComponent.builder().build().injectActivity(this);

        breweryRestAdapter.getBreweries()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::adaptListView);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                if (filter == STATE) {
                    try {
                        breweryRestAdapter.getBreweriesByState(query)
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribeOn(Schedulers.io())
                                .subscribe(breweries -> adaptListView(breweries));
                    } catch (StateNameTooLongException e) {
                        Snackbar.make(view, e.message(), Snackbar.LENGTH_SHORT)
                                .setAction("Action", null).show();
                    } catch (StateNameNumberException e) {
                        Snackbar.make(view, e.message(), Snackbar.LENGTH_SHORT)
                                .setAction("Action", null).show();
                    }
                } else if (filter == CITY) {
                    breweryRestAdapter.getBreweriesByCity(query)
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribeOn(Schedulers.io())
                            .subscribe(breweries -> adaptListView(breweries));
                } else {
                    breweryRestAdapter.getBreweriesByName(query)
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribeOn(Schedulers.io())
                            .subscribe(breweries -> adaptListView(breweries));
                }

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false; // TODO: Implement Suggest Feature
            }
        });

    }

    private void adaptListView(List<Brewery> breweries) {
        breweryListView.setAdapter(new BreweryArrayAdapter(MainActivity.this, breweries));

        breweryListView.setOnItemClickListener((adapterView, view, i, l) -> {

            Brewery brewery = (Brewery) breweryListView.getItemAtPosition(i);

            breweryDetailIntent.putExtra("id", brewery.getId());

            startActivity(breweryDetailIntent);
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.search_by_state) {
            filter = STATE;
        } else if (id == R.id.search_by_city) {
            filter = CITY;
        } else { // the default search filter should be
            filter = NAME;
        }

        return super.onOptionsItemSelected(item);
    }
}
