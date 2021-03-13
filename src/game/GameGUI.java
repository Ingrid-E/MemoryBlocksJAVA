package game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

/**
 * @file GameGUI.java
 * @author Ingrid Echeverri Montoya ingrid.echeverri@correounivalle.edu.co
 * @brief Containts the game visual representation and block controler.
 * @version 0.1
 * @date 12-03-2021
 */

public class GameGUI extends JPanel{
	//Atributes
	private JPanel[][] colorBlocks;
	protected JPanel[] changinBlocks;
	protected JLabel[] viewlives;
	private Color[] possibleColors;
	@SuppressWarnings("unused")
	private Color matchingColors;
	private int level, columns, rows, middleX, middleY;
	private int squareQuantity;
	@SuppressWarnings("unused")
	private Random squarePosition;
	int squareX;
	int squareY;
	private Lisent lisent;
	public Boolean pressedButton;
	private int points;
	private Game game;
	private int changeBlock, lastChangedBlock;

	private Boolean didRestart;
	private int lives;
	Timer[] timer;
	Timer newTimer;
	private int newLevel;
	private Boolean lostLife;
	JPanel topContent;
	private int speed;
	protected Boolean didGameFinish, pressedMenu, pressedPlayAgain;
	private JLabel showLevel, pressButton;
	private static final long serialVersionUID = 1L;
	
	/**
	 * @brief Function that changes the JPanel background
	 */
	@Override
    public void paintComponent(Graphics g) 
    {
        super.paintComponent(g);
        ImageIcon img = new ImageIcon("src/images/GameBackground.png");
        g.drawImage(img.getImage(), 0, 0, this.getWidth(), this.getHeight(), null);
    }
	
	/**
	 * @brief Public Constructor, contains the inicialization of object and atributes.
	 */
	public GameGUI() {
		//Basic Boolean Atributes
		this.pressedMenu = false;
		this.pressedPlayAgain = false;
		this.didGameFinish = false;
		this.didRestart = false;
		this.lostLife = false;
		this.pressedButton = false;
		this.pressedButton = false;
		//Basic int Atributes
		this.newLevel = 2;
		this.speed = 0;
		this.lives  = 3;
		this.points = 0;
		this.changeBlock = 0;
		this.lastChangedBlock = -1;
		this.level = 1;
		this.columns =  5;
		this.rows = 7;
		this.squareQuantity = 2+this.level;
		this.squareX = 0;
		this.squareY = 0;
		this.middleX = 0;
		this.middleY = 0;
		//Other Basic Atributes
		this.matchingColors = new Color(1);
		//Basic Constructors
		newTimer = new Timer();
		timer = new Timer[20];
		for(int i = 0; i<20; i++) {
			timer[i] = new Timer(true);
		}
		this.game = new Game();
		this.showLevel = new JLabel("Level: " + this.level);
		squarePosition = new Random();
		possibleColors = game.colors;
		this.changinBlocks = new JPanel[16];
		this.colorBlocks = new JPanel[5][7];
		//Star GUI
		this.initGUI();
	}
	/**
	 * @brief Returns Current Level
	 * @return int
	 */
	public int getLevel() {
		return level;
	}
	/**
	 * @brief Returns current live quantity
	 * @return int
	 */
	public int getLives() {
		return lives;
	}
	/**
	 * @brief returns points total
	 * @return int
	 */
	public int getPoints() {
		return points;
	}
	/**
	 * @brief Returns true if game finished false if it hasn't finished yet.
	 * @return Boolean
	 */
	public boolean getGameEnded() {
		return this.didGameFinish;
	}
	
	/**
	 * @brief Shows lives and level in the top part of the JPanel
	 */
	private void livePanels() {
		topContent.setOpaque(false);
		//For that makes the hearts
		for(int i=0; i<3; i++) {
			JLabel lives_i  =  new JLabel();
			lives_i.setIcon(new ImageIcon("src/images/Heart.png"));
			this.viewlives[i] = lives_i;
			topContent.add(this.viewlives[i]);
		}
		//Add Content to JPanel
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 4;
		c.gridy = 0;
		c.weightx=1;
		c.weighty=1;
		add(topContent,c);
		//Show level text
		showLevel.setFont(new Font("Arial Rounded MT Bold", Font.BOLD,30));
		showLevel.setForeground(Color.WHITE);
		c.gridx = 0;
		add(showLevel,c);
		
	}
	
