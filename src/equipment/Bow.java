package equipment;

import java.util.ArrayList;

import Math.Point;
import equipment.projectile.Arrow;
import input.InputUtility;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import logic.Cooldownable;

public class Bow extends BaseWeapon implements Cooldownable {
	
	private int amount;
	private double cooldownTime = 500;
	private double lastClickTime = 0;
	private ArrayList<Arrow> listArrow;

	public Bow(int attackDamage) {
		super(attackDamage);
		this.setOffsetPosition(new Point(30, 20));
		
		listArrow = new ArrayList<Arrow>();
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

	@Override
	public void attack() {
		if(!onCooldown()) {

			// find direction to shoot
			Point pos = this.getResolutionPostion();
			double mouseX = InputUtility.mouseX, mouseY = InputUtility.mouseY;
			Point vector = new Point(mouseX - pos.getX(), mouseY - pos.getY());
			vector.unit();
			vector.multiply(10);

			// damge, speed, pos
			Arrow arrow = new Arrow(1, vector, this.getPostion());
			listArrow.add(arrow);
		}
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
