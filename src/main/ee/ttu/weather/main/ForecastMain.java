package ee.ttu.weather.main;

import java.io.*;
import java.util.List;

/**
 * Created by artur on 22.10.2017.
 */
public class ForecastMain {

    public static void main(String[] args) {

        try {

            ForecastAPIRepository forecastAPIRepository = new ForecastAPIRepository();
            MakeNewRequest makeNewRequest = new MakeNewRequest();
            ForecastRead forecastRead = new ForecastRead();
            List<String> locations = forecastRead.getByUserInput();

            List<ForecastAPI> forecasts = forecastAPIRepository.getForecastForLocations(locations);
            ForecastWrite forecastWrite = new ForecastWrite();
            forecastWrite.WriteForecastsToFile(forecasts);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}