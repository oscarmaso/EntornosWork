package pacman;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import java.awt.EventQueue;

public class principioJuego {
    public JPanel panel1;
    private JButton elegirModoJuegoButton;
    private JButton verPuntuacionesButton;
    private JButton salirButton;

    public principioJuego() {
        elegirModoJuegoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
            JFrame elegir_juego = new JFrame("elegirjuego");
            elegir_juego.setContentPane(new elegirjuego().panel1);
            elegir_juego.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            elegir_juego.pack();
            elegir_juego.setVisible(true);
            }
        });
        verPuntuacionesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                JFrame puntuaciones = new JFrame("puntuaciones");
                puntuaciones.setContentPane(new puntuaciones().panel1);
                puntuaciones.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                puntuaciones.pack();
                puntuaciones.setVisible(true);
            }
        });
        salirButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
            System.exit(0);
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("principioJuego");
        frame.setContentPane(new principioJuego().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}