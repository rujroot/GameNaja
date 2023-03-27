package entity;

import equipment.BaseWeapon;
import equipment.Bow;
import input.InputUtility;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;

public class Player extends Entity{
	
	public static Player player;
	
	private BaseWeapon equipment;

	public Player(String name, double hp, double atk, double def, double spd, double poisonStatus) {
		super(name, hp, atk, def, spd, poisonStatus);
		player = this;
		this.setEquipment(new Bow(2));
	}
	
	public void update() {
		if (InputUtility.getKeyPressed(KeyCode.W)) {
			this.setY(this.getY() - 1);
		} if (InputUtility.getKeyPressed(KeyCode.A)) {
			this.setX(this.getX() - 1);
		} if (InputUtility.getKeyPressed(KeyCode.D)) {
			this.setX(this.getX() + 1);
		} if (InputUtility.getKeyPressed(KeyCode.S)) {
			this.setY(this.getY() + 1);
		} if(InputUtility.getKeyPressed(KeyCode.SPACE) && equipment != null) {
			equipment.attack();
		}
	}

	@Override
	public void draw(GraphicsContext gc) {
		gc.setFill(Color.TAN);
		gc.fillRect(this.getX(), this.getY(), 50.0, 50.0);
		gc.setFill(Color.YELLOW);
		
		if(equipment != null) {
			equipment.draw(gc);
		}
		
	}

	@Override
	public void attack(Entity Enemy) {
		
		
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

}
