package ore;

import Data.DataOre;
import Data.Point;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class StoneOre extends BaseOre {

    public StoneOre(Point position, double width, double height, DataOre dataOre) {
        super(position, width, height, dataOre);
    }

    @Override
    public void draw(GraphicsContext gc) {
        gc.setFill(Color.GREEN);
		gc.fillRect(this.getPosition().getX(), this.getPosition().getY(), this.getWidth(), this.getHeight());
    }
    
    
}
