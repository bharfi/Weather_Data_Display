import org.jfree.data.time.Day;


import javax.swing.*;
import java.io.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;

public class CSVParser {

    String fileName;
    String[] splitLine;

    String rootFolder = "src/";     //root folder for loading and saving files
    public static ArrayList<weatherData> weatherDataArrayList = new ArrayList<>(100);
    private static DefaultPanel defaultPanel;      //linked to a SINGLE defaultpanel

    CSVParser(DefaultPanel defaultPanel){
        this.defaultPanel = defaultPanel;  //linked panel
    }

    CSVParser(){} //default constructor


    public void parseReadFile(String givenFileName){      //loads file from givenFileName
        this.fileName = givenFileName;
        String line;

        try{
            BufferedReader br = new BufferedReader(new FileReader(rootFolder + fileName));

            line = br.readLine();// to Read and ignore the headers
            System.out.println(line);
            while ((line = br.readLine()) != null)   //returns a Boolean value
            {
                splitLine = line.split(",");    // use comma as separator
                /* NEW weather data using readLine on CSV File */
                weatherData tempWD =  new weatherData(splitLine[0],splitLine[1],Double.parseDouble(splitLine[2]),splitLine[3].charAt(0));

                weatherDataArrayList.add(tempWD);      //adds to the global CSV data array
                defaultPanel.addRow(tempWD);  //adds row to the table at DefaultPanel
            }
            JOptionPane.showMessageDialog(null,"File has been Loaded");

        }
        catch(FileNotFoundException e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(null,"File can not be found.");
        }
        catch (IOException ie){
            ie.printStackTrace();
        }
    }


    public void addWeatherArray(String givenName,String givenDate,double temperature,char type){
        weatherData wd = new weatherData(givenName,givenDate,temperature,type);     //creates weather data
        weatherDataArrayList.add(wd);       //adds into arraylist with all other csv data
        defaultPanel.addRow(wd);  //adds row to the table at DefaultPanel


    }

    public void parseSaveFile(String givenFileName){    //saves file to givenFileName
        try {
            FileWriter writer = new FileWriter(rootFolder + givenFileName);     //path to where file should go
            BufferedWriter bwr = new BufferedWriter(writer);
            PrintWriter pwr = new PrintWriter(bwr);
            pwr.println("City Name,Date,Temperature,Type of Degree");
            for(weatherData wd:weatherDataArrayList) {      //for each weatherData in arraylist
                pwr.println(wd.getCityName()+","+wd.getDate()+","+wd.getTemperature()+","+wd.getTypeDegree());  //writes line by line
            }
            pwr.close();
            bwr.close();
        }
        catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public String[] uniqueCities()
    {
        String[] uniqueCity = new String[6];
        int i=0 ;
        HashSet<String> stringSet;
        stringSet = new HashSet<String>(5);
        uniqueCity[0] = weatherDataArrayList.get(0).getCityName();
        while (i<weatherDataArrayList.size()) {
            stringSet.add(weatherDataArrayList.get(i).getCityName());
            i++;
        }
        Iterator<String> itr=stringSet.iterator();
        int j= 0;
        while(itr.hasNext()){
            uniqueCity[j] = itr.next();
            j++;
        }
        return uniqueCity;
    }

    public double toFahrenheit(double givenTemp, char type)  //converts given temp and it's type to fahrenheit
   {
       double newTemp;
        switch (Character.toLowerCase(type)) {
            case 'c':
                newTemp = (((9.0 / 5.0) * givenTemp) + 32);
                break;
            case 'k':
                newTemp = (((givenTemp - 273.15) * 1.8) + 32);
                break;
            default:
                newTemp = givenTemp;
                break;
        }
        return newTemp;
    }

    public ArrayList<String>[] createDatasetTime(){
        ArrayList<String>[] newDatasets = new ArrayList[5];
        for (int i = 0; i < 5; i++) {
            newDatasets[i] = new ArrayList<>();
        }
        String[] cities = uniqueCities();
        int i = 0;
        while (i<weatherDataArrayList.size()) {
            for (int j = 0; j < cities.length; j++) {
                if (weatherDataArrayList.get(i).getCityName().equals(cities[j])) {
                    newDatasets[j].add(weatherDataArrayList.get(i).getDate());
                }
            }
            i++;
        }
        return newDatasets;
    }
    public ArrayList<Double>[] createDatasetTemp(){
        ArrayList<Double>[] newDatasets = new ArrayList[5];
            for (int i = 0; i < 5; i++) {
                newDatasets[i] = new ArrayList<>();
            }
            String[] cities = uniqueCities();
            int i = 0;
            while (i<weatherDataArrayList.size()) {
                for (int j = 0; j < cities.length; j++) {
                    if (weatherDataArrayList.get(i).getCityName().equals(cities[j])) {
                        newDatasets[j].add(toFahrenheit(weatherDataArrayList.get(i).getTemperature(), weatherDataArrayList.get(i).getTypeDegree()));
                    }
                }
                i++;
            }

        return newDatasets;
    }

    public Day dateFormat(String date){
        DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
        Date newDate = new Date();
        try {
            newDate = df.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return new Day(newDate);
    }

}
