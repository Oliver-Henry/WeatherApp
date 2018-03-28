package com.example.olive.weatherapp.data.network;

import com.example.olive.weatherapp.data.network.model.WeatherModel;

import io.reactivex.Observable;

/**
 * Created by olive on 27/03/2018.
 */

public class AppDataManager implements IDataManager {
    private IApiHelper iApiHelper;

    public AppDataManager() {iApiHelper = new AppApiHelper(); }


    @Override
    public Observable<WeatherModel> getWeatherDetails() {
        return iApiHelper.getWeatherDetails();
    }
}
