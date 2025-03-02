import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.util.Random;
import javax.swing.JPanel;


public class FallingSlime extends Slime {
    private int x;
    private int y;
    private int speed = 20;
    private int fallSpeed =40;
    private int width;
    private int height;
    private int damage = -5;
    private Player player;
    private int health = 1;
    private Ellipse2D slime;
    private int points =5;
    private Random random;
    private JPanel panel;
    private boolean isFalling = false;

    public FallingSlime(JPanel p, Player player) {
        panel = p;
        dimension = panel.getSize();
        backgroundColour = panel.getBackground ();

        width = 40;
        height = 40;

        this.player = player;

        random = new Random();
        setLocation();
    }

    @Override
    public void setLocation() {
        x = random.nextInt( 900 - width );
        y = 260;
    }

    public void draw () {
        Graphics g = panel.getGraphics ();
        Graphics2D g2 = (Graphics2D) g;

        slime = new Ellipse2D.Double(x, y, width, height);
  
        g2.setColor(Color.GREEN); 
        g2.fill(slime);		// colour the head
  
        g2.setColor(Color.BLACK);	 
        g2.draw(slime);		// draw outline around head
  
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

     public void move(){
        erase();
       if (y >= -100  && !isFalling){
       
              y = y - speed;
              draw();
        }else{
            
              isFalling = true;
              boolean collision = collidesWithplayer();
              y = y + fallSpeed;
              draw();
              if( collision ){
                    player.hurtPlayer(damage);
                    isFalling = false;
                    y =260;
                    erase();
                    System.out.println("Player hit!");
                    setLocation();
              }else if( y >= 260){
                  isFalling=false;
                  y=260;
                  setLocation();  
              }
             
             
         }
     }
     
    public void hurtSlime(){
        if(collideWithAttack()){
            health--;
            if(health == 0){
                player.points= player.points + points;
                GameWindow.updatePointChecker(player.points);
                erase();
                setLocation();
                y=260;
                health = 1;
            }
        }
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
