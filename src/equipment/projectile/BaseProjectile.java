package equipment.projectile;

import data.BaseObject;
import data.Point;
import entity.Entity;
import javafx.scene.canvas.GraphicsContext;
import logic.Hitbox;

public abstract class BaseProjectile extends BaseObject {
	
	private double damage;
	private Point speed;
	
	public abstract void draw(GraphicsContext gc);
	
	public BaseProjectile(double width, double height, double damage, Point speed, Point Position) {
		super(Position, width, height);
		this.setDamage(damage);
		this.setSpeed(speed);
		this.setPosition(Position);
	}

	public void update(){
		Point speed = this.getSpeed();
		Point pos = this.getPosition();
		Point newPos = new Point(pos.getX() + speed.getX(), pos.getY() + speed.getY());
		this.setPosition(newPos);
	}

	public boolean hit(Entity entity){
		Hitbox A = new Hitbox(this.getPosition(), this.getWidth(), this.getHeight());
		Hitbox B = new Hitbox(entity.getPosition(), entity.getWidth(), entity.getHeight());
		return A.isIntersect(B);
	}

	public double getDamage() {
		return damage;
	}

	public void setDamage(double damage) {
		this.damage = damage;
	}

	public Point getSpeed() {
		return speed;
	}

	public void setSpeed(Point speed) {
		this.speed = speed;
	}
	
}
