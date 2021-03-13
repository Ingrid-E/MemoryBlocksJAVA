package game;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.TimerTask;
import java.util.Timer;


import javax.swing.*;
import javax.swing.border.LineBorder;
/**
 * @file MainWindow.java
 * @author Ingrid Echeverri Montoya ingrid.echeverri@correounivalle.edu.co
 * @brief Controls all the windows that appear in the game.
 * @version 0.1
 * @date 12-03-2021
 */
public class MainWindow extends JFrame{
	//Atributes
	private static final long serialVersionUID = 1L;
	private Lisent lisent;
	private JFrame mainWindow;
	private Boolean gamePaused, rulesVisible, menuVisible, gameVisible, finalVisible;
	private GameGUI gameWindow;
	private MainMenuGUI menuWindow;
	private RulesGUI rulesWindow;
	private FinalPointsGUI finalGame;
	private int width, height;
	private JLabel pauseText;
	private JButton mainMenu, resume, exit;
	
	private JDialog pause;

	public MainWindow() {
		//Basic atributes boolean 
		this.rulesVisible = false;
		this.menuVisible = true;
		this.gameVisible = false;
		this.finalVisible = false;
		this.gamePaused = false;
		//Basic atributes int
		this.width = 780;
		this.height = 670;
		//Initializing basic atributes
		this.menuWindow = new MainMenuGUI();
		this.lisent = new Lisent();
		mainWindow = this;
		this.pauseText = new JLabel("PAUSED");
		this.mainMenu = new JButton("MAIN MENU");
		this.resume = new JButton("RESUME");
		this.exit = new JButton("EXIT");
		this.rulesWindow = new RulesGUI();
		
		this.exit.addActionListener(lisent);
		this.resume.addActionListener(lisent);
		this.mainMenu.addActionListener(lisent);
		
		//Layout
		this.addMouseListener(lisent);
		this.addMouseMotionListener(lisent);
		this.setPreferredSize(new Dimension(width, height));
		this.setUndecorated(true);
		this.pack();
		this.getContentPane().setBackground(Color.WHITE);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setFocusable(true);   
		this.addKeyListener(lisent);
		
		add(menuWindow);
		this.setVisible(true);
		//Start Game timer
		startTimer();
			
	}
	/**
	 * @brief Function that changes the JPanel that is shown
	 * @param window
	 */
	private void changeWindows(String window) {
		this.getContentPane().removeAll();
		if(window == "Game") {
			pause = new JDialog(this);
			pause.setVisible(false);
			this.pausedPopOut();
			this.gameWindow = new GameGUI();
			//gameTimer();
			gameVisible = true;
			add(gameWindow);
		}
		if(window == "Rules") {
			this.rulesWindow = new RulesGUI();
			rulesVisible = true;
			add(rulesWindow);
		}
		if(window == "Main Menu") {
			this.menuWindow = new MainMenuGUI();
			menuVisible = true;
			add(menuWindow);
		}
		if(window == "Final Game") {
			finalVisible = true;
			gameWindow.pauseTimer();
			this.finalGame = new FinalPointsGUI(gameWindow.getLevel(), gameWindow.getPoints(), gameWindow.getLives());
			add(finalGame);
		}
		this.revalidate();
		this.repaint();
	}
	
