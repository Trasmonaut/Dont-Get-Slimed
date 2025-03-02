import java.awt.Color;
import java.awt.Dimension;
import java.awt.geom.Rectangle2D;
import javax.swing.JPanel;

public abstract class Slime {

    protected JPanel panel;
    protected int x;
    protected int y=400;
    protected int width;
    protected int height;
    protected Player player;
    protected int dx;		// increment to move along x-axis
   protected int dy;		// increment to move along y-axis

   protected Color backgroundColour;
   protected Dimension dimension;

   public abstract void setLocation();
   public abstract void move();
   public abstract void draw();
   public abstract void erase();
   public abstract void hurtSlime();
   public abstract Rectangle2D.Double getBoundingRectangle();
   public abstract boolean collidesWithplayer();
   public abstract boolean collideWithAttack();

}
