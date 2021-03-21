import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Zdjecia {

    public BufferedImage getZdjecia(String path) {
        BufferedImage zdj = null;
        try {
            zdj = ImageIO.read(getClass().getResource(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return zdj;
    }
}
