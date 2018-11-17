package com.ondraft.beer.brewery;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.ondraft.beer.DaggerApplicationComponent;
import com.ondraft.beer.R;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class BreweryDetailActivity extends AppCompatActivity {

    @Inject
    BreweryRestAdapter breweryRestAdapter;

    private Intent googleMapsIntent;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_brewery_detail);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        googleMapsIntent = new Intent(Intent.ACTION_VIEW);
        textView = findViewById(R.id.brewery_detail_name);

        DaggerApplicationComponent.builder().build().injectActivity(this);

        breweryRestAdapter.getBreweryById(getIntent().getIntExtra("id", 0))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::useBreweryProperties);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(view -> startActivity(googleMapsIntent));
    }

    private void useBreweryProperties(Brewery brewery) {
        textView.setText(brewery.getName());

        googleMapsIntent.setData(
                Uri.parse("geo:0,0?q=" +
                        brewery.getStreet() + " "
                        + brewery.getCity() + ", " + brewery.getState())
        );
    }

}
