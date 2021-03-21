import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


class Plansza extends JPanel implements KeyListener, Runnable {

    public static int SZEROKOSC;
    public static int WYSOKOSC;
    private Thread animator;

    private boolean bieganie = false;
    private boolean koniecGry = false;

    Terakota terakota;
    Dino dino;
    Przeszkody przeszkody;

    private int wynik;

    public Plansza() {
        SZEROKOSC = Dinozaur.SZEROKOSC;
        WYSOKOSC = Dinozaur.WYSOKOSC;
        setBackground(Color.WHITE);

        terakota = new Terakota(WYSOKOSC);
        dino = new Dino();
        przeszkody = new Przeszkody((int)(SZEROKOSC)/2 + 100);

        wynik = 0;

        setSize(SZEROKOSC, WYSOKOSC);
        setVisible(true);
    }

    public void paint(Graphics g) {
        super.paint(g);
        g.drawString("WYNIK:", getWidth() - 95, 50);
        g.drawString(Integer.toString(wynik), getWidth() - 50, 50);
        terakota.stworz(g);
        dino.stworz(g);
        przeszkody.stworz(g);
        if(koniecGry){
            g.drawString("KONIEC GRY", getWidth()/2 - 100, 200);
        }
    }

    public void run() {
        bieganie = true;

        while(bieganie) {
            odswiezGre();
            repaint();
            try {
                Thread.sleep(50);
            } catch(InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void odswiezGre() {
        wynik += 1;
        terakota.odswiez();
        przeszkody.odswiez();

        if(przeszkody.czydotknal()) {
            dino.die();
            repaint();
            bieganie = false;
            koniecGry = true;
        }
    }

    public void reset() {
        wynik = 0;
        przeszkody.wznow();
        koniecGry = false;
    }

    public void keyTyped(KeyEvent e) {
        if(e.getKeyChar() == ' ') {
            if(koniecGry) reset();
            if (animator == null || !bieganie) {
                animator = new Thread(this);
                animator.start();
                dino.startbieg();
            } else {
                dino.skok();
            }
        }
    }

    public void keyPressed(KeyEvent e) {
        // System.out.println("keyPressed: "+e);
    }

    public void keyReleased(KeyEvent e) {
        // System.out.println("keyReleased: "+e);
    }
}
