package equipment;

public abstract class BaseWeapon {
	private int attack;
	
	public BaseWeapon(int attack) {
		this.setAttack(attack);
	}

	public int getAttack() {
		return attack;
	}

	public void setAttack(int attack) {
		this.attack = attack;
	}
}
