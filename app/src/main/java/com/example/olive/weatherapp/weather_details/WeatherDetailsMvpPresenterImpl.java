package com.example.olive.weatherapp.weather_details;

import com.example.olive.weatherapp.data.network.IDataManager;
import com.example.olive.weatherapp.data.network.model.WeatherModel;
import com.example.olive.weatherapp.ui.base.BasePresenter;
import com.example.olive.weatherapp.ui.utils.rx.SchedulerProvider;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;

/**
 * Created by olive on 27/03/2018.
 */

public class WeatherDetailsMvpPresenterImpl<V extends IWeatherDetailsMvpView> extends BasePresenter<V> implements IWeatherDetailsMvpPresenter<V> {


    public WeatherDetailsMvpPresenterImpl(IDataManager dataManager, SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }


    @Override
    public void loadWeatherDetails() {
        getCompositeDisposable().add(getDataManager().getWeatherDetails()
        .subscribeOn(getSchedulerProvider().io())
        .observeOn(getSchedulerProvider().ui())
        .subscribe(new Consumer<WeatherModel>() {
                       @Override
                       public void accept(WeatherModel weatherModel) throws Exception {
                           getMvpView().onFetchDataSuccess(weatherModel);
                       }
                   },
                new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        getMvpView().onFetchDataError(throwable.getMessage());
                    }
                }
        ));
    }
}
