package entity;

import Data.DataEntity;
import Data.Point;
import javafx.scene.canvas.GraphicsContext;

public class Monster extends Entity{

	public Monster(String name, double width, double height, DataEntity data) {
		super(name, width, height, data);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void draw(GraphicsContext gc) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void attack() {
		// TODO Auto-generated method stub
		
	}

	public void follow() {
		// TODO Auto-generated method stub
		Point pp = Player.getPlayer().getPosition();

		double px = pp.getX(), py = pp.getY();

		Point p = new Point(this.getPosition().getX() - px, this.getPosition().getY() - py);
		double distance = Math.sqrt(p.getX() * p.getX() + p.getY() * p.getY());

		DataEntity data = this.getData();

		if (distance > 0) {
			this.move(-p.getX() / distance * data.getSpd(), 0);
			this.move(0, -p.getY() / distance * data.getSpd());
		}

	}

}