	/**
	 * @brief Starts GUI and functions that start the game
	 */
	private void initGUI() {
		//Game Constructors 
		lisent = new Lisent();
		pressButton = new JLabel();
		topContent = new JPanel();
		viewlives = new JLabel[this.lives];
		//Set Layout
		topContent.setLayout(new FlowLayout());
		pressButton.setIcon(new ImageIcon("src/images/PressButton.png"));
		pressButton.setEnabled(false);
		pressButton.addMouseListener(lisent);
		this.setLayout(new GridBagLayout());
		this.setBackground(Color.WHITE);
		this.setVisible(true);
		//Startig game functions
		this.livePanels();
		this.coloredBlocks();
		this.levelTimer(level-1);
	}
	/**
	 * @brief Constructs the main block grid that is a 7*5 grid.
	 */
	protected void coloredBlocks() {
		
		GridBagConstraints c = new GridBagConstraints();
		//Creates the grid
		for(int x = 0; x<columns; x++) {
			for(int y=0; y<rows; y++) {
				c.gridx = x;
				c.gridy = y+1;
				c.weightx=1;
				c.weighty=1;
				middleX = columns/2;
				middleY = rows/2;
				if(x == middleX & y == middleY) {
					add(pressButton,c);
				}else {
					JPanel _x_y = new JPanel();
					//Guardar los JPanels en un array
					this.colorBlocks[x][y] = _x_y;
					_x_y.setBackground(new Color(239,153,228));
					_x_y.setPreferredSize(new Dimension(150,80));
					add(_x_y,c);
				}
			}
		}
		//Adds a Space to the JPanel
		JPanel space = new JPanel();
		space.setOpaque(false);
		JPanel space2 = new JPanel();
		space2.setOpaque(false);
		c.gridx = 0;
		c.gridy = 8;
		c.weightx=1;
		c.weighty=2;
		add(space,c);
		c.gridy = 9;
		add(space2,c);
		//Paints the random blocks level
		this.paintGameLevel();
	}
	
