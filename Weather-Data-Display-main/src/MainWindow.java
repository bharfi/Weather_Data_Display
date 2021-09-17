import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame {
    private MainPanel mainPanel;

    MainWindow(){
        mainPanel = new MainPanel();

        /* ADDING COMPONENTS TO FRAME */
        this.add(mainPanel.getButtonPanel(), BorderLayout.PAGE_START); //adds selection buttons to window
        this.add(mainPanel,BorderLayout.PAGE_END); //adds content panels to window
        this.setSize(800,455);
        this.setLayout(null);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    public static void main(String[] args){
        MainWindow showingWindow = new MainWindow();
    }
}
