package entity;

public class Zombie extends Entity {
	private int poisonDamage;

	public Zombie(String name, int hp, int atk, int def, int spd,int poisonStatus,int poisonDamage) {
		super(name, hp, atk, def, spd,poisonStatus);
		// TODO Auto-generated constructor stub
		this.setPoisonDamage(poisonDamage);
	}

	@Override
	public void attack(Entity Enemy) {
		// TODO Auto-generated method stub
		Enemy.setHp(Enemy.getHp()-this.getAtk());
		Enemy.setPoisonStatus(Enemy.getPoisonStatus()+this.getPoisonDamage());
	}

	public int getPoisonDamage() {
		return poisonDamage;
	}

	public void setPoisonDamage(int poisonDamage) {
		this.poisonDamage = poisonDamage;
	}
	
	

}
