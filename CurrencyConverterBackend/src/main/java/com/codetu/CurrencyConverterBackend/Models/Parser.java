package com.codetu.CurrencyConverterBackend.Models;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;

public class Parser {
    private String toParse;
    public Parser (String stringToParse){
        toParse = stringToParse;
    }
    public Map<String, Object> getJsonFromString() throws JSONException {
        System.out.println("to Parse : " + toParse);
        return new JSONObject(toParse).toMap();
    }
}
