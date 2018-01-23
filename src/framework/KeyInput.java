package framework;

import objects.Bullet;
import objects.Flag;
import window.Handler;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * wutduzzitdo?
 * 1/21/2018
 * Kyle Bankus (kcb2464)
 */
public class KeyInput extends KeyAdapter {

   Handler handler;

   public KeyInput(Handler handler) {
      this.handler = handler;
   }

   public void keyPressed(KeyEvent e) {
      int key = e.getKeyCode();

      for (int i = 0; i < handler.object.size(); i++) {
         GameObject tempObject = handler.object.get(i);

         if (tempObject.getId() == ObjectId.Player) {
            //if (key == KeyEvent.VK_D) tempObject.setVelX(5);
            if (key == KeyEvent.VK_D) handler.setRight(true);
            //if (key == KeyEvent.VK_A) tempObject.setVelX(-5);
            if (key == KeyEvent.VK_A) handler.setLeft(true);
            if (key == KeyEvent.VK_W && !handler.isJumping()) {
               handler.setJumping(true);
               tempObject.setVelY(-10);
            }
            if (key == KeyEvent.VK_SPACE) {
               handler.addObject(new Bullet(tempObject.getX(), tempObject.getY() + 48,
                                            ObjectId.Bullet, tempObject.getFacing() * 10));
            }
         }
      }

      if (key == KeyEvent.VK_ESCAPE) {
         System.exit(1);
      }
   }

   public void keyReleased(KeyEvent e) {
      int key = e.getKeyCode();

      for (int i = 0; i < handler.object.size(); i++) {
         GameObject tempObject = handler.object.get(i);

         if (tempObject.getId() == ObjectId.Player) {
            //if (key == KeyEvent.VK_D) tempObject.setVelX(0);
            if (key == KeyEvent.VK_D) handler.setRight(false);
            //if (key == KeyEvent.VK_A) tempObject.setVelX(0);
            if (key == KeyEvent.VK_A) handler.setLeft(false);
         }
      }
   }
}
