package entity;

import data.DataEntity;
import javafx.scene.image.WritableImage;
import logic.RenderableHolder;

public class Zombie extends Monster {
	private double poisonDamage;
	private WritableImage image = new WritableImage(RenderableHolder.enemy.getPixelReader(), 255, 407, 54, 72);

	public Zombie(String name, double Height, double Width, DataEntity data) {
		super(name, Height, Width, data);
		this.setPoisonDamage(poisonDamage);
		this.setImage(image);
	}

	@Override
	public void attack() {
		super.attack();
	}
	
	public double getPoisonDamage() {
		return poisonDamage;
	}

	public void setPoisonDamage(double poisonDamage) {
		this.poisonDamage = poisonDamage;
	}



	

}
