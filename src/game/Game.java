
package game;

import java.awt.Color;
import java.util.Random;

/**
 * @file Game.java
 * @author Ingrid Echeverri Montoya ingrid.echeverri@correounivalle.edu.co
 * @brief Contains the games block colors and randomizer functions for the game to work correctly.
 * @version 0.1
 * @date 12-03-2021
 */

public class Game {
	//Game Atributes
	private Random randomColor, randomBlock;
	private int blockRandom, colorRandom, changedBlock;
	protected Color[] colors;
	/**
	 * @brief Public Constructor
	 * Containts the possible Colors a Block can have.
	 */
	public Game() {
		//Colors constructor
		colors = new Color[20];
		colors[0] = new Color(255,0,0);
		colors[1] = new Color(0,255,0);
		colors[2] = new Color(255,255,0);
		colors[3] = new Color(92,51,255);
		colors[4] = new Color(0,0,255);
		colors[5] = new Color(127,0,255);
		colors[6] = new Color(255,0,127);
		colors[7] = new Color(255,128,0);
		colors[8] = new Color(128,255,0);
		colors[9] = new Color(0,255,128);
		colors[10] = new Color(0,255,255);
		colors[11] = new Color(0,128,255);
		colors[12] = new Color(255,0,255);
		colors[13] = new Color(128,128,128);
		colors[14] = new Color(178,102,155);
		colors[15] = new Color(153,255,104);
		colors[16] = new Color(255,102,102);
		colors[17] = new Color(178,255,102);
		colors[18] = new Color(255,153,204);
		colors[19] = new Color(153,0,0);
	}
	
	/**
	 * @brief Picks a Random block to change color from the block quantity
	 * @param quantity
	 * @return int 
	 */
	public int randomChangingBlock(int quantity) {
		this.randomBlock = new Random();
		this.blockRandom = randomBlock.nextInt(quantity);
		
		while(this.changedBlock == this.blockRandom) {
			this.randomBlock = new Random();
			this.blockRandom = randomBlock.nextInt(quantity);
		}
		this.changedBlock = blockRandom;
		return blockRandom;
	}
	/**
	 * @bief Picks a Random color to set to a block
	 * @param quantity
	 * @return Color
	 */
	public Color randomColor(int quantity) {
		this.randomColor = new Random();
		this.colorRandom = randomColor.nextInt(quantity);
		return colors[colorRandom];
	}
	
	

	

	
}
