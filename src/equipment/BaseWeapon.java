package equipment;

import data.BaseObject;
import data.Point;
import entity.Player;
import javafx.scene.canvas.GraphicsContext;
import logic.Hitbox;

public abstract class BaseWeapon extends BaseObject {
	private double attackDamage, attackSpeed;
	private Point offsetPosition = new Point(30.0, 20.0);
	
	@Override
	public Point getPosition() {
		//Point Player
		Point pp = Player.getPlayer().getPosition();
		double px = pp.getX(), py = pp.getY();
		
		Point p = new Point(px + offsetPosition.getX(), py + offsetPosition.getY());
		return p;
	}

	public Point getPlayerPosition() {
		return Player.getPlayer().getPosition();
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
	
	public BaseWeapon(double width, double height, double attackDamage, double attackSpeed) {
		super(new Point(0, 0), width, height);
		this.setAttackDamage(attackDamage);
		this.setAttackSpeed(attackSpeed);
	}
	
	public BaseWeapon(double width, double height, double attackDamage) {
		super(new Point(0, 0), width, height);
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
