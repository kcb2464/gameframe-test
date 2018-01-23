package window;

import framework.KeyInput;
import framework.ObjectId;
import framework.Texture;
import objects.Block;
import objects.Flag;
import objects.Player;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
 * wutduzzitdo?
 * 1/21/2018
 * Kyle Bankus (kcb2464)
 */
public class Game extends Canvas implements Runnable {

   private static final long serialVersionUID = -8686571574947657709L;

   private boolean running = false;
   private Thread thread;

   public static int WIDTH, HEIGHT;

   public BufferedImage background = null;

   Handler handler;
   Camera cam;
   static Texture tex;

   Random rand = new Random();

   public static int LEVEL = 1;

   private void init() {
      WIDTH = getWidth();
      HEIGHT = getHeight();

      tex = new Texture();

      BufferedImageLoader loader = new BufferedImageLoader();
      background = loader.loadImage("/background.png"); //loading background

      cam = new Camera(0, 0);
      handler = new Handler(cam);
      this.addKeyListener(new KeyInput(handler));

   }

   public synchronized void start() {
      if (running)
         return;

      running = true;
      thread = new Thread(this);
      thread.start();
   }

   public void run() {

      init();
      this.requestFocus();
      long lastTime = System.nanoTime();
      double amountOfTicks = 60.0;
      double ns = 1000000000 / amountOfTicks;
      double delta = 0;
      long timer = System.currentTimeMillis();
      int updates = 0;
      int frames = 0;
      while (running) {
         long now = System.nanoTime();
         delta += (now - lastTime) / ns;
         lastTime = now;
         while (delta >= 1) {
            tick();
            updates++;
            delta--;
         }
         render();
         frames++;

         if (System.currentTimeMillis() - timer > 1000) {
            timer += 1000;
            System.out.println("FPS: " + frames + " TICKS: " + updates);
            frames = 0;
            updates = 0;
         }
      }
   }

   private void tick() {
      handler.tick();
      for (int i = 0; i < handler.object.size(); i++) {
         if (handler.object.get(i).getId() == ObjectId.Player) {
            cam.tick(handler.object.get(i));
         }
      }
   }

   private void render() {
      BufferStrategy bs = this.getBufferStrategy();
      if (bs == null) {
         this.createBufferStrategy(3);
         return;
      }

      Graphics g = bs.getDrawGraphics();
      Graphics2D g2d = (Graphics2D) g;
      ////////////////// draw below here


      g.setColor(new Color(25, 191, 224));
      g.fillRect(0, 0, getWidth(), getHeight());

      g.drawImage(background, 0, 0, 900, 700, this);

      g2d.translate(cam.getX(), cam.getY());

      handler.render(g);

      g2d.translate(-cam.getX(), -cam.getY());

      ////////////////// draw up there
      g.dispose();
      bs.show();

   }

   public static Texture getInstance(){
      return tex;
   }

   public static void main(String[] args) {
      new Window(800, 600, "Neon series game", new Game());
   }
}
