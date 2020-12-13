package com.example.readjson;

import android.content.res.Resources;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity
{
    ListView listView;
    ArrayList<String> arrayList = new ArrayList<>();
    ArrayAdapter<String> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.list_view);

        //INIZIALIZZO ARRAY JSON
        //SE SI TROVA [ ALLORA SIGNIFICA CHE STA INIZIANDO UN ARRAY

        String marker_array = "{\n" +
                "  \"markers\": [\n" +
                "    {\n" +
                "      \"name\": \"Rixos The Palm Dubai\",\n" +
                "      \"lat\": 25.1212,\n" +
                "      \"lon\": 55.1535\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"Shangri-La Hotel\",\n" +
                "      \"lat\": 25.2084,\n" +
                "      \"lon\": 55.2719\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"Grand Hyatt\",\n" +
                "      \"lat\": 25.2285,\n" +
                "      \"lon\": 55.3273\n" +
                "    }\n" +
                "  ]\n" +
                "}\n";

        //FETCH JSON
        try {
            JSONObject json = new JSONObject(marker_array);
            JSONArray jArray =  json.getJSONArray("markers");
            for (int i = 0;i<jArray.length();i++)
            {
                JSONObject obj = jArray.getJSONObject(i);
                String name = obj.getString("name");
                double lat = obj.getDouble("lat");
                double lon = obj.getDouble("lon");
                arrayList.add("Nome " + name + "\nLat " + lat + "\nLon " + lon);

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        //INIZIALIZZA ARRAY ADAPTER
        arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, arrayList);

        //SET ARRAY ADAPTER TO LISTVIEW
        listView.setAdapter(arrayAdapter);

        //Displayed toast message OnItemClick
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext()
                , arrayList.get(position), Toast.LENGTH_SHORT).show();
            }
        });

    }   //FINE onCreate

}       //FINE MAIN ACTIVITY
