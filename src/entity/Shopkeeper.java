package entity;

import Data.DataEntity;
import Data.Point;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.WritableImage;
import logic.RenderableHolder;

public class Shopkeeper extends Entity {

    private WritableImage image = new WritableImage(RenderableHolder.Tileset.getPixelReader(), 392, 960, 51, 63);

    public Shopkeeper(String name, double width, double height, DataEntity data) {
        super(name, width, height, data);
        this.setWidth(image.getWidth());
		this.setHeight(image.getHeight());
    }

    @Override
    public void draw(GraphicsContext gc) {
        Point pos = this.getPosition();
		gc.drawImage(image, pos.getX(), pos.getY(), image.getWidth() , image.getHeight());
    }

    @Override
    public void attack() {
        
    }
    
}
