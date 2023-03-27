package entity;

import Math.Point;
import logic.IRenderable;

public abstract class Entity implements IRenderable {
	private String name;
	private int hp, atk, def, spd, poisonStatus;
	
	private Point position;
	private int z;
	private boolean visible, destroyed;
	
	
	public Entity(String name,int hp,int atk,int def,int spd,int poisonStatus){
		this.setName(name);
		this.setHp(hp);
		this.setAtk(atk);
		this.setDef(def);
		this.setSpd(spd);
		this.setPoisonStatus(poisonStatus);
	}
	
	public abstract void attack(Entity Enemy);

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getHp() {
		return hp;
	}

	public void setHp(int hp) {
		this.hp = hp;
	}

	public int getAtk() {
		return atk;
	}

	public void setAtk(int atk) {
		this.atk = atk;
	}

	public int getDef() {
		return def;
	}
	
	public void setDef(int def) {
		this.def = def;
	}

	public int getSpd() {
		return spd;
	}

	public void setSpd(int spd) {
		this.spd = spd;
	}

	public int getPoisonStatus() {
		return poisonStatus;
	}

	public void setPoisonStatus(int poisonStatus) {
		this.poisonStatus = poisonStatus;
	}	
	
	@Override
	public boolean isDestroyed(){
		return destroyed;
	}
	
	@Override
	public boolean isVisible(){
		return visible;
	}
	
	@Override
	public int getZ(){
		return z;
	}

	public Point getPosition() {
		return position;
	}

	public void setPosition(Point position) {
		this.position = position;
	}
	
	
}
