package com.example.goro.weatherapp.adapters;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;

import com.example.goro.weatherapp.R;

import org.w3c.dom.Text;

/**
 * Created by Goro on 20.02.2018.
 */

public class LocationHolders extends RecyclerView.ViewHolder {
    public TextView locationCity;
    public TextView weatherInformation;
    public TextView deleteText;
    public RadioButton selectableRadioButton;

    private static final String TAG = LocationHolders.class.getSimpleName();

    public LocationHolders(final View itemView, final List<LocationObject> locationObject) {
        super(itemView);
        locationCity = itemView.findViewById(R.id.city_location);
        weatherInformation = itemView.findViewById(R.id.temp_info);
        selectableRadioButton = itemView.findViewById(R.id.radio_button);
        deleteText = itemView.findViewById(R.id.delete_row);
        deleteText.setTextColor(Color.RED);
    }
}
