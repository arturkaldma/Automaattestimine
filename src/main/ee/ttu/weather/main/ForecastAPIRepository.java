package ee.ttu.weather.main;


import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import java.util.*;
/**
 * Created by artur on 15.10.2017.
 */
public class ForecastAPIRepository {

    private static String apiMainUrl;
    private static String apiKey;

    public ForecastAPIRepository(){
        apiMainUrl = "http://api.openweathermap.org/data/2.5/";
        apiKey = "f5b5ff4a09b750cedfe8adba005f4458";
    }

    public List<ForecastAPI> getForecastForLocations(List<String> locations) {
        List<ForecastAPI> forecasts = new ArrayList<>();
        for (String location: locations) {
            List<String> countryInfo = Arrays.asList(location.split("\\s*,\\s*"));
            ForecastAPIRequest forecastAPIRequest = new ForecastAPIRequest(countryInfo.get(0), countryInfo.get(1));
            forecasts.add(ForecastAtNow.getForecastAtNow(forecastAPIRequest));
            forecasts.addAll(ThreeDaysForecast.getThreeDaysForecast(forecastAPIRequest));
        }

        return forecasts;
    }

    static JSONObject makeRequest(ForecastAPIRequest request, String method){
        try {

            URL url = new URL(apiMainUrl + method+ "?q=" + request.getCity() + "," + request.getCountry() + "&units=" + request.getUnits() + "&APPID=" + apiKey);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + conn.getResponseCode());
            }

            BufferedReader streamReader = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
            StringBuilder responseStrBuilder = new StringBuilder();

            String inputStr;
            while ((inputStr = streamReader.readLine()) != null)
                responseStrBuilder.append(inputStr);

            JSONObject jsonObject = new JSONObject(responseStrBuilder.toString());

            conn.disconnect();

            return jsonObject;

        } catch (Exception e) {

            e.printStackTrace();
            return null;
        }
    }
}
