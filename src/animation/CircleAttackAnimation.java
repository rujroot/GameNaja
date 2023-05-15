package animation;

import data.Point;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class CircleAttackAnimation extends AnimationObject {

    public CircleAttackAnimation(Point position, double width, double height) {
        super(position, width, height);

        this.setEndTime(100);
        this.setSpeedAnim(6);
    }

    @Override
    public void draw(GraphicsContext gc) {
        gc.setFill(Color.RED);
        gc.fillOval(this.getPosition().getX() - this.getCurrTime(), this.getPosition().getY() - this.getCurrTime(), 2 * this.getCurrTime() , 2 * this.getCurrTime());
    }
    
}
