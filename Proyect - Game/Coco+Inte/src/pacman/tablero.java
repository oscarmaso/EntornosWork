package pacman;

import sun.font.TrueTypeFont;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Event;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.lang.Thread;

import javax.swing.*;

public class tablero extends JPanel implements ActionListener {

    private Dimension d;
    private final Font smallfont = new Font("Helvetica", Font.BOLD, 14);

    private Image ii;
    private final Color dotcolor = new Color(192, 192, 0);
    private Color mazecolor;

    private boolean ingame = false;
    private boolean dying = false;
    private boolean Cfruta = false;

    private final int blocksize = 24;
    private final int nrofblocks = 15;
    private final int scrsize = nrofblocks * blocksize;
    private final int pacanimdelay = 2;
    private final int pacmananimcount = 4;
    private final int maxghosts = 12;
    private final int pacmanspeed = 6;

    private int pacanimcount = pacanimdelay;
    private int pacanimdir = 1 ;
    private int pacmananimpos = 0;
    private int Contador = 0;
    private int nrofghosts = 6;
    private int pacsleft, score;
    private int[] dx, dy,ContadorFinal,Contador2;
    private int[] ghostx, ghosty, ghostdx, ghostdy, ghostspeed ,ComidaX,ComidaY,ComidaES;


    private Image fantasma;
    private Image pacman1, pacman2up, pacman2left, pacman2right, pacman2down;
    private Image pacman3up, pacman3down, pacman3left, pacman3right;
    private Image pacman4up, pacman4down, pacman4left, pacman4right;
    private Image Muerte1, Muerte2,Muerte3,Muerte4, Muerte5 ,Muerte6 ,Muerte7, Muerte8, Muerte9,Comida ,Vulnerable, Vulnerable2;

    private int pacmanx, pacmany, pacmandx, pacmandy;
    private int reqdx, reqdy, viewdx, viewdy;

    private final short datosdelnivel[] = {
            19, 18, 26, 26, 26, 18, 18, 18, 18, 18, 26, 26, 26, 18, 22,
            17, 20, 19, 18, 18, 20, 17, 16, 20, 17, 18, 18, 22, 17, 20,
            21, 21, 17, 16, 16, 20, 17, 16, 20, 17, 16, 16, 20, 21, 21,
            21, 25, 28, 17, 16, 20, 17, 16, 20, 17, 16, 20, 25, 28, 21,
            17, 18, 26, 24, 24, 20, 17, 16, 20, 17, 24, 24, 26, 18, 20,
            17, 20, 19, 26, 26, 16, 16, 16, 16, 16, 26, 26, 22, 17, 20,
            17, 20, 21, 19, 18, 24, 24, 24, 24, 24, 18, 22, 21, 17, 20,
            17, 20, 21, 17, 16, 26, 26, 26, 26, 26, 16, 20, 21, 17, 20,
            17, 20, 21, 25, 24, 18, 18, 18, 18, 18, 24, 28, 21, 17, 20,
            17, 20, 25, 26, 26, 16, 16, 16, 16, 16, 26, 26, 28, 17, 20,
            17, 24, 26, 18, 18, 20, 17, 16, 20, 17, 18, 18, 26, 24, 20,
            21, 19, 22, 17, 16, 20, 17, 16, 20, 17, 16, 20, 19, 22, 21,
            21, 21, 17, 16, 16, 20, 17, 16, 20, 17, 16, 16, 20, 21, 21,
            17, 20, 25, 24, 24, 20, 17, 16, 20, 17, 24, 24, 28, 17, 20,
            25, 24, 26, 26, 26, 24, 24, 24, 24, 24, 26, 26, 26, 24, 28
    };


    private final int validspeeds[] = {1, 2, 3, 4, 6, 8};
    private final int maximavelocidad = 6;

    private int velocidadmedia = 3;
    private short[] screendata;
    private Timer timer;

    public tablero() {

        CargarImagenes();
        initVariables();

        addKeyListener(new TAdapter());

        setFocusable(true);

        setBackground(Color.BLACK);
        setDoubleBuffered(true);
    }

