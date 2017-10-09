package Data;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import Model.Places;
import Model.Weather;
import Util.Util;

/**
 * Created by Ishtiak on 10/7/2017.
 */

public class JSONWeatherParser  {
    public static Weather getWeather(String data) {
        Weather weather= new Weather();

        //create object from data
        try {
            JSONObject jsonObject = new JSONObject(data);
            Places place = new Places();

            JSONObject coorObj = Util.getObject("coord", jsonObject);
            place.setLat(Util.getFloat("lat", coorObj));
            place.setLon(Util.getFloat("lon", coorObj));

            //get the sys object
            JSONObject sysObj = Util.getObject("sys", jsonObject);
            place.setCountry(Util.getString("country", sysObj));
            place.setLastUpdate(Util.getInt("dt", jsonObject));
            place.setSunrise(Util.getInt("sunrise", sysObj));
            place.setSunset(Util.getInt("sunset", sysObj));
            place.setCity(Util.getString("name", jsonObject));

            weather.places = place;

            //get the weather info
            JSONArray jsonArray = jsonObject.getJSONArray("weather");
            JSONObject jsonWeather = jsonArray.getJSONObject(0);
            weather.currentCondition.setWeatherId(Util.getInt("id", jsonWeather));
            weather.currentCondition.setDescription(Util.getString("description", jsonWeather));
            weather.currentCondition.setCondition(Util.getString("main", jsonWeather));
            weather.currentCondition.setIcon(Util.getString("icon", jsonWeather));

            //get the wind info
            JSONObject windObj = Util.getObject("wind", jsonObject);
            weather.wind.setSpeed(Util.getFloat("speed", windObj));
            weather.wind.setDeg(Util.getFloat("deg", jsonWeather));

            //get cloud info
            JSONObject  cloudObj = Util.getObject("clouds", jsonObject);
            weather.clouds.setPrecipitation(Util.getInt("all", cloudObj));

            return weather;

        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

}
