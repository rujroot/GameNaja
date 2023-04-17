package ore;

import Data.DataOre;
import Data.Point;
import item.IronIngot;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import logic.Main;

public class IronOre extends BaseOre {
    public IronOre(Point position, double width, double height, DataOre dataOre) {
        super(position, width, height, dataOre);
    }

    public IronOre(Point position, DataOre dataOre) {
        super(position, 20, 20, dataOre);
    }

    @Override
    public void draw(GraphicsContext gc) {
        gc.setFill(Color.WHITESMOKE);
		gc.fillRect(this.getPosition().getX(), this.getPosition().getY(), this.getWidth(), this.getHeight());
    
    }

    @Override
    public void onBreak() {
        IronIngot iron = new IronIngot(getPosition(), getWidth(), getHeight(), 11);
        iron.setAmount(1);
        Main.inventory.addItem(iron);
    }
}
