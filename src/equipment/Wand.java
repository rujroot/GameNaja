package equipment;

import equipment.projectile.Arrow;
import javafx.scene.image.WritableImage;
import logic.RenderableHolder;

public class Wand extends Gun {
	private WritableImage image = new WritableImage(RenderableHolder.equipment1.getPixelReader(), 162*2, 66*2, (191-162)*2, (94-66)*2);

	public Wand(double width, double height) {
		super(width, height,100);
		this.setImage(image);

		this.setCooldownTime(200);
		//this.setArrow(new Arrow(width, height, height, position, position));
	}

	@Override
    public Object clone() throws CloneNotSupportedException {
        Wand clone = (Wand) super.clone();
        return clone;
    }

}
