package inventory;

import java.util.ArrayList;

import Data.BaseObject;
import Data.Point;
import drawing.GameScreen;
import entity.Player;
import item.Item;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import logic.Main;

public class Inventory extends BaseObject{
    private Item[] allItem;
    private int maxIndex, currIndex;

    public Inventory(){
        super(new Point(0, 0) , 700, 60, 10);
        this.setMaxIndex(9);
        this.setCurrIndex(0);
        allItem = new Item[maxIndex];
    }

    public void addItem(ArrayList<Item> items){
        for(Item item : items){
            addItem(item);
        }
    }

    public void addItem(Item items){
        for(int i = 0; i < allItem.length; ++i){
            if(allItem[i] != null && allItem[i].equals(items)){
                allItem[i].addAmount(items.getAmount());
                return;
            }
        }

        for(int i = 0; i < allItem.length; ++i){
            if(allItem[i] == null){
                allItem[i] = items;
                Main.logic.addObject(items);
                return;
            }
        }


    }

    public int getMaxIndex() {
        return maxIndex;
    }

    public void setMaxIndex(int maxIndex) {
        this.maxIndex = maxIndex;
    }

    public int getCurrIndex() {
        return currIndex;
    }

    public void setCurrIndex(int currIndex) {
        this.currIndex = currIndex;
    }

    public Item[] getAllItem() {
        return allItem;
    }

    @Override
    public void draw(GraphicsContext gc) {
        Player player = Player.getPlayer();
        Point pos = player.getPosition();
        Point resolution = GameScreen.getResolution();

        // create base Inventory ui
        gc.setFill(Color.WHITE);
		gc.fillRect(pos.getX() - resolution.getX() / 4, pos.getY() + resolution.getY() / 3, this.getWidth(), this.getHeight());

        // create child ui
        for(int i = 0; i < maxIndex; ++i){
            gc.setFill(Color.AQUAMARINE);

            Point posUI = new Point(
             pos.getX() - (resolution.getX() / 4) + (this.getWidth() / maxIndex) * i + 5,
             pos.getY() + (resolution.getY() / 3) + 5);

            Point sizeUI = new Point(
             (this.getWidth() / maxIndex) - 15, 
             this.getHeight() - 10);

            gc.fillRect(
             posUI.getX(),
             posUI.getY(), 
             sizeUI.getX(), 
             sizeUI.getY()
            );

            if(allItem[i] != null){
                Item item = allItem[i];
                item.setPosition(posUI);
                item.setWidth(sizeUI.getX());
                item.setHeight(sizeUI.getY());
            }

        }


    }
}
