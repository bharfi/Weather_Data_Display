import javax.swing.*;
import java.awt.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class AddDataPanel extends JPanel{
    private CSVParser csvParser = new CSVParser();

    AddDataPanel(){
        frontEnd(); //The gui frontend + csv Data Adder
    }

    public void frontEnd(){
        /*   Declaration   */
        JLabel addCityName,addDate,addTemperature,addDegree;
        JTextField cityField,dateField,tempField,degreeField;
        JButton submitButt;

        /*   Assignment   */
        new JPanel();
        addCityName = new JLabel("City Name:");
        addDate = new JLabel("Date:");
        addTemperature = new JLabel("Temperature:");
        addDegree = new JLabel("Type of Degree:");
        cityField = new JTextField();
        dateField = new JTextField();
        tempField = new JTextField();
        degreeField = new JTextField();
        submitButt = new JButton("Submit");


        /*  Layout  */
        this.setLayout(new GridBagLayout());
        this.setBorder(BorderFactory.createLineBorder(Color.black));

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.PAGE_START;
        constraints.fill = GridBagConstraints.HORIZONTAL;

        // 'City Name:'
        constraints.weightx = 0.6;
        constraints.gridx = 0;
        constraints.gridy = 0;
        this.add(addCityName, constraints);

        //'Date:'
        constraints.weightx = 0.5;
        constraints.gridx = 0;
        constraints.gridy = 1;
        this.add(addDate, constraints);

        //'Temperature:'
        constraints.gridx = 0;
        constraints.gridy = 2;
        this.add(addTemperature, constraints);

        //'Type of Degree:'
        constraints.gridx = 0;
        constraints.gridy = 3;
        this.add(addDegree, constraints);

        /* adding text Fields  */
        //cityfield
        constraints.weightx = 0.6;
        constraints.gridx = 1;
        constraints.gridy = 0;
        this.add(cityField, constraints);

        //datefield
        constraints.weightx = 0.5;
        constraints.gridx = 1;
        constraints.gridy = 1;
        this.add(dateField, constraints);

        //tempfield
        constraints.gridx = 1;
        constraints.gridy = 2;
        this.add(tempField, constraints);

        //degreefield
        constraints.gridx = 1;
        constraints.gridy = 3;
        this.add(degreeField, constraints);

        //submit button
        constraints.gridx = 1;
        constraints.gridy = 4;
        this.add(submitButt, constraints);

        /*    Adding data to CSV   */
        submitButt.addActionListener(e -> {
            try{
                /*  ADDING DATA TO GLOBAL WEATHERDATAARRAYLIST   */
                String cityName = cityField.getText();                     //gets cityName
                String date = dateField.getText();                         //gets date
                double temp = Double.parseDouble(tempField.getText());       //gets temperature
                char typeDegree = Character.toUpperCase(degreeField.getText().charAt(0));       //gets type of degree

                DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
                df.parse(date);     //if code runs, then date is correct; otherwise, catches ParseException

                if(cityName.equals("")){         //if city name is empty
                    JOptionPane.showMessageDialog(this,"Please fill in all the required Fields.");
                }else if(typeDegree != 'F' && typeDegree != 'C' && typeDegree != 'K'){ //if it is NONE of the 3 types of degrees
                    JOptionPane.showMessageDialog(this,"Wrong type of degree.");

                }else {
                    csvParser.addWeatherArray(cityName, date, temp, typeDegree); //adds weatherData to the global array
                    JOptionPane.showMessageDialog(this, "Added data!");
                }
            }
            catch(NumberFormatException numForE){
                JOptionPane.showMessageDialog(this,"Please fill in all the required Fields.");
            }
            catch(NullPointerException nE){
                JOptionPane.showMessageDialog(this,"Please fill in all the required Fields.");
            }
            catch(ArrayIndexOutOfBoundsException Ae)
            {
                JOptionPane.showMessageDialog(this,"The Array is Full.");
            }
            catch (ParseException parseE){
                JOptionPane.showMessageDialog(this,"Wrong date format.");
            }
            catch(StringIndexOutOfBoundsException SIOOBE){
                JOptionPane.showMessageDialog(this,"Please fill in all the required Fields.");
            }
            catch (Exception otherException){
                JOptionPane.showMessageDialog(this,otherException);
            }
        });
    }
}
