package inventory;

import Data.BaseObject;
import Data.Point;
import entity.Player;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class SlotUI extends BaseObject {

    private Image image;
    private BaseObject obj;

    public SlotUI(Point position, double width, double height, Image image) {
        super(position, width, height);
        this.setImage(image);
    }

    @Override
    public void draw(GraphicsContext gc) {
        Player player = Player.player;
        Point posPlayer = player.getPosition();
        Point pos = this.getPosition();
        gc.drawImage(image, posPlayer.getX() + pos.getX(), posPlayer.getY() + pos.getY(), image.getWidth(), image.getHeight());
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public BaseObject getObj() {
        return obj;
    }

    public void setObj(BaseObject obj) {
        this.obj = obj;
    }
    
}