    private void initVariables() {

        screendata = new short[nrofblocks * nrofblocks];
        mazecolor = new Color(5, 100, 5);
        d = new Dimension(400, 400);
        ghostx = new int[maxghosts];
        ghostdx = new int[maxghosts];
        ghosty = new int[maxghosts];
        ghostdy = new int[maxghosts];
        ghostspeed = new int[maxghosts];
        dx = new int[4];
        dy = new int[4];
        ComidaX=new int[4];
        ComidaY=new int[4];
        ComidaES=new int[4];
        Contador2=new int[maxghosts];
        ContadorFinal=new int[maxghosts];


        timer = new Timer(40, this);
        timer.start();
    }

    @Override
    public void addNotify() {
        super.addNotify();

        iniciarjuego();
    }

    private void doAnim() {

        pacanimcount--;
        if (pacmananimpos>=4 || pacmananimpos<0)
        {
            pacmananimpos=0;
            pacanimdir = 1;
        }
        if (pacanimcount <= 0) {
            pacanimcount = pacanimdelay;
            pacmananimpos += pacanimdir;

            if (pacmananimpos == (pacmananimcount - 1) || pacmananimpos == 0) {
                pacanimdir = -pacanimdir;
            }
        }
    }
    private void doAnimM() {

        pacanimcount--;

        if (pacanimcount <= 0) {
            pacanimcount = pacanimdelay;
            pacmananimpos += pacanimdir;
        }
    }


    private void playGame(Graphics2D g2d) {

        if (dying) {

            Muerte(g2d);
            pacmananimpos=0;
            pacanimdir = 1;


        } else {

            moverPacman();
            dibujarFruta(g2d);
            dibujarPacman(g2d);
            moverFantasma(g2d);
            checkMaze();
        }
    }

    private void showIntroScreen(Graphics2D g2d) {

        g2d.setColor(new Color(0, 32, 48));
        g2d.fillRect(50, scrsize / 2 - 30, scrsize - 100, 50);
        g2d.setColor(Color.white);
        g2d.drawRect(50, scrsize / 2 - 30, scrsize - 100, 50);

        String s = "Presiona s para empezar.";
        Font small = new Font("Helvetica", Font.BOLD, 15);
        FontMetrics metr = this.getFontMetrics(small);

        g2d.setColor(Color.white);
        g2d.setFont(small);
        g2d.drawString(s, (scrsize - metr.stringWidth(s)) / 2, scrsize / 2);
    }

    private void dibujarpuntuacion(Graphics2D g) {

        int i;
        String s;

        g.setFont(smallfont);
        g.setColor(new Color(96, 128, 255));
        s = "Score: " + score;
        g.drawString(s, scrsize / 2 + 96, scrsize + 16);

        for (i = 0; i < pacsleft; i++) {
            g.drawImage(pacman2left, i * 28 + 8, scrsize + 1, this);
        }
    }

    private void checkMaze() {

        short i = 0;
        boolean finished = true;

        while (i < nrofblocks * nrofblocks && finished) {

            if ((screendata[i] & 48) != 0) {
                finished = false;
            }

            i++;
        }

        if (finished) {

            score += 50;

            if (nrofghosts < maxghosts) {
                nrofghosts++;
            }

            if (velocidadmedia < maximavelocidad) {
                velocidadmedia++;
            }

            initLevel();
        }
    }

    private void Muerte(Graphics2D g2d) {//murio

        pacsleft--;

        if (pacsleft == 0) {
            ingame = false;
            pacmananimpos=0;
            pacanimcount=3;


        }

        continuarnivel();
    }

