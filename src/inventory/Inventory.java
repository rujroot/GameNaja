package inventory;

import java.util.ArrayList;

import Data.BaseObject;
import Data.Point;
import drawing.GameScreen;
import entity.Player;
import item.Item;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import logic.Main;
import logic.RenderableHolder;

public class Inventory extends BaseObject{
    
    private int maxIndex, currIndex;
    private BaseUI UI;

    private Image backUI = RenderableHolder.backUI, upUI = RenderableHolder.upUI, select = RenderableHolder.selectUI;
    private WritableImage backBtwUI = new WritableImage(backUI.getPixelReader(), 19, 0, 60, 96);
    
    public Inventory(){
        super(new Point(0, 0) , 700, 60, 10);
        this.setMaxIndex(9);
        this.setCurrIndex(0);
        allItem = new Item[maxIndex];

        Point pos = this.getPosition();
        Point resolution = GameScreen.getResolution();
        Point basePoint = new Point(pos.getX() - resolution.getX() / 4 + 100, pos.getY() + resolution.getY() / 3);
        UI = new BaseUI(basePoint, 0, 0, 9, 0);
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
        UI.draw(gc);
        // Player player = Player.getPlayer();
        // Point pos = player.getPosition();
        // Point resolution = GameScreen.getResolution();

        // Point basePoint = new Point(pos.getX() - resolution.getX() / 4 + 100, pos.getY() + resolution.getY() / 3);

        // // create base ui
        // for(int i = 0; i < maxIndex - 1; ++i){

        //     if(i == 0){
        //         gc.drawImage(backUI, basePoint.getX() + (backBtwUI.getWidth() * i) - 18, 
        //                 basePoint.getY(), 
        //                 backUI.getWidth(), 
        //                 backUI.getHeight());
        //         gc.drawImage(backUI, basePoint.getX() + (backBtwUI.getWidth() * (maxIndex - 1)) - 18, 
        //                 basePoint.getY(), 
        //                 backUI.getWidth(), 
        //                 backUI.getHeight());

        //     }else{
        //        gc.drawImage(backBtwUI, basePoint.getX() + (backBtwUI.getWidth() * i), 
        //                 basePoint.getY(), 
        //                 backBtwUI.getWidth(), 
        //                 backBtwUI.getHeight()); 
        //     }

        // }

        // // create child ui
        // for(int i = 0; i < maxIndex; ++i){
        //     gc.drawImage(upUI, basePoint.getX() + (upUI.getWidth() * i), 
        //                 basePoint.getY() + 18, 
        //                 upUI.getWidth(), 
        //                 upUI.getHeight());
            
        //     Point posUI = new Point(basePoint.getX() + (upUI.getWidth() * i), basePoint.getY() + 20);

        //     if(allItem[i] != null){
        //         Item item = allItem[i];
        //         item.setPosition(posUI);
        //         // item.setWidth(upUI.getWidth());
        //         // item.setHeight(upUI.getHeight());
        //     }
        // }

    }
}
