package equipment;

import Math.Point;
import entity.Player;
import javafx.scene.canvas.GraphicsContext;
import logic.Hitbox;

public abstract class BaseWeapon {
	private double attackDamage, attackSpeed;
	private Point offsetPosition;
	
	public Point getPostion() {
		//Point Player
		Point pp = Player.getPlayer().getPosition();
		double px = pp.getX(), py = pp.getY();
		
		Point p = new Point(px + offsetPosition.getX(), py + offsetPosition.getY());
		return p;
	}

	public Point getResolutionPostion(){
		Point pp = Player.getPlayer().getResolutionPosition();
		double px = pp.getX(), py = pp.getY();
		
		Point p = new Point(px + offsetPosition.getX(), py + offsetPosition.getY());
		return p;
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
	public Point getOffsetPosition() {
		return offsetPosition;
	}
	public void setOffsetPosition(Point offsetPosition) {
		this.offsetPosition = offsetPosition;
	}

	

}
