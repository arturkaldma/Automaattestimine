package ee.ttu.weather.main;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
/**
 * Created by artur on 15.10.2017.
 */
public class ForecastAPI {

    private double temp;
    private double minTemp;
    private double maxTemp;
    private Date date;
    private String city;
    private String country;
    private String coordinates;


    public double getMinTemp() {
        return minTemp;
    }

    public void setMinTemp(double minTemp) {
        this.minTemp = minTemp;
    }

    public double getMaxTemp() {
        return maxTemp;
    }

    public void setMaxTemp(double maxTemp) {
        this.maxTemp = maxTemp;
    }

    public Date getDate() { return date; }

    public String getDateString() {
        DateFormat outputFormatter = new SimpleDateFormat("dd.MM.yyyy");
        return outputFormatter.format(date);
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }


    public String getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(String coordinates) {
        this.coordinates = coordinates;
    }

    public double getTemp() {
        return temp;
    }

    public void setTemp(double temp) {
        this.temp = temp;
    }

    @Override
    public String toString() {
        return getDateString() + " Weather forecast in " + city + ',' + country + ", coordinates: '" + coordinates + "': " + "Temperature: " + temp + ", min:" + minTemp + ", max:" + maxTemp;
    }
}
