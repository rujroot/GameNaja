package ore;

import Data.DataOre;
import Data.Point;
import item.GoldIngot;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import logic.Main;

public class GoldOre extends BaseOre{
    public GoldOre(Point position, double width, double height, DataOre dataOre) {
        super(position, width, height, dataOre);
    }

    public GoldOre(Point position, DataOre dataOre) {
        super(position, 20, 20, dataOre);
    }

    @Override
    public void draw(GraphicsContext gc) {
        gc.setFill(Color.GOLD);
		gc.fillRect(this.getPosition().getX(), this.getPosition().getY(), this.getWidth(), this.getHeight());
    
    }

    @Override
    public void onBreak() {
        GoldIngot gold = new GoldIngot(getPosition(), getWidth(), getHeight(), 11);
        gold.setAmount(1);
        Main.inventory.addItem(gold);
    }
}
