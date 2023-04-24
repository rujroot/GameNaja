package entity;

import Data.DataEntity;
import Data.Point;
import Shop.ShopManager;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.WritableImage;
import logic.RenderableHolder;

public class Shopkeeper extends Entity {

    private WritableImage image = new WritableImage(RenderableHolder.Tileset.getPixelReader(), 392, 960, 51, 63);
    private ShopManager Shop;
    private boolean isOffer;

    public Shopkeeper(String name, double width, double height, DataEntity data) {
        super(name, width, height, data);
        this.setWidth(image.getWidth());
		this.setHeight(image.getHeight());

        Shop = new ShopManager(getPosition(), 400, 60, 11);
    }

    public void shop(){
        
    }

    @Override
    public void draw(GraphicsContext gc) {
        Point pos = this.getPosition();
		gc.drawImage(image, pos.getX(), pos.getY(), image.getWidth() , image.getHeight());

        Shop.draw(gc);
    }

    @Override
    public void attack() {
        
    }

    public ShopManager getShop() {
        return Shop;
    }

    public void setShop(ShopManager shop) {
        Shop = shop;
    }

    public boolean isOffer() {
        return isOffer;
    }

    public void setOffer(boolean isOffer) {
        this.isOffer = isOffer;
    }

    
    
}
