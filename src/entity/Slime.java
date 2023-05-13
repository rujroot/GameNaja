package entity;

import data.DataEntity;
import javafx.scene.image.WritableImage;
import logic.RenderableHolder;

public class Slime extends Monster{
	//slime_frames_all
	private WritableImage image = new WritableImage(RenderableHolder.slime.getPixelReader(), 18, 6, 35, 29);
	public Slime(String name, double width, double height, DataEntity data) {
		super(name, width, height, data);
		this.setImage(image);
	}
	
	@Override
	public void attack() {
		super.attack();
	}


}
