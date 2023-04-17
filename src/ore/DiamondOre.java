package ore;

import Data.DataOre;
import Data.Point;
import item.Diamond;
import javafx.scene.image.WritableImage;
import logic.Main;
import logic.RenderableHolder;

public class DiamondOre extends BaseOre {
    
    public DiamondOre(Point position, DataOre dataOre) {
        super(position, 0, 0, dataOre);
        this.setImage(new WritableImage(RenderableHolder.ores.getPixelReader(), 16 * 12, 16 * 3, 16, 16));
        this.setWidth(this.getImage().getWidth() * this.getMutliply());
        this.setHeight(this.getImage().getHeight() * this.getMutliply());
    }

    @Override
    public void onBreak() {
        Diamond diamond = new Diamond(getPosition(), getWidth(), getHeight(), 11);
        diamond.setAmount(1);
        Main.inventory.addItem(diamond);
    }
}
