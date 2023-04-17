package ore;

import Data.DataOre;
import Data.Point;
import item.Diamond;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import logic.Main;

public class DiamondOre extends BaseOre {
    public DiamondOre(Point position, double width, double height, DataOre dataOre) {
        super(position, width, height, dataOre);
    }

    public DiamondOre(Point position, DataOre dataOre) {
        super(position, 20, 20, dataOre);
    }

    @Override
    public void draw(GraphicsContext gc) {
        gc.setFill(Color.AQUAMARINE);
		gc.fillRect(this.getPosition().getX(), this.getPosition().getY(), this.getWidth(), this.getHeight());
    
    }

    @Override
    public void onBreak() {
        Diamond diamond = new Diamond(getPosition(), getWidth(), getHeight(), 11);
        diamond.setAmount(1);
        Main.inventory.addItem(diamond);
    }
}
