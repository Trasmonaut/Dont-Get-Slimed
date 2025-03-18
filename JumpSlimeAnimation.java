import java.awt.Graphics2D;
import java.awt.Image;

public class JumpSlimeAnimation {
    
    Animation animation;

    private int x;
    private int y;

    private int width = 32;
    private int height = 32;

    public JumpSlimeAnimation() {
        animation = new Animation(false);

        Image jump1 = ImageManager.loadImage("images/FallingSlime/slimejump1.png");
        Image jump2 = ImageManager.loadImage("images/FallingSlime/slimejump2.png");
        Image jump3 = ImageManager.loadImage("images/FallingSlime/slimejump3.png");
        Image jump4 = ImageManager.loadImage("images/FallingSlime/slimejump4.png");
        Image jump5 = ImageManager.loadImage("images/FallingSlime/slimejump5.png");
        Image jump6 = ImageManager.loadImage("images/FallingSlime/slimejump6.png");
        Image jump7 = ImageManager.loadImage("images/FallingSlime/slimejump7.png");
        Image jump8 = ImageManager.loadImage("images/FallingSlime/slimejump8.png");
        Image jump9 = ImageManager.loadImage("images/FallingSlime/slimejump9.png");
        Image jump10 = ImageManager.loadImage("images/FallingSlime/slimejump10.png");


        animation.addFrame(jump1, 250);
        animation.addFrame(jump2, 250);
        animation.addFrame(jump3, 250);
        animation.addFrame(jump4, 250);
        animation.addFrame(jump5, 250);
        animation.addFrame(jump6, 250);
        animation.addFrame(jump7, 350);
        animation.addFrame(jump8, 450);
        animation.addFrame(jump9, 250);
        animation.addFrame(jump10, 150);
    }

    public void update() {
        animation.update();
    }

    public void start() {
        animation.start();
    }

    public void draw(Graphics2D g2, int x, int y) {
        if (!animation.isStillActive())
            return;
        g2.drawImage(animation.getImage(), x, y, width, height, null);
    }

    public boolean isStillActive() {
        return animation.isStillActive();
    }
}