package ee.ttu.weather.main;

import static ee.ttu.weather.main.forecastAPIrequest.getJsonCurrent;

/**
 * Created by artur on 15.10.2017.
 */
public class weatherAtNowReport {
    public static String getCity() throws Exception {
        String city = getJsonCurrent().get(forecastAPI.getName()).toString();
        return city;
    }

    /*public static int getCurrentTemp() throws Exception {
        String currentTempString = getJsonCurrent().getJSONObject(weatherTitles.getMain()).get(weatherTitles.getTemp()).toString();
        int currentTemp = Integer.parseInt(currentTempString);
        return currentTemp;
    }*/
}