package equipment.projectile;

import Math.Point;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Arrow extends BaseProjectile {

	public Arrow(double damage, Point speed, Point Position) {
		super(damage, speed, Position);
	}

	@Override
	public void draw(GraphicsContext gc) {
		Point speed = this.getSpeed();
		Point pos = this.getPosition();
		Point newPos = new Point(pos.getX() + speed.getX(), pos.getY() + speed.getY());
		
		gc.setFill(Color.YELLOW);
		gc.fillRect(newPos.getX(), newPos.getY(), 10.0, 10.0);
		
		this.setPosition(newPos);
		
	}

}
