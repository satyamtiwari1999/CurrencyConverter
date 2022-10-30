package com.codetu.CurrencyConverterBackend.Models;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.stereotype.Component;

@Component
public class EnvVars {
    private static Dotenv dotenv = Dotenv.load();
    static private String token = dotenv.get("TOKEN");
    static private String mainUrl = dotenv.get("URL");
    static private String key = dotenv.get("PARAMETER_NAME");

    public static String getToken() {
        return token;
    }

    public static String getMainUrl() {
        return mainUrl;
    }

    public static String getKey() {
        return key;
    }
}
