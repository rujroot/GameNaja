package Shop;

import Data.BaseObject;
import Data.Point;
import drawing.GameScreen;
import entity.Player;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class ShopManager extends BaseObject{
    
    public ShopManager(Point position, double width, double height, int z) {
        super(position, width, height, z);
    }

    @Override
    public void draw(GraphicsContext gc) {
        Player player = Player.getPlayer();
        Point pos = player.getPosition();
        Point resolution = GameScreen.getResolution();

        // create base Inventory ui
        gc.setFill(Color.WHITE);
		gc.fillRect(pos.getX() - resolution.getX() / 4, pos.getY() + resolution.getY() / 2, this.getWidth(), this.getHeight());
    }
    
}
