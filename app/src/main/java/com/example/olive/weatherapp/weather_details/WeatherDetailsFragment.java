package com.example.olive.weatherapp.weather_details;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.olive.weatherapp.R;
import com.example.olive.weatherapp.data.network.AppDataManager;
import com.example.olive.weatherapp.data.network.model.WeatherModel;
import com.example.olive.weatherapp.ui.base.BaseFragment;
import com.example.olive.weatherapp.ui.utils.rx.AppSchedulerProvider;
import com.github.pwittchen.reactivenetwork.library.rx2.ReactiveNetwork;
import com.squareup.picasso.Picasso;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * A simple {@link Fragment} subclass.
 */
public class WeatherDetailsFragment extends BaseFragment implements IWeatherDetailsMvpView {

    private WeatherDetailsMvpPresenterImpl<WeatherDetailsFragment> weatherDetailsFragmentWeatherDetailsMvpPresenter;
    private RecyclerView recyclerView;
    LinearLayoutManager HorizontalLayout;
    private ImageView weatherIcon;
    private TextView currentDT, weatherMainDesc, weatherDesc, currentTemp, humidity, wind;

    public WeatherDetailsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        weatherDetailsFragmentWeatherDetailsMvpPresenter = new WeatherDetailsMvpPresenterImpl<>(new AppDataManager(), new AppSchedulerProvider(), new CompositeDisposable());
        weatherDetailsFragmentWeatherDetailsMvpPresenter.onAttach(this);
        recyclerView= (RecyclerView)view.findViewById(R.id.rV);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        HorizontalLayout = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(HorizontalLayout);
        weatherIcon = (ImageView) view.findViewById(R.id.imgVCurrent);
        currentDT = (TextView) view.findViewById(R.id.tVCurrentDT);
        weatherMainDesc = (TextView) view.findViewById(R.id.tVWMainDesc);
        weatherDesc = (TextView) view.findViewById(R.id.tVWDesc);
        currentTemp = (TextView) view.findViewById(R.id.tVCurrentTemp);
        humidity = (TextView) view.findViewById(R.id.tVHumidity);
        wind = (TextView) view.findViewById(R.id.tVWind);
        callService();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_weather_details, container, false);
    }

    public void callService(){
        ReactiveNetwork.observeInternetConnectivity()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean isConnectedToInternet) throws Exception {
                        if(isConnectedToInternet){
                            weatherDetailsFragmentWeatherDetailsMvpPresenter.loadWeatherDetails();
                        }
                        else {
                            Toast.makeText(getActivity(), "No Internet Connection", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    @Override
    public void onFetchDataProgress() {

    }

    @Override
    public void onFetchDataSuccess(WeatherModel weatherModel) {
        String url = "http://openweathermap.org/img/w/" + weatherModel.getList().get(0).getWeather().get(0).getIcon() + ".png";
        Picasso.with(getActivity()).load(url).resize(300, 200).centerCrop().into(weatherIcon);
        currentDT.setText(weatherModel.getList().get(0).getDtTxt());
        weatherMainDesc.setText(weatherModel.getList().get(0).getWeather().get(0).getMain());
        weatherDesc.setText(weatherModel.getList().get(0).getWeather().get(0).getDescription());
        currentTemp.setText(String.valueOf(Math.round(weatherModel.getList().get(0).getMain().getTemp() - 273.15)) + "°");
        humidity.setText("Humidity " + String.valueOf(weatherModel.getList().get(0).getMain().getHumidity()) + "%");
        wind.setText("Wind " + String.valueOf(weatherModel.getList().get(0).getWind().getDeg()) + "   " + String.valueOf(weatherModel.getList().get(0).getWind().getSpeed()));

         recyclerView.setAdapter(new WeatherDetailsAdaper(getActivity().getApplicationContext(), weatherModel, R.layout.days_weather_layout));
    }

    @Override
    public void onFetchDataError(String error) {

        showMessage(error);
        Toast.makeText(getActivity(), error, Toast.LENGTH_LONG).show();
    }
}
