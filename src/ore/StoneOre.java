package ore;

import Data.DataOre;
import Data.Point;
import item.Stone;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import logic.Main;

public class StoneOre extends BaseOre {

    public StoneOre(Point position, double width, double height, DataOre dataOre) {
        super(position, width, height, dataOre);
    }

    public StoneOre(Point position, DataOre dataOre) {
        super(position, 20, 20, dataOre);
    }

    @Override
    public void draw(GraphicsContext gc) {
        gc.setFill(Color.GRAY);
		gc.fillRect(this.getPosition().getX(), this.getPosition().getY(), this.getWidth(), this.getHeight());
    
    }

    @Override
    public void onBreak() {
        Stone stone = new Stone(getPosition(), getWidth(), getHeight(), 11);
        stone.setAmount(1);
        Main.inventory.addItem(stone);
    }
    
    
}
