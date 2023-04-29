package equipment;

import data.Point;
import entity.Shopkeeper;
import equipment.projectile.Arrow;
import input.InputUtility;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import logic.Cooldownable;
import logic.Main;

public class Bow extends BaseWeapon implements Cooldownable {
	
	private int amount;
	private double cooldownTime = 500;
	private double lastClickTime = 0;

	public Bow(double width, double height, int attackDamage) {
		super(width, height, attackDamage);

		Shopkeeper.addCanBuy(this);
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
			Arrow arrow = new Arrow(10.0 , 10.0 ,1, vector, this.getPosition());
			Main.getLogic().addObject(arrow);
		}
	}

	@Override
	public void draw(GraphicsContext gc) {
		gc.setFill(Color.NAVY);
		
		Point pos = this.getPosition();
		gc.fillRect(pos.getX(), pos.getY(), this.getWidth(), this.getHeight());
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	@Override
    public Object clone() throws CloneNotSupportedException {
        Bow clone = (Bow) super.clone();
        return clone;
    }
	
}
