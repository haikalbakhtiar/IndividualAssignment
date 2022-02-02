package com.example.individualassignment;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class list extends AppCompatActivity {

    dbhelper dbh;
    private ListView LV;

    ArrayList<Data> dataList;
    Data current;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        dbh = new dbhelper(this);
        dataList = new ArrayList<>();

        Cursor data = dbh.getData();
        int numRows = data.getCount();

        if (numRows == 0) {
            String message = "There is no data!";
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        } else {
            int n = 0;
            while (data.moveToNext()) {
                current = new Data(data.getInt(0), data.getFloat(1), data.getFloat(2), data.getFloat(3));
                dataList.add(n, current);

                n++;
            }

            LV = (ListView) findViewById(R.id.lstData);
            tripleAdapter adapter = new tripleAdapter(this, R.layout.triple_adapter, dataList);
            LV = (ListView) findViewById(R.id.lstData);
            LV.setAdapter(adapter);

            LV.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                @Override
                public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                    String message = "Load Successful!";
                    Toast.makeText(list.this, message, Toast.LENGTH_SHORT).show();
                    Data currentTarget = (Data) parent.getItemAtPosition(position);
                    Log.d("CLICK", "CLICKED ON A ROW");
                    //onBackPressed();
                    Intent i = new Intent(list.this, MainActivity.class);

                    i.putExtra("weight", String.valueOf(currentTarget.getWeight()));
                    i.putExtra("height", String.valueOf(currentTarget.getHeight()));
                    i.putExtra("BMI", String.valueOf(currentTarget.getBMI()));

                    startActivity(i);
                    return false;
                }
            });

        }
    }

}