	/**
	 * @brief Paints the colored blocks in a random position in the grid. 
	 */
	private void paintGameLevel() {
		//For that makes a random block in a random position in the grid.
		for(int i=0; i<squareQuantity; i++) {
			//Randomize position
			Random squarePosition = new Random();
			squareX = squarePosition.nextInt(columns);
			squareY = squarePosition.nextInt(rows);
			//Dont put a block in the button position
			while(squareX == middleX && squareY == middleY) {
				squarePosition = new Random();
				squareX = squarePosition.nextInt(columns);
				squareY = squarePosition.nextInt(rows);

			}
			//When it's the first level, and null blocks exits.
			if(newLevel == 2) {
				this.changinBlocks[i] = this.colorBlocks[squareX][squareY];
				this.existingBlock(i+1);
				matchingBlocks(i);
				if(changinBlocks[i] != null) {
					this.changinBlocks[i].setBackground(possibleColors[i]);
				}
			}
			//Check if changin block is not null
			while(this.changinBlocks[i] == null) {
				squarePosition = new Random();
				squareX = squarePosition.nextInt(columns);
				squareY = squarePosition.nextInt(rows);
				while(squareX == middleX && squareY == middleY) {
					squarePosition = new Random();
					squareX = squarePosition.nextInt(columns);
					squareY = squarePosition.nextInt(rows);

				}
				this.changinBlocks[i] = this.colorBlocks[squareX][squareY];
			}
			//Set a changin block
			this.changinBlocks[i] = this.colorBlocks[squareX][squareY];
			this.existingBlock(i+1);
			matchingBlocks(i);
			//Check if the block is not null
			if(changinBlocks[i] != null) {
				this.changinBlocks[i].setBackground(possibleColors[i]);
			}
		}
	}
	/**
	 * @brief Checks the blocks everytime to make sure they don't have the same background color
	 * @param quantity
	 */
	protected void matchingBlocks(int quantity) {
		for(int i = 0; i< quantity; i++) {
			for(int j = 0; j<  quantity; j++) {
				if(this.changinBlocks[i] != null && this.changinBlocks[j] != null  ) {
				while(this.changinBlocks[i].getBackground() == this.changinBlocks[j].getBackground() && i != j) {
					for(int colorsPossible = 0; colorsPossible < possibleColors.length ; colorsPossible++) {
						this.changinBlocks[i].setBackground(possibleColors[colorsPossible]);
					}
				}
				}
			}
		}
	}	

	
	/**
	 * @brief Checks the Blocks to see if there are matching blocks in the game or not
	 * @return Boolean
	 */
	protected boolean matchingBlocks() {
		boolean matching = false;
		for(int i = 0; i< this.squareQuantity; i++) {
			for(int j = 0; j<  this.squareQuantity; j++) {
				if(this.changinBlocks[i] != null && this.changinBlocks[j] != null  ) {
					if(this.changinBlocks[i].getBackground() == this.changinBlocks[j].getBackground() && i != j) {
						matchingColors = this.changinBlocks[i].getBackground();
						matching = true;
					}
				}
			}
		}
		return matching;
	}
	/**
	 * @brief New game level, changes basic atributes and rePaints the colores Block in the 
	 * grid adding one more.
	 */
	private void nextLevel() {
		//Atributes
		this.lostLife = false;
		this.pressedButton = false;
		this.newLevel = 1;
		this.lastChangedBlock = -1;
		this.changeBlock = 0;
		this.level = this.level + 1;
		this.showLevel.setText("Level: " + this.level);	
		this.squareQuantity = this.squareQuantity+1;
		newTimer = new Timer();
		this.colorBlocks = new JPanel[5][7];
		//Finish if level = 10;
		if(this.level == 10) {
			this.didGameFinish = true;
			this.level = this.level - 1;
		}else {
			this.levelTimer(level-1);
		}
		//Restart Painting Blocks
		this.coloredBlocks();
	}
	/**
	 * @brief Retarts the game level if the player lost a life.
	 */
	private void restart() {
		//Atributes
		this.pressedButton = false;
		this.lostLife = true;
		this.changeBlock = 0;
		this.lastChangedBlock = -1;
		this.newLevel = 1;
		this.topContent = new JPanel();
		this.colorBlocks = new JPanel[5][7];
		this.newTimer = new Timer();
		//Keep playing if lives is not equal to 0
		if(lives != 0) {
			this.coloredBlocks();
			this.levelTimer(10);
		}
		//Finished game
		if(lives == 0) {
			this.didGameFinish = true;
			pauseTimer();
		}
		//Change live image icon if player lost life
		for(int i= 0; i<3-this.lives; i++) {
			viewlives[i].setIcon(new ImageIcon("src/images/HeartLost.png"));
		}
		
		

	}
	/**
	 * @brief Get boolean if player lost life
	 * @return Boolean
	 */
	public Boolean getLostLife() {
		return 	lostLife;
	}
	/**
	 * @brief Cancels the current timer to pause it
	 */
	public void pauseTimer() {
		pressButton.setEnabled(false);
		if(lostLife == true) {
			newTimer.cancel();
		}else {
			timer[level-1].cancel();
		}
	}
	/**
	 * @brief Start the current timer
	 */
	public void startTimer() {
		pressButton.setEnabled(true);
		if(lostLife == true) {
			newTimer = new Timer();
			this.levelTimer(10);
		}else {
			timer[level-1] = new Timer();
			this.levelTimer(level-1);
		}
	}
	

		
	/**
	 * @brief Checks if the block alreadt exits in that position to change it.
	 * @param quantity
	 */
	private void existingBlock(int quantity) {
		for(int i= 0; i< quantity; i++) {
			for(int j=0; j<quantity; j++) {
				if(this.changinBlocks[i] == this.changinBlocks[j] && i!=j) {
					Random squarePosition = new Random();
					squareX = squarePosition.nextInt(columns);
					squareY = squarePosition.nextInt(rows);
					this.changinBlocks[j] = this.colorBlocks[squareX][squareY];
				}
			}
		}
	}
	
