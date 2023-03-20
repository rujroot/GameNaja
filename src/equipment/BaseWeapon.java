package equipment;

import entity.Entity;
import logic.Hitbox;

public abstract class BaseWeapon {
	private double attackDamage;
	private double attackSpeed;
	private Hitbox attackHitBox;
	
	public abstract void attack(Entity entity);
	
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
