package ee.ttu.weather.main;

import org.json.JSONObject;

/**
 * Created by artur on 18.12.2017.
 */
public class ForecastAtNow {
    public static ForecastAPI getForecastAtNow(ForecastAPIRequest request)
    {
        try {
            JSONObject response = ForecastAPIRepository.makeRequest(request, "weather");

            if (response == null){
                return null;
            }

            ForecastAPI forecastAPI = new ForecastAPI();

            forecastAPI.setDate(new java.util.Date(response.getLong("dt")*1000));
            forecastAPI.setCity(response.getString("name"));
            forecastAPI.setCountry(response.getJSONObject("sys").getString("country"));
            forecastAPI.setTemp(response.getJSONObject("main").getDouble("temp"));
            forecastAPI.setMinTemp(response.getJSONObject("main").getDouble("temp_min"));
            forecastAPI.setMaxTemp(response.getJSONObject("main").getDouble("temp_max"));
            forecastAPI.setCoordinates(response.getJSONObject("coord").getDouble("lat") + ":" + response.getJSONObject("coord").getDouble("lon"));
            return forecastAPI;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
