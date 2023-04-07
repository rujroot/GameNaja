package logic;

import java.util.ArrayList;
import entity.Entity;
import entity.Zombie;
import equipment.projectile.Arrow;

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
		for(Entity entity: gameObjectContainer) {
			if(entity instanceof Zombie) {
				((Zombie) entity).follow();
			}
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
