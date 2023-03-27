package equipment;

import java.util.ArrayList;

import Math.Point;
import entity.Entity;
import entity.Player;
import equipment.projectile.Arrow;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Bow extends BaseWeapon {
	
	private int amount;
	private ArrayList<Arrow> listArrow;

	public Bow(int attackDamage) {
		super(attackDamage);
		this.setPostion(new Point(30, 20));
		
		listArrow = new ArrayList<Arrow>();
	}

	@Override
	public void attack() {
		// damge, speed, pos
		Arrow arrow = new Arrow(1, new Point(5, 0), this.getPostion());
		listArrow.add(arrow);
	}

	@Override
	public void draw(GraphicsContext gc) {
		gc.setFill(Color.BROWN);
		
		Point pos = this.getPostion();
		gc.fillRect(pos.getX(), pos.getY(), 30.0, 10.0);
		for(Arrow arrow : listArrow) {
			arrow.draw(gc);
		}
		
	}
}
