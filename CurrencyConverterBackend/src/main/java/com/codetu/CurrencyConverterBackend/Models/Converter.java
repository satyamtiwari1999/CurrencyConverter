package com.codetu.CurrencyConverterBackend.Models;

import com.codetu.CurrencyConverterBackend.Controllers.GetDataFromExchange;
import java.util.Arrays;

public class Converter {
    public static String convert(String c1, String c2, double val) throws AssertionError{
        assert GetDataFromExchange.currencyValues.containsKey("rates");

        Double val1, val2;
        System.out.println("result : " + GetDataFromExchange.currencyValues.get("rates"));
        var resultMap = GetDataFromExchange.currencyValues.get("rates");
        String[] list = resultMap.toString().split(",", -1);

        var stream = Arrays.stream(list).filter((mapper) -> {
            if (mapper.contains(c1)){
                return true;
            }
            else return mapper.contains(c2);
        }).toList();

        val1 = Double.valueOf(stream.get(0).split("=", -1)[1]);
        val2 = Double.valueOf(stream.get(1).split("=", -1)[1]);

        return String.valueOf(val2 / val1 * val);
    }
}
