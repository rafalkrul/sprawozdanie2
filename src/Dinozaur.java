import javax.swing.*;
import java.awt.*;

class Dinozaur {
    JFrame Okno = new JFrame("CHRISTMAS");

    public static int SZEROKOSC = 800;
    public static int WYSOKOSC = 500;

    public void GUI() {
        Okno.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Container container = Okno.getContentPane();

        Plansza plansza = new Plansza();
        plansza.addKeyListener(plansza);
        plansza.setFocusable(true);

        container.setLayout(new BorderLayout());

        container.add(plansza, BorderLayout.CENTER);

        Okno.setSize(SZEROKOSC, WYSOKOSC);
        Okno.setResizable(false);
        Okno.setVisible(true);
    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Dinozaur().GUI();
                new Muzyka();
                Muzyka.Muza();

            }
        });
    }
}