import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import javax.swing.JPanel;


public class Health {
    private int width;
    private int height;
    private int x;
    private int y;

    private JPanel panel;
    private Ellipse2D orb;
    private Player player;

    public Health(JPanel p, Player player, int x) {
        panel = p;
        width = 20;
        height = 20;
        x = x;
        y = 280;

        draw();

    }

    public void draw() {
        Graphics g = panel.getGraphics();
        Graphics2D g2 = (Graphics2D) g;

        orb = new Ellipse2D.Double(x, y, width, height);

        g2.setColor(Color.PINK);
        g2.fill(orb);

        g2.setColor(Color.BLACK);
        g2.draw(orb);

        g.dispose();
    }

    public void erase() {
        Graphics g = panel.getGraphics();
        Graphics2D g2 = (Graphics2D) g;

        g2.setColor(panel.getBackground());
        g2.fill(orb);

        g.dispose();
    }

    public boolean getBounds() {
        Rectangle2D.Double playerBounds = player.getPlayerBoundingRectangle();
        Rectangle2D.Double healthBounds = new Rectangle2D.Double(x, y, width, height);

        return playerBounds.intersects(healthBounds);
    }


}
