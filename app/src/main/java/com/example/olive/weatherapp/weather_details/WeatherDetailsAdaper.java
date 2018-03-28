package com.example.olive.weatherapp.weather_details;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.olive.weatherapp.R;
import com.example.olive.weatherapp.data.network.model.WeatherModel;
import com.squareup.picasso.Picasso;

/**
 * Created by olive on 28/03/2018.
 */

class WeatherDetailsAdaper extends RecyclerView.Adapter<WeatherDetailsAdaper.MyViewHolder> {
    Context applicationContext;
    WeatherModel weatherModel;
    int days_weather_layout;

    public WeatherDetailsAdaper(Context applicationContext, WeatherModel weatherModel, int days_weather_layout) {
    this.applicationContext = applicationContext;
    this.weatherModel = weatherModel;
    this.days_weather_layout = days_weather_layout;
    }

    @Override
    public WeatherDetailsAdaper.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(days_weather_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(WeatherDetailsAdaper.MyViewHolder holder, int position) {
        String url = "http://openweathermap.org/img/w/" + weatherModel.getList().get(position).getWeather().get(0).getIcon() + ".png";
        Picasso.with(applicationContext).load(url).resize(100, 100).centerCrop().into(holder.imageView);

//        Calendar c = Calendar.getInstance();
//        c.setTime(SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(weatherModel.getList().get(position).getDtTxt()));
//        int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);

        holder.textView1.setText(String.valueOf(Math.round(weatherModel.getList().get(position).getMain().getTemp() - 273.15)) + "°");
        holder.textView2.setText(weatherModel.getList().get(position).getDtTxt());

    }

    @Override
    public int getItemCount() {
        return weatherModel.getList().size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        private TextView textView1;
        private TextView textView2;


        public MyViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imgV);
            textView1 = itemView.findViewById(R.id.tVtemp);
            textView2 = itemView.findViewById(R.id.tVdate);

        }
    }
}
