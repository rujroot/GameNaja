package logic;

import java.util.ArrayList;
import java.util.List;

import entity.Entity;

public class GameLogic {
	
	private List<Entity> gameObjectContainer;
	
	public GameLogic() {
		// create new ObjectContainer
		this.gameObjectContainer = new ArrayList<Entity>();
		
		
	}
	
	// Add object to gameObject and Renderable
	public void addNewObject(Entity entity) {
		gameObjectContainer.add(entity);
		RenderableHolder.getInstance().add(entity);
	}
	
	//This method should run every sec
	public void logicUpdate() {
		//Todo
	}
	
	

}
