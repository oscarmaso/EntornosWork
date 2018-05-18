package pacman;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class elegirjuego {

    public JPanel panel1;
    private JButton contrarrelojButton;
    private JButton volverButton;
    private JButton flappyBirdButton;
    private JButton pacmanButton;

    public elegirjuego() {
        flappyBirdButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

            }
        });
        pacmanButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

            }
        });
        contrarrelojButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

            }
        });
        volverButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                JFrame principio = new JFrame("principioJuego");
                principio.setContentPane(new principioJuego().panel1);
                principio.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                principio.pack();
                principio.setVisible(true);
            }
        });
    }
}
