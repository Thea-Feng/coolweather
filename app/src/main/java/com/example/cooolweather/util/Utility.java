package com.example.cooolweather.util;


import android.text.TextUtils;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.cooolweather.db.City;
import com.example.cooolweather.db.County;
import com.example.cooolweather.db.Province;
import com.example.cooolweather.gson.Weather;
import com.google.gson.Gson;

public class Utility {
//    解析和处理服务器返回的省级数据

    public static boolean handleProvinceResponse(String response){
        if(!TextUtils.isEmpty(response)){
            try {
                JSONArray allProvinces = new JSONArray(response);
                for (int i = 0; i < allProvinces.length(); i++){
                    JSONObject provinceObject = allProvinces.getJSONObject(i);
                    Province province = new Province();
                    province.setProvinceName(provinceObject.getString("name"));
                    province.setProvinceCode(provinceObject.getInt("id"));
                    province.save(); //save 从litepal中继承来，我们包的名字就是litepal
                }
                return true;
            } catch (Exception e){
                e.printStackTrace();
            }
        }
        return false;
    }

    public static boolean handleCityResponse(String response, int provinceId){
        if(!TextUtils.isEmpty(response)){
            try {
                JSONArray allCites = new JSONArray(response);
                for (int i = 0; i < allCites.length(); i++){
                    JSONObject cityObject = allCites.getJSONObject(i);
                    City city = new City();
                    city.setCityName(cityObject.getString("name"));
                    city.setCityCode(cityObject.getInt("id"));
                    city.setProvinceId(provinceId);
                    city.save();
                }
                return true;
            } catch (Exception e){
                e.printStackTrace();
            }
        }
        return false;
    }

    public static boolean handleCountyResponse(String response, int cityId){
        if(!TextUtils.isEmpty(response)){
            try {
                JSONArray allCounties= new JSONArray(response);
                for (int i = 0; i < allCounties.length(); i++){
                    JSONObject countyObject = allCounties.getJSONObject(i);
                    County county = new County();
                    county.setCountyName(countyObject.getString("name"));
                    county.setCityId(cityId);
                    county.setWeatherId(countyObject.getString("weather_id"));
                    county.save();
                }
                return true;
            } catch (Exception e){
                e.printStackTrace();
            }
        }
        return false;
    }

    public static Weather handleWeatherResponse(String response){
        try {
            Log.d("Utility", "handleWeatherResponse: ");
            JSONObject jsonObject = new JSONObject(response);
            JSONArray jsonArray = jsonObject.getJSONArray("HeWeather");
            String weatherContent = jsonArray.getJSONObject(0).toString();
            return new Gson().fromJson(weatherContent, Weather.class);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return  null;
    }
}
