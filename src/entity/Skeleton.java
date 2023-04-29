package entity;

import data.DataEntity;
import data.Point;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Skeleton extends Monster {
	private int protection;

	public Skeleton(String name, double Height, double Width, DataEntity data) {
		super(name, Height, Width, data);
		this.setProtection(protection);
	}

	public int getProtection() {
		return protection;
	}

	public void setProtection(int protection) {
		this.protection = protection;
	}

	@Override
	public void draw(GraphicsContext gc) {
		// TODO Auto-generated method stub
		gc.setFill(Color.WHITESMOKE);
		gc.fillRect(this.getPosition().getX(), this.getPosition().getY(), this.getWidth(), this.getHeight());
		this.drawHP(gc);

	}

	@Override
	public void attack() {
		// TODO Auto-generated method stub
		super.attack();
	}

	@Override
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
