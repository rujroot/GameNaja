package entity;

import data.DataEntity;
import data.Point;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;
import logic.RenderableHolder;

public class Demon extends Monster {
	private int magicAttack;
	
	public Demon(String name, double width, double height, DataEntity data) {
		super(name, width, height, data);
		this.setMagicAttack(magicAttack);
		this.setImage(new WritableImage(RenderableHolder.demon.getPixelReader(), 32 * 12, 32 * 3, 32, 32));
		// TODO Auto-generated constructor stub
	}


	@Override
	public void attack() {
		//Enemy.setHp(Enemy.getHp()-this.getAtk());
		super.attack();
	}
	
	@Override
	public void draw(GraphicsContext gc) {
		
		gc.setFill(Color.FIREBRICK);
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

	public int getMagicAttack() {
		return magicAttack;
	}


	public void setMagicAttack(int magicAttack) {
		this.magicAttack = magicAttack;
	}
	
	
}
