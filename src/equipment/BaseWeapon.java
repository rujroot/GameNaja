package equipment;

import Math.Point;
import entity.Player;
import javafx.scene.canvas.GraphicsContext;
import logic.Hitbox;

public abstract class BaseWeapon {
	private double attackDamage;
	private Point position;
	private double attackSpeed;
	
	public Point getPostion() {
		
		//Point Player
		Point pp = Player.getPlayer().getPosition();
		double px = pp.getX(), py = pp.getY();
		
		Point p = new Point(px + position.getX(), py + position.getY());
		return p;
	}
	public void setPostion(Point position) {
		this.position = position;
	}

	private Hitbox attackHitBox;
	
	public abstract void attack();
	public abstract void draw(GraphicsContext gc);
	
	public BaseWeapon(int attackDamage, int attackSpeed) {
		this.setAttackDamage(attackDamage);
		this.setAttackSpeed(attackSpeed);
	}
	
	public BaseWeapon(int attackDamage) {
		this.setAttackDamage(attackDamage);
		this.setAttackSpeed(1);
	}

	public double getAttackDamage() {
		return attackDamage;
	}

	public void setAttackDamage(double attackDamage) {
		this.attackDamage = attackDamage;
	}

	public double getAttackSpeed() {
		return attackSpeed;
	}

	public void setAttackSpeed(double attackSpeed) {
		this.attackSpeed = attackSpeed;
	}

	public Hitbox getAttackHitBox() {
		return attackHitBox;
	}

	public void setAttackHitBox(Hitbox attackHitBox) {
		this.attackHitBox = attackHitBox;
	}

	

}
