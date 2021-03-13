package game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
/**
 * @file RulesGUI.java
 * @author Ingrid Echeverri Montoya ingrid.echeverri@correounivalle.edu.co
 * @brief JPanel that containts points, level and basic information that shows at the end of the game.
 * @version 0.1
 * @date 12-03-2021
 */
public class RulesGUI extends JPanel{
	//Atributes
	private static final long serialVersionUID = 1L;
	private JLabel mainMenu, nextL, nextR, imageRules, ruleTitle;
	private Boolean menuIsPressed;
	private Lisent lisent;
	private int rules;
	/**
	 * @brief Public Constructor
	 */
	public RulesGUI() {
		//Initialiaze objects
		this.lisent = new Lisent();
		this.ruleTitle = new JLabel("Rules");
		this.nextR = new JLabel();
		this.nextL = new JLabel();
		this.mainMenu = new JLabel();
		this.imageRules = new JLabel();
		menuIsPressed = false;
		this.rules = 1;
		this.setLayout(new GridBagLayout());
		//Set Layout
		this.ruleTitle.setFont(new Font("Arial Rounded MT Bold", Font.BOLD,60));
		this.ruleTitle.setForeground(Color.WHITE);
		
		this.nextR.setIcon(new ImageIcon("src/images/rightArrow.png"));
		this.nextR.addMouseListener(lisent);
		
		this.nextL.setIcon(new ImageIcon("src/images/leftArrow.png"));
		this.nextL.addMouseListener(lisent);
		
		mainMenu.setIcon(new ImageIcon("src/images/MainMenuButton.png"));
		mainMenu.addMouseListener(lisent);

		imageRules.setIcon(new ImageIcon("src/images/Rules" + rules + ".png"));

		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 3;
		c.insets = new Insets(0,25,-30,0);
		add(ruleTitle,c);
		c.gridx = 1;
		c.gridy = 1;
		c.gridwidth = 1;
		c.insets = new Insets(20,0,0,0);
		add(imageRules,c);
		c.gridx = 0;
		c.gridy = 1;
		c.gridwidth = 1;
		c.insets = new Insets(20,40,0,-10);
		add(nextL,c);
		c.gridx = 2;
		c.gridy = 1;
		c.gridwidth = 1;
		c.insets = new Insets(20,0,0,40);
		add(nextR,c);
		c.gridx = 1;
		c.gridy = 2;
		c.gridwidth = 1;
		c.insets = new Insets(0,40,0,0);
		c.anchor = GridBagConstraints.CENTER;
		add(mainMenu,c);
	}
	//Sets background image
	@Override
    public void paintComponent(Graphics g) 
    {
        super.paintComponent(g);
        ImageIcon img = new ImageIcon("src/images/RulesBackground.png");
        g.drawImage(img.getImage(), 0, 0, this.getWidth(), this.getHeight(), null);
    }
	/**
	 * @brief Get if menu button is pressed true if pressed false if not.
	 * @return Boolean
	 */
	public boolean getMenuPressed() {
		return menuIsPressed;
	}
	/**
	 * @brief Set menu button is pressed true if pressed false if not.
	 * @return Boolean
	 */
	public void setMenuPressed(Boolean pressed) {
		menuIsPressed = pressed;
	}
	/**
	 * @file RulesGUI.java
	 * @author Ingrid Echeverri Montoya ingrid.echeverri@correounivalle.edu.co
	 * @brief Containts all the mouse, keyBoard and button events.
	 * @version 0.1
	 * @date 12-03-2021
	 */
	private class Lisent implements MouseListener{

		@Override
		public void mouseClicked(MouseEvent eventAction) {
			if(eventAction.getSource() == mainMenu) {
				menuIsPressed = true;
			}
			if(eventAction.getSource() == nextR) {
				if(rules + 1 < 6) {
					rules = rules + 1;
					imageRules.setIcon(new ImageIcon("src/images/Rules" + rules + ".png"));
				}
			}
			if(eventAction.getSource() == nextL) {
				if(rules - 1 != 0) {
					rules = rules - 1;
					imageRules.setIcon(new ImageIcon("src/images/Rules" + rules + ".png"));
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
		public void mouseEntered(MouseEvent eventAction) {
			if(eventAction.getSource() == mainMenu) {
				mainMenu.setIcon(new ImageIcon("src/images/MainMenuButtonHovered.png"));
			}
			
		}

		@Override
		public void mouseExited(MouseEvent eventAction) {
			if(eventAction.getSource() == mainMenu) {
				mainMenu.setIcon(new ImageIcon("src/images/MainMenuButton.png"));
			}
			
		}
		
	}
	
	
}
