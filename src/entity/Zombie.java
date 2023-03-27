package entity;

import javafx.scene.canvas.GraphicsContext;

public class Zombie extends Entity {
	private int poisonDamage;

	public Zombie(String name, int hp, int atk, int def, int spd,int poisonStatus,int poisonDamage) {
		super(name, hp, atk, def, spd,poisonStatus);
		this.setPoisonDamage(poisonDamage);
	}

	@Override
	public void attack(Entity Enemy) {
		Enemy.setHp(Enemy.getHp()-this.getAtk());
		Enemy.setPoisonStatus(Enemy.getPoisonStatus()+this.getPoisonDamage());
	}

	public int getPoisonDamage() {
		return poisonDamage;
	}

	public void setPoisonDamage(int poisonDamage) {
		this.poisonDamage = poisonDamage;
	}

	@Override
	public void draw(GraphicsContext gc) {
		
		
	}

	

}
