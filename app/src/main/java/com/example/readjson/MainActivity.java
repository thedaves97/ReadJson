package com.example.readjson;

import android.content.res.Resources;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final String coor = "coordinates";
        final String name = "name";
        final String type = "type";
        final String address = "address";


        String s = "{\n" +
                "  \"type\": \"FeatureCollection\",\n" +
                "  \"features\": [\n" +
                "    {\"type\":\"Feature\", \"geometry\":{ \"type\":\"Point\", \"coordinates\":[7.6955659, 45.0646803]}, \"properties\":{\"name\":\"Jumping Jester\",\"type\":\"pub\",\"address\": \"Via Roma, 102\", \"city\":\"Torino\"}},\n" +
                "    {\"type\":\"Feature\",\"geometry\":{ \"type\":\"Point\", \"coordinates\":[7.6944488, 45.0655545]}, \"properties\":{\"name\":\"Clorophilla\", \"type\":\"Cocktail Bar\", \"address\": \"Piazza Vittorio Veneto, 17\", \"city\":\"Torino\"}},\n" +
                "    {\"type\":\"Feature\",\"geometry\":{ \"type\":\"Point\", \"coordinates\":[7.659841, 45.071133]}, \"properties\":{\"name\":\"Irish Pub\", \"type\":\"Pub\", \"address\": \"Viale Nizza, 38\", \"city\":\"Torino\"}},\n" +
                "    {\"type\":\"Feature\",\"geometry\":{ \"type\":\"Point\", \"coordinates\":[7.6943166, 45.0643651]}, \"properties\":{\"name\":\"La Rhumerie 18\", \"type\":\"Cocktail Bar\", \"address\": \"Via Maria Vittoria, 49\", \"city\":\"Torino\"}},\n" +
                "    {\"type\":\"Feature\",\"geometry\":{ \"type\":\"Point\", \"coordinates\":[7.6891726, 45.0650854]}, \"properties\":{\"name\":\"L alchimista\", \"type\":\"Cocktail Bar\", \"address\": \"Via delle Rosine, 10\", \"city\":\"Torino\"}},\n" +
                "    {\"type\":\"Feature\",\"geometry\":{ \"type\":\"Point\", \"coordinates\":[7.6948529, 45.0643833]}, \"properties\":{\"name\":\"Soho.23\", \"type\":\"Cocktail Bar\", \"address\": \"Piazza Vittorio Veneto, 23F\", \"city\":\"Torino\"}},\n" +
                "    {\"type\":\"Feature\",\"geometry\":{ \"type\":\"Point\", \"coordinates\":[7.6930938, 45.0642776 ]}, \"properties\":{\"name\":\"La Drogheria\", \"type\":\"Cocktail Bar\", \"address\": \"Piazza Vittorio Veneto, 18/D\", \"city\":\"Torino\"}},\n" +
                "    {\"type\":\"Feature\",\"geometry\":{ \"type\":\"Point\", \"coordinates\":[7.6926851, 45.06377]}, \"properties\":{\"name\":\"Al Bona\", \"type\":\"Cocktail Bar\", \"address\": \"Via Alfonso Bonafus, 2\", \"city\":\"Torino\"}}\n" +
                "      ]\n" +
                "}";

        JSONParser jParser = new JSONParser();

        JSONArray locali = null;

        JSONObject json = null;
        StringBuilder str = new StringBuilder();

        try {
            json = jParser.getJSON(s);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        try
        {
            // Getting Array of Contacts
            locali = json.getJSONArray(type);

            // looping through All Contacts
            for (int i = 0; i < locali.length(); i++) {
                JSONObject c = locali.getJSONObject(i);

                // Storing each json item in variable
                String coordinates = c.getString(coor);
                String nome = c.getString(name);
                String indirizzo = c.getString(address);
                str.append(nome);

            }       //FINE CICLO FOR
        }
        catch(JSONException e) {
            e.printStackTrace();
        }   //FINE CATCH



    }   //FINE onCreate

}       //FINE MAIN ACTIVITY
