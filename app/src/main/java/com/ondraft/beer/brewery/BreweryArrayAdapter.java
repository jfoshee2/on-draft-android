package com.ondraft.beer.brewery;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.ondraft.beer.R;

import java.util.List;

public class BreweryArrayAdapter extends ArrayAdapter<Brewery> {

    public BreweryArrayAdapter(@NonNull Context context, @NonNull List<Brewery> breweries) {
        super(context, R.layout.brewery_row, breweries);
    }

    @NonNull
    @Override
    public View getView(int i, View view, @NonNull ViewGroup viewGroup) {
        LayoutInflater layoutInflater = LayoutInflater.from(getContext());
        // @SuppressLint("ViewHolder") // Not sure why android does this....
        View customView = layoutInflater.inflate(R.layout.brewery_row, viewGroup, false);

        Brewery brewery = getItem(i); // Grab single menu item at a given index

        TextView itemName = customView.findViewById(R.id.brewery_name);
        itemName.setText(brewery != null ? brewery.getName() : null);

        return customView;
    }
}
