package entity;

import java.util.ArrayList;

import Data.BaseObject;
import Data.DataEntity;
import Data.Point;
import Dungeon.GenerateDungeon;
import Dungeon.Room;
import equipment.BaseWeapon;
import equipment.Bow;
import input.InputUtility;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import logic.Cooldownable;
import logic.GameLogic;
import logic.Hitbox;
import logic.Main;
import ore.BaseOre;

public class Player extends Entity implements Cooldownable{
	
	public static Player player;
	
	private BaseWeapon equipment;
	private double lastClickTime = 0, cooldownTime = 1000;
	private Point resolutionPosition;

	public Player(String name, double Height, double Width, DataEntity data) {
		super(name, Height, Width, data);
		player = this;
		this.setEquipment(new Bow(30.0, 10.0, 2));
	}
	
	public void move(double moveX, double moveY){
		Point pos = this.getPosition();

		for(double x = moveX; x > 0; --x){
			if(isLegalMove(x, 0)){
				pos.setX(pos.getX() + x);
				return;
			}
		}

		for(double x = moveX; x < 0; ++x){
			if(isLegalMove(x, 0)){
				pos.setX(pos.getX() + x);
				return;
			}
		}

		for(double y = moveY; y > 0; --y){
			if(isLegalMove(0, y)){
				pos.setY(pos.getY() + y);
				return;
			}
		}

		for(double y = moveY; y < 0; ++y){
			if(isLegalMove(0, y)){
				pos.setY(pos.getY() + y);
				return;
			}
		}
	}

	public void update() {
		//Move Section
		DataEntity data = this.getData();
		
		if (InputUtility.getKeyPressed(KeyCode.W) && isLegalMove(0, -1) ) {
			this.move(0, -data.getSpd());
		} if (InputUtility.getKeyPressed(KeyCode.A) && isLegalMove(-1, 0) ) {
			this.move(-data.getSpd(), 0);
		} if (InputUtility.getKeyPressed(KeyCode.D) && isLegalMove(1, 0) ) {
			this.move(data.getSpd(), 0);
		} if (InputUtility.getKeyPressed(KeyCode.S) && isLegalMove(0, 1) ) {
			this.move(0, data.getSpd());
		} 
		
		
		//Action Section
		if(InputUtility.getKeyPressed(KeyCode.SPACE)) {
			attack();
		} if (InputUtility.getKeyPressed(KeyCode.J) && !onCooldown()) {
			GameLogic logic = Main.getLogic();
			Zombie zombie = new Zombie("Zombie", 50, 50, new DataEntity(10, 1, 1, 4.5));
			logic.addObject(zombie);
		} if (InputUtility.getKeyPressed(KeyCode.Z)){
			Main.getLogic().nextFloor();
		}
	}
	
	public boolean isLegalMove(double moveX, double moveY) {
		int currLevel = GenerateDungeon.getCurrLevel();
		ArrayList<Room> level = GenerateDungeon.getContainer().get(currLevel);
		
		Point posPlayer = this.getPosition();
		Point newPosPlayer = new Point(posPlayer.getX() + moveX, posPlayer.getY() + moveY);
		
		double sum = 0;
		
		for(Room room : level) {
			
			Point posRoom = room.getPosition();
			
			double rect1X1 = posRoom.getX();
		    double rect1Y1 = posRoom.getY();
		    double rect1X2 = posRoom.getX() + room.getWidth();
		    double rect1Y2 = posRoom.getY() + room.getHeight();
		    
		    double X1 = newPosPlayer.getX();
		    double Y1 = newPosPlayer.getY();
		    double X2 = newPosPlayer.getX() + this.getWidth();
		    double Y2 = newPosPlayer.getY() + this.getHeight();

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

		ArrayList<BaseObject> allObject = Main.getLogic().getGameObjectContainer();
		for(BaseObject object : allObject){
			if(object instanceof BaseOre){
				Hitbox A = new Hitbox(newPosPlayer, this.getWidth(), this.getHeight());
				Hitbox B = new Hitbox(object.getPosition(), object.getWidth(), object.getHeight());
				if(A.isIntersect(B)) return false;
			}
		}

		return Math.round(sum) == Math.round(this.getWidth() * this.getHeight());   
	}

	@Override
	public void draw(GraphicsContext gc) {
		Point pos = this.getPosition();
		gc.setFill(Color.TAN);
		gc.fillRect(pos.getX(), pos.getY(), this.getWidth(), this.getHeight());
		this.drawHP(gc);
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
