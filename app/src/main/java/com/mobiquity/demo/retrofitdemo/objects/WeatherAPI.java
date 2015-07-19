package com.mobiquity.demo.retrofitdemo.objects;

import com.mobiquity.demo.retrofitdemo.models.OpenWeatherMap;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created by jdonlan on 7/16/15.
 */
public interface WeatherAPI{
    @GET("/weather")
    public void getWeatherByZip(@Query("zip") String zipCode, Callback<OpenWeatherMap> callback);
}