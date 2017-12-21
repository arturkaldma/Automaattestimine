package ee.ttu.weather.main;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;
/**
 * Created by artur on 22.10.2017.
 */
public class ForecastWrite {

    private String outputDir = "C:/Users/artur/workspace/Forecast/src/main/";

    public void InsertDataIntoFile(String fileName, String data) throws IOException {
        String outputFile = outputDir + fileName;
        BufferedWriter bw = new BufferedWriter(new FileWriter(outputFile));
        bw.write(data);
        bw.close();
    }

    public void WriteForecastsToFile(List<ForecastAPI> forecasts) throws IOException {
        Map<String, List<ForecastAPI>> groupedList = forecasts.stream().collect(Collectors.groupingBy(ForecastAPI::getCity));

        for (Map.Entry<String, List<ForecastAPI>> group : groupedList.entrySet()){
            String fileName = group.getKey() + ".txt";
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < group.getValue().size(); i++) {
                ForecastAPI element = group.getValue().get(i);
                if (i == 0) {
                    sb.append(element.toString() + " \n");
                } else {
                    sb.append(element.getDateString() + " ");
                    sb.append("min: " + String.valueOf(element.getMinTemp()) + " ");
                    sb.append("max: " + String.valueOf(element.getMaxTemp()) + " \n");
                }
            }
            insertDataIntofile(fileName, sb.toString());
        }
    }
}