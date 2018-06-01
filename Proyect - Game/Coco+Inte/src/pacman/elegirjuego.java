package pacman;

import sun.nio.ch.ThreadPool;

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
                SwingUtilities.getWindowAncestor(panel1).setVisible(false);
                Thread thread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        new FlappyBird();
                    }
                });
                thread.start();
            }
        });
        pacmanButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                SwingUtilities.getWindowAncestor(panel1).setVisible(false);
                JFrame pacman = new Pacman();
                pacman.setVisible(true);
            }
        });
        contrarrelojButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                SwingUtilities.getWindowAncestor(panel1).setVisible(false);
                JFrame pacman = new Pacman();
                pacman.setVisible(true);
            }
        });
        volverButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                JFrame principio = new JFrame("principioJuego");
                SwingUtilities.getWindowAncestor(panel1).setVisible(false);
                principio.setContentPane(new principioJuego().panel1);
                principio.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                principio.pack();
                principio.setLocationRelativeTo(null);
                principio.setVisible(true);
            }
        });
    }
}
