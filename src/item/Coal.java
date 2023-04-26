package item;

import Data.Point;
import javafx.scene.image.WritableImage;
import logic.RenderableHolder;

public class Coal extends Item {
    public Coal(Point position, double width, double height) {
        super(position, width, height);
        this.setName("Coal");
        this.setImage(new WritableImage(RenderableHolder.ores.getPixelReader(), 32 * 12, 32 * 5, 32, 32));
    }

    public Coal(Point position, double width, double height, int z) {
        super(position, width, height, z);
        this.setName("Coal");
        this.setImage(new WritableImage(RenderableHolder.ores.getPixelReader(), 32 * 12, 32 * 5, 32, 32));
    }
    
}
