package com.example.olive.weatherapp.data.network;

import com.example.olive.weatherapp.data.network.model.WeatherModel;
import com.example.olive.weatherapp.data.network.service.IRequestInterface;
import com.example.olive.weatherapp.data.network.service.ServiceConnection;

import io.reactivex.Observable;

/**
 * Created by olive on 27/03/2018.
 */

public class AppApiHelper implements IApiHelper{

    private IRequestInterface iRequestInterface;


    @Override
    public Observable<WeatherModel> getWeatherDetails() {
        return iRequestInterface.getWeather();
    }

    public AppApiHelper() { iRequestInterface = ServiceConnection.getConnection();}
}
