import javax.swing.*;
import java.awt.*;

public class AboutPanel extends JPanel {
    private JLabel aboutLabel;

    AboutPanel(){
        aboutLabel = new JLabel("<html>About<br>Team Project Group 6<br><br>&emsp;Alexander Labenz<br>&emsp;Bharath Prabakaran<br>&emsp;Courtney Banks<br>&emsp;David Zalewski</html>");
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        this.setLayout(new BorderLayout());
        this.add(aboutLabel,BorderLayout.PAGE_START);
    }
}
