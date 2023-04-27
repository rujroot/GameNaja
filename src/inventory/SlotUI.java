package inventory;

import Data.BaseObject;
import Data.Point;
import entity.Entity;
import item.Item;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class SlotUI extends BaseObject {

    private Image image;
    private Item item;
    private Point offset = new Point(0, 0);
    private Entity entity;

    public SlotUI(Point position, double width, double height, Image image) {
        super(position, width, height);
        this.setImage(image);
    }

    @Override
    public void draw(GraphicsContext gc) {
        
        Point pos = this.getPosition(); 
        gc.drawImage(image, pos.getX(), pos.getY(), image.getWidth(), image.getHeight());

        if(item != null){
            Point posItem = item.getPosition();
            posItem.setX(pos.getX() + offset.getX());
            posItem.setY(pos.getY() + offset.getY());
            item.draw(gc);
        }
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public boolean equals(Item item){
        return this.item.equals(item);
    }

    public Point getOffset() {
        return offset;
    }

    public void setOffset(Point offset) {
        this.offset = offset;
    }

    public Entity getEntity() {
        return entity;
    }

    public void setEntity(Entity entity) {
        this.entity = entity;
    }

    

    
    
}
