package ee.ttu.weather.main;

import java.io.*;
import java.util.List;

/**
 * Created by artur on 22.10.2017.
 */
public class ForecastMain {

    public static void main(String[] args) {

        try {

            ForecastAPIRepository weatherForecastService = new ForecastAPIRepository();
            ForecastRead dataReaderRepository = new ForecastRead();
            List<String> locations = dataReaderRepository.getByUserInput();

            List<ForecastAPI> forecasts = weatherForecastService.getForecastForLocations(locations);
            ForecastWrite dataWriterRepository = new ForecastWrite();
            dataWriterRepository.WriteForecastsToFile(forecasts);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}