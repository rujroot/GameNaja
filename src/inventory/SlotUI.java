package inventory;

import data.BaseObject;
import data.Point;
import entity.Entity;
import item.Item;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import logic.RenderableHolder;

public class SlotUI extends BaseObject {

    private Image image, selectUI = RenderableHolder.selectUI;
    private Item item;
    private Point offset = new Point(0, 0);
    private Entity entity;
    private boolean select = false;

    public SlotUI(Point position, double width, double height, Image image, Entity entity) {
        super(position, width, height);
        this.setImage(image);
        this.setEntity(entity);
    }

    @Override
    public void draw(GraphicsContext gc) {
        
        Point pos = this.getPosition(); 
        Point posEntity = entity.getPosition();

        gc.drawImage(image, pos.getX() + posEntity.getX(), pos.getY() + posEntity.getY(), image.getWidth(), image.getHeight());

        if(item != null){
            Point posItem = item.getPosition();
            posItem.setX(pos.getX() + posEntity.getX() + offset.getX());
            posItem.setY(pos.getY() + posEntity.getY() + offset.getY());
            item.draw(gc);
        }

        if(isSelect()){
            gc.drawImage(selectUI,
                        pos.getX() + posEntity.getX() + offset.getX(), 
                        pos.getY() + posEntity.getY()  + offset.getY(), 
                        selectUI.getWidth(), 
                        selectUI.getHeight());
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

    public boolean isSelect() {
        return select;
    }

    public void setSelect(boolean select) {
        this.select = select;
    }

}
