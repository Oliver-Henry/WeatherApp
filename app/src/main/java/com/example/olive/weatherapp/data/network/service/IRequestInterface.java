package com.example.olive.weatherapp.data.network.service;

import com.example.olive.weatherapp.data.network.model.WeatherModel;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * Created by olive on 27/03/2018.
 */

public interface IRequestInterface {

    @GET(ApiList.WEATHER_DETAILS)
    Observable<WeatherModel> getWeather();
//    Observable<WeatherModel> getWeather(@Query("APPID") String APPID);
}
