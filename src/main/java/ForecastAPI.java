
/**
 * Created by artur on 15.10.2017.
 */
public class ForecastAPI {
    public String nameOfCity;
    public String codeOfCountry;
    public String units;

    public ForecastAPIrequest(){
        this.nameOfCity = "Tallinn";
        this.codeOfCountry = "EE";
        this.units = "metric";
    }

    public String getNameOfCity(){

        return nameOfCity;
    }

    public String getCodeOfCountry(){
        return codeOfCountry;
    }

    public String getUnits(){
        return units;
    }
}