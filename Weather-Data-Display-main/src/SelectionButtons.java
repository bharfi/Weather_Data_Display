import javax.swing.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class SelectionButtons extends JPanel {
    private ArrayList<JButton> buttons;
    private ButtonGroup buttonsGroup;
    private BoxLayout boxLayout; // See:  https://www.javatpoint.com/BoxLayout

    SelectionButtons(){
        buttons = new ArrayList<JButton>();
        boxLayout = new BoxLayout(this,BoxLayout.Y_AXIS);
        this.setLayout(boxLayout);
        buttonsGroup = new ButtonGroup(); //assigns selection buttons to a group to check if one is already selected or not

        addButton("About");  //adds buttons
        addButton("Load data");
        addButton("Add data");
        addButton("Save data");
        addButton("Plot data");
    }

    public ButtonGroup getButtonGroup() {
        return buttonsGroup;
    }

    public void addActionListeners(ActionListener listener){
        for(int i = 0;i < buttons.size();i++){
            buttons.get(i).addActionListener(listener);
        }
    }
    public void addButton(String newButtText){ //adds new button to the vertical list
        JButton newButt = new JButton(newButtText);
        buttons.add(newButt);  //adds to button arraylist
        this.add(newButt);  //adds to panel
        buttonsGroup.add(newButt);  //adds to the button group
    }
    public JButton getSelected(){
        JButton returnButt = null;
        for(JButton tempButt : buttons){
            if(buttonsGroup.isSelected(tempButt.getModel())){       //loops through all the buttons and returns which JButton is selected currently
                returnButt = tempButt;
            }
        }
        return returnButt;
    }
}
