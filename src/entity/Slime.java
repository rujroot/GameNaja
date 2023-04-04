package entity;

public class Slime extends Entity{

	public Slime(String name, int hp, int atk, int def, int spd,int poisonDamage) {
		super(name, hp, atk, def, spd, poisonDamage);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void attack(Entity Enemy) {
		// TODO Auto-generated method stub
		Enemy.setHp(Enemy.getHp()-this.getAtk());
	}

}
