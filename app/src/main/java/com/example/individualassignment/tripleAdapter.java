package com.example.individualassignment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class tripleAdapter extends ArrayAdapter<Data> {
    private LayoutInflater mInflater;
    private ArrayList<Data> data;
    private  int mViewResourceId;


    public tripleAdapter(Context context, int textViewResourceId, ArrayList<Data> data1){
        super(context, textViewResourceId, data1);
        data = data1;
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mViewResourceId = textViewResourceId;
    }

    public View getView(int position, View convertView, ViewGroup parents){
        convertView = mInflater.inflate(mViewResourceId, null);

        Data currentData = data.get(position);

        if(data != null){
            TextView first = (TextView) convertView.findViewById(R.id.firstCol);
            TextView second = (TextView) convertView.findViewById(R.id.secondCol);
            TextView third = (TextView) convertView.findViewById(R.id.thirdCol);

            if(first != null)
                first.setText(String.valueOf(currentData.getWeight()));

            if(second != null)
                second.setText(String.format("%.2f", currentData.getHeight()));

            if(third != null)
                third.setText(String.format("%.2f", currentData.getBMI()));

        }

        return  convertView;
    }
}
