package pacman;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Scanner;
import java.io.FileWriter;


public class nuevapuntuaciones {
    public JPanel panel1;
    private JButton salirButton;
    private JButton volverButton;
    private JTextArea PUNTUACIONESTextArea;
    private JTextArea RECORDTextArea;


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

/*    public class LeeFichero {

        public static void main(String[] args) {

            // Fichero del que queremos leer
            File fichero = new File("puntuaciones.txt");
            Scanner s = null;

            try {
                // Leemos el contenido del fichero
                System.out.println("... Leemos el contenido del fichero ...");
                s = new Scanner(fichero);

                // Leemos linea a linea el fichero
                while (s.hasNextLine()) {
                    String linea = s.nextLine(); 	// Guardamos la linea en un String
                    System.out.println(linea);      // Imprimimos la linea
                }

            } catch (Exception ex) {
                System.out.println("Mensaje: " + ex.getMessage());
            } finally {
                // Cerramos el fichero tanto si la lectura ha sido correcta o no
                try {
                    if (s != null)
                        s.close();
                } catch (Exception ex2) {
                    System.out.println("Mensaje 2: " + ex2.getMessage());
                }
            }
        }
    }
*/
  /*  public class EscribeFichero {

        public static void main(String[] args) {


            FORMA 1 DE ESCRITURA
            FileWriter fichero = null;
            try {

                fichero = new FileWriter("puntuaciones.txt");

                // Escribimos linea a linea en el fichero
                for (String linea : lineas) {
                    fichero.write(linea + "\n");
                }

                fichero.close();

            } catch (Exception ex) {
                System.out.println("Mensaje de la excepción: " + ex.getMessage());
            }
        }
    }*/
}
