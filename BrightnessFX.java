import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.Random;

public class BrightnessFX implements ImageFX {

    private static final int WIDTH = 64;        // width of the image
    private static final int HEIGHT = 64;       // height of the image
    private static final int YPOS = 236;        // vertical position of the image

    private GamePanel panel;

    private int x;
    private int y;

    private BufferedImage spriteImage;           // image for sprite effect
    private BufferedImage copy;                  // copy of image

    Graphics2D g2;

    int brightness, brightnessChange;            // to alter the brightness of the image
    private boolean isFlashing = false;          // flag to indicate if flashing is active

    public BrightnessFX(GamePanel p) {
        panel = p;

        Random random = new Random();
        y = YPOS;

        brightness = 0;                          // range is -255 to 255; negative values darken the
                                                 // image and positive values brighten the image

        brightnessChange = 50;                  // increase of brightness in each update
    }

    private int truncate(int colourValue) {      // keeps colourValue within [0..255] range
        if (colourValue > 255)
            return 255;

        if (colourValue < 0)
            return 0;

        return colourValue;
    }

    private int brighten(int pixel) {

        int alpha, red, green, blue;
        int newPixel;

        alpha = (pixel >> 24) & 255;
        red = (pixel >> 16) & 255;
        green = (pixel >> 8) & 255;
        blue = pixel & 255;

        // Increase or decrease the value of the RGB components based on the value of brightness

        red = red + brightness;
        green = green + brightness;
        blue = blue + brightness;

        // Check the boundaries for 8-bit RGB components [0..255]

        red = truncate(red);
        green = truncate(green);
        blue = truncate(blue);

        newPixel = blue | (green << 8) | (red << 16) | (alpha << 24);

        return newPixel;
    }

    public void draw(Graphics2D g2, boolean facing, int playerX) {
        // Load the appropriate sprite based on the player's direction
        if (facing) {
            spriteImage = ImageManager.loadBufferedImage("images/player/static.png");
        } else {
            spriteImage = ImageManager.loadBufferedImage("images/playerleft/static.png");
        }

        copy = ImageManager.copyImage(spriteImage);      // make copy of image for brightness effect

        int imWidth = copy.getWidth();
        int imHeight = copy.getHeight();

        int[] pixels = new int[imWidth * imHeight];
        copy.getRGB(0, 0, imWidth, imHeight, pixels, 0, imWidth);

        for (int i = 0; i < pixels.length; i++) {
            pixels[i] = brighten(pixels[i]);
        }

        copy.setRGB(0, 0, imWidth, imHeight, pixels, 0, imWidth);

        g2.drawImage(copy, playerX, y, WIDTH, HEIGHT, null);
	
    }

    public Rectangle2D.Double getBoundingRectangle() {
        return new Rectangle2D.Double(x, y, WIDTH, HEIGHT);
    }

    public void update() {                        // modify brightness (-255 to 255)
       
            brightness = brightness + 255;

			if (brightness > 255) {
				brightness = 255;
				brightnessChange = -brightnessChange;  // start decreasing brightness
			}

           
            
        
	}

}

    