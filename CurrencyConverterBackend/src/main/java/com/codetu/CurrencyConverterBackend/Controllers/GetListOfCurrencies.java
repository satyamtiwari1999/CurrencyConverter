package com.codetu.CurrencyConverterBackend.Controllers;

import com.codetu.CurrencyConverterBackend.Models.EnvVars;
import com.codetu.CurrencyConverterBackend.Models.GetFromExternalAPI;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GetListOfCurrencies {
    private String result;
    private String url;
    private String currencyEndpoint = "currencies.json";

    @GetMapping("/currencies")
    public String getListOfCurrencies() {
        setUrl();
        System.out.println("url to be hit : " + url);
        getCurrencies();
        return result;
    }

    private void setUrl(){
        url = EnvVars.getMainUrl() + "/" + currencyEndpoint + "?" + EnvVars.getKey() + "=" + EnvVars.getToken();
    }

    private void getCurrencies() {
        GetFromExternalAPI apiReq = new GetFromExternalAPI(url);
        try{
            System.out.println("All Currencies Response \n");
            System.out.println(apiReq.getFromUrl());
            System.out.println("\n response ends ... ");

            result = apiReq.getFromUrl();
        }
        catch (Exception e) {
            System.out.println("Exception while getting currencies" + e.getMessage());
            result = e.getMessage();
        }
    }
}
