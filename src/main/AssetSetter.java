package main;

import object.OBJ_Chest;
import object.OBJ_Door;
import object.OBJ_Key;

public class AssetSetter {
	
	GamePanel gp;
	public AssetSetter(GamePanel gp) {
		this.gp = gp;
	}
	
	//METHOD
	public void setObject() {
		
		gp.obj[0] = new OBJ_Key();
		gp.obj[0].worldX = 40 * gp.tileSize;
		gp.obj[0].worldY = 13 * gp.tileSize;
				// 40col 13row
		
		gp.obj[1] = new OBJ_Key();
		gp.obj[1].worldX = 30 * gp.tileSize;
		gp.obj[1].worldY = 30 * gp.tileSize;
				// 30col 30 row
		
		gp.obj[2] = new OBJ_Door();
		gp.obj[2].worldX = 0 * gp.tileSize;
		gp.obj[2].worldY = 22 * gp.tileSize;
			// 2col 22row
		
		gp.obj[3] = new OBJ_Chest();
		gp.obj[3].worldX = 35 * gp.tileSize;
		gp.obj[3].worldY = 39 * gp.tileSize;
		 	//35col 39row
		
		//gp.obj[4] = new OBJ_Key();
		//gp.obj[4].worldX = 2 * gp.tileSize;
		//gp.obj[4].worldY = 22 * gp.tileSize;
		
	}

}
