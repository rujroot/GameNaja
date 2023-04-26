package inventory;

import Data.BaseObject;
import Data.Point;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import logic.RenderableHolder;

public class BaseUI extends BaseObject{
    
    private int maxIndex, currIndex = 0;
    private double offset;
    private SlotUI[] posIndex;

    private Image backUI = RenderableHolder.backUI, upUI = RenderableHolder.upUI, select = RenderableHolder.selectUI;
    private WritableImage backBtwUI = new WritableImage(backUI.getPixelReader(), 19, 0, 60, 96);

    public BaseUI(Point stPos, double width, double height, int amount, double offset){
        super(stPos, width, height);

        this.setMaxIndex(amount);
        this.setOffset(offset);
        posIndex = new SlotUI[amount];

        if(offset == 0){
            for(int i = 0; i < maxIndex - 1; ++i){

                if(i == 0){
                    posIndex[i] = new SlotUI(new Point(stPos.getX() - 18 , stPos.getY()),
                                            backUI.getWidth(), 
                                            backUI.getHeight(), 
                                            backUI);

                    posIndex[maxIndex - 1] = new SlotUI(new Point(stPos.getX() + (backBtwUI.getWidth() * (maxIndex - 1)) - 18 , 
                                            stPos.getY()) , 
                                            backUI.getWidth(), 
                                            backUI.getHeight(), backUI);
                }else{
                    posIndex[i] = new SlotUI(new Point(stPos.getX() + (backBtwUI.getWidth() * i) , stPos.getY()) , backBtwUI.getWidth(), backBtwUI.getHeight(), backBtwUI);
                }

            }
        }else{

        }

    }

    @Override
    public void draw(GraphicsContext gc) {
        if(offset == 0){
            posIndex[maxIndex - 1].draw(gc);
            for(int i = 0; i < maxIndex - 1; ++i){
                posIndex[i].draw(gc);
              } 
        }else{
            for(int i = 0; i < maxIndex; ++i){
                posIndex[i].draw(gc);
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

    public double getOffset() {
        return offset;
    }

    public void setOffset(double offset) {
        this.offset = offset;
    }

    
    

}
