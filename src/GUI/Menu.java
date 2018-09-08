package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu extends JFrame implements ActionListener{
    private int width, height;

    private JButton start = new JButton("start");
    private JButton exit = new JButton("exit");
    private JButton mainMenu = new JButton("main menu");
    private ImageIcon mapImgIcon;
    private CardLayout layout = new CardLayout();

    private JPanel panel = new JPanel();
    private JPanel game = new Game();
    private JPanel menu = new JPanel();

    public Menu (int width, int height){
        this.width = width;
        this.height = height;

        panel.setLayout(layout);
        addButtons();

        setSize(width, height);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
        setTitle("Risk");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        requestFocus();
    }
    private void addButtons(){

        start.addActionListener(this);
        exit.addActionListener(this);
        mainMenu.addActionListener(this);

        menu.add(start);
        menu.add(exit);

        game.add(mainMenu);

        //game.setBackground(Color.magenta);
        game.add(new JLabel(new ImageIcon("res/riskMap.jpg")));
        panel.add(menu, "Menu");
        panel.add(game, "Game");

        add(panel);
        layout.show(panel, "Menu");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();

        if(source == exit){
            System.exit(0);
        }else if (source == start) {
            System.out.println("start button hit");
            layout.show(panel, "Game");
        }else if (source == mainMenu){
            System.out.println("button hit");
            layout.show(panel, "Menu");
        }
    }
}
