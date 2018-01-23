package window;

import framework.GameObject;
import framework.ObjectId;
import objects.Block;
import objects.Flag;
import objects.Player;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

/**
 * wutduzzitdo?
 * 1/21/2018
 * Kyle Bankus (kcb2464)
 */
public class Handler {

   public LinkedList<GameObject> object = new LinkedList<GameObject>();

   private boolean up = false, down = false, right = false, left = false;
   public boolean falling = true;
   public boolean jumping = false;

   private GameObject tempObject;

   private Camera cam;

   private BufferedImage level2 = null;

   public Handler(Camera cam) {
      this.cam = cam;

      BufferedImageLoader loader = new BufferedImageLoader();
      level2 = loader.loadImage("/level2.png"); //loading level 2
   }

   public void tick() {
      for (int i = 0; i < object.size(); i++) {
         tempObject = object.get(i);
         tempObject.tick(object);
      }
   }

   public void render(Graphics g) {
      for (int i = 0; i < object.size(); i++) {
         tempObject = object.get(i);
         tempObject.render(g);
      }
   }

   public void loadImageLevel (BufferedImage image) {
      int w = image.getWidth();
      int h = image.getHeight();

      for (int xx = 0; xx < h; xx++) {
         for (int yy = 0; yy < w; yy++) {
            int pixel = image.getRGB(xx, yy);
            int red = (pixel >> 16) & 0xff;
            int green = (pixel >> 8) & 0xff;
            int blue = (pixel) & 0xff;

            if (red == 255 && green == 255 && blue == 255)
               addObject(new Block(xx*32, yy*32, 0, ObjectId.Block));
            if (red == 128 && green == 128 && blue == 128)
               addObject(new Block(xx*32, yy*32, 1, ObjectId.Block));
            if (red == 0 && green == 0 && blue == 255)
               addObject(new Player(xx*32, yy*32, this, cam, ObjectId.Player));
            if (red == 255 && green == 216 && blue == 0)
               addObject(new Flag(xx*32, yy*32, ObjectId.Flag));
         }
      }
   }

   public void switchLevel() {
      clearLevel();
      cam.setX(0);

      switch(Game.LEVEL) {
         case 1: loadImageLevel(level2);
         break;
      }

      Game.LEVEL++;
   }

   private void clearLevel() {
      object.clear();
   }

   public void addObject(GameObject object) {
      this.object.add(object);
   }
   public void removeObject(GameObject object) {
      this.object.remove(object);
   }

   public boolean isUp() {
      return up;
   }

   public void setUp(boolean up) {
      this.up = up;
   }

   public boolean isDown() {
      return down;
   }

   public void setDown(boolean down) {
      this.down = down;
   }

   public boolean isRight() {
      return right;
   }

   public void setRight(boolean right) {
      this.right = right;
   }

   public boolean isLeft() {
      return left;
   }

   public void setLeft(boolean left) {
      this.left = left;
   }
   public boolean isFalling() {
      return falling;
   }

   public void setFalling(boolean falling) {
      this.falling = falling;
   }

   public boolean isJumping() {
      return jumping;
   }

   public void setJumping(boolean jumping) {
      this.jumping = jumping;
   }
}
