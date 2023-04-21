package equipment;

import Data.BaseObject;
import Data.Point;
import animation.AnimationController;
import animation.AnimationObject;
import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;

public class AttackObject extends AnimationObject{

    private double startAngle, endAngle, damage;

    public AttackObject(Point position, double width, double height) {
        super(position, width, height);
        this.setStartAngle(0);
        this.setEndAngle(360);
    }

    public AttackObject(Point position, double width, double height, double startAngle, double endAngle) {
        super(position, width, height);
        this.setStartAngle(startAngle);
        this.setEndAngle(endAngle);
    }

    @Override
    public void play(GraphicsContext gc) {

        Point pos = this.getPosition();
        
        gc.setFill(Color.WHITE);
        gc.fillArc(pos.getX(), pos.getY(), 100, 100, this.getStartAngle(), this.getCurrTime() + 1, ArcType.ROUND);

    }

    @Override
    public void draw(GraphicsContext gc) {

        this.setStartTime(startAngle);
        this.setEndTime(endAngle);

        AnimationController.animations.add(this);

        // Point pos = this.getPosition();
        // double w = this.getWidth(), h = this.getHeight();

        // gc.setFill(Color.WHITE);
        // gc.fillArc(pos.getX(), pos.getY(), 100, 100, startAngle, i + 1, ArcType.ROUND);

        // Thread thread = new Thread(()->{
                    
        //     for (int i = (int)startAngle; i<= (int) endAngle; i++) {
        //         int angle = i;

        //         Platform.runLater(new Runnable() {
        //             @Override
        //             public void run() {
        //                 gc.setFill(Color.WHITE);
        //                 gc.fillArc(pos.getX(), pos.getY(), 100, 100, startAngle, angle + 1, ArcType.ROUND);

        //             }
        //         });
                
        //         try {
        //             Thread.sleep(100);
        //         } catch (InterruptedException e) {
        //             e.printStackTrace();
        //         }
                
        //     }
        // });
        // thread.start();
			
    }

    public double getStartAngle() {
        return startAngle;
    }

    public void setStartAngle(double startAngle) {
        this.startAngle = startAngle;
    }

    public double getEndAngle() {
        return endAngle;
    }

    public void setEndAngle(double endAngle) {
        this.endAngle = endAngle;
    }

    public double getDamage() {
        return damage;
    }

    public void setDamage(double damage) {
        this.damage = damage;
    }

}
