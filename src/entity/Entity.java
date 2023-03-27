package entity;

import Math.Point;
import logic.IRenderable;

public abstract class Entity implements IRenderable {
	private String name;
	private double hp, atk, def, spd, poisonStatus;
	
	private Point position;
	private int z;
	private boolean visible, destroyed;
	
	
	public Entity(String name,double hp,double atk,double def,double spd,double poisonStatus){
		this.setName(name);
		this.setHp(hp);
		this.setAtk(atk);
		this.setDef(def);
		this.setSpd(spd);
		this.setPoisonStatus(poisonStatus);
		this.setPosition(new Point(Math.random() * 640 , Math.random() * 480));
		this.visible = true;
		this.destroyed = false;
	}
	
	public abstract void attack();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public double getHp() {
		return hp;
	}

	public void setHp(double hp) {
		this.hp = hp;
	}

	public double getAtk() {
		return atk;
	}

	public void setAtk(double atk) {
		this.atk = atk;
	}

	public double getDef() {
		return def;
	}

	public void setDef(double def) {
		this.def = def;
	}

	public double getSpd() {
		return spd;
	}

	public void setSpd(double spd) {
		this.spd = spd;
	}

	public double getPoisonStatus() {
		return poisonStatus;
	}

	public void setPoisonStatus(double poisonStatus) {
		this.poisonStatus = poisonStatus;
	}

	public void setDestroyed(boolean destroyed) {
		this.destroyed = destroyed;
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
