package equipment;

import Data.BaseObject;
import Data.Point;

public abstract class Melee extends BaseWeapon{

    private double attackRange;

    public Melee(double width, double height, double attackDamage, double attackRange) {
        super(width, height, attackDamage);
        this.setAttackRange(attackRange);
    }

    public Melee(double width, double height) {
        super(width, height, 1);
        this.setAttackRange(100);
    }

    public boolean pointIntersectsRectangle(double x, double y, double rectX, double rectY, double rectWidth, double rectHeight) {
        if (x >= rectX && x <= rectX + rectWidth && y >= rectY && y <= rectY + rectHeight) {
            return true;
        }
        return false;
    }

    public double distant(double x1, double y1, double x2, double y2){
        return Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2));
    }

    public boolean intersectsCirclePart(BaseObject object) {
        
        boolean intersect = false;
        Point pos = object.getPosition();

        double rectX1 = pos.getX();
        double rectY1 = pos.getY();
        double rectX2 = pos.getX() + object.getWidth();
        double rectY2 = pos.getY() + object.getHeight();

        double centerX = getPosition().getX();
        double centerY = getPosition().getY();

        intersect = intersect || distant(rectX1, rectY1, centerX , centerY) <= this.getAttackRange();
        intersect = intersect || distant(rectX2, rectY1, centerX , centerY) <= this.getAttackRange();
        intersect = intersect || distant(rectX1, rectY2, centerX , centerY) <= this.getAttackRange();
        intersect = intersect || distant(rectX2, rectY2, centerX , centerY) <= this.getAttackRange();

        return intersect;
        // // Step 1: Find the center of the circle.
        // double centerX = getPosition().getX();
        // double centerY = getPosition().getY();

        // Point rect = object.getPosition();
        
        // // Step 2: Check if the rectangle contains the center of the circle.
        // if (pointIntersectsRectangle(centerX, centerY, rect.getX(), rect.getY(), object.getWidth(), object.getHeight())) {
        //     return true;
        // }
        
        // // Step 3: Find the points on the circle that intersect with the edges of the rectangle.
        // double start = Math.toRadians(startAngle);
        // double end = Math.toRadians(endAngle);
        // double step = Math.toRadians(1);
        // for (double a = start; a <= end; a += step) {
        //     double x = centerX + radius * Math.cos(a);
        //     double y = centerY + radius * Math.sin(a);
        //     if (pointIntersectsRectangle(x, y, rect.getX(), rect.getY(), object.getWidth(), object.getHeight())) {
        //         // Step 4: Check if any of the points are inside the rectangle.
        //         return true;
        //     }
        // }
        
        // // No intersection found.
        // return false;
    }

    public double getAttackRange() {
        return attackRange;
    }

    public void setAttackRange(double attackRange) {
        this.attackRange = attackRange;
    }}
