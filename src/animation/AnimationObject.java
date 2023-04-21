package animation;

import Data.BaseObject;
import Data.Point;
import javafx.scene.canvas.GraphicsContext;

public abstract class AnimationObject extends BaseObject {
    
    double startTime, currTime, endTime;
    public abstract void play(GraphicsContext gc);

    public AnimationObject(Point position, double width, double height) {
        super(position, width, height);
    }
    public double getStartTime() {
        return startTime;
    }
    public void setStartTime(double startTime) {
        this.startTime = startTime;
    }
    public double getCurrTime() {
        return currTime;
    }
    public void setCurrTime(double currTime) {
        this.currTime = currTime;
    }
    public double getEndTime() {
        return endTime;
    }
    public void setEndTime(double endTime) {
        this.endTime = endTime;
    }
    public void nextAnim(){
        this.currTime = this.currTime + 1;
    }
    


    
}
