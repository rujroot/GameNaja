package inventory;

import java.util.ArrayList;

import Data.BaseObject;
import Data.Point;
import drawing.GameScreen;
import item.Item;
import javafx.scene.canvas.GraphicsContext;

public class Inventory extends BaseObject{

    private BaseUI UI;
    
    public Inventory(){
        super(new Point(0, 0) , 700, 60, 10);

        Point pos = this.getPosition();
        Point resolution = GameScreen.getResolution();
        Point basePoint = new Point(pos.getX() - resolution.getX() / 4 + 100, pos.getY() + resolution.getY() / 3);
        UI = new BaseUI(basePoint, 0, 0, 9, 0);
        UI.setFollowPlayer(false);
    }

    public void addItem(ArrayList<Item> items){
        for(Item item : items){
            UI.addItem(item);
        }
    }

    public void addItem(Item items){
        UI.addItem(items);
    }

    @Override
    public void draw(GraphicsContext gc) {
        UI.draw(gc);

    }
}
