package equipment.projectile;

import Data.DataEntity;
import Data.Point;
import entity.Entity;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Arrow extends BaseProjectile {

	public Arrow(double damage, Point speed, Point Position) {
		super(damage, speed, Position);
	}

	public void makeDamge(Entity entity){
		DataEntity data = entity.getData();
		data.setHp(data.getHp() - this.getDamage());
	}

	@Override
	public void draw(GraphicsContext gc) {
		Point pos = this.getPosition();
		gc.setFill(Color.YELLOW);
		gc.fillRect(pos.getX(), pos.getY(), 10.0, 10.0);
	}

}
