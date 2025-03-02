import javax.swing.JPanel;

/**
   A component that displays all the game entities
*/

public class GamePanel extends JPanel implements Runnable {
      
      Player player;
      Floor floor;

      private Thread gameThread;
      private boolean isStarted;
      private boolean isRunning;
      private Slime[] AllSlimes;

      public GamePanel () {
      player = null;
      floor = null;
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
           floor.draw();
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
      for (Slime s : AllSlimes){
         s.hurtSlime();
         
      }
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
            if(player.health <= 0){
               isRunning=false;
               System.out.println("Player Died. Game over. Final Points :" + player.points);
            }
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

   public void startGame(){
      if (isStarted){
      System.out.println("Game already Started. Reopen application to start again");
      return;
      }
      if (isRunning) return;

      isStarted = true;
      isRunning = true;
      gameThread = new Thread(this);
      gameThread.start();
      createGameEntities();
      drawGameEntities();

		System.out.println("Number of threads: " + Thread.activeCount());

   }

}