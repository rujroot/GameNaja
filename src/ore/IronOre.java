package ore;

import Data.DataOre;
import Data.Point;
import item.IronIngot;
import javafx.scene.image.WritableImage;
import logic.Main;
import logic.RenderableHolder;

public class IronOre extends BaseOre {

    public IronOre(Point position, DataOre dataOre) {
        super(position, 0, 0, dataOre);
        this.setImage(new WritableImage(RenderableHolder.ores.getPixelReader(), 16 * 6, 16 * 5, 16, 16));
        this.setWidth(this.getImage().getWidth() * this.getMutliply());
        this.setHeight(this.getImage().getHeight() * this.getMutliply());
    }

    @Override
    public void onBreak() {
        IronIngot iron = new IronIngot(getPosition(), getWidth(), getHeight(), 11);
        iron.setAmount(1);
        Main.inventory.addItem(iron);
    }
}
