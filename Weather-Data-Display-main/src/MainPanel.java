import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainPanel extends JPanel implements ActionListener {
    private final CardLayout cardLayout;  //layout for determining what panel is shown; See: https://www.geeksforgeeks.org/java-awt-cardlayout-class/
    private final SelectionButtons buttonsPanel; //panel for side buttons
    private ButtonGroup buttonsGroup;

    private final DefaultPanel defaultPanel;
    private final JPanel aboutPanel;
    private final JPanel savePanel;
    private final LoadDataPanel loadPanel;
    private final AddDataPanel addPanel;
    private final JPanel plotPanel; //all panels that contain content (tables, plots, etc.)
    private final CSVParser csvParser;

    MainPanel(){ //MainPanel is a JPanel and uses the actionPerformed event listener

        cardLayout = new CardLayout();
        this.setLayout(cardLayout);

        defaultPanel = new DefaultPanel(); //default panel (table)
        aboutPanel = new AboutPanel(); //about panel
        savePanel = new SaveDataPanel();   //save data panel
        loadPanel = new LoadDataPanel();   //load data panel
        addPanel = new AddDataPanel();
        plotPanel = new PlotDataPanel();

        csvParser = new CSVParser(defaultPanel);  //linked default panel to csvparser

        /* ADDING ALL PANELS TO ONE MAIN PANEL */
        this.add("Default", defaultPanel); //adds default panel to main panel and sets custom name for panel (so it can be selected by NAME later!)
        this.add("About", aboutPanel);
        this.add("Load data", loadPanel);
        this.add("Add data", addPanel);
        this.add("Save data", savePanel);
        this.add("Plot data", plotPanel);

        this.setBounds(150,0,600,425);

        /* CREATING SIDE BUTTONS */
        buttonsPanel = new SelectionButtons();
        buttonsPanel.setBounds(0,0,150,300);
        cardLayout.show(this,"About");
        buttonsPanel.addActionListeners(this); //listening if button is clicked; if clicked, go to actionPerformed method

    }
    public SelectionButtons getButtonPanel(){
        return buttonsPanel;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) { //actionevent for button switching to new page
        try {
            JButton caughtButton = ((JButton)actionEvent.getSource()); //caughtButton = button that was clicked
            buttonsGroup = buttonsPanel.getButtonGroup();       //gets the group

            /*  COSMETIC BUTTON COLORS  */
            JButton currentlySelected = buttonsPanel.getSelected();     //gets currently selected button
            if (currentlySelected != null) {        //if there is a selected button
                currentlySelected.setBackground(null);      //reset button color
            }


            if(buttonsGroup.isSelected(caughtButton.getModel())){               //if button was already selected
                buttonsGroup.clearSelection(); //clears ALL selected buttons
                cardLayout.show(this,"Default");    //Shows panel with name "Default"

            }else if(!(buttonsGroup.isSelected(caughtButton.getModel()))){      //if button was not selected
                buttonsGroup.clearSelection(); //clears ALL selected buttons
                buttonsGroup.setSelected(caughtButton.getModel(),true); //pressed button is SELECTED
                caughtButton.setBackground(Color.lightGray); //pressed button has LIGHTGRAY COLOR

                cardLayout.show(this,caughtButton.getText()); //Shows panel with invisible name of selected panel
            }


        }catch(Exception x){  //event error
            System.out.println("Error at button event");
        }
    }
}
