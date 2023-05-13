package equipment;

import data.Point;
import equipment.projectile.Arrow;
import input.InputUtility;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;
import logic.Cooldownable;
import logic.Main;
import logic.RenderableHolder;

public class Wand extends BaseWeapon implements Cooldownable{
	private WritableImage image = new WritableImage(RenderableHolder.equipment1.getPixelReader(), 162*2, 66*2, (191-162)*2, (94-66)*2);
	private int amount;
	private double cooldownTime = 100;
	private double lastClickTime = 0;

	public Wand(double width, double height) {
		super(width, height,100);
		this.setImage(image);
	}
	
	public Wand(double width, double height, double attackDamage, double attackSpeed) {
		super(width, height, attackDamage, attackSpeed);
		
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
		// TODO Auto-generated method stub
		gc.setFill(Color.CORNSILK);

		Point pos = this.getPosition();
		gc.fillRect(pos.getX(), pos.getY(), this.getWidth(), this.getHeight());
		super.draw(gc);
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
    public Object clone() throws CloneNotSupportedException {
        Wand clone = (Wand) super.clone();
        return clone;
    }

}