    private void moverFantasma(Graphics2D g2d) {//mover fantasmas

        short i;
        int pos;
        int count;

        for (i = 0; i < nrofghosts; i++) {
            if (ghostx[i] % blocksize == 0 && ghosty[i] % blocksize == 0) {
                pos = ghostx[i] / blocksize + nrofblocks * (int) (ghosty[i] / blocksize);

                count = 0;

                if ((screendata[pos] & 1) == 0 && ghostdx[i] != 1) {
                    dx[count] = -1;
                    dy[count] = 0;
                    count++;
                }

                if ((screendata[pos] & 2) == 0 && ghostdy[i] != 1) {
                    dx[count] = 0;
                    dy[count] = -1;
                    count++;
                }

                if ((screendata[pos] & 4) == 0 && ghostdx[i] != -1) {
                    dx[count] = 1;
                    dy[count] = 0;
                    count++;
                }

                if ((screendata[pos] & 8) == 0 && ghostdy[i] != -1) {
                    dx[count] = 0;
                    dy[count] = 1;
                    count++;
                }

                if (count == 0) {

                    if ((screendata[pos] & 15) == 15) {
                        ghostdx[i] = 0;
                        ghostdy[i] = 0;
                    } else {
                        ghostdx[i] = -ghostdx[i];
                        ghostdy[i] = -ghostdy[i];
                    }

                } else {

                    count = (int) (Math.random() * count);

                    if (count > 3) {
                        count = 3;
                    }

                    ghostdx[i] = dx[count];
                    ghostdy[i] = dy[count];
                }

            }

            ghostx[i] = ghostx[i] + (ghostdx[i] * ghostspeed[i]);
            ghosty[i] = ghosty[i] + (ghostdy[i] * ghostspeed[i]);
            if (Cfruta) {
                Contador2[i]++;
                if (Contador2[i] < 80) {
                    g2d.drawImage(Vulnerable, ghostx[i] + 1, ghosty[i] + 1, this);
                } else {
                    if (ContadorFinal[i] == 20) {
                        g2d.drawImage(Vulnerable2, ghostx[i] + 1, ghosty[i] + 1, this);
                        ContadorFinal[i] = 0;
                    } else {
                        g2d.drawImage(Vulnerable, ghostx[i] + 1, ghosty[i] + 1, this);
                        ContadorFinal[i]++;
                    }
                }
                if (Contador2[i] >= 160) {
                    Cfruta = false;
                    for (int X = 0; X<=maxghosts; X++){
                        Contador2[X] = 0;
                        ContadorFinal[X] = 0;
                    }
                }
            }

            else {
                drawGhost(g2d, ghostx[i] + 1, ghosty[i] + 1);

            }

            if (pacmanx > (ghostx[i] - 12) && pacmanx < (ghostx[i] + 12)
                    && pacmany > (ghosty[i] - 12) && pacmany < (ghosty[i] + 12)
                    && ingame) {
                if (Cfruta==false){
                    dying = true;}
                else{
                    ghostx[i] = 7 * blocksize;
                    ghosty[i]= 11 * blocksize;
                    score+=50;

                }
            }
        }
    }

    private void drawGhost(Graphics2D g2d, int x, int y) {

        g2d.drawImage(fantasma, x, y, this);
    }

    private void moverPacman() {

        int pos;
        short ch;

        if (reqdx == -pacmandx && reqdy == -pacmandy) {
            pacmandx = reqdx;
            pacmandy = reqdy;
            viewdx = pacmandx;
            viewdy = pacmandy;
        }

        if (pacmanx % blocksize == 0 && pacmany % blocksize == 0) {
            pos = pacmanx / blocksize + nrofblocks * (int) (pacmany / blocksize);
            ch = screendata[pos];

            if ((ch & 16) != 0) {
                screendata[pos] = (short) (ch & 15);
                score++;
            }

            if (reqdx != 0 || reqdy != 0) {
                if (!((reqdx == -1 && reqdy == 0 && (ch & 1) != 0)
                        || (reqdx == 1 && reqdy == 0 && (ch & 4) != 0)
                        || (reqdx == 0 && reqdy == -1 && (ch & 2) != 0)
                        || (reqdx == 0 && reqdy == 1 && (ch & 8) != 0))) {
                    pacmandx = reqdx;
                    pacmandy = reqdy;
                    viewdx = pacmandx;
                    viewdy = pacmandy;
                }
            }

            // Check for standstill
            if ((pacmandx == -1 && pacmandy == 0 && (ch & 1) != 0)
                    || (pacmandx == 1 && pacmandy == 0 && (ch & 4) != 0)
                    || (pacmandx == 0 && pacmandy == -1 && (ch & 2) != 0)
                    || (pacmandx == 0 && pacmandy == 1 && (ch & 8) != 0)) {
                pacmandx = 0;
                pacmandy = 0;
            }
        }
        pacmanx += pacmanspeed * pacmandx;
        pacmany += pacmanspeed * pacmandy;
        for (int i=0 ;i<4;i++) {
            if (pacmanx == ComidaX[i] && pacmany == ComidaY[i] && ComidaES[i]==1){
                ComidaES[i]=0;
                Cfruta = true;
                for (int X = 0; X<maxghosts; X++){
                    Contador2[X] = 0;
                    ContadorFinal[X] = 0;
                }
            }
        }
    }

