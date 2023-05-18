package animation;

import data.Point;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class RectAttackAnimation extends AnimationObject{
    public RectAttackAnimation(Point position, double endTime, double speed) {
        super(position, 0, 0);

        this.setEndTime(endTime);
        this.setSpeedAnim(speed);
    }

    @Override
    public void draw(GraphicsContext gc) {
        gc.setFill(Color.RED);
        gc.fillRect(this.getPosition().getX() - this.getCurrTime(), this.getPosition().getY() - this.getCurrTime(), 2 * this.getCurrTime() , 2 * this.getCurrTime());
    }
}
