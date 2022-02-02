package com.example.individualassignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.View;

import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    private Button calculate;
    private Button about, btnView;
    private EditText weight, height;
    private TextView results;
    dbhelper dbh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        about = (Button) findViewById(R.id.buttonAb);

        about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMainActivity2();
            }
        });

        calculate=findViewById(R.id.buttonCalc);
        weight = findViewById(R.id.fillweight);
        height = findViewById(R.id.fillheight);
        results = findViewById(R.id.resultTxt);

        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String getW=weight.getText().toString();
                String getH=height.getText().toString();

                float W=Float.parseFloat(getW);
                float H=Float.parseFloat(getH);

                float newH = H/100;
                float bmi = W/(newH*newH);



                if(bmi<18.5)
                {
                    results.setText("Category: Underweight\n Your Health Risk: Malnutrition Risk");
                }

                else if (bmi>18.5 && bmi<24.9)
                {
                    results.setText("Category: Underweight\nYour BMI is>18.5 && BMI<24.9\nYour Health Risk: Low Risk");
                }

                else if (bmi>25 && bmi<29.9)
                {
                    results.setText("Category: Overweight\nYour BMI>25 && BMI<29.9\nYour Health Risk: Enchanced Risk");
                }

                else if (bmi>30 && bmi<34.9)
                {
                    results.setText("Category: Moderately Obese\nYour BMI>30 && BMI<34.9\nYour Health Risk: Medium Risk");
                }

                else if (bmi>35 && bmi<39.9)
                {
                    results.setText("Category: Severely Obese\nYour BMI>35 && BMI<39.9\nYour Health Risk: High Risk");
                }

                else if (bmi>40)
                {
                    results.setText("Category: Very Severely Obese\nYour BMI>40\nYour Health Risk: Very High Risk");
                }
                dbh = new dbhelper(MainActivity.this);
                Boolean insertion = dbh.insertRow(String.valueOf(W), String.valueOf(H), String.valueOf(bmi));

                if(insertion) {
                    String message = "Inserted!";
                    Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
                }else {
                    String message = "Insertion failed!";
                    Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnView = (Button) findViewById(R.id.btnView);
        btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, list.class);
                startActivity(i);
            }
        });

        Bundle extras = getIntent().getExtras();

        if (extras != null) {

            weight.setText(extras.getString("weight"));
            height.setText(extras.getString("height"));
            weight.setText(String.format("%.2f", Double.parseDouble(extras.getString("BMI"))));

        }
    }
    public void openMainActivity2(){
        Intent intent = new Intent(this, MainActivity2.class);
        startActivity(intent);
    }
}
