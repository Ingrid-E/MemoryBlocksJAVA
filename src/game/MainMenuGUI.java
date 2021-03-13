package game;

import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MainMenuGUI extends JPanel{
	private JLabel title, playButton, rulesButton, exitButton;
	private Boolean pressPlay, pressRules, pressExit;
	private Lisent lisent;
	private static final long serialVersionUID = 1L;
	
	
	public MainMenuGUI() {
		//Create Listent Object
		lisent = new Lisent();
		//Title Image
		title= new JLabel();
		title.setIcon(new ImageIcon("src/images/GameTitle.png"));
		//Play Button Image
		playButton = new JLabel();
		playButton.setIcon(new ImageIcon("src/images/PlayButton.png"));
		playButton.addMouseListener(lisent);
		//Rules Button Image
		rulesButton = new JLabel();
		rulesButton.setIcon(new ImageIcon("src/images/RulesButton.png"));
		rulesButton.addMouseListener(lisent);
		//Exit Button Image
		exitButton = new JLabel();
		exitButton.setIcon(new ImageIcon("src/images/ExitButton.png"));
		exitButton.addMouseListener(lisent);
		//Current Button State
	    pressPlay = false;
	    pressRules = false;
	    pressExit = false;
	    //JPanel Layout
	    this.setLayout(new GridBagLayout());
	    //Add Elements to the MainMenuGUI
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 2;
		c.gridy = 0;
		c.anchor = GridBagConstraints.FIRST_LINE_START;
		c.insets = new Insets(-30, 0,0,40);
		add(exitButton,c);
		c.gridx = 0;
		c.gridy = 1;
		c.gridwidth = 2;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.anchor = GridBagConstraints.CENTER;
		c.insets = new Insets(60, 40,70,-60);
		add(title,c);
		c.gridx = 1;
		c.gridy = 2;
		c.gridwidth = 1;
		c.fill = GridBagConstraints.NONE;
		c.anchor = GridBagConstraints.CENTER;
		c.insets = new Insets(0, 0,30,-90);
		add(playButton,c);
		c.gridx = 1;
		c.gridy = 3;
		c.gridwidth = 1;
		add(rulesButton,c);
		
	}
	//Paint Background
	@Override
    public void paintComponent(Graphics g) 
    {
        super.paintComponent(g);
        ImageIcon img = new ImageIcon("src/images/MainMenuBackground.png");
        g.drawImage(img.getImage(), 0, 0, this.getWidth(), this.getHeight(), null);
    }
	
	//Get and Set Pressed Button 
	
	public boolean pressExit() {
		return pressExit;
	}
	
	public boolean pressedRuleButton() {
		return pressRules;
	}
	
	public boolean pressedPlayButton() {
		return pressPlay;
	}
	
	public void setPressedRuleButton(boolean pressed) {
		pressRules = pressed;
	}
	
	public void setPressedPlayButton(boolean pressed) {
		pressPlay = pressed;
	}
	
	//Mouse listener to add to Buttons
	
	private class Lisent implements MouseListener{

		@Override
		public void mouseClicked(MouseEvent eventAction) {
			if(eventAction.getSource() == playButton) {
				pressPlay = true;
			}
			if(eventAction.getSource() == rulesButton) {
				System.out.println("Rules Pressed");
				pressRules = true;
			}
			if(eventAction.getSource() == exitButton) {
				pressExit = true;
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
			if(eventAction.getSource() == playButton) {
				playButton.setIcon(new ImageIcon("src/images/PlayButtonHovered.png"));
			}
			if(eventAction.getSource() == rulesButton) {
				rulesButton.setIcon(new ImageIcon("src/images/RulesButtonHovered.png"));
			}
			
		}

		@Override
		public void mouseExited(MouseEvent eventAction) {
			if(eventAction.getSource() == playButton) {
				playButton.setIcon(new ImageIcon("src/images/PlayButton.png"));
			}
			if(eventAction.getSource() == rulesButton) {
				rulesButton.setIcon(new ImageIcon("src/images/RulesButton.png"));
			}
			
		}
		
	}
	

}






