package com.codetu.CurrencyConverterBackend.Models;

import okhttp3.*;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class GetFromExternalAPI {

    private String url;

    public GetFromExternalAPI(String s){
        url = s;
    }
    public GetFromExternalAPI(){}

    public String getFromUrl() throws IOException {
        final OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(url).build();
        try {
            Response response = client.newCall(request).execute();
            assert response.isSuccessful();
            return response.body().string();
        }
        catch (Exception e){
            System.out.println("error in getFromUrl : " + e);
            throw e;
        }
    }


}
