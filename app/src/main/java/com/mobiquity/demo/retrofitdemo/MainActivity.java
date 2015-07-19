package com.mobiquity.demo.retrofitdemo;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mobiquity.demo.retrofitdemo.models.Main;
import com.mobiquity.demo.retrofitdemo.models.OpenWeatherMap;
import com.mobiquity.demo.retrofitdemo.models.Sys;
import com.mobiquity.demo.retrofitdemo.objects.WeatherAPI;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;
import retrofit.converter.GsonConverter;

public class MainActivity extends Activity {

    public static final String TAG = "MainActivity";
    private static final String BASEURL = "http://api.openweathermap.org/data/2.5";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getWeatherByZip("32765");
    }


    private void getWeatherByZip(String zip) {
        /*
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy'-'MM'-'dd'T'HH':'mm':'ss'.'SSS'Z'")
                .create();
        */

        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(BASEURL)
//                .setConverter(new GsonConverter(gson))
                .build();

        WeatherAPI weatherAPI = restAdapter.create(WeatherAPI.class); //this is how retrofit create your api
        weatherAPI.getWeatherByZip(zip+",us", new Callback<OpenWeatherMap>(){
            @Override
            public void success(OpenWeatherMap openWeatherMap, Response response) {
                Main main = openWeatherMap.getMain();
                Log.i(TAG, "Temperature: " + main.getTemp().toString());
                Sys sys = openWeatherMap.getSys();
                Log.i(TAG, "Sunrise: " + sys.getSunrise().toString());
            }
            @Override
            public void failure(RetrofitError error) {

            }
        });
    }
}
