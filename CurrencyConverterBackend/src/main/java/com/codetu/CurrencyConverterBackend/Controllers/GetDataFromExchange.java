package com.codetu.CurrencyConverterBackend.Controllers;

import com.codetu.CurrencyConverterBackend.Models.EnvVars;
import com.codetu.CurrencyConverterBackend.Models.GetFromExternalAPI;
import com.codetu.CurrencyConverterBackend.Models.Parser;

import java.util.Map;

public class GetDataFromExchange {
    private static String result;
    private static GetFromExternalAPI apiReq;
    private static String url;

    public static Map<String, Object> currenciesList;
    public static Map<String, Object> currencyValues;

    public static String getResult() {
        return result;
    }

    private static void setUrlToCurrencyEndpoint(){
        String currencyEndpoint = "currencies.json";
        url = EnvVars.getMainUrl() + "/" + currencyEndpoint + "?" + EnvVars.getKey() + "=" + EnvVars.getToken();
    }

    private static void setUrlToLatestEndpoint(){
        String currencyEndpoint = "latest.json";
        url = EnvVars.getMainUrl() + "/" + currencyEndpoint + "?" + EnvVars.getKey() + "=" + EnvVars.getToken();
    }

    public static void getCurrencies() {
        setUrlToCurrencyEndpoint();
        apiReq = new GetFromExternalAPI(url);
        try{
            result = apiReq.getFromUrl().string();
        }
        catch (Exception e) {
            System.out.println("Exception while getting currencies" + e.getMessage());
            result = e.getMessage();
        }
    }

    public static void getLatestCurrencyValues(){
        setUrlToLatestEndpoint();
        apiReq = new GetFromExternalAPI(url);
        try{
            result = apiReq.getFromUrl().string();
        }
        catch (Exception e) {
            System.out.println("Exception while getting latest values : " + e.getMessage());
            result = e.getMessage();
        }
    }
}
