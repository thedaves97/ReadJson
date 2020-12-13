package com.example.readjson;

import android.content.res.Resources;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
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

        //SE SI TROVA [ ALLORA SIGNIFICA CHE STA INIZIANDO UN ARRAY
        String s = "{\n" +
                "  \"markers\": [\n" +
                "    {\n" +
                "      \"name\": \"Rixos The Palm Dubai\",\n" +
                "      \"position\": [25.1212, 55.1535],\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"Shangri-La Hotel\",\n" +
                "      \"location\": [25.2084, 55.2719]\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"Grand Hyatt\",\n" +
                "      \"location\": [25.2285, 55.3273]\n" +
                "    }\n" +
                "  ]\n" +
                "}\n";


        //CREAZIONE JSON OBJ
        try {
            JSONObject json = new JSONObject(s);

            JSONArray marker = json.getJSONArray("markers");
            for (int i = 0; i < marker.length(); i++)
            {
                String n = marker.getJSONObject(i).getString("name");
                //System.out.println(n);

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }   //FINE onCreate

    public void loadMarker(View view)
    {
        Resources res = getResources();
        InputStream is = res.openRawResource(R.raw.marker);
        Scanner scanner = new Scanner(is);
        StringBuilder builder = new StringBuilder();
        while (scanner.hasNextLine())
        {
            builder.append(scanner.nextLine());
        }
        parseJson(builder.toString());

    }

    private void parseJson(String s)
    {
        TextView txt = (TextView) findViewById(R.id.text_display);
        StringBuilder builder = new StringBuilder();
        try {
            JSONObject root = new JSONObject(s);
            JSONArray mark = root.getJSONArray("markers");

            //builder.append("Name: ").append(mark.getString(0)).append("\n");
            //builder.append("Location: ").append(mark.getDouble(1)).append("\n");
            JSONArray coordinate = mark.getJSONArray(1);

            for(int i = 0; i<coordinate.length();i++)
            {
                JSONObject c = coordinate.getJSONObject(i);
                builder.append(c.getString("name")).append("\n");
                JSONArray latLon = new JSONArray(1);
                for(int j=0;j<latLon.length();j++)
                {
                    builder.append(latLon.getDouble(0)).append(" ").append(latLon.getDouble(1)).append("\n");
                }
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        txt.setText(builder.toString());
    }

}       //FINE MAIN ACTIVITY
