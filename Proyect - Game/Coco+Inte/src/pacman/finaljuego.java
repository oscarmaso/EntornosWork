package pacman;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class finaljuego {
    public JPanel panel1;
    private JButton menuPrincipalButton;
    private JButton salirButton;

    public finaljuego() {
        menuPrincipalButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                SwingUtilities.getWindowAncestor(panel1).setVisible(false);
                JFrame principioJuego = new JFrame("principioJuego");
                principioJuego.setContentPane(new principioJuego().panel1);
                principioJuego.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                principioJuego.pack();
                principioJuego.setLocationRelativeTo(null);
                principioJuego.setVisible(true);
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
