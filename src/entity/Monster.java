package entity;

import data.DataEntity;
import data.Point;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.WritableImage;
import logic.Cooldownable;
import logic.Hitbox;

public class Monster extends Entity implements Cooldownable {

	private double cooldownTime = 1000;
	private double lastClickTime = 0;
	private WritableImage image;
	
	public Monster(String name, double width, double height, DataEntity data) {
		super(name, width, height, data);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void draw(GraphicsContext gc) {
		// TODO Auto-generated method stub

	}

	public boolean isIntersectPlayer() {
		// check 2 rectangle
		Point MonBox = new Point(this.getPosition().getX()-10,this.getPosition().getY()-10);
		Hitbox A = new Hitbox(MonBox, this.getWidth()+20, this.getHeight()+20);
		Hitbox B = new Hitbox(Player.getPlayer().getPosition(), Player.getPlayer().getWidth(), Player.getPlayer().getHeight());
 		return A.isIntersect(B);
	}

	@Override
	public void attack() {
		// TODO Auto-generated method stub
		// check if player intersect with monster attack box

		// decrease player's hp
		if(!onCooldown()) {
			Player.getPlayer().getData().setHp(Player.getPlayer().getData().getHp() - 1);
		}
		
	}

//	public boolean hit(Entity entity){
//		Hitbox A = new Hitbox(this.getPosition(), this.getWidth(), this.getHeight());
//		Hitbox B = new Hitbox(entity.getPosition(), entity.getWidth(), entity.getHeight());
//		return A.isIntersect(B);
//	}

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

	@Override
	public boolean onCooldown() {
		long currentTime = System.currentTimeMillis();
		if(currentTime - lastClickTime > cooldownTime) {
			lastClickTime = currentTime;
			return false;
		}else {
			return true;
		}
	}
	
	public void setImage(WritableImage image) {
        this.image = image;
    }
}
