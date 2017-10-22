package ee.ttu.weather.main;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by artur on 15.10.2017.
 */
public class forecastAPIrepository {
    private static URL url_atNow;

    public static URL getUrl_atNow() throws MalformedURLException {
        String http = "http://api.openweathermap.org/data/2.5/weather?q=Tallinn,EE";
        String apiKey = "f5b5ff4a09b750cedfe8adba005f4458";
        URL url_atNow = new URL(http + "&APPID=" + apiKey + "&units=metric");

        return url_atNow;

    }
}