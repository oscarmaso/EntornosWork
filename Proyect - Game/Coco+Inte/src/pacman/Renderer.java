package pacman;


//este documento define los graficos del juego 

import java.awt.Graphics;
import javax.swing.*;

public class Renderer extends JPanel
{

    private static final long serialVersionUID = 1L;
    private FlappyBird flappyBird;

    public Renderer(FlappyBird flappyBird) {
        this.flappyBird = flappyBird;
    }

    @Override

    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        flappyBird.repaint(g);
        
    }

}
