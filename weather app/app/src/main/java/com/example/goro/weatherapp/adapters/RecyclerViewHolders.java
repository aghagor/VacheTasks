package com.example.goro.weatherapp.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.goro.weatherapp.R;

/**
 * Created by Goro on 20.02.2018.
 */

public class RecyclerViewHolders extends RecyclerView.ViewHolder {
    private static final String TAG = RecyclerViewHolders.class.getSimpleName();
    public TextView dayOfWeek;
    public ImageView weatherIcon;
    public TextView weatherResult;
    public TextView weatherResultSmall;

    public RecyclerViewHolders(View itemView) {
        super(itemView);
        dayOfWeek = itemView.findViewById(R.id.day_of_week);
        weatherIcon = itemView.findViewById(R.id.weather_icon);
        weatherResult = itemView.findViewById(R.id.weather_result);
        weatherResultSmall = itemView.findViewById(R.id.weather_result_small);
    }
}
