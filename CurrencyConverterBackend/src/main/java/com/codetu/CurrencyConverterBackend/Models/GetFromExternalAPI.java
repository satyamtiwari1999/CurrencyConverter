package com.codetu.CurrencyConverterBackend.Models;

import okhttp3.*;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class GetFromExternalAPI {

    private String url;

    public GetFromExternalAPI(String url){
        this.url = url;
    }
    public GetFromExternalAPI(){}

    public ResponseBody getFromUrl() throws IOException {
        final OkHttpClient client = new OkHttpClient();
        System.out.println("url to get from : " + url);
        Request request = new Request.Builder().url(url).build();
        try {
            Response response = client.newCall(request).execute();
            assert response.isSuccessful();
            return response.body();
        }
        catch (Exception e){
            System.out.println("error in getFromUrl : " + e);
            throw e;
        }
    }
}
