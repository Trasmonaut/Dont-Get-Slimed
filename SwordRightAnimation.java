import java.awt.Graphics2D;
import java.awt.Image;

public class SwordRightAnimation {
    
    Animation animation;

    private int x;
    private int y;

    private int width = 64;
	private int height = 64;

    public SwordRightAnimation(int x){
        animation = new Animation(false);

        x = this.x;
        y = 236;

        Image swing1 = ImageManager.loadImage("images/playerleft/swing1.png");
        Image swing2 = ImageManager.loadImage("images/playerleft/swing2.png");
        Image swing3 = ImageManager.loadImage("images/playerleft/swing3.png");
        Image swing4 = ImageManager.loadImage("images/playerleft/swing4.png");
        Image swing5 = ImageManager.loadImage("images/playerleft/swing5.png");
        Image swing6 = ImageManager.loadImage("images/playerleft/swing6.png");
        Image swing7 = ImageManager.loadImage("images/playerleft/swing7.png");


        animation.addFrame(swing1, 50);
        animation.addFrame(swing2, 50);
        animation.addFrame(swing3, 50);
        animation.addFrame(swing4, 50);
        animation.addFrame(swing5, 50);
        animation.addFrame(swing6, 50);
        animation.addFrame(swing7, 50);

    }   

    public void update() {
		if (!animation.isStillActive())
			return;

		animation.update();
    }


    public void start (int x){
        x = this.x;

        animation.start();
    }

    public void draw(Graphics2D g2,int x) {
		if (!animation.isStillActive())
			return;
		g2.drawImage(animation.getImage(), x, y, 64, 64, null);
	}
}