    private void dibujarPacman(Graphics2D g2d) {

        if (viewdx == -1) {
            Pacmanizquierda(g2d);
        } else if (viewdx == 1) {
            Pacmanaderecha(g2d);
        } else if (viewdy == -1) {
            Pacmanarriba(g2d);
        } else {
            Pacmanabajo(g2d);
        }
    }
    private void dibujarFruta(Graphics2D g2d) {
        for (int i = 0; i < 4; i++) {
            if (ComidaES[i] == 1) {
                g2d.drawImage(Comida, ComidaX[i], ComidaY[i], this);
            }
        }
    }

    private void Pacmanarriba(Graphics2D g2d) {
        switch (pacmananimpos) {
            case 1:
                g2d.drawImage(pacman2up, pacmanx + 1, pacmany + 1, this);

                break;
            case 2:
                g2d.drawImage(pacman3up, pacmanx + 1, pacmany + 1, this);
                break;
            case 3:
                g2d.drawImage(pacman4up, pacmanx + 1, pacmany + 1, this);
                break;
            default:
                g2d.drawImage(pacman1, pacmanx + 1, pacmany + 1, this);
                break;
        }
    }
    private void Pacmanabajo(Graphics2D g2d) {

        switch (pacmananimpos) {
            case 1:
                g2d.drawImage(pacman2down, pacmanx + 1, pacmany + 1, this);
                break;
            case 2:
                g2d.drawImage(pacman3down, pacmanx + 1, pacmany + 1, this);
                break;
            case 3:
                g2d.drawImage(pacman4down, pacmanx + 1, pacmany + 1, this);
                break;
            default:
                g2d.drawImage(pacman1, pacmanx + 1, pacmany + 1, this);
                break;
        }
    }

    private void Pacmanizquierda(Graphics2D g2d) {

        switch (pacmananimpos) {
            case 1:
                g2d.drawImage(pacman2left, pacmanx + 1, pacmany + 1, this);
                break;
            case 2:
                g2d.drawImage(pacman3left, pacmanx + 1, pacmany + 1, this);
                break;
            case 3:
                g2d.drawImage(pacman4left, pacmanx + 1, pacmany + 1, this);
                break;
            default:
                g2d.drawImage(pacman1, pacmanx + 1, pacmany + 1, this);
                break;
        }
    }

    private void Pacmanaderecha(Graphics2D g2d) {

        switch (pacmananimpos) {
            case 1:
                g2d.drawImage(pacman2right, pacmanx + 1, pacmany + 1, this);
                break;
            case 2:
                g2d.drawImage(pacman3right, pacmanx + 1, pacmany + 1, this);
                break;
            case 3:
                g2d.drawImage(pacman4right, pacmanx + 1, pacmany + 1, this);
                break;
            default:
                g2d.drawImage(pacman1, pacmanx + 1, pacmany + 1, this);
                break;
        }
    }

