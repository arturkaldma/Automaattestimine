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

 public class ForecastTest {

     private ForecastAPIRepository forecastAPIRepository;
     private ForecastAPIRequest commonRequest;
     private ThreeDaysForecast threeDaysForecast;
     private ForecastAtNow forecastAtNow;

     @Before
     public void startIt() throws Exception {
         forecastAPIRepository = new ForecastAPIRepository();
         commonRequest = new ForecastAPIRequest("Tallinn", "EE");
         threeDaysForecast = new ThreeDaysForecast();
         forecastAtNow = new ForecastAtNow();
     }

     @Test
     public void testGetWeatherAtNowIfCityAndCountryAreSame() {
         ForecastAPI response = forecastAtNow.getForecastAtNow(commonRequest);

         assertEquals(commonRequest.getCity(), response.getCity());
         assertEquals(commonRequest.getCountry(), response.getCountry());
     }

     @Test
     public void testGetWeatherAtNow() {
         ForecastAPI response = forecastAtNow.getForecastAtNow(commonRequest);

         assertNotNull(response);
     }

     @Test
     public void testGetNextThreeDaysForecast() {
         List<ForecastAPI> response = threeDaysForecast.getThreeDaysForecast(commonRequest);

         assertNotNull(response);
         assertEquals(3, response.size());

         for (ForecastAPI e : response) {
             assertNotNull(e.getCity());
         }
     }

     @Test
     public void testIfWeatherAtNowHasGeoCoordinates() {
         ForecastAPI response = new ForecastAtNow().getForecastAtNow(commonRequest);

         assertNotNull(response);
         assertNotNull(response.getCoordinates());
     }

     @Test
     public void testIfForecastHasGeoCoordinates() {
         List<ForecastAPI> response = new ThreeDaysForecast().getThreeDaysForecast(commonRequest);

         for (ForecastAPI e : response) {
             assertNotNull(e.getCoordinates());
         }
     }
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
 }