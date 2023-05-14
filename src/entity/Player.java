package entity;

import data.BaseObject;
import data.DataEntity;
import data.Point;
import drawing.Description;
import drawing.GameScreen;
import dungeon.GenerateDungeon;
import equipment.BaseWeapon;
import equipment.Pickaxe;
import equipment.Shield;
import equipment.*;
import input.InputUtility;
import inventory.Inventory;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.WritableImage;
import javafx.scene.input.KeyCode;
import logic.Cooldownable;
import logic.GameLogic;
import logic.Main;
import logic.RenderableHolder;
import scene.SceneController;

public class Player extends Entity implements Cooldownable {

	public static Player player;
	private double cooldownTime = 2000;
	private double lastClickTime = 0;
	private double money = 0;

	public static Inventory inventory;

	private BaseWeapon equipment;
	private Point resolutionPosition;
	private WritableImage image = new WritableImage(RenderableHolder.Tileset.getPixelReader(), 960, 944, 59, 79);
	private Description description, descriptionMoney, descriptionFloor;

	public Player(String name, double Height, double Width, DataEntity data) {
		super(name, Height, Width, data);
		this.setWidth(image.getWidth());
		this.setHeight(image.getHeight());
		player = this;
	}

	public double getMouseAngle() {
		// Attack Object section
		Point pos = this.getPosition();
		Point res = GameScreen.resolution;
		double mouseX = InputUtility.mouseX, mouseY = InputUtility.mouseY;

		double posMouseX = pos.getX() + mouseX - res.getX() / 2;
		double posMouseY = pos.getY() + mouseY - res.getY() / 2;

		double startAt = Math.atan2(posMouseY - (pos.getY() + this.getHeight() / 2),
				posMouseX - (pos.getX() + this.getWidth() / 2));
		if (startAt < 0) {
			startAt += 2 * Math.PI;
		}
		return 360 - Math.toDegrees(startAt);
	}

	public void update() {
		// Move Section
		DataEntity data = this.getData();

		if (InputUtility.getKeyPressed(KeyCode.W) && isLegalMove(0, -1)) {
			this.move(0, -data.getSpd());
		}
		if (InputUtility.getKeyPressed(KeyCode.A) && isLegalMove(-1, 0)) {
			this.move(-data.getSpd(), 0);
		}
		if (InputUtility.getKeyPressed(KeyCode.D) && isLegalMove(1, 0)) {
			this.move(data.getSpd(), 0);
		}
		if (InputUtility.getKeyPressed(KeyCode.S) && isLegalMove(0, 1)) {
			this.move(0, data.getSpd());
		}
		if (InputUtility.getKeyPressed(KeyCode.DIGIT1)) {
			inventory.selectIndex(0);
			this.setEquipment(inventory.getObject(0));
		}
		if (InputUtility.getKeyPressed(KeyCode.DIGIT2)) {
			inventory.selectIndex(1);
			this.setEquipment(inventory.getObject(1));
		}
		if (InputUtility.getKeyPressed(KeyCode.DIGIT3)) {
			this.setEquipment(inventory.getObject(2));
			inventory.selectIndex(2);
		}
		if (InputUtility.getKeyPressed(KeyCode.DIGIT4)) {
			this.setEquipment(inventory.getObject(3));
			inventory.selectIndex(3);
		}
		if (InputUtility.getKeyPressed(KeyCode.DIGIT5)) {
			inventory.selectIndex(4);
			this.setEquipment(inventory.getObject(4));
		}
		if (InputUtility.getKeyPressed(KeyCode.DIGIT6)) {
			inventory.selectIndex(5);
			this.setEquipment(inventory.getObject(5));
		}
		if (InputUtility.getKeyPressed(KeyCode.DIGIT7)) {
			inventory.selectIndex(6);
			this.setEquipment(inventory.getObject(6));
		}
		if (InputUtility.getKeyPressed(KeyCode.DIGIT8)) {
			inventory.selectIndex(7);
			this.setEquipment(inventory.getObject(7));
		}
		if (InputUtility.getKeyPressed(KeyCode.DIGIT9)) {
			inventory.selectIndex(8);
			this.setEquipment(inventory.getObject(8));
		}
		if (InputUtility.getKeyPressed(KeyCode.F)) {
			this.getData().setHp(this.getData().getHp() + 10);
		}
		if (InputUtility.getKeyPressed(KeyCode.R)) {
			System.out.println(inventory.getUI().getSelectIndex());
			inventory.removeItem(inventory.getUI().getSelectIndex());
			this.setEquipment(inventory.getObject(inventory.getUI().getSelectIndex()));
		}
		// Action Section
		if ((InputUtility.getKeyPressed(KeyCode.SPACE) || InputUtility.isLeftClickTriggered())) {
			attack();
		}
		if (InputUtility.getKeyPressed(KeyCode.J) && !onCooldown()) {
			GameLogic logic = Main.getLogic();
			// Data : hp atk def spd
			Demon demon = new Demon("Demon", 70, 70, new DataEntity(100, 10, 10, 6.5));
			logic.addObject(demon);
			Zombie zombie = new Zombie("Zombie", 50, 50, new DataEntity(100, 1, 1, 4.5));
			Skeleton skeleton = new Skeleton("Skeleton", 50, 50, new DataEntity(100, 1, 1, 4.5));
			Goblin goblin = new Goblin("Goblin", 40, 40, new DataEntity(100, 10, 10, 6.5));
			Demon demon2 = new Demon("Demon", 70, 70, new DataEntity(100, 10, 10, 6.5));
			Slime slime = new Slime("Slime", 30, 30, new DataEntity(100, 10, 10, 6.5));
			logic.addObject(zombie);
			logic.addObject(skeleton);
			logic.addObject(goblin);
			logic.addObject(demon2);
			logic.addObject(slime);
		}
		if (InputUtility.getKeyPressed(KeyCode.Z) && !onCooldown()) {
			SceneController.dungeon.increaseFloor(1);
			Main.getLogic().nextFloor();
		}
	}

