package pacman;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;                   //librerias utilizadas
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JFrame;
import javax.swing.Timer;



public class  FlappyBird implements ActionListener, MouseListener, KeyListener
{
    
    //variables
    public final int WIDTH = 800, HEIGHT = 800; //tamaño de la ventana
    public Renderer renderer; //
    public Rectangle bird; // flappy
    public ArrayList<Rectangle> columns; //tuberias
    public int ticks, yMotion, score;
    public boolean gameOver, started;
    public Random rand;
    public JFrame jframe;

    public FlappyBird() //el main llama a esta para que se ejecute
{                //caracetristicas de la ventana en la que se ejecuta el juego
    jframe = new JFrame(); //crea una ventana
    Timer timer = new Timer(20, this); //hace que el repaint de action performed actue durante el tiempo indicado
    renderer = new Renderer(this);
    rand = new Random();
    jframe.add(renderer);
    jframe.setTitle("Flappy Bird"); //titulo que aparecera en la barra de arriba del programa
    jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//define que al cerrar la ventan se deje de ejjcutar la aplicacion
    jframe.setSize(WIDTH, HEIGHT); //tamaño de la ventana
    jframe.addMouseListener(this);
    jframe.addKeyListener(this);
    jframe.setLocationRelativeTo(null);
    jframe.setResizable(false); //junto con la clase render lo utiliza para que la tamaño de la ventana no se pueda modificar
    jframe.setVisible(true); //para que aparezca la pantalla
    bird = new Rectangle(WIDTH / 2 - 10, HEIGHT / 2 - 10, 20, 20); //tamaño de flappy
    columns = new ArrayList<Rectangle>(); //tuberias
    addColumn(true);
    addColumn(true);
    addColumn(true);
    addColumn(true);
    timer.start();
}

public void addColumn(boolean start)
{
    int space = 300; //espacio entre tuberias
    int width = 100; 
    int height = 50 + rand.nextInt(300);
    if (start)
    {
        columns.add(new Rectangle(WIDTH + width + columns.size() * 300, HEIGHT - height - 120, width, height));
        columns.add(new Rectangle(WIDTH + width + (columns.size() - 1) * 300, 0, width, HEIGHT - height - space));
    }
    else
    {
        columns.add(new Rectangle(columns.get(columns.size() - 1).x + 600, HEIGHT - height - 120, width, height));
        columns.add(new Rectangle(columns.get(columns.size() - 1).x, 0, width, HEIGHT - height - space));
    }
    }

public void paintColumn(Graphics g, Rectangle column)
{
    g.setColor(Color.green.darker());
    g.fillRect(column.x, column.y, column.width, column.height);
}

public void jump()
{
    if (gameOver)
    {
        bird = new Rectangle(WIDTH / 2 - 10, HEIGHT / 2 - 10, 20, 20);
        columns.clear();
        yMotion = 0;
        score = 0;
        addColumn(true);
        addColumn(true);
        addColumn(true);
        addColumn(true);
        gameOver = false;
    }
    if (!started)
    {
        started = true;
    }
    else if (!gameOver)
    {
    if (yMotion > 0)
    {
        yMotion = 0;
    }
        yMotion -= 10;
    }
}
@Override

public void actionPerformed(ActionEvent e)
{
    int speed = 10;
    ticks++;
    if (started)
    {
            for (int i = 0; i < columns.size(); i++)
        {
            Rectangle column = columns.get(i);
            column.x -= speed;
        }
        if (ticks % 2 == 0 && yMotion < 15)
        {
            yMotion += 2;
        }
        for (int i = 0; i < columns.size(); i++)
        {
            Rectangle column = columns.get(i);
            if (column.x + column.width < 0)
            {
                columns.remove(column);
                if (column.y == 0)
                {
                    addColumn(false);
                }
            }
        }
        bird.y += yMotion;
        for (Rectangle column : columns)
        {
            if (column.y == 0 && bird.x + bird.width / 2 > column.x + column.width / 2 - 10 && bird.x + bird.width / 2 < column.x + column.width / 2 + 10)
            {
                score++;
            }
            if (column.intersects(bird))
            {
                gameOver = true;
                if (bird.x <= column.x)
                {
                    bird.x = column.x - bird.width;
                }
                else
                {
                if (column.y != 0)
                {
                    bird.y = column.y - bird.height;
                }
                else if (bird.y < column.height)
                {
                    bird.y = column.height;
                }
                }
            }
        }
        if (bird.y > HEIGHT - 120 || bird.y < 0)
        {
            gameOver = true;
        }
        if (bird.y + yMotion >= HEIGHT - 120)
        {
            bird.y = HEIGHT - 120 - bird.height;
            gameOver = true;
        }
    }
    renderer.repaint(); //repinta los elementos
}

public void repaint(Graphics g)
{
    g.setColor(Color.cyan); //color de fondo 
    g.fillRect(0, 0, WIDTH, HEIGHT);//tamaño del fondo
    
    g.setColor(Color.orange);  //color franjas de suelo
    g.fillRect(0, HEIGHT - 120, WIDTH, 120); //grosor de la primera franja del suelo
    
    g.setColor(Color.green);//color franjas de suelo
    g.fillRect(0, HEIGHT - 120, WIDTH, 20);//grosor de la primera franja del suelo
    
    g.setColor(Color.red);//color del flappy
    g.fillRect(bird.x, bird.y, bird.width, bird.height);//tamaño del cuadrado que representa un flappy
    for (Rectangle column : columns)
    {
        paintColumn(g, column);
    }
    g.setColor(Color.white);
    g.setFont(new Font("Arial", 1, 100));
    if (!started)
    {
        g.drawString("Click to start!", 75, HEIGHT / 2 - 50);
    }
    if (gameOver)
    {
        g.drawString("Game Over!", 100, HEIGHT / 2 - 50);
    }
    if (!gameOver && started)
    {
        g.drawString(String.valueOf(score), WIDTH / 2 - 25, 100);
    }
}


@Override

public void mouseClicked(MouseEvent e)
{
    jump();
}
@Override

public void keyReleased(KeyEvent e)
{
    if (e.getKeyCode() == KeyEvent.VK_SPACE)
    {
        jump();
    }
}
@Override

public void mousePressed(MouseEvent e)
{
    
}
@Override

public void mouseReleased(MouseEvent e)
{

}
@Override

public void mouseEntered(MouseEvent e)
{

}
@Override

public void mouseExited(MouseEvent e)
{

}
@Override

public void keyTyped(KeyEvent e)
{

}
@Override

public void keyPressed(KeyEvent e)
{

}



}