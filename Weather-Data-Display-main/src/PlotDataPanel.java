import javax.swing.*;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.general.SeriesException;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;



public class PlotDataPanel extends JPanel {
    private JFreeChart chart;
    private ChartPanel  panel;
    private XYPlot plot;
    PlotDataPanel(){

        JButton plotButt = new JButton("Plot Graph");
        plotButt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                XYDataset dataset = createDataset();
                chart = ChartFactory.createTimeSeriesChart(null, "Date", "Temperature (°F)", dataset);
                plot = (XYPlot) chart.getPlot();
                //XYPlot plot = (XYPlot) chart.getPlot();
                plot.setBackgroundPaint(new Color(255,229,196));
                plot.setDomainGridlinesVisible(true);
                plot.setDomainGridlinePaint(Color.black);
                plot.setRangeGridlinePaint(Color.black);
                XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer(true, true);
                plot.setRenderer(renderer);
                renderer.setDefaultItemLabelsVisible(true);
                panel = new ChartPanel(chart);
                panel.setPreferredSize(new Dimension(400, 400));
                Container C = plotButt.getParent();
                plotButt.getParent().add(panel);
                plotButt.getParent().remove(plotButt);
                C.revalidate();
                C.repaint();
                System.out.println("Updated Graph step 2");
            }
        });
        this.add(plotButt);
    }


    private XYDataset createDataset() {

        TimeSeriesCollection timeDataset = new TimeSeriesCollection();

        CSVParser csvParser = new CSVParser();
        TimeSeries dateSeries1 = null;
        TimeSeries dateSeries2 = null;
        TimeSeries dateSeries3 = null;
        TimeSeries dateSeries4 = null;
        TimeSeries dateSeries5  = null;
        try {

            String[] cities = csvParser.uniqueCities();

            if(cities[0]!= null)
            dateSeries1 = new TimeSeries(cities[0]);
        if(cities[1]!= null)
            dateSeries2 = new TimeSeries(cities[1]);
        if(cities[2]!= null)
            dateSeries3 = new TimeSeries(cities[2]);
        if(cities[3]!= null)
            dateSeries4 = new TimeSeries(cities[3]);
        if(cities[4]!= null)
            dateSeries5 = new TimeSeries(cities[4]);
        ArrayList<String>[] timeArray ;
        ArrayList<Double>[] tempArray ;

            timeArray = csvParser.createDatasetTime();
            tempArray = csvParser.createDatasetTemp();
                if(cities[0]!= null) {
                    for (int j = 0; j < timeArray[0].size(); j++) {
                        if (dateSeries1 != null) {
                            dateSeries1.add(csvParser.dateFormat(timeArray[0].get(j)), tempArray[0].get(j));
                        }
                    }
                    timeDataset.addSeries(dateSeries1);
                }
                if(cities[1]!= null) {
                    for (int j = 0; j < timeArray[1].size(); j++) {
                        if (dateSeries2 != null) {
                            dateSeries2.add(csvParser.dateFormat(timeArray[1].get(j)), tempArray[1].get(j));
                        }
                    }
                    timeDataset.addSeries(dateSeries2);

                }
                if(cities[2]!= null) {
                    for (int j = 0; j < timeArray[2].size(); j++) {
                        if (dateSeries3 != null) {
                            dateSeries3.add(csvParser.dateFormat(timeArray[2].get(j)), tempArray[2].get(j));
                        }
                    }
                    timeDataset.addSeries(dateSeries3);

                }
                if(cities[3]!= null) {
                    for (int j = 0; j < timeArray[3].size(); j++) {
                        if (dateSeries4 != null) {
                            dateSeries4.add(csvParser.dateFormat(timeArray[3].get(j)), tempArray[3].get(j));
                        }
                    }
                    timeDataset.addSeries(dateSeries4);

                }
                if(cities[4]!= null) {
                    for (int j = 0; j < timeArray[4].size(); j++) {
                        dateSeries5.add(csvParser.dateFormat(timeArray[4].get(j)), tempArray[4].get(j));
                    }
                    timeDataset.addSeries(dateSeries5);

                }
        }
        catch (NullPointerException | IndexOutOfBoundsException e)
        {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null,"No File has bee loaded yet!\nPlease RESTART and Load File First.");
        }
        catch (SeriesException s){
            JOptionPane.showMessageDialog(null,"Duplicate date's found!\nPlease restart and try again");

        }
        catch (Exception exception){
            exception.printStackTrace();
        }



        return timeDataset;


    }


}

