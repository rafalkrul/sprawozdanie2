import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.*;

public class Terakota {

    private class TerakotaZdjecie {
        BufferedImage zdjecie;
        int x;
    }

    public static int TERAKOTA_Y;

    private BufferedImage image;

    private ArrayList<TerakotaZdjecie> terakotaZdjecieSet;

    public Terakota(int WYSOKOSC) {

        TERAKOTA_Y = (int)(WYSOKOSC - 0.25 * WYSOKOSC);

        try{
            image = new Zdjecia().getZdjecia("terakota.png");
        } catch(Exception e) {e.printStackTrace();}

        terakotaZdjecieSet = new ArrayList<TerakotaZdjecie>();

        for(int i=0; i<3; i++) {
            TerakotaZdjecie obj = new TerakotaZdjecie();
            obj.zdjecie = image;
            obj.x = 0;
            terakotaZdjecieSet.add(obj);
        }
    }

    public void odswiez() {
        Iterator<TerakotaZdjecie> petla = terakotaZdjecieSet.iterator();
        TerakotaZdjecie pierw = petla.next();

        pierw.x -= 10;

        int popX = pierw.x;
        while(petla.hasNext()) {
            TerakotaZdjecie next = petla.next();
            next.x = popX + image.getWidth();
            popX = next.x;
        }

        if(pierw.x < -image.getWidth()) {
            terakotaZdjecieSet.remove(pierw);
            pierw.x = popX + image.getWidth();
            terakotaZdjecieSet.add(pierw);
        }

    }

    public void stworz(Graphics g) {
        for(TerakotaZdjecie img : terakotaZdjecieSet) {
            g.drawImage(img.zdjecie, (int) img.x, TERAKOTA_Y, null);
        }
    }
}
