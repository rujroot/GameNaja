package logic;

import java.util.ArrayList;

import Data.BaseObject;
import Data.Point;
import Dungeon.GenerateDungeon;
import Dungeon.Room;
import entity.Entity;
import entity.Player;
import entity.Zombie;
import equipment.projectile.Arrow;

public class GameLogic {
	
	private ArrayList<BaseObject> gameObjectContainer;
	
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
	public void logicUpdate() {
		for (int i = gameObjectContainer.size() - 1; i >= 0; i--) {
			BaseObject object = gameObjectContainer.get(i);
			if(object instanceof Entity){
					Entity entity = (Entity) object;
					if(entity.getData().getHp() <= 0) {
						entity.setDestroyed(true);
						gameObjectContainer.remove(entity);
					}

					if(entity instanceof Zombie) {
						((Zombie) entity).follow();
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


		}
	}

	public void nextFloor(){
		
		//Get next dungeon
		int Currlevel = GenerateDungeon.getCurrLevel();
		if(Currlevel >= GenerateDungeon.getLevel() - 1) return;
		GenerateDungeon.setCurrLevel(Currlevel + 1);

		//Get first room
		Room firstRoom = GenerateDungeon.getContainer().get(Currlevel + 1).get(0);
		System.out.println("Go to " + (Currlevel + 1));
		
		Player player = Player.getPlayer();
		// Get postion to spawn
		Point spawnPoint = new Point(firstRoom.getPosition().getX() + 10, firstRoom.getPosition().getY() + 10 );

		// Warp player
		player.setPosition(spawnPoint);

		this.clearObject();

	}

	public void clearObject(){
		for (int i = gameObjectContainer.size() - 1; i >= 0; i--) {
			BaseObject object = gameObjectContainer.get(i);
			if(object instanceof Player) continue;
			object.setDestroyed(true);
			gameObjectContainer.remove(object);
		}

	}

	public ArrayList<BaseObject> getGameObjectContainer() {
		return gameObjectContainer;
	}

}
