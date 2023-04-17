package ore;

import Data.DataOre;
import Data.Point;
import item.GoldIngot;
import javafx.scene.image.WritableImage;
import logic.Main;
import logic.RenderableHolder;

public class GoldOre extends BaseOre{

    public GoldOre(Point position, DataOre dataOre) {
        super(position, 0, 0, dataOre);
        this.setImage(new WritableImage(RenderableHolder.ores.getPixelReader(), 16 * 6, 16 * 4, 16, 16));
        this.setWidth(this.getImage().getWidth() * this.getMutliply());
        this.setHeight(this.getImage().getHeight() * this.getMutliply());
    }

    @Override
    public void onBreak() {
        GoldIngot gold = new GoldIngot(getPosition(), getWidth(), getHeight(), 11);
        gold.setAmount(1);
        Main.inventory.addItem(gold);
    }
}
