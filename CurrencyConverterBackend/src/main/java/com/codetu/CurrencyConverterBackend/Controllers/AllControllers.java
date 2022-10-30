package com.codetu.CurrencyConverterBackend.Controllers;

import com.codetu.CurrencyConverterBackend.Models.Converter;
import com.codetu.CurrencyConverterBackend.Models.Parser;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class AllControllers {
    @GetMapping("/currencies")
    public Map<String, Object> getListOfCurrencies() {
        GetDataFromExchange.getCurrencies();
        return GetDataFromExchange.currenciesList = new Parser(GetDataFromExchange.getResult()).getJsonFromString();
    }

    @GetMapping(value = "/convertFrom", produces = "application/json")
    public Map<String, Object> convert(
            @RequestParam(name = "currencyOne") String curr1,
            @RequestParam (name = "currencyTwo") String curr2,
            @RequestParam (name = "valueOne") Double value){
        try {
            GetDataFromExchange.getLatestCurrencyValues();
            GetDataFromExchange.currencyValues = new Parser(GetDataFromExchange.getResult()).getJsonFromString();
            Map<String, Object> map = new HashMap<>();
            map.put("data", Converter.convert(curr1, curr2, value));
            map.put("code", 0);
            map.put("message", "success");
            return map;
        }
        catch (Exception e){
            Map<String, Object> map = new HashMap<>();
            map.put("error", e.getMessage());
            return map;
        }
    }
}
