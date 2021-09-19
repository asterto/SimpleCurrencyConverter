package com.example.simplecurrencyconverter;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Rates {

    @SerializedName("EUR")
    @Expose
    private Double eur;

    @SerializedName("RUB")
    @Expose
    private Double rub;

    @SerializedName("USD")
    @Expose
    private Double usd;

    @SerializedName("INR")
    @Expose
    private Double inr;

    @SerializedName("AUD")
    @Expose
    private Double aud;

    @SerializedName("GBP")
    @Expose
    private Double gbp;

    @SerializedName("CAD")
    @Expose
    private Double cad;

    public Double getInr() {
        return inr;
    }

    public Double getAud() {
        return aud;
    }

    public Double getGbp() {
        return gbp;
    }

    public Double getCad() {
        return cad;
    }

    public Double getEur() {
        return eur;
    }

    public Double getRub() {
        return rub;
    }

    public Double getUsd() {
        return usd;
    }

}
