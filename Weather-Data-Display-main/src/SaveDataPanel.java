import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SaveDataPanel extends JPanel {
    
    
    private JLabel selectFileLabel;
    private JTextField saveFileField;

    SaveDataPanel(){
        selectFileLabel = new JLabel("Select File:");
        saveFileField = new JTextField();

        GridBagConstraints constraints = new GridBagConstraints();
        this.setLayout(new GridBagLayout());
        this.setBorder(BorderFactory.createLineBorder(Color.black));
        constraints.anchor = GridBagConstraints.PAGE_START;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridx = 0;
        constraints.gridy = 0;
        this.add(selectFileLabel, constraints);
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.weightx = 0.5;
        constraints.gridx = 2;
        constraints.gridy = 1;
        this.add(saveFileField, constraints);
        saveFileField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String fileName = saveFileField.getText();
                CSVParser csvParser = new CSVParser();
                try {
                    csvParser.parseSaveFile(fileName);  //saves CSV array into file
                    JOptionPane.showMessageDialog(null, "Invalid file Name. Please Try again.");
                } catch (Exception exception) {
                    JOptionPane.showMessageDialog(SaveDataPanel.this, "Invalid file Name. Please Try again.");

                }
            }
        });

    }
}
