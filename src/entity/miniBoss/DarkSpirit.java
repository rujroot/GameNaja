package entity.miniBoss;

import data.DataEntity;
import javafx.scene.image.WritableImage;
import logic.RenderableHolder;

public class DarkSpirit extends MiniBossEntity {

    private WritableImage image = new WritableImage(RenderableHolder.Tileset.getPixelReader(), 532, 712, 619 - 532, 831 - 712);

    public DarkSpirit(String name, double width, double height, DataEntity data) {
        super(name, width, height, data);
        this.setImage(image);
    }
    
}
