package equipment.projectile;

import Math.Point;
import entity.Entity;
import javafx.scene.canvas.GraphicsContext;
import logic.Hitbox;
import logic.IRenderable;

public abstract class BaseProjectile implements IRenderable {
	
	private double damage, width, height;
	private Point speed;
	private Point Position;
	private int z;
	private boolean visible = true, destroyed = false;
	
	public abstract void draw(GraphicsContext gc);
	
	public BaseProjectile(double damage, Point speed, Point Position) {
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

	public Point getPosition() {
		return Position;
	}

	public void setPosition(Point position) {
		Position = position;
	}

	public double getWidth() {
		return width;
	}

	public void setWidth(double width) {
		this.width = width;
	}

	public double getHeight() {
		return height;
	}

	public void setHeight(double height) {
		this.height = height;
	}

	@Override
	public int getZ() {
		return z;
	}

	@Override
	public boolean isDestroyed() {
		return destroyed;
	}

	@Override
	public boolean isVisible() {
		return visible;
	}

	public void setZ(int z) {
		this.z = z;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	public void setDestroyed(boolean destroyed) {
		this.destroyed = destroyed;
	}

	
	
}
