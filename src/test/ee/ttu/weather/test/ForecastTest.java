package ee.ttu.weather.test; /**
 * Created by artur on 25.09.2017.
 */
 import static org.junit.Assert.*;

 import static org.mockito.Mockito.*;
 import ee.ttu.weather.main.*;
 import org.junit.Test;
 import org.junit.Before;

 import java.io.File;
 import java.util.ArrayList;
 import java.util.Date;
 import java.util.List;
 import java.io.IOException;
 import static org.junit.Assert.assertTrue;
 import org.json.JSONObject;


 public class ForecastTest {

     private ForecastAPIRepository forecastAPIRepository;
     private ForecastAPIRequest commonRequest;

     @Before
     public void startIt() throws Exception {
         forecastAPIRepository = new ForecastAPIRepository();
         commonRequest = new ForecastAPIRequest("Tallinn", "EE");
     }

     @Test
     public void testGetWeatherAtNowIfCityAndCountryAreSame() {
         ForecastAPI response = forecastAPIRepository.getForecastAtNow(commonRequest);

         assertEquals(commonRequest.getCity(), response.getCity());
         assertEquals(commonRequest.getCountry(), response.getCountry());
     }

     @Test
     public void testGetWeatherAtNow() {
         ForecastAPI response = forecastAPIRepository.getForecastAtNow(commonRequest);

         assertNotNull(response);
     }

     @Test
     public void testGetNextThreeDaysForecast() {
         List<ForecastAPI> response = forecastAPIRepository.getThreeDaysForecast(commonRequest);

         assertNotNull(response);
         assertEquals(3, response.size());

         for (ForecastAPI e : response) {
             assertNotNull(e.getCity());
         }
     }

     @Test
     public void testIfWeatherAtNowHasMaxTemp() {
         ForecastAPI response = new ForecastAPIRepository().getForecastAtNow(commonRequest);

         assertNotNull(response);
         assertNotNull(response.getMaxTemp());
     }

     @Test
     public void testIfForecastHasGeoCoordinates() {
         List<ForecastAPI> response = new ForecastAPIRepository().getThreeDaysForecast(commonRequest);

         for (ForecastAPI e : response) {
             assertNotNull(e.getCoordinates());
         }
     }

     //vana test mis ei ole väga mockimine
     @Test
     public void testBothRequestsIfFilesCreated() throws IOException {
         ForecastAPIRepository forecastAPIRepository = mock(ForecastAPIRepository.class);
         ForecastWrite forecastWrite = new ForecastWrite();
         List<String> locations = new ArrayList<>();
         locations.add("Tallinn,EE");
         List<ForecastAPI> response = new ArrayList<>();
         for (int i = 0; i < 3; i++) {
             ForecastAPI rsp = new ForecastAPI();
             rsp.setCity("Tallinn");
             rsp.setCountry("EE");
             rsp.setCoordinates("0.000000;0.000000");
             rsp.setDate(new Date());
             response.add(rsp);
         }

         // delete file if exists
         File file = new File("C:/Users/artur/workspace/Forecast/src/main/Tallinn.txt");
         if (file.exists() && !file.isDirectory()){
             file.delete();
         }

         when(forecastAPIRepository.getForecastForLocations(locations)).thenReturn(response);
         response = forecastAPIRepository.getForecastForLocations(locations);
         verify(forecastAPIRepository, times(1)).getForecastForLocations(locations);
         forecastWrite.WriteForecastsToFile(response);

         file = new File("C:/Users/artur/workspace/Forecast/src/main/Tallinn.txt");

         assertTrue(file.exists());
     }

     //uus test mis on rohkem mockimine
     @Test
     public void testMockBothRequestsIfFilesCreated() throws IOException {

         MakeNewRequest makeNewRequest = mock(MakeNewRequest.class);
         ForecastAPIRepository forecastAPIRepository = new ForecastAPIRepository();
         forecastAPIRepository.setMaker(makeNewRequest);
         ForecastWrite forecastWrite = new ForecastWrite();


         ForecastAPI response = new ForecastAPI();
         response.setCity("Tallinn");
         response.setCountry("EE");
         response.setCoordinates("0.000000;0.000000");
         response.setDate(new Date());


         when(makeNewRequest.makeRequest(any(), anyString())).thenReturn(new JSONObject(response));



         forecastWrite.InsertDataIntoFile("Tallinn.txt", response.toString());

     }
 }


