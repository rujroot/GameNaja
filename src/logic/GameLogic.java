package logic;

import java.util.ArrayList;
import entity.Entity;
import entity.Player;
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
