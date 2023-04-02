package entity;

import Math.Point;
import equipment.BaseWeapon;
import equipment.Bow;
import input.InputUtility;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import logic.Cooldownable;
import logic.GameLogic;
import logic.Main;

public class Player extends Entity implements Cooldownable{
	
	public static Player player;
	
	private BaseWeapon equipment;
	private double lastClickTime = 0;
	private double cooldownTime = 1000;

	public Player(String name, double hp, double atk, double def, double spd, double poisonStatus) {
		super(name, hp, atk, def, spd, poisonStatus);
		player = this;
		this.setEquipment(new Bow(2));
	}
	
	public void update(GraphicsContext gc) {
		Point pos = this.getPosition();
		if (InputUtility.getKeyPressed(KeyCode.W)) {
			pos.setY(pos.getY() - 1);
			gc.translate(0, -1);
		} if (InputUtility.getKeyPressed(KeyCode.A)) {
			pos.setX(pos.getX() - 1);
			gc.translate(-1, 0);
		} if (InputUtility.getKeyPressed(KeyCode.D)) {
			pos.setX(pos.getX() + 1);
			gc.translate(1, 0);
		} if (InputUtility.getKeyPressed(KeyCode.S)) {
			pos.setY(pos.getY() + 1);
			gc.translate(0, 1);
		} if(InputUtility.getKeyPressed(KeyCode.SPACE)) {
			attack();
			
		} if (InputUtility.getKeyPressed(KeyCode.J) && !onCooldown()) {
			GameLogic logic = Main.getLogic();
			Zombie zombie = new Zombie("Zombie", 10, 1, 1, Math.random() + 0.2, 0, 0);
			logic.addNewObject(zombie);
		}
	}

	@Override
	public void draw(GraphicsContext gc) {
		gc.setFill(Color.TAN);
		gc.fillRect(this.getPosition().getX(), this.getPosition().getY(), 50.0, 50.0);
		if(equipment != null) {
			equipment.draw(gc);
		}
		
	}

	@Override
	public void attack() {
		if(equipment != null) equipment.attack();
	}
	
	public static Player getPlayer() {
		return player;
	}
	
	public BaseWeapon getEquipment() {
		return equipment;
	}

	public void setEquipment(BaseWeapon equipment) {
		this.equipment = equipment;
	}

	@Override
	public boolean onCooldown() {
		long currentTime = System.currentTimeMillis();
		if(currentTime - lastClickTime > cooldownTime) {
			lastClickTime = currentTime;
			return false;
		}else {
			return true;
		}
	}

}
