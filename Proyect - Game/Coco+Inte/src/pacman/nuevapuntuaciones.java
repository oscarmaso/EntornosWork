package pacman;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class nuevapuntuaciones {
    public JPanel panel1;
    private JButton salirButton;
    private JButton volverButton;


    public nuevapuntuaciones() {
        volverButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                SwingUtilities.getWindowAncestor(panel1).setVisible(false);
                JFrame prin = new JFrame("principioJuego");
                prin.setContentPane(new principioJuego().panel1);
                prin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                prin.pack();
                prin.setLocationRelativeTo(null);
                prin.setVisible(true);
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
