
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.util.Random;
import javax.swing.JPanel;


public class LargeSlime extends Slime{
    public JPanel panel;
    public int x;
    public int y;
    public int width;
    private int points = 1;
    public int height;
    public int damage = -5;
    public Player player;
    public int health = 2;
    private Ellipse2D smallSlime;
    public int dx;		// increment to move along x-axis
    public int dy;		// increment to move along y-axis
    private Random random;

    public LargeSlime(JPanel p, Player player) {
        panel = p;
        dimension = panel.getSize();
        backgroundColour = panel.getBackground ();

        width = 64;
        height = 64;

        this.player = player;

        random = new Random();

        dx = 2;			// no movement along x-axis

        setLocation();
    }
    public void draw () {
        Graphics g = panel.getGraphics ();
        Graphics2D g2 = (Graphics2D) g;

        smallSlime = new Ellipse2D.Double(x, y, width, height);
  
        g2.setColor(Color.PINK); 
        g2.fill(smallSlime);		// colour the head
  
        g2.setColor(Color.BLACK);	 
        g2.draw(smallSlime);		// draw outline around head
  
        g2.setColor(Color.BLACK);
  
        g.dispose();
     }
  
  
     public void erase () {
        Graphics g = panel.getGraphics ();
        Graphics2D g2 = (Graphics2D) g;
  
        // erase face by drawing a rectangle on top of it
  
        g2.setColor (backgroundColour);
        g2.fill (new Rectangle2D.Double (x, y, 30, 30));
  
        g.dispose();
     }
  
  
    public void move() {
  
        erase();
        if (!panel.isVisible ()) return;

        if(player.x < x){
            x = x - dx;
        }
        else if(player.x > x){
            x = x + dx;
        }

        boolean collision = collidesWithplayer();
        if (collision && player.shield && collideWithAttack()) {
            collideWithSheild();

        }else if(collision){  
            ((GamePanel) panel).hurtPlayer();
            player.hurtPlayer(damage);
            if(player.x < x){
                x = x + 20;
            }
            else if(player.x > x){
                x = x - 20;
            }	
        }

        draw();
    }

    public void collideWithSheild(){
                erase();
                if(player.x < x){
                    x = x + 20;
                }
                else if(player.x > x){
                    x = x - 20;
                }
                draw();
            }
        
    

    public void hurtSlime(){
        if(collideWithAttack()){
            erase();
            if(player.x < x){
                x = x + 10;
            }
            else if(player.x > x){
                x = x - 10;
            
            }
            draw();

            health = health + player.damage;
            if(health <= 0){
                ((GamePanel) panel).killedSlime();
                erase();
                setLocation();
                health =2;
                player.points= player.points + points;
                GameWindow.updatePointChecker(player.points);
            }
            System.out.println("Slime health: " + health);
        }
    }
    

    @Override
    public void setLocation() {
        x = random.nextInt( 900 - width );
        y = 236;
    }

    public Rectangle2D.Double getBoundingRectangle() {
        return new Rectangle2D.Double (x, y, width, height);
     }
    public boolean collideWithAttack(){
        Rectangle2D.Double myRect = getBoundingRectangle();
        Rectangle2D.Double attackRect = player.getFacingBoundingRectangle();    

        return myRect.intersects(attackRect);
      
    }
     
     public boolean collidesWithplayer() {
        Rectangle2D.Double myRect = getBoundingRectangle();
        Rectangle2D.Double playerRect = player.getPlayerBoundingRectangle();
        
        return myRect.intersects(playerRect); 

     } 
}
