package entity;

public class Goblin extends Entity {

	public Goblin(String name, int hp, int atk, int def, int spd,int poisonStatus) {
		super(name, hp, atk, def, spd, poisonStatus);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void attack(Entity Enemy) {
		// TODO Auto-generated method stub
		Enemy.setHp(Enemy.getHp()-this.getAtk());
	}

}
