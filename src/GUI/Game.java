package GUI;

import javax.swing.*;
import java.awt.*;

public class Game extends JPanel {
    private Graphics2D g2d;
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g2d = (Graphics2D) g;
    }

}
