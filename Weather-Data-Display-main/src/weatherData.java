public class weatherData{
    String cityName;
    String date;
    double temperature;
    char typeDegree;

    weatherData(String name,String date,double temp,char degree){
        this.cityName = name;
        this.date = date;
        this.temperature = temp;
        this.typeDegree = degree;
    }
    public String getCityName(){
        return cityName;
    }
    public String getDate(){
        return date;
    }
    public double getTemperature(){
        return temperature;
    }
    public char getTypeDegree(){
        return typeDegree;
    }


}
