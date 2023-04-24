package entity;

import Data.DataEntity;
import Data.Point;
import drawing.GameScreen;
import equipment.Axe;
import equipment.BaseWeapon;
import equipment.Bow;
import equipment.Knife;
import equipment.Pickaxe;
import equipment.Punch;
import equipment.Shield;
import equipment.Spear; 
import equipment.Sword;
import equipment.Wand;
import input.InputUtility;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.WritableImage;
import javafx.scene.input.KeyCode;
import logic.Cooldownable;
import logic.GameLogic;
import logic.Main;
import logic.RenderableHolder;

public class Player extends Entity implements Cooldownable{
	
	public static Player player; 
	
	private BaseWeapon equipment;
	private double lastClickTime = 0, cooldownTime = 1000;
	private Point resolutionPosition;
	private WritableImage image = new WritableImage(RenderableHolder.Tileset.getPixelReader(), 960, 944, 59, 79);
	private int mutliply = 1;

	public Player(String name, double Height, double Width, DataEntity data) {
		super(name, Height, Width, data);
		this.setWidth(image.getWidth() * mutliply);
		this.setHeight(image.getHeight() * mutliply);
		player = this;
		//this.setEquipment(new Pickaxe(30.0, 10.0));
		//this.setEquipment(new Bow(30.0, 10.0, 2));
		
		this.setEquipment(new Knife(30.0, 10.0,10.0,500,60));
	}

	public double getMouseAngle(){
		// Attack Object section
        Point pos = this.getPosition();
        Point res = GameScreen.resolution;
		double mouseX = InputUtility.mouseX, mouseY = InputUtility.mouseY;

        double posMouseX = pos.getX() + mouseX - res.getX() / 2;
        double posMouseY = pos.getY() + mouseY - res.getY() / 2;

        double startAt = Math.atan2(posMouseY - (pos.getY() + this.getHeight() / 2), posMouseX - (pos.getX() + this.getWidth() / 2));
        if (startAt < 0) {
            startAt += 2 * Math.PI;
        }
        return 360 - Math.toDegrees(startAt);
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
		} if (InputUtility.getKeyPressed(KeyCode.DIGIT1)) {
			this.setEquipment(new Bow(30.0, 10.0, 2));
		} if (InputUtility.getKeyPressed(KeyCode.DIGIT2)) {
			this.setEquipment(new Knife(30.0, 10.0,10.0,500,60));
		} if (InputUtility.getKeyPressed(KeyCode.DIGIT3)) {
			this.setEquipment(new Sword(30.0, 10.0,10.0,500,60));
		} if (InputUtility.getKeyPressed(KeyCode.DIGIT4)) {
			this.setEquipment(new Wand(30.0, 10.0, 2,10));
		} if (InputUtility.getKeyPressed(KeyCode.DIGIT5)) {
			this.setEquipment(new Pickaxe(30.0, 10.0));
		}
		
		
		//Action Section
		if((InputUtility.getKeyPressed(KeyCode.SPACE)||InputUtility.isLeftClickTriggered()) && !onCooldown()) {
			attack();
		} if (InputUtility.getKeyPressed(KeyCode.J) && !onCooldown()) {
			GameLogic logic = Main.getLogic();
			Zombie zombie = new Zombie("Zombie", 50, 50, new DataEntity(10, 1, 1, 4.5));
			logic.addObject(zombie);
		} if (InputUtility.getKeyPressed(KeyCode.Z)){
			Main.getLogic().nextFloor();
		} if (InputUtility.getKeyPressed(KeyCode.E)){
			Main.getLogic().playerinteraction();
		}
	}
	
	@Override
	public void draw(GraphicsContext gc) {
		Point pos = this.getPosition();
		gc.drawImage(image, pos.getX(), pos.getY(), image.getWidth() * mutliply, image.getHeight() * mutliply);
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
