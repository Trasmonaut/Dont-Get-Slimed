import java.awt.Graphics;
import java.awt.Image;
import javax.swing.JPanel;

public class Floor {
    
    private JPanel panel;
    private int height;
    private int width;
    private int y;

    private Image floorImage;

    public Floor (JPanel p, int yPos) {
        panel = p;
        y = yPos;
        height = 100;

        floorImage = ImageManager.loadImage("images/floor.png");
    }

    public void draw(Graphics g2){

        g2.drawImage(floorImage, 0, y, 600, height, null);
    //     width = panel.getWidth(); 
        
    //     Graphics g = panel.getGraphics();
    //     Graphics2D g2 = (Graphics2D) g;


    //     Rectangle2D.Double floor = new Rectangle2D.Double(0, y,  width, height);
    //     g2.setColor(Color.BLACK);
    //     g2.fill(floor);

    //     g.dispose();
    }
}