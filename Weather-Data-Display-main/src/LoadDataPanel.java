import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoadDataPanel extends JPanel{
    //TODO: complete .csv file loading + panel format
    private JLabel selectFileLabel; //testing content
    private JTextField loadFileField;

    LoadDataPanel(){
        frontEnd(); //The gui frontend + csv Data Loader
    }
    public void frontEnd(){
        /*   Assignment   */
        new JPanel();
        selectFileLabel = new JLabel("Select File:");
        loadFileField = new JTextField();

        /*  Layout  */
        this.setLayout(new GridBagLayout());
        this.setBorder(BorderFactory.createLineBorder(Color.black));

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.PAGE_START;
        constraints.fill = GridBagConstraints.HORIZONTAL;

        //'Select File:'
        constraints.gridx = 0;
        constraints.gridy = 0;
        this.add(selectFileLabel, constraints);

        /*   adding fields   */
        //loadfield
        constraints.weightx = 0.5;
        constraints.gridx = 2;
        constraints.gridy = 1;
        this.add(loadFileField, constraints);

        /*  Loading CSV data  */
        loadFileField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String fileName = loadFileField.getText();
                CSVParser csvParser = new CSVParser();   //TODO: Replace w/CSVManager CSV file
                try{
                    csvParser.parseReadFile(fileName);
                }
                catch (Exception exception){
                    JOptionPane.showMessageDialog(null,"Invalid file Name. Please Try again.");

                }
            }
        });
    }
}
