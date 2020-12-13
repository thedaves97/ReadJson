package com.example.readjson;

import android.util.Log;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class JSONParser {

    static InputStream is = null;
    static JSONObject jObj = null;
    static String json = "";

    // constructor
    public JSONParser() {}

    public JSONObject getJSON(String s) throws JSONException {
        JSONObject jObj = new JSONObject(s);


        return jObj;
    }


}
