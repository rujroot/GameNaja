package entity;

import Data.DataEntity;
import Data.Point;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Goblin extends Monster {
	private int swiftness;
	
	public Goblin(String name, double width, double height, DataEntity data) {
		super(name, width, height, data);
		this.setSwiftness(swiftness);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void attack() {
		//Enemy.setHp(Enemy.getHp()-this.getAtk());
		//Enemy.setPoisonStatus(Enemy.getPoisonStatus()+this.getPoisonDamage());
	}
	
	@Override
	public void draw(GraphicsContext gc) {
		gc.setFill(Color.CADETBLUE);
		gc.fillRect(this.getPosition().getX(), this.getPosition().getY(), this.getWidth(), this.getHeight());
		this.drawHP(gc);
	}

	@Override
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

	public int getSwiftness() {
		return swiftness;
	}

	public void setSwiftness(int swiftness) {
		this.swiftness = swiftness;
	}
}
