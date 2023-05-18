package animation;

import data.Point;
import entity.Player;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class ElementAttackAnimation extends AnimationObject {

    private double radius, cooldown;
    private Point center, offset = new Point(0, 0), targetPoint;

    public ElementAttackAnimation(Point position, double endTime, double speed, double radius, Point offset) {
        super(position, 0, 0);

        this.setEndTime(1000000);
        this.setSpeedAnim(speed);
        this.setRadius(radius);
        this.setCenter(position);
        this.setOffset(offset);
        this.setCooldown(endTime);
    }

    public ElementAttackAnimation(Point position, double endTime, double speed, double radius) {
        super(position, 0, 0);

        this.setEndTime(100000);
        this.setSpeedAnim(speed);
        this.setRadius(radius);
        this.setCenter(position);
        this.setCooldown(endTime);
    }

    @Override
    public void draw(GraphicsContext gc) {
        double currTime = this.getCurrTime();
        double r = this.getRadius();

        if(this.getCurrTime() <= this.getCooldown()){

            double x = Math.sin(Math.toRadians(currTime)) * r;
            double y = Math.cos(Math.toRadians(currTime)) * r;

            Point pos = this.getCenter();
            this.setPosition(new Point(pos.getX() + x + offset.getX(), pos.getY() + y + offset.getY()));
            
            gc.setFill(Color.BLUE);
            gc.fillOval(this.getPosition().getX() , this.getPosition().getY(), 30 , 30);
           
            Point pp = Player.getPlayer().getPosition();

            Point p = new Point(this.getPosition().getX() - pp.getX() , this.getPosition().getY() - pp.getY());
		    double distance = Math.sqrt(p.getX() * p.getX() + p.getY() * p.getY());

            this.setTargetVector(new Point(-p.getX() / distance, -p.getY() / distance));
        }else if(this.getCurrTime() <= this.getCooldown() * 2){

            Point target = this.getTargetPoint();

            Point pos = this.getPosition();
            pos.setX(this.getPosition().getX() + target.getX() * 30);
            pos.setY(this.getPosition().getY() + target.getY() * 30);

            gc.setFill(Color.RED);
            gc.fillOval(this.getPosition().getX() , this.getPosition().getY(), 30 , 30);

        }else{
            this.setCurrTime(10000000);
        }
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    public Point getCenter() {
        return center;
    }

    public void setCenter(Point center) {
        this.center = center;
    }

    public Point getOffset() {
        return offset;
    }

    public void setOffset(Point offset) {
        this.offset = offset;
    }

    public Point getTargetPoint() {
        return targetPoint;
    }

    public void setTargetVector(Point targetPoint) {
        this.targetPoint = targetPoint;
    }

    public double getCooldown() {
        return cooldown;
    }

    public void setCooldown(double cooldown) {
        this.cooldown = cooldown;
    }

    

    
    
}
