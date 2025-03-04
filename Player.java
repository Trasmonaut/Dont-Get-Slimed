import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
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
   public int points =0;
   public boolean shield = false;

   private int dx;
  
   private int hitboxHeight = 64;
   private int hitboxWidth = 32;
   private int hitboxX;

   private boolean facing;

   private Rectangle2D.Double bat;
   private Rectangle2D.Double facingSide;

   private Color backgroundColour;
   private Dimension dimension;
   //private Health orb;


   public Player (JPanel p) {
      panel = p;
      dimension = panel.getSize();

      backgroundColour = panel.getBackground ();
      x = 270;
      y = 236;

      dx = 5;	// make bigger (smaller) to increase (decrease) speed

      width = 64;
      height = 64;

      facing = true;       //0: move left, 1= move right
   }


   public void draw () {
      Graphics g = panel.getGraphics ();
      Graphics2D g2 = (Graphics2D) g;

      bat = new Rectangle2D.Double(x, y, width, height);
      g2.setColor(Color.RED);
      g2.fill(bat);

      g.dispose();
   }


   public void erase () {
      Graphics g = panel.getGraphics ();
      Graphics2D g2 = (Graphics2D) g;

      
      g2.setColor (backgroundColour);
      g2.fill (new Rectangle2D.Double (x, y, width, height));

      g2.fill(new Rectangle2D.Double(hitboxX, y, hitboxWidth, hitboxHeight));

      g.dispose();
   }


   public void move (int direction) {

      if (!panel.isVisible ()) return;
      
      dimension = panel.getSize();

      if (direction == 1) {	// move left
         facing = false;
          x = x - dx;
          hitboxX = x - 30;
	      if (x < 0)
	      x = 0;
      }
      else
      if (direction == 2) {  	// move right
         facing = true;
          x = x + dx;
          hitboxX = x + 50;
	      if (x + width > dimension.width)
	      x = dimension.width - width;
      }

      // if (orb != null) {
      //    boolean hcollision = orb.getBounds();
      //    if (hcollision) {
      //       orb.erase();
      //       hurtPlayer(10);
      //       System.out.println("Player healed: " + health);
      //    }
      // }


   }

   public void facingSide() {

      Graphics g = panel.getGraphics ();
      Graphics2D g3 = (Graphics2D) g;
      
   
         facingSide = new Rectangle2D.Double(hitboxX, y, hitboxWidth, hitboxHeight);
      
      g3.setColor(Color.YELLOW);
      g3.fill(facingSide);

      g.dispose();
   }


   public void hurtPlayer(int damage) {
      health +=damage;
      System.out.println("Player health: " + health);
      GameWindow.updatePlayerHealht(this.health);

      if (health <= 0) {
         System.out.println("Game Over");
         
      }
   }


   public Rectangle2D.Double getPlayerBoundingRectangle() {
      return new Rectangle2D.Double (x, y, width, height);
   }

   public Rectangle2D.Double getFacingBoundingRectangle() {
      return new Rectangle2D.Double (hitboxX, y, hitboxWidth, hitboxHeight);
   }

}