import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;

/**
   A component that displays all the game entities
*/

public class GamePanel extends JPanel implements Runnable {
      
      Player player;
      Floor floor;

      private SoundManager soundManager;

      private Thread gameThread;
      private boolean isStarted;
      private boolean isRunning;
      private Slime[] AllSlimes;

      private BufferedImage image;
      private Image foregroundgrass;
      private Image background;

      public GamePanel () {
      player = null; 
      floor = null;

      background = ImageManager.loadImage("images/background.png");
      foregroundgrass = ImageManager.loadImage("images/grass.png");
      soundManager = SoundManager.getInstance();
      image = new BufferedImage(600, 400, BufferedImage.TYPE_INT_RGB);
   }


   public void createGameEntities() {
      player = new Player (this); 
      GameWindow.updatePlayerHealht(player.health);

      AllSlimes = new Slime[5];
      AllSlimes[0] = new SmallSlime(this, player);
      AllSlimes[1] = new SmallSlime(this, player);
      AllSlimes[2] = new SmallSlime(this,player);
      AllSlimes[3] = new FallingSlime(this,player);
      AllSlimes[4] = new LargeSlime(this, player);

      floor = new Floor (this, 300);
   }

   public void drawGameEntities() {
       if (player != null) {
       	  player.draw();
       }
   }

   public void updateGameEntities(int direction) {

      if (player == null)
         return;

      if (isRunning){
         player.erase();
         player.move(direction);
         player.facingSide();
      }
   }

   public void swung(){
      soundManager.playRandomClip("slash", 4 , false);
      for (Slime s : AllSlimes){
         s.hurtSlime();
         
      }
   }

   public void killedSlime(){
      soundManager.playRandomClip("slimekilled", 2, false);
      soundManager.playClip("point", false);
   }

   public void hurtPlayer(){
      soundManager.playClip("playerhurt", false);

   }
   public void slimeShield(){
      soundManager.playRandomClip("slimeblock", 3, false);
   }

   public void windup(){
      soundManager.playClip("windup", false);
   }

   public void fallingSlimeDead(){
      soundManager.playRandomClip("fallingslimeboom",2, false);
   }
   public void sheild(){
      if (player.shield)
         player.shield=false;
      else
         player.shield=true;
   }


   public void run(){
      try{
      while (isRunning){
        
            updateGameEntities();
            if (player.health <= 10) {
               soundManager.setVolume("lowHealth", 1.0f);
               soundManager.playClip("lowHealth", true);

      }
            if(player.health <= 0){
               
               isRunning=false;
               System.out.println("Player Died. Game over. Final Points :" + player.points);

               soundManager.stopClip("lowHealth");
               soundManager.stopClip("background");

               soundManager.playClip("playerdeath", false);
            }

            gameRender();
            Thread.sleep(100);

            
         }
      }
      catch (Exception e) {
         e.printStackTrace();
      }
      
   }

   public void updateGameEntities(){
      for(Slime s : AllSlimes){
         s.move();
      }
   }

   public void gameRender(){
      
      Graphics2D imageContext = (Graphics2D) image.getGraphics();
      
      imageContext.drawImage(background, 0, 0, null);
      imageContext.drawImage(foregroundgrass, 0, 285,null);
      if (floor != null){
         floor.draw(imageContext);
      }

      Graphics2D g2 = (Graphics2D) getGraphics();	// get the graphics context for the panel
		g2.drawImage(image, 0, 0, 600, 400, null);

		imageContext.dispose();
		g2.dispose();
   }

   public void startGame(){
      if (isStarted){
      System.out.println("Game already Started. Reopen application to start again");
      return;
      }
      if (isRunning) return;

      isStarted = true;
      isRunning = true;

      //soundManager.setVolume("background", 0.85f);
      soundManager.playClip("background", true);
      gameThread = new Thread(this);
      gameThread.start();
      createGameEntities();
      //drawGameEntities();

		System.out.println("Number of threads: " + Thread.activeCount());
      
   }

}