package com.example.simplecurrencyconverter;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Settings {
    @SerializedName("rates")
    @Expose
    private Rates rates;

    public Rates getRates() {
        return rates;
    }
}
