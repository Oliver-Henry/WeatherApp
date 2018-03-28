package com.example.olive.weatherapp.weather_details;

import com.example.olive.weatherapp.ui.base.MvpPresenter;

/**
 * Created by olive on 27/03/2018.
 */

public interface IWeatherDetailsMvpPresenter<V extends IWeatherDetailsMvpView> extends MvpPresenter<V> {
    void loadWeatherDetails();
}
