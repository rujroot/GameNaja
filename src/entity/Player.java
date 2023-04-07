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
			Zombie zombie = new Zombie("Zombie", 50, 50, new DataEntity(1, 1, 1, 4.5));
			logic.addNewObject(zombie);
		}
	}
	
	public boolean isLegalMove(double moveX, double moveY) {
		int currLevel = GenerateDungeon.getCurrLevel();
		ArrayList<Room> level = GenerateDungeon.getContainer().get(currLevel);
		
		Point posPlayer = this.getPosition();
		
		double sum = 0;
		
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

			// calculate the coordinates of the intersection rectangle
			double xLeft = Math.max(rect1X1, X1);
			double yTop = Math.max(rect1Y1, Y1);
			double xRight = Math.min(rect1X2, X2);
			double yBottom = Math.min(rect1Y2, Y2);
			
			// calculate the area of the intersection rectangle
			double intersectionArea = 0.0;
			if (xRight > xLeft && yBottom > yTop) {
				intersectionArea = (xRight - xLeft) * (yBottom - yTop);
			}
			
			sum += intersectionArea;
		}

		return Math.round(sum) == Math.round(this.getWidth() * this.getHeight());   
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
