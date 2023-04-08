package logic;

import java.util.ArrayList;

import Dungeon.GenerateDungeon;
import Dungeon.Room;
import Math.Point;
import drawing.GameScreen;
import entity.Entity;
import entity.Player;
import entity.Zombie;
import equipment.projectile.Arrow;
import javafx.scene.canvas.GraphicsContext;

public class GameLogic {
	
	private ArrayList<Entity> gameObjectContainer;
	private ArrayList<Arrow> gameArrowContainer;
	
	public GameLogic() {
		// create new ObjectContainer
		this.gameObjectContainer = new ArrayList<Entity>();
		this.gameArrowContainer = new ArrayList<Arrow>();
	}
	
	// Add object to gameObject and Renderable
	public void addNewObject(Entity entity) {
		gameObjectContainer.add(entity);
		RenderableHolder.getInstance().add(entity);
	}

	public void addNewArrow(Arrow arrow) {
		gameArrowContainer.add(arrow);
		RenderableHolder.getInstance().add(arrow, "Arrow");
	}
	
	//This method should run every sec
	public void logicUpdate() {
		for (int i = gameObjectContainer.size() - 1; i >= 0; i--) {
			Entity entity = gameObjectContainer.get(i);
			if(entity.getData().getHp() <= 0) {
				entity.setDestroyed(true);
				gameObjectContainer.remove(entity);
			}

			if(entity instanceof Zombie) {
				((Zombie) entity).follow();
			}
		}

		for (int i = gameArrowContainer.size() - 1; i >= 0; i--) {
			Arrow arrow = gameArrowContainer.get(i);
			arrow.update();
			for(Entity entity: gameObjectContainer) {
				if(!(entity instanceof Player) && arrow.hit(entity)){
					arrow.makeDamge(entity);
					arrow.setDestroyed(true);
					gameArrowContainer.remove(arrow);
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
			Entity entity = gameObjectContainer.get(i);
			if(entity instanceof Player) continue;
			entity.setDestroyed(true);
			gameObjectContainer.remove(entity);
		}

		for (int i = gameArrowContainer.size() - 1; i >= 0; i--) {
			Arrow arrow = gameArrowContainer.get(i);
			arrow.setDestroyed(true);
			gameArrowContainer.remove(arrow);
		}
	}

	public ArrayList<Entity> getGameObjectContainer() {
		return gameObjectContainer;
	}

	public void setGameObjectContainer(ArrayList<Entity> gameObjectContainer) {
		this.gameObjectContainer = gameObjectContainer;
	}

	public ArrayList<Arrow> getGameArrowContainer() {
		return gameArrowContainer;
	}

	public void setGameArrowContainer(ArrayList<Arrow> gameArrowContainer) {
		this.gameArrowContainer = gameArrowContainer;
	}
	
	

}
