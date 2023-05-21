package ore;

import data.DataOre;
import data.Point;
import entity.Player;
import item.GoldIngot;
import javafx.scene.image.WritableImage;
import logic.RenderableHolder;

public class GoldOre extends BaseOre{

    public GoldOre(Point position, DataOre dataOre) {
        super(position, 0, 0, dataOre);
        this.setImage(new WritableImage(RenderableHolder.ores.getPixelReader(), 32 * 6, 32 * 4, 32, 32));
        this.setWidth(this.getImage().getWidth() * this.getMutliply());
        this.setHeight(this.getImage().getHeight() * this.getMutliply());
    }

    @Override
    public void onBreak() {
        DataOre data = this.getDataOre();
        GoldIngot gold = new GoldIngot(data.getAmount(), data.getValue());
        Player.inventory.addItem(gold);
    }
}
