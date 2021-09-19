package com.example.simplecurrencyconverter;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface JSONPlaceHolderApi {
    @GET("latest")
    Call<Settings> getFrom(@Query("amount") Double amount, @Query("from") String fromCurrency, @Query("to") String toCurrency);
}
