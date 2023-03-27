package entity;

import Math.Point;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Zombie extends Entity {
	private double poisonDamage;

	public Zombie(String name, double hp, double atk, double def, double spd,double poisonStatus,double poisonDamage) {
		super(name, hp, atk, def, spd,poisonStatus);
		this.setPoisonDamage(poisonDamage);
	}

	@Override
	public void attack(Entity Enemy) {
		Enemy.setHp(Enemy.getHp()-this.getAtk());
		Enemy.setPoisonStatus(Enemy.getPoisonStatus()+this.getPoisonDamage());
	}
	
	public void follow() {
		Player player = Player.getPlayer();
		
		double px = player.getX(), py = player.getY();
		
		Point p = new Point(this.getX() - px, this.getY() - py);
		double distance = Math.sqrt(p.getX() * p.getX() + p.getY() * p.getY());
		
		if(distance > 0) {
			this.setX( this.getX() - p.getX()/distance * this.getSpd());
			this.setY( this.getY() - p.getY()/distance * this.getSpd() );
		}
		
	}

	public double getPoisonDamage() {
		return poisonDamage;
	}

	public void setPoisonDamage(double poisonDamage) {
		this.poisonDamage = poisonDamage;
	}

	@Override
	public void draw(GraphicsContext gc) {
		gc.setFill(Color.GREEN);
		gc.fillRect(this.getX(), this.getY(), 50.0, 50.0);
	}

	

}
