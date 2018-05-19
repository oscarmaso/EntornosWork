package pacman;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class puntuaciones {
    public JPanel panel1;
    private JTextArea puntuacionesTextArea;
    private JButton volverButton;
    private JTextArea recordTextArea;
    private JButton salirButton;

    public puntuaciones() {
        volverButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                JFrame puntuaciones = new JFrame("puntuaciones");
                puntuaciones.setVisible(false);
                JFrame principio = new JFrame("principioJuego");
                principio.setContentPane(new principioJuego().panel1);
                principio.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                principio.pack();
                principio.setVisible(true);
            }
        });
        salirButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
            System.exit(0);
            }
        });
    }
}
