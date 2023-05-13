package entity;

import data.DataEntity;
import javafx.scene.image.WritableImage;
import logic.RenderableHolder;

public class Skeleton extends Monster {
	private int protection;
	private WritableImage image = new WritableImage(RenderableHolder.enemy.getPixelReader(), 337, 411, 52, 68);

	public Skeleton(String name, double Height, double Width, DataEntity data) {
		super(name, Height, Width, data);
		this.setProtection(protection);
		this.setImage(image);
	}

	public int getProtection() {
		return protection;
	}

	public void setProtection(int protection) {
		this.protection = protection;
	}


	@Override
	public void attack() {
		// TODO Auto-generated method stub
		super.attack();
	}


}
