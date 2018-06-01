package flappyBird;


//este documento define los graficos del juego 

import java.awt.Graphics;
import javax.swing.JPanel;
import java.awt.Graphics2D;

public class Renderer extends JPanel
{

    private static final long serialVersionUID = 1L;

    @Override

    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        FlappyBird.flappyBird.repaint(g);
    }

}
