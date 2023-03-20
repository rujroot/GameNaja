package entity;

public class Skeleton extends Entity {
	private int protection;

	public Skeleton(String name, int hp, int atk, int def, int spd,int poisonStatus) {
		super(name, hp, atk, def, spd, poisonStatus);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void attack(Entity Enemy) {
		// TODO Auto-generated method stub
		Enemy.setHp(Enemy.getHp()-this.getAtk());
	}

	public int getProtection() {
		return protection;
	}

	public void setProtection(int protection) {
		this.protection = protection;
	}
	
	

}
