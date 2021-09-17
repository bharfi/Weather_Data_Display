import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class DefaultPanel extends JPanel {
    private DefaultTableModel tableModel;   //table model, which handles the adding/deletion of rows
    private JTable table;
    private JScrollPane scrollPane;

    DefaultPanel(){
        frontEnd();
    }
    public void frontEnd(){
        /* TableModel sets whats in the rows and columns of the table, then is used to create an actual table GUI */
        tableModel = new DefaultTableModel();
        tableModel.addColumn("City Name");
        tableModel.addColumn("Date");
        tableModel.addColumn("Temperature");
        tableModel.addColumn("Type of Degree");

        table = new JTable(tableModel);    //creates actual table GUI from tableModel
        scrollPane = new JScrollPane(table);  //scrolling for table

        /*  Creating DefaultPanel  */
        new JPanel();
        setLayout(new BorderLayout());      //borderlayout to allow the ScrollPane to be placed at top left corner of panel
        add(scrollPane,BorderLayout.PAGE_START);        //places at top left corner of panel
    }
    public void addRow(weatherData data){       //for adding a row to the table

        /* adding one row */
        Object tempData[] = {data.getCityName(),data.getDate(),data.getTemperature(),data.getTypeDegree()};
        tableModel.addRow(tempData);
    }
}
