package entity;

import data.DataEntity;
import data.Point;
import logic.RenderableHolder;

public class Demon extends Monster {
	private int magicAttack;
	
	public Demon(String name, double width, double height, DataEntity data) {
		super(name, width, height, data);
		this.setMagicAttack(magicAttack);
		this.setImage(RenderableHolder.demon);
	}

	@Override
	public void attack() {
		//Enemy.setHp(Enemy.getHp()-this.getAtk());
		super.attack();
	}

	public int getMagicAttack() {
		return magicAttack;
	}


	public void setMagicAttack(int magicAttack) {
		this.magicAttack = magicAttack;
	}
	
	
}
