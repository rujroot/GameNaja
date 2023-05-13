package entity;

import java.util.ArrayList;

import data.BaseObject;
import data.DataEntity;
import data.Point;
import equipment.BaseWeapon;
import equipment.Knife;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.WritableImage;
import logic.Cooldownable;
import logic.Hitbox;
import logic.RenderableHolder;

public class Npc extends Entity implements Cooldownable {

	private double cooldownTime = 1000;
	private double lastClickTime = 0;

	private WritableImage image = new WritableImage(RenderableHolder.Tileset.getPixelReader(), 644, 964, 59, 59);
	private Entity followEntity;
	private double maxDistance = 200;
	private BaseWeapon equipment;

	public Npc(String name, double width, double height, DataEntity data) {
		super(name, width, height, data);
		this.setWidth(image.getWidth());
		this.setHeight(image.getHeight());

		this.setEquipment(new Knife(10, 10));
	}

	@Override
	public void draw(GraphicsContext gc) {
		Point pos = this.getPosition();
		gc.drawImage(image, pos.getX(), pos.getY(), image.getWidth(), image.getHeight());
		this.drawHP(gc);

		if (equipment != null) {
			equipment.draw(gc);
		}
	}

	@Override
	public void attack() {
		if (followEntity.equals(Player.getPlayer()) || onCooldown())
			return;
		equipment.attack();
	}

	public void doBehavior() {
		follow(followEntity);
		attack();
	}

	public void follow(Entity entity) {
		Point pp = entity.getPosition();

		double px = pp.getX(), py = pp.getY();

		Point p = new Point(this.getPosition().getX() - px - 0, this.getPosition().getY() - py - 0);
		double distance = Math.sqrt(p.getX() * p.getX() + p.getY() * p.getY());

		DataEntity data = this.getData();

		if (distance > 0) {
			double mx = -p.getX() / distance * data.getSpd();
			double my = -p.getY() / distance * data.getSpd();
			// Check for obstacles
			if (!this.isLegalMove(mx, my) && !this.isIntersectPlayer()) {
				// Turn 90 degrees
				double newX = -p.getY();
				double newY = p.getX();
				double newDistance = Math.sqrt(newX * newX + newY * newY);
				this.move(newX / newDistance * data.getSpd(), 0);
                this.move(0, newY / newDistance * data.getSpd());
				//this.move(newX / newDistance * data.getSpd(), newY / newDistance * data.getSpd());
			} else {
				// Move towards target
				this.move(-p.getX() / distance * data.getSpd(), 0);
                this.move(0, -p.getY() / distance * data.getSpd());
				//this.move(-p.getX() / distance * data.getSpd(), -p.getY() / distance * data.getSpd());
			}
		}

	}// this.isLegalMove( mx, my)
	
	public boolean isIntersectPlayer() {
		// check 2 rectangle
		Point MonBox = new Point(this.getPosition().getX()-10,this.getPosition().getY()-10);
		Hitbox A = new Hitbox(MonBox, this.getWidth()+20, this.getHeight()+20);
		Hitbox B = new Hitbox(Player.getPlayer().getPosition(), Player.getPlayer().getWidth(), Player.getPlayer().getHeight());
 		return A.isIntersect(B);
	}

	public double distance(Point pos1) {
		Point pos2 = this.getPosition();
		return Math.sqrt(Math.pow(pos1.getX() - pos2.getX(), 2) + Math.pow(pos1.getY() - pos2.getY(), 2));
	}

	public void findNearestMonster(ArrayList<BaseObject> gameObjectContainer) {
		Entity nearestMonster = Player.getPlayer();
		double minDistance = 1e9;

		for (int i = gameObjectContainer.size() - 1; i >= 0; i--) {
			BaseObject object = gameObjectContainer.get(i);
			if (object instanceof Monster && distance(object.getPosition()) <= maxDistance) {
				if (distance(object.getPosition()) <= minDistance) {
					nearestMonster = (Entity) object;
					minDistance = distance(object.getPosition());
				}
			}
		}

		followEntity = nearestMonster;
	}

	public double getEntityAngle() {

		Point pos = this.getPosition();

		double posX = followEntity.getPosition().getX();
		double posY = followEntity.getPosition().getY();
		;

		double startAt = Math.atan2(posY - (pos.getY() + this.getHeight() / 2),
				posX - (pos.getX() + this.getWidth() / 2));
		if (startAt < 0) {
			startAt += 2 * Math.PI;
		}
		return 360 - Math.toDegrees(startAt);
	}

	public Entity getFollowEntity() {
		return followEntity;
	}

	public void setFollowEntity(Entity followEntity) {
		this.followEntity = followEntity;
	}

	public double getMaxDistance() {
		return maxDistance;
	}

	public void setMaxDistance(double maxDistance) {
		this.maxDistance = maxDistance;
	}

	public BaseWeapon getEquipment() {
		return equipment;
	}

	public void setEquipment(BaseWeapon equipment) {
		this.equipment = equipment;
		equipment.setEntity(this);
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
