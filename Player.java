import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.Rectangle2D;
import javax.swing.JPanel;

public class Player {

   private JPanel panel;
   public int x;
   private int y;
   private int width;
   private int height;
   public int health = 50;
   public int damage = -1;
   public int points = 0;
   public boolean shield = false;

   private int dx;
  
   private int hitboxHeight = 64;
   private int hitboxWidth = 32;
   private int hitboxX;

   public boolean facing;

   private Rectangle2D.Double psize;
   private Rectangle2D.Double facingSide;

   private Color backgroundColour;
   private Dimension dimension;
   //private Health orb;

   private Image state;
   private Image idle_right;
   private Image idle_left;
   private Image shield_left;
   private Image sheild_right;

   private Image blank;

   public Player(JPanel p, Image background) {
      panel = p;
      dimension = panel.getSize();

      backgroundColour = panel.getBackground();
      x = 270;
      y = 236;

      dx = 5; // make bigger (smaller) to increase (decrease) speed

      width = 64;
      height = 64;

      facing = true; // 0: move left, 1= move right
      idle_right = ImageManager.loadImage("images/player/static.png");
      idle_left = ImageManager.loadImage("images/playerleft/static.png");
      sheild_right = ImageManager.loadImage("images/player/shieldright.png");
      shield_left = ImageManager.loadImage("images/playerleft/shieldleft.png");
   }

   public void drawShieldedPlayer(Graphics2D g2){
      if (facing){
         g2.drawImage(sheild_right, x, y, width, height, null);
      }else{
         g2.drawImage(shield_left, x, y, width, height, null);
      }

   }

   public void drawPlayerBox(){
      Graphics g = panel.getGraphics();
      Graphics2D g3 = (Graphics2D) g;

      psize= new Rectangle2D.Double(x+12, y, 32, height);
      
      g3.setColor(Color.RED);
      g3.fill(psize);
      g.dispose();
   }

   public void draw(Graphics2D g2) {
      if (facing){
         g2.drawImage(idle_right, x, y, width, height, null);
      }else{
         g2.drawImage(idle_left, x, y, width, height, null);
      }
   }

   public void erase(Graphics2D g2) {
      
      g2.drawImage(blank, 0, 0, 600, 337, null);

   }

   public void move(int direction) {
      if (!panel.isVisible()) return;
      dimension = panel.getSize();

      if (direction == 1) { // move left
         facing = false;
         x = x - dx;
         hitboxX = x -hitboxWidth;
         if (x < 0)
            x = 0;

      } else if (direction == 2) { // move right
         facing = true;
         x = x + dx;
         hitboxX = x + width;

         if (x + width > dimension.width)
            x = dimension.width - width;
      }
   }

   public void facingSide() {
      Graphics g = panel.getGraphics();
      Graphics2D g3 = (Graphics2D) g;

      facingSide = new Rectangle2D.Double(hitboxX, y, hitboxWidth, hitboxHeight);
      
      g3.setColor(Color.YELLOW);
      g3.fill(facingSide);
      g.dispose();
   }

   public void hurtPlayer(int damage) {
      health += damage;
      System.out.println("Player health: " + health);
      GameWindow.updatePlayerHealht(this.health);

      if (health <= 0) {
         System.out.println("Game Over");
      }
   }

   public Rectangle2D.Double getPlayerBoundingRectangle() {
      return new Rectangle2D.Double(x+12, y, 32, height);
   }

   public Rectangle2D.Double getFacingBoundingRectangle() {
      return new Rectangle2D.Double(hitboxX, y, hitboxWidth, hitboxHeight);
   }
}