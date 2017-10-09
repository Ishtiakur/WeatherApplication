package com.example.ishtiak.weatherapplication;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import Data.JSONWeatherParser;
import Data.WeatherHttpClient;
import Model.Weather;

public class MainActivity extends AppCompatActivity {

    private TextView cityText;
    private ImageView iconView;
    private TextView temp;
    private TextView wind;
    private TextView cloud;
    private TextView pressure;
    private TextView humidity;
    private TextView sunrise;
    private TextView sunset;
    private TextView lastUpdate;


    Weather weather = new Weather();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cityText = (TextView) findViewById(R.id.cityText);
        iconView = (ImageView) findViewById(R.id.thumbnailIcon);
        temp = (TextView) findViewById(R.id.tempText);
        wind = (TextView) findViewById(R.id.windText);
        cloud = (TextView) findViewById(R.id.cloudText);
        pressure = (TextView) findViewById(R.id.pressureTxt);
        humidity = (TextView) findViewById(R.id.humidityText);
        sunrise = (TextView) findViewById(R.id.sunriseText);
        sunset = (TextView) findViewById(R.id.sunsetText);
        lastUpdate = (TextView) findViewById(R.id.lastUpdateText);

        renderWeatherData("Spokane,US");

    }

    public void renderWeatherData(String city){

        WeatherTask weatherTask = new WeatherTask();
        weatherTask.execute(new String[]{city + "&units =  metric"});

    }
    private class WeatherTask extends AsyncTask<String, Void, Weather>{
        @Override
        protected Weather doInBackground(String... param) {
            String data = ( (new WeatherHttpClient() ).getWeatherData(param[0]));
            weather = JSONWeatherParser.getWeather(data);
            Log.v("data", weather.places.getCity());
            return null;
        }

        @Override
        protected void onPostExecute(Weather weather) {
            super.onPostExecute(weather);

        }
    }





    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        return super.onOptionsItemSelected(item);
    }
}
