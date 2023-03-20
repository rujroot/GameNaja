package entity;

public class Goblin extends Entity {
	private int swiftness;

	public Goblin(String name, int hp, int atk, int def, int spd,int poisonStatus,int swiftness) {
		super(name, hp, atk, def, spd, poisonStatus);
		// TODO Auto-generated constructor stub
		this.setSwiftness(swiftness);
	}

	@Override
	public void attack(Entity Enemy) {
		// TODO Auto-generated method stub
		Enemy.setHp(Enemy.getHp()-this.getAtk());
	}

	public int getSwiftness() {
		return swiftness;
	}

	public void setSwiftness(int swiftness) {
		this.swiftness = swiftness;
	}
	
	

}
