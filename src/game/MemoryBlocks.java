/**
 * @file CrapsGame.java
 * @author Ingrid Echeverri Montoya ingrid.echeverri@correounivalle.edu.co
 * @brief Containts the main interface and invokes it
 * @version 0.2
 * @date 01-03-2021
 */
package game;
import java.awt.EventQueue;

public class MemoryBlocks {

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						//Runs window
						@SuppressWarnings("unused")
						MainWindow window = new MainWindow();
					}
				});
			}
			
		});

	}

}
