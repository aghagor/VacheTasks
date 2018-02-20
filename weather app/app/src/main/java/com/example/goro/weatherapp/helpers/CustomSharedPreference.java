package com.example.goro.weatherapp.helpers;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.widget.ThemedSpinnerAdapter;

import com.example.goro.weatherapp.entity.ListJsonObject;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by Goro on 20.02.2018.
 */

public class CustomSharedPreference {
    private SharedPreferences sharedPreferences;
    private Gson gson;

    public CustomSharedPreference(Context context) {
        sharedPreferences = context.getSharedPreferences(Helper.PREFS_TAG, Context.MODE_PRIVATE);
        gson = new Gson();
    }

    public void setDataFromSharedPreferences(String key, List<ListJsonObject> listObjects) {
        String json = gson.toJson(listObjects);
        sharedPreferences.edit().putString(key, json).apply();
    }

    public List<ListJsonObject> getAllDataObject(String key) {
        String stringObjects = sharedPreferences.getString(key, "");
        Type type = new TypeToken<List<ListJsonObject>>() {
        }.getType();
        return gson.fromJson(stringObjects, type);
    }

    public void setDataSourceIfPresent(boolean isData) {
        sharedPreferences.edit().putBoolean(Helper.IS_DATA_PRESENT, isData).apply();
    }

    public boolean getDataSourceIfPresent() {
        return sharedPreferences.getBoolean(Helper.IS_DATA_PRESENT, false);
    }

    public void setLocationInPreference(String cityName) {
        sharedPreferences.edit().putString(Helper.LOCATION_PREFS, cityName).apply();
    }

    public String getLocationInPreference() {
        return sharedPreferences.getString(Helper.LOCATION_PREFS, "");
    }
}
