
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.util.Random;
import javax.swing.JPanel;


public class SmallSlime extends Slime{
    public JPanel panel;
    public int x;
    public int y;
    public int width;
    private int points = 1;
    public int height;
    public int damage = -1;
    public Player player;
    public int health = 2;
    private Ellipse2D smallSlime;
    public int dx;		// increment to move along x-axis
    public int dy;		// increment to move along y-axis
    private Random random;
    private boolean facing; //fasle = left, true = right

    private Image slimeleft;
    private Image slimeright;

    public SmallSlime(JPanel p, Player player) {
        panel = p;
        dimension = panel.getSize();
        backgroundColour = panel.getBackground ();

        width = 32;
        height = 32;

        this.player = player;

        random = new Random();
        dx = 2;			// no movement along x-axis

        setLocation();
        slimeleft = ImageManager.loadImage("Images/SmallSlime/slimeleft.png");
        slimeright = ImageManager.loadImage("images/SmallSlime/slimeright.png");
    }
    public void draw (Graphics g2) {
        if(!facing){
            g2.drawImage(slimeleft, x, y, null);
        }else{
            g2.drawImage(slimeright, x, y, null);
        }
     }
  
  
     public void erase () {
        Graphics g = panel.getGraphics ();
        Graphics2D g2 = (Graphics2D) g;
  
        g2.setColor (backgroundColour);
        g2.fill (new Rectangle2D.Double (x, y, 30, 30));
  
        g.dispose();
     }
  
  
    public void move() {
        if (!panel.isVisible ()) return;

        if(player.x < x){
            x = x - dx;
            facing = false;
        }
        else if(player.x > x){
            x = x + dx;
            facing = true;
        }

        boolean collision = collidesWithplayer();
        if (collision && player.shield && collideWithAttack()) {
            collideWithSheild();
            ((GamePanel)panel).slimeShield();

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

    }

    public void collideWithSheild(){
                if(player.x < x){
                    x = x + 20;
                }
                else if(player.x > x){
                    x = x - 20;
                }
            }
        
    public void hurtSlime(){
        if(collideWithAttack()){
           
            if(player.x < x){
                x = x + 20;
            }
            else if(player.x > x){
                x = x - 20;
            
            }
            health = health + player.damage;
            if(health <= 0){
                ((GamePanel) panel).killedSlime();
                
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
        x = random.nextInt( 600 - width );
        y = 268;
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
