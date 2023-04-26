package item;

import Data.Point;
import javafx.scene.image.WritableImage;
import logic.RenderableHolder;

public class GoldIngot extends Item {

    public GoldIngot(Point position, double width, double height) {
        super(position, width, height);
        this.setName("Gold Ingot");
        this.setImage(new WritableImage(RenderableHolder.ores.getPixelReader(), 32 * 6, 32 * 4, 32, 32));
    }

    public GoldIngot(Point position, double width, double height, int z) {
        super(position, width, height, z);
        this.setName("Gold Ingot");
        this.setImage(new WritableImage(RenderableHolder.ores.getPixelReader(), 32 * 6, 32 * 4, 32, 32));
    }

}
