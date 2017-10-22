 package ee.ttu.weather.main;

/**
 * Created by artur on 25.09.2017.
 */

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


 public class weatherTest {
     private forecastAPIrepository repository;
     private forecastAPIrequest request;



     /*@Test
     public void testGetCurrentWeather() {
         forecastAPIrepository response = forecastAPI.getCity(request);
         assertNotNull(response);
     }*/

    @Test
    public void checkCity()  {
        Assert.assertEquals(forecastAPI.getCity(),"Tallinn");

    }

    @Test
    public void checkIfCurrentTemperatureIsPossible(){
       // Assert.
    }

    @Test
    public void getCurrrentTemperature(){
        fail("Not yet implemented");
    }

    @Test
    public void getWindSpeed(){
        fail("Not yet implemented");
    }

    @Test
    public void getLocation(){
        fail("Not yet implemented");
    }

    @Test
    public void testConnectionExist() {
        fail("Not yet implemented");
    }
}