import java.awt.Graphics;
import java.awt.Image;
import java.awt.geom.Rectangle2D;
import java.util.Random;
import javax.swing.JPanel;

public class FallingSlime extends Slime {
    private int x;
    private int y;
    private int speed = 20;
    private int fallSpeed = 40;
    private int width;
    private int height;
    private int damage = -5;
    private Player player;
    private int health = 1;
    private int points = 5;
    private Random random;
    private JPanel panel;
    public boolean isFalling = false;
    private boolean isJumping = false;

    Image slime;

    private JumpSlimeAnimation jumpSlimeAnimation;

    public FallingSlime(JPanel p, Player player) {
        panel = p;
        dimension = panel.getSize();
        backgroundColour = panel.getBackground();

        width = 48;
        height = 48;

        this.player = player;

        random = new Random();
        isFalling = false;
        isJumping = false;
        setLocation();

        slime = ImageManager.loadImage("Images/FallingSlime/fallingslime.png");
        jumpSlimeAnimation = new JumpSlimeAnimation();
    }

    @Override
    public void setLocation() {
        x = random.nextInt(600 - width);
        y = -100;
    }

    public void draw(Graphics g2) {
        if (!isJumping) {
            
            g2.drawImage(slime, x, y, null);
        }
    }

    public void move() {
        if (!isFalling && !isJumping) {
           if (random.nextInt(500) < 5) {
                isJumping = true;
                y=268;
                jumpSlimeAnimation.start();
            }
        }

        if (isJumping) {
            jumpSlimeAnimation.update();
            if (!jumpSlimeAnimation.isStillActive()) {
                isJumping = false;
                isFalling = true;
                y= -50;
            }
        }

        if (isFalling) {
            y = y + fallSpeed;
            ((GamePanel) panel).windup();
            boolean collision = collidesWithplayer();

            if (collision) {
                ((GamePanel) panel).hurtPlayer();
                ((GamePanel) panel).fallingSlimeDead();
                player.hurtPlayer(damage);
                isFalling = false;
            
                System.out.println("Player hit!");
                setLocation();
            } else if (y >= 252) {
                ((GamePanel) panel).fallingSlimeDead();
                isFalling = false;
                //y = 260;
                setLocation();
            }
        }
    }

    public void hurtSlime() {
        if (collideWithAttack()) {
            health--;
            if (health <= 0) {
                ((GamePanel) panel).killedSlime();
                player.points = player.points + points;
                player.health = player.health + 5;
                GameWindow.updatePointChecker(player.points);
                setLocation();
                y = 260;
                health = 1;
            }
        }
    }

    public Rectangle2D.Double getBoundingRectangle() {
        return new Rectangle2D.Double(x, y, width, height);
    }

    public boolean collideWithAttack() {
        Rectangle2D.Double myRect = getBoundingRectangle();
        Rectangle2D.Double attackRect = player.getFacingBoundingRectangle();

        return myRect.intersects(attackRect);
    }

    public boolean collidesWithplayer() {
        Rectangle2D.Double myRect = getBoundingRectangle();
        Rectangle2D.Double playerRect = player.getPlayerBoundingRectangle();

        return myRect.intersects(playerRect);
    }

    @Override
    public void erase() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'erase'");
    }
}