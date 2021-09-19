package com.example.simplecurrencyconverter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private static final String BASE_URL = "https://api.frankfurter.app/";
    EditText fromInput, toOutput;
    Button convertButton;
    Spinner fromDropdown, toDropdown;
    String fromCurrency, toCurrency;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fromInput = findViewById(R.id.fromCurrency);
        toOutput = findViewById(R.id.toCurrency);
        convertButton = findViewById(R.id.convertButton);
        fromDropdown = findViewById(R.id.fromDropdown);
        toDropdown = findViewById(R.id.toDropdown);
        setupListeners();
    }

    void setupListeners() {
        fromDropdown.setOnItemSelectedListener(new FromDropdown());
        toDropdown.setOnItemSelectedListener(new ToDropdown());
        convertButton.setOnClickListener(v -> {
            if (!hasConnection(this)) {
                Toast.makeText(MainActivity.this, "Host unreachable, check your internet connection and try again", Toast.LENGTH_SHORT).show();
            }
            if (fromCurrency.equals(toCurrency)) {
                Toast.makeText(MainActivity.this, "From and to values are same.", Toast.LENGTH_SHORT).show();
            } else if (fromInput.getText().toString().isEmpty()) {
                Toast.makeText(MainActivity.this, "Please enter a value to convert.", Toast.LENGTH_SHORT).show();
            } else {
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                JSONPlaceHolderApi jsonPlaceHolderApi = retrofit.create(JSONPlaceHolderApi.class);

                Call<Settings> money = jsonPlaceHolderApi.getFrom(Double.valueOf(fromInput.getText().toString()), fromCurrency, toCurrency);

                money.enqueue(new Callback<Settings>() {
                    @SuppressLint("SetTextI18n")
                    @Override
                    public void onResponse(Call<Settings> call, Response<Settings> response) {
                        Settings settings = response.body();
                        switch (toCurrency) {
                            case "RUB":
                                toOutput.setText(settings.getRates().getRub().toString());
                                break;
                            case "USD":
                                toOutput.setText(settings.getRates().getUsd().toString());
                                break;
                            case "EUR":
                                toOutput.setText(settings.getRates().getEur().toString());
                                break;
                            case "INR":
                                toOutput.setText(settings.getRates().getInr().toString());
                                break;
                            case "AUD":
                                toOutput.setText(settings.getRates().getAud().toString());
                                break;
                            case "CAD":
                                toOutput.setText(settings.getRates().getCad().toString());
                                break;
                            case "GBP":
                                toOutput.setText(settings.getRates().getGbp().toString());
                                break;
                        }
                    }

                    @Override
                    public void onFailure(Call<Settings> call, Throwable t) {

                    }
                });
            }
        });
    }

    public static boolean hasConnection(final Context context) {
        ConnectivityManager cm =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }

    class FromDropdown implements AdapterView.OnItemSelectedListener {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            fromCurrency = parent.getItemAtPosition(position).toString();
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {
        }
    }

    class ToDropdown implements AdapterView.OnItemSelectedListener {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            toCurrency = parent.getItemAtPosition(position).toString();
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {
        }
    }

}
