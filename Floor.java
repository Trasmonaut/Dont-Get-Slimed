import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import javax.swing.JPanel;

public class Floor {
    
    private JPanel panel;
    private int height;
    private int width;
    private int y;

    public Floor (JPanel p, int yPos) {
        panel = p;
        y = yPos;
        height = 400;
    }

    public void draw(){
        width = panel.getWidth(); 
        
        Graphics g = panel.getGraphics();
        Graphics2D g2 = (Graphics2D) g;


        Rectangle2D.Double floor = new Rectangle2D.Double(0, y,  width, height);
        g2.setColor(Color.BLACK);
        g2.fill(floor);

        g.dispose();
    }
}