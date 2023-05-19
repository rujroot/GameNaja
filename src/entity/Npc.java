package entity;

import java.util.ArrayList;

import data.BaseObject;
import data.DataEntity;
import data.Point;
import entity.boss.BossEntity;
import equipment.BaseWeapon;
import equipment.Knife;
import equipment.Melee;
import equipment.Wand;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.WritableImage;
import logic.Cooldownable;
import logic.RenderableHolder;

public class Npc extends Entity implements Cooldownable {

	private double cooldownTime = 1000;
	private double lastClickTime = 0;

	private WritableImage image = new WritableImage(RenderableHolder.Tileset.getPixelReader(), 644, 964, 59, 59);
	private Entity followEntity , targetEntity;
	private double maxDistance = 500;
	private BaseWeapon equipment;

	public Npc(String name, double width, double height, DataEntity data) {
		super(name, width, height, data);
		this.setWidth(image.getWidth());
		this.setHeight(image.getHeight());

		//this.setEquipment(new Knife(10, 10));
		//this.setEquipment(new Wand(10, 10));

		followEntity = Player.getPlayer();
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
		if(equipment == null) {
			follow(followEntity); 
			return;
		}

		if(targetEntity != null && !(targetEntity instanceof Player)){
			if(equipment instanceof Melee)
				follow(targetEntity);
			equipment.attack(); 
			
		} 
		else follow(followEntity);
	}

	public void doBehavior() {
		attack();
	}

	public void follow(Entity entity) {
		if(entity == null) return;

		Point pp = entity.getPosition();

		double px = pp.getX(), py = pp.getY();

		Point p = new Point(this.getPosition().getX() - px - 50, this.getPosition().getY() - py );
		double distance = Math.sqrt(p.getX() * p.getX() + p.getY() * p.getY());

		DataEntity data = this.getData();

		if (distance > 50 && distance < 500) {
			double mx = -p.getX() / distance * data.getSpd();
			double my = -p.getY() / distance * data.getSpd();
			// Check for obstacles
			if (!this.isLegalMove(mx, my)) {
				// Turn 90 degrees
				double newX = -p.getY();
				double newY = p.getX();
				double newDistance = Math.sqrt(newX * newX + newY * newY);
				this.move(newX / newDistance * data.getSpd(), 0);
                this.move(0, newY / newDistance * data.getSpd());
			} else {
				// Move towards target
				this.move(-p.getX() / distance * data.getSpd(), 0);
                this.move(0, -p.getY() / distance * data.getSpd());
			}
		}else if(distance > 1000){
			warpToEntity(entity);
		}

	}

	public void warpToEntity(Entity entity){
		this.getPosition().setX(entity.getPosition().getX());
		this.getPosition().setY(entity.getPosition().getY());
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
			if ((object instanceof Monster || object instanceof BossEntity) && distance(object.getPosition()) <= maxDistance) {
				if (distance(object.getPosition()) <= minDistance) {
					nearestMonster = (Entity) object;
					minDistance = distance(object.getPosition());
				}
			}
		}

		targetEntity = nearestMonster;
	}

	public double getEntityAngle() {

		Point pos = this.getPosition();

		double posX = targetEntity.getPosition().getX();
		double posY = targetEntity.getPosition().getY();

		double startAt = Math.atan2(posY - (pos.getY() + this.getHeight() / 2),
				posX - (pos.getX() + this.getWidth() / 2));
		if (startAt < 0) {
			startAt += 2 * Math.PI;
		}
		return 360 - Math.toDegrees(startAt);
	}

	public Point getEntityVector(){
		Point pos = this.getPosition();

		double posX = targetEntity.getPosition().getX();
		double posY = targetEntity.getPosition().getY();
		Point vector = new Point(posX - pos.getX(), posY - pos.getY());
		vector.unit();

		return vector;
	}

	// getter setter 

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

	public Entity getTargetEntity() {
		return targetEntity;
	}

	public void setTargetEntity(Entity targetEntity) {
		this.targetEntity = targetEntity;
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
