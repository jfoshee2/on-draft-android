package com.ondraft.beer;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import com.ondraft.beer.brewery.Brewery;
import com.ondraft.beer.brewery.BreweryArrayAdapter;
import com.ondraft.beer.brewery.BreweryClient;
import com.ondraft.beer.brewery.BreweryDetailActivity;
import com.ondraft.beer.brewery.BreweryRestAdapter;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    @Inject
    BreweryRestAdapter breweryRestAdapter;

    private ListView breweryListView;
    private Intent breweryDetailIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        breweryListView = findViewById(R.id.brewery_list_view);
        breweryDetailIntent = new Intent(this, BreweryDetailActivity.class);

        DaggerApplicationComponent.builder().build().injectActivity(this);

        breweryRestAdapter.getBreweries()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::adaptListView);

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
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
