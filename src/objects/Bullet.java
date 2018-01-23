package objects;

import framework.GameObject;
import framework.ObjectId;
import window.Game;

import java.awt.*;
import java.util.LinkedList;

/**
 * wutduzzitdo?
 * 1/21/2018
 * Kyle Bankus (kcb2464)
 */
public class Bullet extends GameObject {

   public Bullet(float x, float y, ObjectId id, int velX) {
      super(x, y, id);
      this.velX = velX;
   }

   public void tick(LinkedList<GameObject> object) {
      x += velX;
   }

   public void render(Graphics g) {
      g.setColor(Color.red);
      g.fillOval((int)x, (int)y, 16, 16);
   }

   public Rectangle getBounds() {
      return new Rectangle((int) x, (int) y, 16, 16);
   }
}
