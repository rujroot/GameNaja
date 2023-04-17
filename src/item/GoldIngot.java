package item;

import Data.Point;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class GoldIngot extends Item {
    public GoldIngot(Point position, double width, double height) {
        super(position, width, height);
        this.setName("Gold Ingot");
    }

    public GoldIngot(Point position, double width, double height, int z) {
        super(position, width, height, z);
        this.setName("Gold Ingot");
    }

    @Override
    public void draw(GraphicsContext gc) {
        gc.setFill(Color.YELLOW);
		Point pos = this.getPosition();
		gc.fillRect(pos.getX(), pos.getY(), this.getWidth(), this.getHeight());

        gc.setFont(new Font("Arial", 12));
        gc.setFill(Color.BLACK);
        gc.fillText(Integer.toString(getAmount()), pos.getX() + this.getWidth(), pos.getY() + this.getHeight(), 100);
    }
}
