package com.example.goro.weatherapp.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.TextView;

import com.example.goro.weatherapp.R;
import com.example.goro.weatherapp.entity.ListJsonObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Goro on 20.02.2018.
 */

public class CustomArrayAdapter extends ArrayAdapter<ListJsonObject> {

    private final String MY_DEBUG_TAG = "ListJsonObjectAdapter";
    private List<ListJsonObject> items;
    private List<ListJsonObject> itemsAll;
    private List<ListJsonObject> suggestions;

    private int viewResourceId;

    public CustomArrayAdapter(Context context, int viewResourceId, List<ListJsonObject> items) {
        super(context, viewResourceId, items);
        this.items = items;
        this.itemsAll = new ArrayList<>(this.items);
        this.suggestions = new ArrayList<ListJsonObject>();
        this.viewResourceId = viewResourceId;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;

        if (v == null) {
            LayoutInflater vi = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = vi.inflate(viewResourceId, null);
        }

        ListJsonObject listJsonObject = items.get(position);

        if (listJsonObject != null) {
            TextView listJsonObjectNameLable = v.findViewById(R.id.text_suggestion);
            if (listJsonObjectNameLable != null) {
                listJsonObjectNameLable.setText(listJsonObject.getName() + "," + listJsonObject.getCountry());
            }
        }
        return v;
    }

    @NonNull
    @Override
    public Filter getFilter() {
        return nameFilter;
    }

    Filter nameFilter = new Filter() {

        @Override
        public String convertResultToString(Object resultValue) {
            String str = ((ListJsonObject) (resultValue)).getName();
            return str;
        }

        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            if (null != charSequence) {
                if (charSequence.length() == 3) {
                    for (ListJsonObject listJsonObject : itemsAll) {
                        if (listJsonObject.getName().toLowerCase().startsWith(charSequence.toString().toLowerCase())) {
                            suggestions.add(listJsonObject);
                        }
                    }
                }

                if (charSequence.length() >= 3) {
                    List<ListJsonObject> newData = new ArrayList<>(suggestions);
                    suggestions.clear();
                    for (ListJsonObject newList : newData) {
                        if (newList.getName().toLowerCase().startsWith(charSequence.toString().toLowerCase())) {
                            suggestions.add(newList);
                        }
                    }
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = suggestions;
                filterResults.count = suggestions.size();
                System.out.println("Counting new " + suggestions.size());
                return filterResults;
            } else {
                suggestions.clear();
                return new FilterResults();
            }
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            List<ListJsonObject> filteredList = (List<ListJsonObject>) filterResults.values;
            if (filterResults !=null && filterResults.count>0){
                clear();
                for (Iterator<ListJsonObject> data = filteredList.iterator(); data.hasNext();){
                    ListJsonObject obtainedJsonObject = data.next();
                    add(obtainedJsonObject);
                }
                notifyDataSetChanged();
            }
        }
    };
}
