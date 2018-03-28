package com.example.olive.weatherapp.data.network;

import com.example.olive.weatherapp.data.network.model.WeatherModel;

import io.reactivex.Observable;

/**
 * Created by olive on 27/03/2018.
 */

public interface IApiHelper {
    Observable<WeatherModel> getWeatherDetails();
}
