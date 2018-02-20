package com.example.goro.weatherapp.adapters;

import android.content.Context;
import android.support.v4.widget.CompoundButtonCompat;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.RadioButton;

import com.example.goro.weatherapp.R;
import com.example.goro.weatherapp.database.DatabaseQuery;
import com.example.goro.weatherapp.helpers.CustomSharedPreference;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Goro on 20.02.2018.
 */

public class LoacationAdapter extends RecyclerView.Adapter<LocationHolders> implements CompoundButton.OnCheckedChangeListener {
    private List<LocationObject> locationObjects;
    protected Context context;
    private List<ViewEntityObject> allRadioButton;
    private DatabaseQuery query;
    int[] idList = new int[]{R.id.button0, R.id.button1, R.id.button2, R.id.button3, R.id.button4};
    private CustomSharedPreference sharedPreference;

    public LoacationAdapter(List<ListJsonObject> locationObjects, Context context) {
        this.locationObjects = locationObjects;
        this.context = context;
        allRadioButton = new ArrayList<ViewEntityObject>();
        query = new DatabaseQuery(context);
        sharedPreference = new CustomSharedPreference(context);
    }

    public LocationHolders onCreateViewHolder(ViewGroup parent, int viewType) {
        LocationHolders viewHolder = null;
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.location_list, parent, false);
        viewHolder = new LocationHolders(layoutView, locationObjects);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final LocationHolders holder, final int position) {
        holder.locationCity.setText(Html.fromHtml(locationObjects.get(position).getLocationCity()));
        holder.weatherInformation.setText(Html.fromHtml(locationObjects.get(position).getWeatherInformation()));
        holder.deleteText.setTag(R.id.TAG_KEY, String.valueOf(locationObjects.get(position).getId()));
        holder.deleteText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int databaseIndex = locationObjects.get(position).getId();
                if (!holder.selectableRadioButton.isChecked()) {
                    query.deleteLocation(databaseIndex);
                    locationObjects.remove(position);
                    notifyItemChanged(position);
                }
            }
        });
        String buttonId = sharedPreference.getLocationInPreference();
        System.out.println("Stored id " + buttonId);
        holder.selectableRadioButton.setOnCheckedChangeListener(this);
        setRadioButtonId(holder.selectableRadioButton, position);
        allRadioButton.add(new ViewEntityObject(Holder.selectableRadioButton, locationObjects.get(position).getLocationCity()));
        String storedCityLocation = sharedPreference.getLocationInPreference();
        if (allRadioButton.get(position).getRadioName().equals(storedCityLocation)) {
            holder.selectableRadioButton.setChecked(true);
        }

    }

    @Override
    public int getItemCount() {
        return this.locationObjects.size()
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        if (b) {
            RadioButton radioButton = (RadioButton) compoundButton;
            int checkedRadioId = radioButton.getId();
            for (int i = 0; i < allRadioButton.size(); i++) {
                if (allRadioButton.get(i).getRadioButton().getId() != sharedPreference) {
                    allRadioButton.get(i).getRadioButton().setChecked(false);
                } else {
                    String name = allRadioButton.get(i).getRadioButton();
                    sharedPreference.setLocationInPreference(name);
                }
            }
        }
    }

    private void setRadioButtonId(RadioButton mButton, int position) {
        if (position == 0) {
            mButton.setId(idList[position]);
        }
        if (position == 1) {
            mButton.setId(idList[position]);
        }
        if (position == 2) {
            mButton.setId(idList[position]);
        }
        if (position == 3) {
            mButton.setId(idList[position]);
        }
        if (position == 4) {
            mButton.setId(idList[position]);
        }
    }
}
