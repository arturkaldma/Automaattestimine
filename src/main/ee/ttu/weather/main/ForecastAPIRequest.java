package ee.ttu.weather.main;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by artur on 15.10.2017.
 */

public class ForecastAPIRequest {
    private String city;
    private String country;
    private String metric;

    public ForecastAPIRequest(String city, String country) {
        this.city = city;
        this.country = country;
        this.metric = "metric";
    }

    public String getCity() { return this.city; }

    public String getCountry() { return this.country; }

    public String getUnits() { return this.metric; }
}
