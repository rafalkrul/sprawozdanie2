import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;


public class Dino {
    private static int dinoBaseY, dinoTopY, dinoStartX, dinoEndX;
    private static int dinoTop, dinoBottom, topPoint;

    private static boolean czyMaksSkok;
    private static int wysSkoku = 15;

    public static final int POSTOJ = 1,
            BIEGANIE = 2,
            SKAKANIE = 3,
            SMIERC = 4;

    private static int stan;


    static BufferedImage zdj,ded;


    public Dino() {
        initDino();
    }
    private void initDino(){
        zdj = new Zdjecia().getZdjecia("saber.png");
        ded = new Zdjecia().getZdjecia("saberded.png");

        dinoBaseY = Terakota.TERAKOTA_Y + 20;
        dinoTopY = Terakota.TERAKOTA_Y - zdj.getHeight();
        dinoStartX = 100;
        dinoEndX = dinoStartX + zdj.getWidth();
        topPoint = dinoTopY - 120;

        stan = 1;

    }

    public void stworz(Graphics g) {
        dinoBottom = dinoTop + zdj.getHeight();
        switch(stan) {

            case POSTOJ:
                g.drawImage(zdj, dinoStartX, dinoTopY, null);
                break;

            case BIEGANIE:
                g.drawImage(zdj, dinoStartX, dinoTopY, null);
                break;

            case SKAKANIE:
                if(dinoTop > topPoint && !czyMaksSkok) {
                    g.drawImage(zdj, dinoStartX, dinoTop -= wysSkoku, null);
                    break;
                }
                if(dinoTop >= topPoint && !czyMaksSkok) {
                    czyMaksSkok = true;
                    g.drawImage(zdj, dinoStartX, dinoTop += wysSkoku, null);
                    break;
                }
                if(dinoTop > topPoint && czyMaksSkok) {
                    if(dinoTopY == dinoTop && czyMaksSkok) {
                        stan = BIEGANIE;
                        czyMaksSkok = false;
                        break;
                    }
                    g.drawImage(zdj, dinoStartX, dinoTop += wysSkoku, null);
                    break;
                }
            case SMIERC:
                g.drawImage(ded, dinoStartX, dinoTop, null);
                break;
        }
    }

    public void die() {
        stan = SMIERC;
    }

    public static Rectangle getDino() {
        Rectangle dino = new Rectangle();
        dino.x = dinoStartX;

        if(stan == SKAKANIE && !czyMaksSkok) dino.y = dinoTop - wysSkoku;
        else if(stan == SKAKANIE && czyMaksSkok) dino.y = dinoTop + wysSkoku;
        else if(stan != SKAKANIE) dino.y = dinoTop;

        dino.width = zdj.getWidth();
        dino.height = zdj.getHeight();

        return dino;
    }

    public void startbieg() {
        dinoTop = dinoTopY;
        stan = BIEGANIE;
    }

    public void skok() {
        dinoTop = dinoTopY;
        czyMaksSkok = false;
        stan = SKAKANIE;
    }

}
