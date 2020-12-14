package com.example.readjson;

import android.content.res.Resources;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
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

        InputStream is = getResources().openRawResource(R.raw.marker);
        Writer writer = new StringWriter();
        char[] buffer = new char[1024];

        try {
            Reader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));

            int n;
            while((n = reader.read(buffer)) !=-1)
            {
                writer.write(buffer, 0, n);
            } //FINE WHILE

        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String marker_array = writer.toString();


        //FETCH JSON
        try {

            JSONObject json = new JSONObject(marker_array);
            JSONArray jArray =  json.getJSONArray("markers");
            for (int i = 0;i<jArray.length();i++)
            {
                JSONObject obj = jArray.getJSONObject(i);
                String name = obj.getString("name");
                String type = obj.getString("type");
                String add = obj.getString("address");
                double lat = obj.getDouble("lat");
                double lon = obj.getDouble("lon");
                Marker m = new Marker();
                m.setName(name);
                m.setType(type);
                m.setAddress(add);
                m.setLat(lat);
                m.setLon(lon);

                arrayList.add("Nome " + m.getName() + "\nTipologia "+ m.getType() + "\nIndirizzo "+ m.getAddress() +
                        "\nLat " + m.getLat() + "\nLon " + m.getLon());

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
