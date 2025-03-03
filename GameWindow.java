import java.awt.*;			// need this for GUI objects
import java.awt.event.*;			// need this for Layout Managers
import javax.swing.*;		// need this to respond to GUI events
	
public class GameWindow extends JFrame 
				implements ActionListener,
					   KeyListener,
					   MouseListener
{
	// declare instance variables for user interface objects

	// declare labels 

	private JLabel statusBarL;
	private JLabel keyL;
	private JLabel pointsL;
	private JLabel healthL;	

	// declare text fields

	private JTextField statusBarTF;
	private JTextField keyTF;
		private static JTextField pointsTF;
		private static JTextField healthTF;
	
		// declare buttons
	
		private JButton startB;
		private JButton pauseB;
		private JButton focusB;
		private JButton exitB;
	
		private Container c;
	
		private JPanel mainPanel;
		private GamePanel gamePanel;
	
		@SuppressWarnings({"unchecked"})
		public GameWindow() {
	 
			setTitle ("Don't Get Slimmed 0.1.3");
			setSize (1200, 800);
	
			// create user interface objects
	
			// create labels
	
			statusBarL = new JLabel ("Application Status: ");
			keyL = new JLabel("Key Pressed: ");
			pointsL = new JLabel("Points Scored: ");
			healthL = new JLabel("Player Health: ");
	
			// create text fields and set their colour, etc.
	
			statusBarTF = new JTextField (25);
			statusBarTF.setFont(new Font(Font.DIALOG, Font.BOLD, 12));
			keyTF = new JTextField (25);
			keyTF.setFont(new Font(Font.DIALOG, Font.BOLD, 12));
			pointsTF = new JTextField (25);
			pointsTF.setFont(new Font(Font.DIALOG, Font.BOLD, 12));
			healthTF = new JTextField (25);
			healthTF.setFont(new Font(Font.DIALOG, Font.BOLD, 12));
			
	
			statusBarTF.setEditable(false);
			keyTF.setEditable(false);
			pointsTF.setEditable(false);
			healthTF.setEditable(false);
	
			statusBarTF.setBackground(Color.decode("#53854a"));
			keyTF.setBackground(Color.decode("#53854a"));
			pointsTF.setBackground(Color.decode("#53854a"));
			healthTF.setBackground(Color.decode("#53854a"));
	
			// create buttons
	
			startB = new JButton ("Start Game");
				pauseB = new JButton ("Drop Alien");
				focusB = new JButton ("Focus on Key");
			exitB = new JButton ("Exit");
	
			// add listener to each button (same as the current object)
	
			startB.addActionListener(this);
			pauseB.addActionListener(this);
			focusB.addActionListener(this);
			exitB.addActionListener(this);
	
			
			// create mainPanel
	
			mainPanel = new JPanel();
			FlowLayout flowLayout = new FlowLayout();
			mainPanel.setLayout(flowLayout);
	
			GridLayout gridLayout;
	
			// create the gamePanel for game entities
	
			gamePanel = new GamePanel();
			gamePanel.setPreferredSize(new Dimension(1000, 600));
			gamePanel.createGameEntities();
	
	
			// create infoPanel
	
			JPanel infoPanel = new JPanel();
			gridLayout = new GridLayout(4, 2);
			infoPanel.setLayout(gridLayout);
			infoPanel.setBackground(Color.decode("#53854a"));
	
			// add user interface objects to infoPanel
		
			infoPanel.add (statusBarL);
			infoPanel.add (statusBarTF);
	
			infoPanel.add (keyL);
			infoPanel.add (keyTF);		
	
			infoPanel.add (pointsL);
			infoPanel.add (pointsTF);
	
			infoPanel.add(healthL);
			infoPanel.add(healthTF);
	
			
			// create buttonPanel
	
			JPanel buttonPanel = new JPanel();
			gridLayout = new GridLayout(1, 4);
			buttonPanel.setLayout(gridLayout);
	
			// add buttons to buttonPanel
	
			buttonPanel.add (startB);
			buttonPanel.add (pauseB);
			buttonPanel.add (focusB);
			buttonPanel.add (exitB);
	
			// add sub-panels with GUI objects to mainPanel and set its colour
	
			mainPanel.add(infoPanel);
			mainPanel.add(gamePanel);
			mainPanel.add(buttonPanel);
			mainPanel.setBackground(Color.decode("#89a672"));
	
			// set up mainPanel to respond to keyboard and mouse
	
			gamePanel.addMouseListener(this);
			mainPanel.addKeyListener(this);
	
			// add mainPanel to window surface
	
			c = getContentPane();
			c.add(mainPanel);
	
			// set properties of window
	
			setResizable(true);
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
			setVisible(true);
	
			// set status bar message
	
			statusBarTF.setText("Application started.");
		}
	
	
		// implement single method in ActionListener interface
	
		public void actionPerformed(ActionEvent e) {
	
			String command = e.getActionCommand();
			
			statusBarTF.setText(command + " button clicked.");
	
			
			if (command.equals(startB.getText()))
				gamePanel.startGame();
				mainPanel.requestFocus();
	
				
			if (command.equals(pauseB.getText()))
			//gamePanel.pauseGame();
			System.out.println("Does nothing yet");
	
			if (command.equals(focusB.getText()))
				mainPanel.requestFocus();
	
			if (command.equals(exitB.getText()))
				System.exit(0);
	
			mainPanel.requestFocus();
		}
	
	
		// implement methods in KeyListener interface
	
		public void keyPressed(KeyEvent e) {
			int keyCode = e.getKeyCode();
			String keyText = e.getKeyText(keyCode);
			keyTF.setText(keyText + " pressed.");
	
			if (keyCode == KeyEvent.VK_RIGHT || keyCode == KeyEvent.VK_D ) {
				gamePanel.updateGameEntities(2);
				gamePanel.drawGameEntities();
			}
	
			if (keyCode == KeyEvent.VK_LEFT || keyCode == KeyEvent.VK_A ) {
				gamePanel.updateGameEntities(1);
				gamePanel.drawGameEntities();
			}
		}
	
		public void keyReleased(KeyEvent e) {
	
		}
	
		public void keyTyped(KeyEvent e) {
	
		}
	
		// implement methods in MouseListener interface
	
		public static void updatePointChecker(int x){
			pointsTF.setText(Integer.toString(x));
		}
	
		public static void updatePlayerHealht (int x){
			healthTF.setText(Integer.toString(x));
	}

	public void mouseClicked(MouseEvent e) {
	
		if (SwingUtilities.isLeftMouseButton(e)) {
			statusBarTF.setText("Left click");
			gamePanel.swung();

		} 
	}


	public void mouseEntered(MouseEvent e) {
	
	}

	public void mouseExited(MouseEvent e) {
	
	}

	public void mousePressed(MouseEvent e) {
        if (SwingUtilities.isRightMouseButton(e)) {
            statusBarTF.setText("Right click held");
            gamePanel.sheild();
        }
    }

    public void mouseReleased(MouseEvent e) {
        if (SwingUtilities.isRightMouseButton(e)) {
            statusBarTF.setText("Right click released");
            gamePanel.sheild();
        }
    }

}