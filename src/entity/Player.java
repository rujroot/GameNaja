package entity;

import java.util.ArrayList;

import Dungeon.GenerateDungeon;
import Dungeon.Room;
import Math.DataEntity;
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

	public Player(String name, double Height, double Width, DataEntity data) {
		super(name, Height, Width, data);
		player = this;
		this.setEquipment(new Bow(2));
	}
	
	public void update(GraphicsContext gc) {
		//Move Section
		Point pos = this.getPosition();
		DataEntity data = this.getData();
		
		if (InputUtility.getKeyPressed(KeyCode.W) && isLegalMove(0, -data.getSpd()) ) {
			pos.setY(pos.getY() - data.getSpd());
			gc.translate(0, data.getSpd());
		} if (InputUtility.getKeyPressed(KeyCode.A) && isLegalMove(-data.getSpd(), 0) ) {
			pos.setX(pos.getX() - data.getSpd());
			gc.translate(data.getSpd(), 0);
		} if (InputUtility.getKeyPressed(KeyCode.D) && isLegalMove(data.getSpd(), 0) ) {
			pos.setX(pos.getX() + data.getSpd());
			gc.translate(-data.getSpd(), 0);
		} if (InputUtility.getKeyPressed(KeyCode.S) && isLegalMove(0, data.getSpd()) ) {
			pos.setY(pos.getY() + data.getSpd());
			gc.translate(0, -data.getSpd());
		} 
		
		
		//Action Section
		if(InputUtility.getKeyPressed(KeyCode.SPACE)) {
			attack();
		} if (InputUtility.getKeyPressed(KeyCode.J) && !onCooldown()) {
			GameLogic logic = Main.getLogic();
			Zombie zombie = new Zombie("Zombie", 50, 50, new DataEntity(1, 1, 1, 1));
			logic.addNewObject(zombie);
		}
	}
	
	public boolean isLegalMove(double moveX, double moveY) {
		int currLevel = GenerateDungeon.getCurrLevel();
		ArrayList<Room> level = GenerateDungeon.getContainer().get(currLevel);
		
		Point posPlayer = this.getPosition();
		
		for(Room room : level) {
			
			Point posRoom = room.getPosition();
			
			double rect2X1 = posRoom.getX();
		    double rect2Y1 = posRoom.getY();
		    double rect2X2 = posRoom.getX() + room.getWidth();
		    double rect2Y2 = posRoom.getY() + room.getHeight();
		    
		    double rect1X1 = posPlayer.getX() + moveX;
		    double rect1Y1 = posPlayer.getY() + moveY;
		    double rect1X2 = posPlayer.getX() + this.getWidth();
		    double rect1Y2 = posPlayer.getY() + this.getHeight();
	
		    boolean isTopLeftInside = rect2X1 <= rect1X1 && rect2Y1 <= rect1Y1;
		    boolean isBottomRightInside = rect2X2 >= rect1X2 && rect2Y2 >= rect1Y2;
	
		    if( isTopLeftInside && isBottomRightInside) return true;
		}
		System.out.println("Out of range");
		return false;
	    
	}

	@Override
	public void draw(GraphicsContext gc) {
		Point pos = this.getPosition();
		gc.setFill(Color.TAN);
		gc.fillRect(pos.getX(), pos.getY(), this.getWidth(), this.getHeight());
		
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