	/**
	 * @brief Paints a Random colored block in the grid and adds a border.
	 */
	public void paintRandomBlock() {
		switch(this.newLevel) {
		//Changes border to nothing so the JPanel will change in the timer
		case 1:
			changeBlock = game.randomChangingBlock(this.squareQuantity);
			if(changinBlocks[changeBlock] != null) {
				this.changinBlocks[changeBlock].setBorder(new LineBorder(new Color(0,0,0), 0, true));
			}
			break;
		//Changes the border and block color
		case 0:
			changeBlock = game.randomChangingBlock(this.squareQuantity);
			double closeRange = ((1/(double)this.squareQuantity)*30);
			int range = 20 - (int)closeRange;
			if(this.changinBlocks[changeBlock] != null) {
				this.changinBlocks[changeBlock].setBackground(game.randomColor(range));
				this.changinBlocks[changeBlock].setBorder(new LineBorder(Color.ORANGE, 4, true));
			}
			if(lastChangedBlock != -1) {
				this.changinBlocks[lastChangedBlock].setBorder(null);
			}
			lastChangedBlock = changeBlock;
			break;
			
		};
	}
	
	/**
	 * @brief Returns true if game restarted and false if not
	 * @return Boolean
	 */
	public Boolean getdidRestart() {
		return didRestart;
	}
	/**
	 * @brief Returns true if a new Level started and false if not
	 * @return int
	 */
	public int getNewLevel() {
		return newLevel;
	}
	/**
	 * @brief Timer that controls changing block colors and button.
	 * @param timerLevel
	 */
	public void levelTimer(int timerLevel) {
		TimerTask task = new TimerTask() {
			int counter = 0;
			int startingCounter = 0;
			
			@Override
			public void run() {

				if(lives == 0) {
					pauseTimer();
				}
				if(newLevel == 2) {
					startingCounter = 1;
					newLevel = 1;
				}
				if(startingCounter == 1 && newLevel != 2) {
					paintRandomBlock();
					didRestart = true;
					newLevel = 0;
				}
				if(startingCounter >= 2){
					didRestart = false;
					if(counter == 1) {
						pressButton.setEnabled(true);
						paintRandomBlock();
					}
					if(counter == 3) {
						if(matchingBlocks() == true && pressedButton == false) {
							pauseTimer();
							pressButton.setEnabled(false);
							lives  = lives - 1;
							restart();
						}
						counter = 0;
					}
					counter++;
					
				}
				startingCounter++;
			};
		};
		
		
		//If game restarted to keep same speed
		if(timerLevel == 10) {
			if(speed == 0) {
				newTimer.scheduleAtFixedRate(task, 1000, 2000);
			}else {
				newTimer.scheduleAtFixedRate(task, 1000, speed);
			}
		//New Timer with new Speed
		}else {
			speed = 900 - (50*timerLevel);
			System.out.println("Speed: " + speed);
			timer[timerLevel].scheduleAtFixedRate(task, 500, speed);
		}
	}
	
	/**
	 * @file GameGUI.java
	 * @author Ingrid Echeverri Montoya ingrid.echeverri@correounivalle.edu.co
	 * @brief Containts all mouse and button events.
	 * @version 0.1
	 * @date 12-03-2021
	 */
	private class Lisent implements ActionListener,MouseListener{
		@Override
		public void mouseClicked(MouseEvent eventAction) {
			if(eventAction.getSource() == pressButton) {
				pressedButton = true;
				//If two blocks are matching blocks and button is pressed
				if(matchingBlocks() == true) {
					pressButton.setEnabled(false);
					points = points + 5;
					timer[level-1].cancel();
					newTimer.cancel();
					nextLevel();
				//If there are no matching blocks and button is pressed
				}else {
					pressButton.setEnabled(false);
					lives  = lives - 1;
					timer[level-1].cancel();
					newTimer.cancel();
					restart();
				}

			}
			
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			
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
		public void actionPerformed(ActionEvent eventAction) {
			
		}
		
	}
	
	

}

