package entity;

import data.DataEntity;
import javafx.scene.image.WritableImage;
import logic.RenderableHolder;

public class Goblin extends Monster {
	private int swiftness;
	private WritableImage image = new WritableImage(RenderableHolder.enemy.getPixelReader(), 485, 285, 63, 73);
	public Goblin(String name, double width, double height, DataEntity data) {
		super(name, width, height, data);
		this.setSwiftness(swiftness);
		this.setImage(image);
	}
	
	@Override
	public void attack() {
		//Enemy.setHp(Enemy.getHp()-this.getAtk());
		super.attack();
	}

	public int getSwiftness() {
		return swiftness;
	}

	public void setSwiftness(int swiftness) {
		this.swiftness = swiftness;
	}
}
