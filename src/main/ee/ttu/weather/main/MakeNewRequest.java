package ee.ttu.weather.main;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;



/**
 * Created by artur on 28.12.2017.
 */

public class MakeNewRequest {

    private static String apiBaseUrl;
    private static String apiKey;

    public MakeNewRequest(){
        apiBaseUrl = "http://api.openweathermap.org/data/2.5/";
        apiKey = "84ca155245fbded1dcb35c2b0d491ec9";
    }

    public JSONObject makeRequest(ForecastAPIRequest request, String method){
        try {

            URL url = new URL(apiBaseUrl + method+ "?q=" + request.getCity() + "," + request.getCountry() + "&units=" + request.getUnits() + "&APPID=" + apiKey);
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
