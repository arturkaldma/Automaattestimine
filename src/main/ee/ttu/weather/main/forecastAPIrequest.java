package ee.ttu.weather.main;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by artur on 15.10.2017.
 */

public class forecastAPIrequest {

    private static JSONObject jsonCurrent;

    public static JSONObject getJsonCurrent() throws Exception {

        StringBuilder sb = new StringBuilder();
        InputStream inputStream = forecastAPIrepository.getUrl_atNow().openStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
        String line;
        while ((line = br.readLine()) != null) {
            sb.append(line);
        }
        JSONObject jsonCurrent = new JSONObject(sb.toString());


        return jsonCurrent;
    }

}