	/**
	 * @brief Show the pause menu in top of the game 
	 */
	private void pausedPopOut() {
		//Basic atributes
		pauseText.setFont(new Font("Arial Rounded MT Bold", Font.BOLD,50));
		pauseText.setForeground(new Color(239,153,228));
		mainMenu.setBackground(new Color(239,153,228));
		mainMenu.setBorder(BorderFactory.createEmptyBorder());
		mainMenu.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN,18));
		mainMenu.setForeground(new Color(44,70,163));
		mainMenu.setPreferredSize(new Dimension(120, 40));
		mainMenu.setFocusable( false );
		exit.setBackground(new Color(239,153,228));
		exit.setBorder(BorderFactory.createEmptyBorder());
		exit.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN,18));
		exit.setForeground(new Color(44,70,163));
		exit.setPreferredSize(new Dimension(90, 40));
		resume.setBackground(new Color(239,153,228));
		resume.setBorder(BorderFactory.createEmptyBorder());
		resume.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN,18));
		resume.setForeground(new Color(44,70,163));
		resume.setPreferredSize(new Dimension(110, 40));
		resume.setFocusable( false );
		//Layout
		pause.setLayout(new GridBagLayout());
		pause.setSize(400,200);
      	pause.setUndecorated(true);
      	pause.setLocation(mainWindow.getLocation().x + 200, mainWindow.getLocation().y + 260);
      	//Adding Elements to Pause Pop Out
      	pause.getContentPane().setBackground(new Color(44,70,163));
      	pause.getRootPane().setBorder(new LineBorder(new Color(103,117,222), 8, true));
      	//Grid
      	GridBagConstraints c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 3;
		c.weighty=1;
		c.fill = GridBagConstraints.NONE;
		c.anchor = GridBagConstraints.CENTER;
		pause.add(pauseText,c);
		c.gridy = 1;
		c.weightx=1;
		c.weighty=1;
		c.gridwidth = 1;
		pause.add(mainMenu,c);
		c.gridx = 1;
		exit.setFocusable( false );
		pause.add(exit,c);
		c.gridx = 2;
		pause.add(resume,c);
	
	}
	
	/**
	 * @brief Timer that checks the JPanels state and button clicked on it, according to button pressed the JPanel will change
	 */
	public void startTimer() {
		Timer timer = new Timer();
		TimerTask task = new TimerTask() {
			@Override
			public void run() {
				if(menuVisible == true) {
					if(menuWindow.pressedPlayButton() == true) {
						gameVisible = true;
						menuVisible = false;
						changeWindows("Game");
						menuWindow.setPressedPlayButton(false);
					}
					if(menuWindow.pressedRuleButton() == true) {
						rulesVisible = true;
						menuVisible = false;
						changeWindows("Rules");
						menuWindow.setPressedRuleButton(false);
					}
					if(menuWindow.pressExit() == true) {
						System.exit(0);
					}
				}
				
				if(rulesVisible == true && rulesWindow.getMenuPressed() == true) {
					changeWindows("Main Menu");
					menuVisible = true;
					rulesVisible = false;
					rulesWindow.setMenuPressed(false);
				}
				
				if(gameVisible == true && gameWindow.getGameEnded() == true) {
					changeWindows("Final Game");
					finalVisible = true;
					gameVisible = false;
				}
				
				if(finalVisible == true ) {
					if(finalGame.getMenu() == true) {
						finalVisible = false;
						changeWindows("Main Menu");
					}
					if(finalGame.getPlay() == true) {
						finalVisible = false;
						changeWindows("Game");
					}
					if(finalGame.getExit() == true) {
						System.exit(0);
					}
				}
				
			};
		};
		timer.scheduleAtFixedRate(task, 500, 10);
	}
	
	/**
	 * @file MainWindow.java
	 * @author Ingrid Echeverri Montoya ingrid.echeverri@correounivalle.edu.co
	 * @brief Containts all the mouse, keyBoard and button events.
	 * @version 0.1
	 * @date 12-03-2021
	 */
	private class Lisent implements KeyListener, ActionListener, MouseListener, MouseMotionListener{
		private int x,y;
		
		@Override
		public void keyTyped(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void keyPressed(KeyEvent eventAction) {
			if(eventAction.getKeyCode() == 80 && gamePaused == false && gameVisible == true) {
				gameWindow.pauseTimer();
				pause.setVisible(true);
			}
		}

		@Override
		public void keyReleased(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void actionPerformed(ActionEvent eventAction) {
			if(eventAction.getSource() == resume) {
				gameWindow.startTimer();
				pause.setVisible(false);
			}
			if(eventAction.getSource() == exit) {
				mainWindow.setVisible(false);
				mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				System.exit(0);
				mainWindow.dispose();
			}
			if(eventAction.getSource() == mainMenu) {
				changeWindows("Main Menu");
				menuVisible = true;
				gameVisible = false;
				pause.setVisible(false);
			}
			
		}

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(MouseEvent eventMouse) {
			x = eventMouse.getX();
			y = eventMouse.getY();
			System.out.println("Holi");
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseDragged(MouseEvent eventMouseMotion) {
			setLocation(mainWindow.getLocation().x+eventMouseMotion.getX()-x, 
			        mainWindow.getLocation().y +eventMouseMotion.getY()-y);
					if(gameVisible == true && pause.isVisible() == true) {
						pause.setLocation(pause.getLocation().x+eventMouseMotion.getX()-x, 
						        pause.getLocation().y +eventMouseMotion.getY()-y);
					}
					

			
		}

		@Override
		public void mouseMoved(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

	}
	
	

//Intentar hacer una forma en donde puede contar los numeros sin tener que aumentar la velocidad.
	
	
}
