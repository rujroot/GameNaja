package inventory;

import data.BaseObject;
import data.Point;
import entity.Player;
import item.Item;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class SlotUI extends BaseObject {

    private Image image;
    private Item item;
    private Point offset = new Point(0, 0);
    private boolean followPlayer;

    public SlotUI(Point position, double width, double height, Image image) {
        super(position, width, height);
        this.setImage(image);
    }

    @Override
    public void draw(GraphicsContext gc) {
        
        Point pos = this.getPosition();

        if(followPlayer){
            Point posPlayer = Player.player.getPosition();
            pos.setX(posPlayer.getX() + pos.getX());
            pos.setY(posPlayer.getY() + pos.getY());
        }
        
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

    public boolean isFollowPlayer() {
        return followPlayer;
    }

    public void setFollowPlayer(boolean followPlayer) {
        this.followPlayer = followPlayer;
    }

    
    
}
