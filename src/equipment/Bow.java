package equipment;

import javafx.scene.image.WritableImage;
import logic.RenderableHolder;

public class Bow extends Gun {

	private WritableImage image = new WritableImage(RenderableHolder.equipment1.getPixelReader(), 129*2, 0, (159-129)*2, (32-0)*2);
	
	public Bow(double width, double height) {
		super(width, height,100);
		this.setImage(image);

		this.setCooldownTime(500);
	}

	public Bow(double width, double height, int attackDamage) {
		super(width, height, attackDamage);

	}

	@Override
    public Object clone() throws CloneNotSupportedException {
        Bow clone = (Bow) super.clone();
        return clone;
    }
	
}