    private void drawMaze(Graphics2D g2d) {

        short i = 0;
        int x, y;

        for (y = 0; y < scrsize; y += blocksize) {
            for (x = 0; x < scrsize; x += blocksize) {

                g2d.setColor(mazecolor);
                g2d.setStroke(new BasicStroke(2));

                if ((screendata[i] & 1) != 0) {
                    g2d.drawLine(x, y, x, y + blocksize - 1);
                }

                if ((screendata[i] & 2) != 0) {
                    g2d.drawLine(x, y, x + blocksize - 1, y);
                }

                if ((screendata[i] & 4) != 0) {
                    g2d.drawLine(x + blocksize - 1, y, x + blocksize - 1,
                            y + blocksize - 1);
                }

                if ((screendata[i] & 8) != 0) {
                    g2d.drawLine(x, y + blocksize - 1, x + blocksize - 1,
                            y + blocksize - 1);
                }

                if ((screendata[i] & 16) != 0) {
                    g2d.setColor(dotcolor);
                    g2d.fillRect(x + 11, y + 11, 2, 2);
                }

                i++;
            }
        }
    }
    private void AnimacionMuerte (Graphics2D g2d){

        switch (pacmananimpos) {
            case 1:
                g2d.drawImage(pacman2up, pacmanx + 1, pacmany + 1, this);
                break;
            case 2:
                g2d.drawImage(pacman3up, pacmanx + 1, pacmany + 1, this);
                break;
            case 3:
                g2d.drawImage(Muerte1, pacmanx + 1, pacmany + 1, this);
                break;
            case 4:
                g2d.drawImage(Muerte2, pacmanx + 1, pacmany + 1, this);
                break;
            case 5:
                g2d.drawImage(Muerte3, pacmanx + 1, pacmany + 1, this);
                break;
            case 6:
                g2d.drawImage(Muerte4, pacmanx + 1, pacmany + 1, this);
                break;
            case 7:
                g2d.drawImage(Muerte5, pacmanx + 1, pacmany + 1, this);
                break;
            case 8:
                g2d.drawImage(Muerte6, pacmanx + 1, pacmany + 1, this);
                break;
            case 9:
                g2d.drawImage(Muerte7, pacmanx + 1, pacmany + 1, this);
                break;
            case 10:
                g2d.drawImage(Muerte8, pacmanx + 1, pacmany + 1, this);
                break;
            case 11:
                g2d.drawImage(Muerte9, pacmanx + 1, pacmany + 1, this);
                SwingUtilities.getWindowAncestor(this).setVisible(false);
                JFrame finaljuego = new JFrame("finaljuego");
                finaljuego.setContentPane(new finaljuego().panel1);
                finaljuego.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                finaljuego.pack();
                finaljuego.setLocationRelativeTo(null);
                finaljuego.setVisible(true);
                break;}

    }

    private void iniciarjuego() {

        pacsleft = 1;
        score = 0;
        initLevel();
        nrofghosts = 4;
        velocidadmedia = 3;
    }

    private void initLevel() {

        int i;
        for (i = 0; i < nrofblocks * nrofblocks; i++) {
            screendata[i] = datosdelnivel[i];
        }

        continuarnivel();
    }

    private void continuarnivel() {

        short i;
        int dx = 1;
        int random;

        for (i = 0; i < nrofghosts; i++) {

            ghosty[i] = 4 * blocksize;
            ghostx[i] = 4 * blocksize;
            ghostdy[i] = 0;
            ghostdx[i] = dx;
            dx = -dx;
            random = (int) (Math.random() * (velocidadmedia + 1));

            if (random > velocidadmedia) {
                random = velocidadmedia;
            }

            ghostspeed[i] = validspeeds[random];
        }

        ComidaX[0]=1* blocksize;
        ComidaY[0]=1 * blocksize;
        ComidaES[0]=1;
        ComidaX[1]=13 * blocksize;
        ComidaY[1]=1 * blocksize;
        ComidaES[1]=1;
        ComidaX[2]=1 * blocksize;
        ComidaY[2]=13 * blocksize;
        ComidaES[2]=1;
        ComidaX[3]=13 * blocksize;
        ComidaY[3]=13 * blocksize;
        ComidaES[3]=1;

        pacmanx = 7 * blocksize;
        pacmany = 11 * blocksize;
        pacmandx = 0;
        pacmandy = 0;
        reqdx = 0;
        reqdy = 0;
        viewdx = -1;
        viewdy = 0;
        dying = false;
        Cfruta=false;
        for (int X = 0; X<maxghosts; X++){
            Contador2[X] = 0;
            ContadorFinal[X] = 0;
        }
    }

