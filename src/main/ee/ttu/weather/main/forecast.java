package ee.ttu.weather.main;

import org.json.JSONArray;
import org.json.JSONObject;

import java.sql.Timestamp;

/**
 * Created by artur on 22.10.2017.
 */
/*public class forecast {

    public tempForDay tempForDay(JSONObject forecastDataObject, int day){
        int dayToday = (new Timestamp(System.currentTimeMillis())).toLocalDateTime().getDayOfMonth();
        JSONArray forecast = getJsonForecastList()
        JSONArray jsonForecastList = getJsonForecast().getJSONArray(weatherTitles.getList());
        double latestMinTemp = Integer.MAX_VALUE;
        double latestMaxTemp = Integer.MIN_VALUE;
        for (int i = 0; i < forecast.length(); i++)   {
            JSONObject singleForecastObject = (JSONObject) forecast.get(i);
            Timestamp timestamp = new Timestamp((Long) singleForecastObject.get("dt") * 1000);
            JSONObject mainObject = (JSONObject) singleForecastObject.get("main");
            Object minTemperatureObject = mainObject.get("temp_min");
            Object maxTemperatureObject = mainObject.get("temp_max");
            double minTempValue = new Double(minTemperatureObject.toString());
            double maxTempValue = new Double(maxTemperatureObject.toString());
            int daysFromToday = timestamp.toLocalDateTime().getDayOfMonth() - dayToday;
            if (daysFromToday == day)   {
                if (minTempValue < latestMinTemp) {
                    latestMinTemp = minTempValue;
                }
                if (maxTempValue < latestMaxTemp) {
                    latestMaxTemp = maxTempValue;
                }
            }
        }
        tempForDay tempForDay = new tempForDay(latestMaxTemp,latestMinTemp);
        return tempForDay;
    }


}
*/