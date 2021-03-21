import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Iterator;


public class Przeszkody {

    private class Przeszkoda {
        BufferedImage zdjecie;
        int x;
        int y;

        Rectangle getPrzeszkoda() {
            Rectangle przeszkoda = new Rectangle();
            przeszkoda.x = x;
            przeszkoda.y = y;
            przeszkoda.width = zdjecie.getWidth();
            przeszkoda.height = zdjecie.getHeight();

            return przeszkoda;
        }
    }

    private int PierwX;
    private int odleglosc;
    private int predkosc;

    private ArrayList<BufferedImage> listaZdj;
    private ArrayList<Przeszkoda> listaprzeszkod;


    public Przeszkody(int PierwPoz) {
        listaprzeszkod = new ArrayList<Przeszkoda>();
        listaZdj = new ArrayList<BufferedImage>();

        PierwX = PierwPoz;
        odleglosc = 350;
        predkosc = 15;

        listaZdj.add(new Zdjecia().getZdjecia("drzewo1.png"));
        listaZdj.add(new Zdjecia().getZdjecia("drzewo2.png"));
        listaZdj.add(new Zdjecia().getZdjecia("drzewo3.png"));
        listaZdj.add(new Zdjecia().getZdjecia("drzewo4.png"));


        int x = PierwX;

        for(BufferedImage bi : listaZdj) {

            Przeszkoda ob = new Przeszkoda();

            ob.zdjecie = bi;
            ob.x = x;
            ob.y = Terakota.TERAKOTA_Y - bi.getHeight() + 3;
            x += odleglosc;

            listaprzeszkod.add(ob);
        }
    }

    public void odswiez() {
        Iterator<Przeszkoda> petla = listaprzeszkod.iterator();

        Przeszkoda pierwPrze = petla.next();
        pierwPrze.x -= predkosc;

        while(petla.hasNext()) {
            Przeszkoda ob = petla.next();
            ob.x -= predkosc;
        }


        if(pierwPrze.x < -pierwPrze.zdjecie.getWidth()) {
            listaprzeszkod.remove(pierwPrze);
            pierwPrze.x = listaprzeszkod.get(listaprzeszkod.size() - 1).x + odleglosc;
            listaprzeszkod.add(pierwPrze);
        }
    }

    public void stworz(Graphics g) {
        for(Przeszkoda ob : listaprzeszkod) {
            g.drawImage(ob.zdjecie, ob.x, ob.y, null);
        }
    }

    public boolean czydotknal() {
        for(Przeszkoda ob : listaprzeszkod) {
            if(Dino.getDino().intersects(ob.getPrzeszkoda())) {
                return true;
            }
        }
        return false;
    }

    public void wznow() {
        int x = PierwX;
        listaprzeszkod = new ArrayList<Przeszkoda>();

        for(BufferedImage bi : listaZdj) {

            Przeszkoda ob = new Przeszkoda();

            ob.zdjecie = bi;
            ob.x = x;
            ob.y = Terakota.TERAKOTA_Y - bi.getHeight() + 3;
            x += odleglosc;

            listaprzeszkod.add(ob);
        }
    }

}
