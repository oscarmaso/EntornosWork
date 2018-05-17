package pacman;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import java.awt.EventQueue;

public class principioJuego {
    private JPanel panel1;
    private JButton elegirModoJuegoButton;
    private JButton verPuntuacionesButton;
    private JButton salirButton;

    public principioJuego() {
        elegirModoJuegoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

            }
        });
        verPuntuacionesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

            }
        });
        salirButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

            }
        });
    }

    /*public static void main(String[] args) {

        EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                principioJuego principio = new principioJuego();
                principio.setVisible(true);
            }
        });
    }*/
}