	public void initInventory() {
		// create inventory
		inventory = new Inventory();
		Main.getLogic().addObject(inventory);

		inventory.addItem(new Pickaxe(30.0, 10.0));
		inventory.addItem(new Shield(30.0, 10.0));
		inventory.addItem(new Punch(30.0, 10.0));
		inventory.addItem(new Bow(30.0, 10.0));
		inventory.addItem(new Wand(30.0, 10.0));
		inventory.selectIndex(0);
		this.setEquipment(inventory.getObject(0));

		// create description above inventory
		Point resolution = GameScreen.getResolution();
		Point basePoint = new Point(-resolution.getX() / 4 + 100, resolution.getY() / 3 - 25);
		description = new Description(basePoint, 400, 100, this);

		descriptionMoney = new Description(new Point(-resolution.getX() / 4 + 600, resolution.getY() / 3 - 25), 100.0,
				100.0, this);
		descriptionFloor = new Description(new Point(-resolution.getX() / 4 + 600, resolution.getY() / 3 - 65), 100.0,
				100.0, this);

	}

	@Override
	public void draw(GraphicsContext gc) {
		Point pos = this.getPosition();
		gc.drawImage(image, pos.getX(), pos.getY(), image.getWidth(), image.getHeight());
		this.drawHP(gc);
		description.draw(gc);
		displayMoney(gc);
		displayFloor(gc);

		if (equipment != null) {
			equipment.draw(gc);
		}

	}

	@Override
	public void attack() {
		if (equipment != null)
			equipment.attack();
	}

	public void displayMoney(GraphicsContext gc) {
		descriptionMoney.setText(Double.toString(money) + "$");
		descriptionMoney.draw(gc);
	}
	
	public void displayFloor(GraphicsContext gc) {
		descriptionFloor.setText("Floor " + Integer.toString(GenerateDungeon.getCurrLevel()));
		descriptionFloor.draw(gc);
	}

	public static Player getPlayer() {
		return player;
	}

	public BaseWeapon getEquipment() {
		return equipment;
	}

	public void setEquipment(BaseObject equipment) {
		if (equipment instanceof BaseWeapon) {
			this.equipment = (BaseWeapon) equipment;
		} else {
			this.equipment = null;
		}
	}

	public Point getResolutionPosition() {
		return resolutionPosition;
	}

	public void setResolutionPosition(Point resolutionPosition) {
		this.resolutionPosition = resolutionPosition;
	}

	public Description getDescription() {
		return description;
	}

	public void setDescription(String text) {
		this.description.setText(text);
	}

	public double getMoney() {
		return money;
	}

	public void setMoney(double money) {
		this.money = money;
	}

	public void addMoney(double money) {
		this.money += money;
	}

	public Inventory getInventory() {
		return inventory;
	}

	@Override
	public boolean onCooldown() {
		long currentTime = System.currentTimeMillis();
		if (currentTime - lastClickTime > cooldownTime) {
			lastClickTime = currentTime;
			return false;
		} else {
			return true;
		}
	}
}
