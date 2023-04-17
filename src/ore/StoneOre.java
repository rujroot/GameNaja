package ore;

import Data.DataOre;
import Data.Point;
import item.Stone;
import javafx.scene.image.WritableImage;
import logic.Main;
import logic.RenderableHolder;

public class StoneOre extends BaseOre {

    public StoneOre(Point position, DataOre dataOre) {
        super(position, 20, 20, dataOre);
        this.setImage(new WritableImage(RenderableHolder.ores.getPixelReader(), 0, 64, 16, 16));
        this.setWidth(this.getImage().getWidth() * this.getMutliply());
        this.setHeight(this.getImage().getHeight() * this.getMutliply());
    }

    @Override
    public void onBreak() {
        Stone stone = new Stone(getPosition(), getWidth(), getHeight(), 11);
        stone.setAmount(1);
        Main.inventory.addItem(stone);
    }
    
    
}
