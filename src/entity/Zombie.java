package entity;

import Data.DataEntity;
import Data.Point;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Zombie extends Entity {
	private double poisonDamage;

	public Zombie(String name, double Height, double Width, DataEntity data) {
		super(name, Height, Width, data);
		this.setPoisonDamage(poisonDamage);
	}

	@Override
	public void attack() {
		//Enemy.setHp(Enemy.getHp()-this.getAtk());
		//Enemy.setPoisonStatus(Enemy.getPoisonStatus()+this.getPoisonDamage());
	}
	
	public void follow() {
		Point pp = Player.getPlayer().getPosition();
		
		double px = pp.getX(), py = pp.getY();
		
		Point p = new Point(this.getPosition().getX() - px, this.getPosition().getY() - py);
		double distance = Math.sqrt(p.getX() * p.getX() + p.getY() * p.getY());
		
		DataEntity data = this.getData();
		
		if(distance > 0) {
			this.move(-p.getX()/distance * data.getSpd(), 0);
			this.move(0, -p.getY()/distance * data.getSpd());
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
		gc.fillRect(this.getPosition().getX(), this.getPosition().getY(), this.getWidth(), this.getHeight());
		this.drawHP(gc);
	}


	

}
