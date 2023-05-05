package logic;

import java.io.IOException;
import java.util.ArrayList;

import data.BaseObject;
import data.DataOre;
import data.Point;
import drawing.GameScreen;
import dungeon.GenerateDungeon;
import dungeon.Room;
import entity.Entity;
import entity.Monster;
import entity.Npc;
import entity.Player;
import entity.Shopkeeper;
import equipment.projectile.Arrow;
import inventory.Inventory;
import item.Item;
import ore.BaseOre;
import scene.SceneController;

public class GameLogic {
	public static GameScreen gameScreen;
	private static ArrayList<BaseObject> gameObjectContainer;
	private int currentLevel;
	
	public GameLogic() {
		// create new ObjectContainer
		this.gameObjectContainer = new ArrayList<BaseObject>();
	}
	
	// Add object to gameObject and Renderable
	public void addObject(BaseObject object) {
		gameObjectContainer.add(object);
		RenderableHolder.getInstance().add(object);
	}
	
	//This method should run every sec
	public void logicUpdate(){
		for (int i = gameObjectContainer.size() - 1; i >= 0; i--) {
			BaseObject object = gameObjectContainer.get(i);
			if(object instanceof Entity){
					Entity entity = (Entity) object;
					
					
					if(entity.getData().getHp() <= 0) {
						entity.setDestroyed(true);
						gameObjectContainer.remove(entity);
					}

					if(entity instanceof Monster) {
						((Monster) entity).follow();
						if(((Monster) entity).isIntersectPlayer()) {
							((Monster) entity).attack();
						}
					}
					
					if(entity instanceof Player) {
						if(entity.getData().getHp()<=0) {
							SceneController sceneController = new SceneController();
							resetGame();
							try {
								sceneController.switchToGameOverScene2();
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
					}
					
					if(object instanceof Shopkeeper){
						Shopkeeper shopkeeper = (Shopkeeper) object;
						shopkeeper.updateInput();
					}
				
					if(object instanceof Npc){
						Npc npc = (Npc) object;
						npc.findNearestMonster(gameObjectContainer);
						npc.doBehavior();
					}
				}
			
			else if(object instanceof Arrow){
				Arrow arrow = (Arrow) object;
				arrow.update();
				for (int j = gameObjectContainer.size() - 1; j >= 0; j--) {
					BaseObject thatObject = gameObjectContainer.get(j);
					if(!(thatObject instanceof Player) && (thatObject instanceof Entity) && arrow.hit((Entity)thatObject)){
						arrow.makeDamge((Entity)thatObject);
						arrow.setDestroyed(true);
						gameObjectContainer.remove(arrow);
					}
				}
			}

			else if(object instanceof BaseOre){
				BaseOre ore = (BaseOre) object;
				DataOre data = ore.getDataOre();
				if(data.getDurability() <= 0){
					ore.onBreak();
					ore.setDestroyed(true);
					gameObjectContainer.remove(ore);
				}
			}
	
		}
	}

	public void playerinteraction(){
		Player player = Player.player;
		for (int i = gameObjectContainer.size() - 1; i >= 0; i--) {
			BaseObject object = gameObjectContainer.get(i);
			if(object instanceof Shopkeeper){
				Shopkeeper shopkeeper = (Shopkeeper) object;
			}
			

		}
	}

	public void nextFloor(){
		
		//Get next dungeon
		int currentLevel = GenerateDungeon.getCurrLevel();
		if(currentLevel >= GenerateDungeon.getLevel() - 1) return;
		GenerateDungeon.setCurrLevel(currentLevel+ 1);
		this.clearObject();

		//Get first room
		ArrayList<Room> nextLevel = GenerateDungeon.getContainer().get(currentLevel + 1);
		Room firstRoom = nextLevel.get(0);
		System.out.println("Go to " + (currentLevel + 1));

		for(int i = 1; i < nextLevel.size(); ++i){
			for(BaseOre ore : nextLevel.get(i).getOres()){
				ore.setVisible(true);
			}
		}
		
		Player player = Player.getPlayer();
		// Get postion to spawn
		Point spawnPoint = new Point(firstRoom.getPosition().getX() + 10, firstRoom.getPosition().getY() + 10 );

		// Warp player
		player.setPosition(spawnPoint);

	}

	public void clearObject(){
		for (int i = gameObjectContainer.size() - 1; i >= 0; i--) {
			BaseObject object = gameObjectContainer.get(i);
			if(object instanceof Player) continue;
			if(object instanceof BaseOre && !object.isVisible()) continue;
			if(object instanceof Inventory) continue;
			if(object instanceof Item) continue;

			object.setDestroyed(true);
			gameObjectContainer.remove(object);
		}

	}
	
	public void resetGame(){
		for (int i = gameObjectContainer.size() - 1; i >= 0; i--) {
			BaseObject object = gameObjectContainer.get(i);
			object.setDestroyed(true);
			gameObjectContainer.remove(object);
		}

	}

	public ArrayList<BaseObject> getGameObjectContainer() {
		return gameObjectContainer;
	}

	public static void removeObj(BaseObject object){
		gameObjectContainer.remove(object);
	}
	

}
