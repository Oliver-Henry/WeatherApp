package com.example.olive.weatherapp.weather_details;

import com.example.olive.weatherapp.data.network.model.WeatherModel;
import com.example.olive.weatherapp.ui.base.MvpView;

/**
 * Created by olive on 27/03/2018.
 */

public interface IWeatherDetailsMvpView extends MvpView {
    void onFetchDataProgress();
    void onFetchDataSuccess(WeatherModel weatherModel);
    void onFetchDataError(String error);
}
