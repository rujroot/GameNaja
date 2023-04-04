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
	private double lastClickTime = 0, cooldownTime = 1000;
	private Point resolutionPosition;

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
		
		boolean TL = false, TR = false, BL = false, BR = false;
		
		for(Room room : level) {
			
			Point posRoom = room.getPosition();
			
			double rect1X1 = posRoom.getX();
		    double rect1Y1 = posRoom.getY();
		    double rect1X2 = posRoom.getX() + room.getWidth();
		    double rect1Y2 = posRoom.getY() + room.getHeight();
		    
		    double X1 = posPlayer.getX() + moveX;
		    double Y1 = posPlayer.getY() + moveY;
		    double X2 = posPlayer.getX() + this.getWidth() + moveX;
		    double Y2 = posPlayer.getY() + this.getHeight() + moveY;
	
		    if( (X1 >= rect1X1 && X1 <= rect1X2) && (Y1 >= rect1Y1 && Y1 <= rect1Y2) ) TL = TL || true;
		    if( (X2 >= rect1X1 && X2 <= rect1X2) && (Y1 >= rect1Y1 && Y1 <= rect1Y2) ) TR = TR || true;
		    if( (X1 >= rect1X1 && X1 <= rect1X2) && (Y2 >= rect1Y1 && Y2 <= rect1Y2) ) BL = BL || true;
		    if( (X2 >= rect1X1 && X2 <= rect1X2) && (Y2 >= rect1Y1 && Y2 <= rect1Y2) ) BR = BR || true;
	
		}
		//System.out.print(TL);
		//System.out.print(TR);
		//System.out.print(BL);
		//System.out.print(BR);
		return TL && TR && BL && BR;
	    
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

	public Point getResolutionPosition() {
		return resolutionPosition;
	}

	public void setResolutionPosition(Point resolutionPosition) {
		this.resolutionPosition = resolutionPosition;
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
