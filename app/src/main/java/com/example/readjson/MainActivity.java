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
                "      \"name\": \"Jumping Jester\",\n" +
                "      \"type\": \"Pub\",\n" +
                "      \"address\": \"Via Roma, 102\",\n" +
                "      \"lat\": 45.0646803,\n" +
                "      \"lon\": 7.6955659\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"Clorophilla\",\n" +
                "      \"type\": \"Cocktail Bar\",\n" +
                "      \"address\": \"Piazza Vittorio Veneto, 17\",\n" +
                "      \"lat\": 45.0655545,\n" +
                "      \"lon\": 7.6944488\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"La Rhumerie 18\",\n" +
                "      \"type\": \"Cocktail Bar\",\n" +
                "      \"address\": \"Via Maria Vittoria, 49\",\n" +
                "      \"lat\": 45.0643651,\n" +
                "      \"lon\": 7.6943166\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"L\\\\'alchimista\",\n" +
                "      \"type\": \"Cocktail Bar\",\n" +
                "      \"address\": \"Via delle Rosine, 10\",\n" +
                "      \"lat\": 45.0650854,\n" +
                "      \"lon\": 7.6891726\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"Soho.23\",\n" +
                "      \"type\": \"Cocktail Bar\",\n" +
                "      \"address\": \"Piazza Vittorio Veneto, 23F\",\n" +
                "      \"lat\": 45.0643833,\n" +
                "      \"lon\": 7.6948529\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"La Drogheria\",\n" +
                "      \"type\": \"Cocktail Bar\",\n" +
                "      \"address\": \"Piazza Vittorio Veneto, 18/D\",\n" +
                "      \"lat\": 45.0642776,\n" +
                "      \"lon\": 7.6930938\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"Al Bona\",\n" +
                "      \"type\": \"Cocktail Bar\",\n" +
                "      \"address\": \"Via Alfonso Bonafus, 2\",\n" +
                "      \"lat\": 45.06377,\n" +
                "      \"lon\": 7.6926851\n" +
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
                String type = obj.getString("type");
                String add = obj.getString("address");
                double lat = obj.getDouble("lat");
                double lon = obj.getDouble("lon");

                arrayList.add("Nome " + name + "\nTipologia "+ type + "\nIndirizzo "+ add + "\nLat " + lat + "\nLon " + lon);

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
