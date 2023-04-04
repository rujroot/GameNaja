package entity;

public class Demon extends Entity {
	private int magicAttack;
	
	public Demon(String name, int hp, int atk, int def, int spd, int poisonStatus,int magicAttack) {
		super(name, hp, atk, def, spd, poisonStatus);
		// TODO Auto-generated constructor stub
		this.setMagicAttack(magicAttack);
	}
	


	@Override
	public void attack(Entity Enemy) {
		// TODO Auto-generated method stub
		Enemy.setHp(Enemy.getHp()-this.getAtk()-this.getMagicAttack());
		
	}

	public int getMagicAttack() {
		return magicAttack;
	}


	public void setMagicAttack(int magicAttack) {
		this.magicAttack = magicAttack;
	}
	

}