    private void CargarImagenes() {

        fantasma = new ImageIcon(getClass().getResource("../imagenes/ghost.png")).getImage();
        pacman1 = new ImageIcon(getClass().getResource("../imagenes/pacman.png")).getImage();
        pacman2up = new ImageIcon(getClass().getResource("../imagenes/up1.png")).getImage();
        pacman3up = new ImageIcon(getClass().getResource("../imagenes/up2.png")).getImage();
        pacman4up = new ImageIcon(getClass().getResource("../imagenes/up3.png")).getImage();
        pacman2down = new ImageIcon(getClass().getResource("../imagenes/down1.png")).getImage();
        pacman3down = new ImageIcon(getClass().getResource("../imagenes/down2.png")).getImage();
        pacman4down = new ImageIcon(getClass().getResource("../imagenes/down3.png")).getImage();
        pacman2left = new ImageIcon(getClass().getResource("../imagenes/left1.png")).getImage();
        pacman3left = new ImageIcon(getClass().getResource("../imagenes/left2.png")).getImage();
        pacman4left = new ImageIcon(getClass().getResource("../imagenes/left3.png")).getImage();
        pacman2right = new ImageIcon(getClass().getResource("../imagenes/right1.png")).getImage();
        pacman3right = new ImageIcon(getClass().getResource("../imagenes/right2.png")).getImage();
        pacman4right = new ImageIcon(getClass().getResource("../imagenes/right3.png")).getImage();
        Muerte1 = new ImageIcon(getClass().getResource("../imagenes/pacmanM1.png")).getImage();
        Muerte2 = new ImageIcon(getClass().getResource("../imagenes/pacmanM2.png")).getImage();
        Muerte3 = new ImageIcon(getClass().getResource("../imagenes/pacmanM3.png")).getImage();
        Muerte4 = new ImageIcon(getClass().getResource("../imagenes/pacmanM4.png")).getImage();
        Muerte5 = new ImageIcon(getClass().getResource("../imagenes/pacmanM5.png")).getImage();
        Muerte6 = new ImageIcon(getClass().getResource("../imagenes/pacmanM6.png")).getImage();
        Muerte7 = new ImageIcon(getClass().getResource("../imagenes/pacmanM7.png")).getImage();
        Muerte8 = new ImageIcon(getClass().getResource("../imagenes/pacmanM8.png")).getImage();
        Muerte9 = new ImageIcon(getClass().getResource("../imagenes/pacmanM9.png")).getImage();
        Comida = new ImageIcon(getClass().getResource("../imagenes/Comida.png")).getImage();
        Vulnerable = new ImageIcon(getClass().getResource("../imagenes/Vulnerable.png")).getImage();
        Vulnerable2 = new ImageIcon(getClass().getResource("../imagenes/Vulnerable2.png")).getImage();

    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        doDrawing(g);
    }

    private void doDrawing(Graphics g) {

        Graphics2D g2d = (Graphics2D) g;

        g2d.setColor(Color.black);
        g2d.fillRect(0, 0, d.width, d.height);

        drawMaze(g2d);
        dibujarpuntuacion(g2d);
        if (ingame) {
            doAnim();
        }

        if (ingame) {
            if (pacmananimpos>5)
            {
                pacmananimpos=0;
                pacanimdir=1;
            }
            playGame(g2d);
            Contador++;
        } else {

            if (pacmananimpos<12 && Contador!=0){
                doAnimM();
                AnimacionMuerte(g2d);
                }
            else {
                showIntroScreen(g2d);
            }
        }

        g2d.drawImage(ii, 5, 5, this);
        Toolkit.getDefaultToolkit().sync();
        g2d.dispose();
    }

    class TAdapter extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {
            //configuración del teclado
            int key = e.getKeyCode();

            if (ingame) {
                if (key == KeyEvent.VK_LEFT) {
                    reqdx = -1;
                    reqdy = 0;
                } else if (key == KeyEvent.VK_RIGHT) {
                    reqdx = 1;
                    reqdy = 0;
                } else if (key == KeyEvent.VK_UP) {
                    reqdx = 0;
                    reqdy = -1;
                } else if (key == KeyEvent.VK_DOWN) {
                    reqdx = 0;
                    reqdy = 1;
                } else if (key == KeyEvent.VK_ESCAPE && timer.isRunning()) {
                    ingame = false;
                } else if (key == KeyEvent.VK_PAUSE) {
                    if (timer.isRunning()) {
                        timer.stop();
                    } else {
                        timer.start();
                    }
                }
            } else {
                if (key == 's' || key == 'S') {
                    ingame = true;
                    iniciarjuego();
                }


            }
        }

        @Override
        public void keyReleased(KeyEvent e) {

            int key = e.getKeyCode();

            if (key == Event.LEFT || key == Event.RIGHT
                    || key == Event.UP || key == Event.DOWN) {
                reqdx = 0;
                reqdy = 0;
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        repaint();
    }
}
