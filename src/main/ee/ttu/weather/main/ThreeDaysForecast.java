package ee.ttu.weather.main;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.*;

/**
 * Created by artur on 18.12.2017.
 */
public class ThreeDaysForecast {
    public static List<ForecastAPI> getThreeDaysForecast(ForecastAPIRequest request)
    {

        List<ForecastAPI> allForecasts = new ArrayList<>();
        List<List<ForecastAPI>> sortedByDateForecasts = new ArrayList<>();
        List<ForecastAPI> responseForecasts = new ArrayList<>();

        Date date = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int currentDay = cal.get(Calendar.DAY_OF_MONTH);

        JSONObject response = ForecastAPIRepository.makeRequest(request, "forecast");

        if (response == null){
            return null;
        }

        try {
            JSONArray list = response.getJSONArray("list");

            for (int i = 0; i < list.length(); i++){
                JSONObject respWeatherObject = (JSONObject)list.get(i);
                Date forecastDate = new java.util.Date(respWeatherObject.getLong("dt")*1000);
                cal.setTime(forecastDate);

                // truncate time information.
                cal.set(Calendar.HOUR_OF_DAY, 0);
                cal.set(Calendar.MINUTE, 0);
                cal.set(Calendar.SECOND, 0);
                cal.set(Calendar.MILLISECOND, 0);

                ForecastAPI forecast = new ForecastAPI();
                forecast.setDate(cal.getTime());
                forecast.setCity(response.getJSONObject("city").getString("name"));
                forecast.setCountry(response.getJSONObject("city").getString("country"));
                forecast.setTemp(respWeatherObject.getJSONObject("main").getDouble("temp"));
                forecast.setMinTemp(respWeatherObject.getJSONObject("main").getDouble("temp_min"));
                forecast.setMaxTemp(respWeatherObject.getJSONObject("main").getDouble("temp_max"));
                forecast.setCoordinates(response.getJSONObject("city").getJSONObject("coord").getDouble("lat") + ":" + response.getJSONObject("city").getJSONObject("coord").getDouble("lon"));

                int forecastDay = cal.get(Calendar.DAY_OF_MONTH);
                int day = (forecastDay - currentDay);

                if (day > 0 && day <= 3) {
                    if (sortedByDateForecasts.size() < day || sortedByDateForecasts.size() == 0) {
                        sortedByDateForecasts.add(new ArrayList<>());

                    }
                    sortedByDateForecasts.get(sortedByDateForecasts.size() - 1).add(forecast);
                }
            }

            for (List<ForecastAPI> forecasts: sortedByDateForecasts) {
                ForecastAPI thisDayMin = Collections.min(forecasts, Comparator.comparingDouble(ForecastAPI::getTemp));
                ForecastAPI thisDayMax = Collections.max(forecasts, Comparator.comparingDouble(ForecastAPI::getTemp));

                ForecastAPI forecast = new ForecastAPI();
                forecast.setCountry(thisDayMax.getCountry());
                forecast.setCity(thisDayMax.getCity());
                forecast.setCoordinates(thisDayMax.getCoordinates());
                forecast.setTemp(thisDayMax.getTemp());
                forecast.setMaxTemp(thisDayMax.getTemp());
                forecast.setMinTemp(thisDayMin.getTemp());
                forecast.setDate(thisDayMax.getDate());

                responseForecasts.add(forecast);
            }

            return responseForecasts;
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return allForecasts;
    }
}
