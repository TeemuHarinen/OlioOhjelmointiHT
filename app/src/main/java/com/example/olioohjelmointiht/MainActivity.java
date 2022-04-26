package com.example.olioohjelmointiht;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        readTaxData();
    }

    private List<TaxSample> TaxSamples = new ArrayList<>();

    private void readTaxData() {
        InputStream is = getResources().openRawResource(R.raw.verotiedot10);
        BufferedReader reader = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));

        // Get rid of tittle line
        try {
            String extra = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        String line = "";
        try {
                while ((line = reader.readLine()) != null){
                    Log.d("MyActivity", "Line:" + line);
                    // Split by ','
                    String[] tokens = line.split(";");

                    // Read the data
                    TaxSample sample = new TaxSample();
                    sample.setID(tokens[1]);
                    sample.setName(tokens[2]);
                    sample.setLocation(tokens[3]);
                    sample.setTaxedIncome(String.valueOf((tokens[4])));
                    sample.setPayedTax(String.valueOf(tokens[5]));
                    TaxSamples.add(sample);

                    Log.d("MyActivity", "Just created "+ sample);
                }
            } catch (IOException e) {
                Log.wtf("MyActivity", "Error reading data on line " + line, e);
                e.printStackTrace();
            }

    }


}

