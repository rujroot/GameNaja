package entity;

import data.DataEntity;
import data.Point;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;
import logic.RenderableHolder;

public class Skeleton extends Monster {
	private int protection;
	private WritableImage image = new WritableImage(RenderableHolder.enemy.getPixelReader(), 133, 164, 157-133, 191-164);

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
