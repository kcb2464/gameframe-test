package window;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * wutduzzitdo?
 * 1/21/2018
 * Kyle Bankus (kcb2464)
 */
public class BufferedImageLoader {

   private BufferedImage image;

   public BufferedImage loadImage(String path) {

      try {
         image = ImageIO.read(getClass().getResource(path));
      }
      catch (IOException e) {
         e.printStackTrace();
      }
      return image;
   }
}
