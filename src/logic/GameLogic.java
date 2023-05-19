package logic;

import java.io.IOException;
import java.util.ArrayList;
import data.BaseObject;
import data.DataOre;
import data.Point;
import drawing.GameScreen;
import dungeon.GenerateDungeon;
import dungeon.Ladder;
import dungeon.Room;
import entity.Entity;
import entity.Monster;
import entity.Npc;
import entity.Player;
import entity.Shopkeeper;
import entity.Team;
import entity.boss.BossEntity;
import entity.miniBoss.MiniBossEntity;
import equipment.projectile.BaseProjectile;
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
						if(entity instanceof MiniBossEntity || entity instanceof BossEntity){
							Ladder ladder = new Ladder(entity.getPosition());
							this.addObject(ladder);
						}
						entity.setDestroyed(true);
						gameObjectContainer.remove(entity);
					}

					if(entity instanceof Monster) {
						if(entity instanceof BossEntity){
							BossEntity bossEntity = (BossEntity) object;
							bossEntity.attack();
							continue;
						}

						if(entity instanceof MiniBossEntity){
							MiniBossEntity miniBossEntity = (MiniBossEntity) object;
							miniBossEntity.findNearestEntity(gameObjectContainer);
							miniBossEntity.attack();
							miniBossEntity.specialAttack();
							continue;
						}
						
						Monster monster = (Monster) entity;
						monster.findNearestEntity(gameObjectContainer);
						monster.attack();
					}
					
					if(entity instanceof Player) {
						Player player = (Player) entity;
						player.checkPlayerInBossRoom();
						if(entity.getData().getHp()<=0) {
							RenderableHolder.sound.stop();
							SceneController sceneController = new SceneController();
							resetGame();
							try {
								sceneController.switchToGameOverScene();
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
			
			else if(object instanceof BaseProjectile){
				BaseProjectile arrow = (BaseProjectile) object;
				arrow.update();
				if(!arrow.inRoom()){
					arrow.setDestroyed(true);
					gameObjectContainer.remove(arrow);
					continue;
				}

				Team team = arrow.getTeam();
				for (int j = gameObjectContainer.size() - 1; j >= 0; j--) {
					BaseObject thatObject = gameObjectContainer.get(j);
					if(team.equals(Team.Player) && (thatObject instanceof Monster) && arrow.hit((Entity)thatObject)){
						arrow.makeDamge((Entity)thatObject);
						arrow.setDestroyed(true);
						gameObjectContainer.remove(arrow);
					}else if(team.equals(Team.Monster) && ((thatObject instanceof Player) || (thatObject instanceof Npc)) && arrow.hit((Entity)thatObject)){
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
	
			else if(object instanceof Ladder){
				Ladder ladder = (Ladder) object;
				ladder.updateInput();
			}
		}
	}

	public void nextFloor(){
		SceneController.dungeon.increaseFloor(1);
		
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
			if(object instanceof Npc) continue;

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
