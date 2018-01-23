package objects;

import framework.GameObject;
import framework.ObjectId;

import java.awt.*;
import java.util.LinkedList;

/**
 * wutduzzitdo?
 * 1/21/2018
 * Kyle Bankus (kcb2464)
 */
public class Flag extends GameObject {

   public Flag(float x, float y, ObjectId id) {
      super(x, y, id);
   }

   public void tick(LinkedList<GameObject> object) {
   }

   public void render(Graphics g) {
      g.setColor(Color.yellow);
      g.fillRect((int)x, (int)y, 32, 32);
   }

   public Rectangle getBounds() {
      return new Rectangle((int) x, (int) y, 32, 32);
   }
}