package equipment.projectile;

import Math.Point;
import javafx.scene.canvas.GraphicsContext;

public abstract class BaseProjectile {
	
	private double damage;
	private Point speed;
	private Point Position;
	
	public abstract void draw(GraphicsContext gc);
	
	public BaseProjectile(double damage, Point speed, Point Position) {
		this.setDamage(damage);
		this.setSpeed(speed);
		this.setPosition(Position);
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

	public Point getPosition() {
		return Position;
	}

	public void setPosition(Point position) {
		Position = position;
	}
	
}
