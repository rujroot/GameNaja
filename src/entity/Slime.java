package entity;

import data.DataEntity;
import data.Point;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;
import logic.RenderableHolder;

public class Slime extends Monster{
	//slime_frames_all
	private WritableImage image = new WritableImage(RenderableHolder.slime.getPixelReader(), 7, 2, 24-7, 15-2);
	public Slime(String name, double width, double height, DataEntity data) {
		super(name, width, height, data);
		this.setImage(image);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void attack() {
		//Enemy.setHp(Enemy.getHp()-this.getAtk());
		super.attack();
	}


}
