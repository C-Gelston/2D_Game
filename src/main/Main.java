package main;

import javax.swing.JFrame;
import main.GamePanel;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		JFrame window = new JFrame();
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(false);
		window.setTitle("Ghibli Game");
		
		GamePanel gamePanel = new GamePanel();
		window.add(gamePanel);
		
		window.pack();
		window.setLocationRelativeTo(null);;
		window.setVisible(true); 
		
		gamePanel.setupGame(); 
		
		gamePanel.startGameThread();
	}

}
