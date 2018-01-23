package framework;

import window.BufferedImageLoader;

import java.awt.image.BufferedImage;

/**
 * wutduzzitdo?
 * 1/21/2018
 * Kyle Bankus (kcb2464)
 */
public class Texture {

   SpriteSheet bs, ps;
   private BufferedImage block_sheet = null;
   private BufferedImage player_sheet = null;

   public BufferedImage[] block = new BufferedImage[2];
   public BufferedImage[] player = new BufferedImage[14];
   public BufferedImage[] player_jump = new BufferedImage[6];

   public Texture() {

      BufferedImageLoader loader = new BufferedImageLoader();
      try {
         block_sheet = loader.loadImage("/block_sheet.png");
         player_sheet = loader.loadImage("/player_sheet.png");
      } catch (Exception e){
         e.printStackTrace();
      }
      bs = new SpriteSheet(block_sheet);
      ps = new SpriteSheet(player_sheet);

      getTextures();
   }

   private void getTextures() {
      block[0] = bs.grabImage(1, 1, 32, 32); //dirt block
      block[1] = bs.grabImage(2, 1, 32, 32); //grass block

      //looking right
      for (int i = 0; i < 7; i++) {
         player[i] = ps.grabImage(i+1, 1, 32, 64); //idle frame for player
      }
      //looking left
      for (int i = 7, ii = 20; i < 14; i++, --ii) {
         player[i] = ps.grabImage(ii, 1, 32, 64); //idle frame for player
      }
      //jumping
      for (int i = 0, ii = 8; i < 6; i++, ii++) {
         player_jump[i] = ps.grabImage(ii, 2, 32, 64); //idle frame for player
      }
   }
}
