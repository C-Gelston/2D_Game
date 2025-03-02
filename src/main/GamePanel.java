package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import entity.Player;
import object.SuperObject;
import tile.TileManager;

//subclass of the Jpanel so it has all functions: works as a gamescreen
public class GamePanel extends JPanel  implements Runnable{
	
	//SCREEN SETTINGS
	final int originalTileSize = 16; //16 x 16 tile(standard size for 2D games) Modern comp has much higher res. so 16 x 16 looks very small 
	//SCALE SETTINGS (INCREASE BY 3 SO 16 X 3(SCALE)= 48)
	final int scale = 3; 
	
	public final int tileSize = originalTileSize * scale; // 48 x 48 tile
	public final int maxScreenCol = 16;
	public final int maxScreenRow = 12; //Ration 4 x 3 
	public final int screenWidth = tileSize * maxScreenCol; // 48 * 16 = 768px
	public final int screenHeight = tileSize * maxScreenRow; //48 * 12 = 576px
	
	//WORLD SETTINGS
	public final int maxWorldCol = 50;
	public final int maxWorldRow = 50;
	public final int worldWidth = tileSize * maxWorldCol;
	public final int worldHeight = tileSize * maxWorldRow;
	
	//FPS
	int FPS = 60;
	
	TileManager tileM = new TileManager(this);
	
	KeyHandler keyH = new KeyHandler();
	
	//TIME IN GAME :  once thread started it keeps game running cont. 
	Thread gameThread;
	
	public CollisionChecker cChecker = new CollisionChecker(this);
	
	public AssetSetter aSetter = new AssetSetter(this);
	
	public Player player = new Player(this,keyH);
	
	public SuperObject obj[]= new SuperObject[10]; //10 slots/object: change if need more
	
	
	
	
	public GamePanel() {
		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true);
		this.addKeyListener(keyH);
		this.setFocusable(true);
	}
	
	public void setupGame() {
		
		aSetter.setObject();
	}
	
	public void startGameThread() {
		gameThread = new Thread(this); //passing game panel class to threads constructor
		gameThread.start();
		
	}
	@Override
	public void run() {
		double drawInterval = 1000000000/FPS; //0.016 seconds draw
		double nextDrawTime = System.nanoTime() + drawInterval;
	
		
		
		while(gameThread != null) {
			
			
		// TEST CODE:	System.out.println("The game loop is running");
		//1.FPS UPDATE: update information such as character positions. Calling update method
			update(); 
			
		//2.DRAW: draw the screen with the updated information. Calling repaint method
			repaint();
			
			try {
				//Sleep Method used: try using Delta accumulator method
				
				double remainingTime = nextDrawTime - System.nanoTime(); 
				remainingTime = remainingTime/1000000; //accept remaining time from nano to miliseconds
				if(remainingTime < 0) {
					remainingTime = 0;
				}
				Thread.sleep((long) remainingTime);
				
				nextDrawTime += drawInterval;
				
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
		}
	}
	public void update() {
		player.update();

		
	}
	public void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		//Graphics in 2D. This graphics class has more usable 2D functions. Think of layers i.e tiles before player to show player ontop 
		Graphics2D g2 = (Graphics2D)g;
		
		//TILE
		tileM.draw(g2);
		//OBJECT
		for(int i = 0; i < obj.length; i++) {
			if(obj[i] != null) {
				obj[i].draw(g2,  this);
			}
		}
		//PLAYER
		player.draw(g2);

		g2.dispose(); 
		
		
	
	}
}
