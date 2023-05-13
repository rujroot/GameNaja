package entity;

import data.DataEntity;
import data.Point;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;
import logic.RenderableHolder;

public class Zombie extends Monster {
	private double poisonDamage;
	private WritableImage image = new WritableImage(RenderableHolder.enemy.getPixelReader(), 102, 162, 124-102, 191-162);

	public Zombie(String name, double Height, double Width, DataEntity data) {
		super(name, Height, Width, data);
		this.setPoisonDamage(poisonDamage);
		this.setImage(image);
	}

	@Override
	public void attack() {
		//Enemy.setHp(Enemy.getHp()-this.getAtk());
		super.attack();
	}
	
	
	public double getPoisonDamage() {
		return poisonDamage;
	}

	public void setPoisonDamage(double poisonDamage) {
		this.poisonDamage = poisonDamage;
	}



	

}
