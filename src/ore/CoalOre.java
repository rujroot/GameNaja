package ore;

import Data.DataOre;
import Data.Point;
import item.Coal;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import logic.Main;

public class CoalOre extends BaseOre {
    public CoalOre(Point position, double width, double height, DataOre dataOre) {
        super(position, width, height, dataOre);
    }

    public CoalOre(Point position, DataOre dataOre) {
        super(position, 20, 20, dataOre);
    }

    @Override
    public void draw(GraphicsContext gc) {
        gc.setFill(Color.BLACK);
		gc.fillRect(this.getPosition().getX(), this.getPosition().getY(), this.getWidth(), this.getHeight());
    
    }

    @Override
    public void onBreak() {
        Coal coal = new Coal(getPosition(), getWidth(), getHeight(), 11);
        coal.setAmount(1);
        Main.inventory.addItem(coal);
    }
}
