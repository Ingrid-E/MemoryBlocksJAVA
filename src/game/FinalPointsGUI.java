package game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
/**
 * @file FinalPointsGUI.java
 * @author Ingrid Echeverri Montoya ingrid.echeverri@correounivalle.edu.co
 * @brief JPanel that containts points, level and basic information that shows at the end of the game.
 * @version 0.1
 * @date 12-03-2021
 */
public class FinalPointsGUI extends JPanel{

	private static final long serialVersionUID = 1L;
	private JLabel mainMenu, endText, finalLevel, finalPoints, playAgain, exit, finalLives;
	private Lisent lisent;
	private Boolean menuPressed, playPressed, exitPressed;
	//Sets Background image
	@Override
    public void paintComponent(Graphics g) 
    {
        super.paintComponent(g);
        ImageIcon img = new ImageIcon("src/images/EndingBackground.png");
        g.drawImage(img.getImage(), 0, 0, this.getWidth(), this.getHeight(), null);
    }
	/**
	 * @brief Constructor with atributes to show in the JPanel
	 * @param level 
	 * @param points
	 * @param lives
	 */
	public FinalPointsGUI(int level, int points, int lives) {
			//Atributes boolean
			this.menuPressed = false;
			this.playPressed = false;
			this.exitPressed = false;
			//Basic Constructor
			this.lisent = new Lisent();
			this.endText = new JLabel();
			this.finalLevel  = new JLabel("Level: " + level);
			this.finalLives =  new JLabel("Lives: " + lives);
			this.finalPoints = new JLabel("Final Points: " + points);
			this.mainMenu = new JLabel();
			this.playAgain = new JLabel();
			this.exit = new JLabel();
			//Layout
			this.setLayout(new GridBagLayout());
			GridBagConstraints c = new GridBagConstraints();

			endText.setIcon(new ImageIcon("src/images/EndingTitle.png"));
			finalLevel.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN,45));
			finalLevel.setForeground(Color.WHITE);
			finalLives.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN,45));
			finalLives.setForeground(Color.WHITE);
			finalPoints.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN,45));
			finalPoints.setForeground(Color.WHITE);
			//Image Icons
			mainMenu.setIcon(new ImageIcon("src/images/MainMenuButton.png"));
			playAgain.setIcon(new ImageIcon("src/images/PlayAgainButton.png"));
			exit.setIcon(new ImageIcon("src/images/ExitButton.png"));
			
		    //Add Listener to JLabel as buttons
			mainMenu.addMouseListener(lisent);
			playAgain.addMouseListener(lisent);
			exit.addMouseListener(lisent);
			
			c.gridx = 2;
			c.gridy= 0;
			c.insets = new Insets(0, 30, 0, 0);
			add(exit,c);
			c.gridx = 0;
			c.gridy = 1;
			c.gridwidth = 3;
			c.fill =  GridBagConstraints.HORIZONTAL;
			c.anchor = GridBagConstraints.CENTER;
			c.insets = new Insets(0, 30, 50, 0);
			add(endText,c);
			c.gridx = 0;
			c.gridy = 2;
			c.gridwidth = 1;
			c.insets = new Insets(0, 50, 30, 0);
			add(finalLevel,c);
			c.gridy = 3;
			c.insets = new Insets(0, 50, 30, 0);
			add(finalLives,c);
			c.gridy = 4;
			c.insets = new Insets(0, 50, 50, 0);
			add(finalPoints,c);
			c.gridx = 1;
			c.gridy = 5;
			c.insets = new Insets(30, 0, 30, 20);
			add(mainMenu,c);
			c.gridx = 2;
			c.gridy = 5;
			c.insets = new Insets(30, 0, 30, 0);
			add(playAgain,c);
			this.setVisible(true);
		}
	/**
	 * @brief Get if menu button is pressed, true if pressed false if not.
	 * @return Boolean
	 */
	public Boolean getMenu() {
		return menuPressed;
	}
	/**
	 * @brief Get if play button is pressed, true if pressed false if not.
	 * @return Boolean
	 */
	public Boolean getPlay() {
		return playPressed;
	}
	/**
	 * @brief Get if exit button is pressed, true if pressed false if not.
	 * @return Boolean
	 */
	public Boolean getExit() {
		return exitPressed;
	}
	
	/**
	 * @file FinalPointsGUI.java
	 * @author Ingrid Echeverri Montoya ingrid.echeverri@correounivalle.edu.co
	 * @brief Containts all the mouse, keyBoard and button events.
	 * @version 0.1
	 * @date 12-03-2021
	 */
	private class Lisent implements MouseListener, ActionListener{

		@Override
		public void mouseClicked(MouseEvent eventAction) {
			if(eventAction.getSource() == mainMenu) {
				menuPressed = true;
				mainMenu.setIcon(new ImageIcon("src/images/MainMenuButtonHovered.png"));
				
			}
			if(eventAction.getSource() == playAgain) {
				playPressed = true;
				playAgain.setIcon(new ImageIcon("src/images/PlayAgainButtonHovered.png"));
			}
			if(eventAction.getSource() == exit) {
				exitPressed = true;
				playAgain.setIcon(new ImageIcon("src/images/PlayAgainButtonHovered.png"));
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
		public void mouseEntered(MouseEvent eventAction) {
			if(eventAction.getSource() == mainMenu) {
				mainMenu.setIcon(new ImageIcon("src/images/MainMenuButtonHovered.png"));
			}
			if(eventAction.getSource() == playAgain) {
				playAgain.setIcon(new ImageIcon("src/images/PlayAgainButtonHovered.png"));
			}
		}

		@Override
		public void mouseExited(MouseEvent eventAction) {
			if(eventAction.getSource() == mainMenu) {
				mainMenu.setIcon(new ImageIcon("src/images/MainMenuButton.png"));
			}
			if(eventAction.getSource() == playAgain) {
				playAgain.setIcon(new ImageIcon("src/images/PlayAgainButton.png"));
			}
		}

		@Override
		public void actionPerformed(ActionEvent e) {

			
		}
			
	}

	
}
