package equipment;

import Data.BaseObject;
import Data.Point;
import input.InputUtility;

public abstract class Melee extends BaseWeapon{

    private double attackRange, attackDegree;

    public Melee(double width, double height, double attackDamage, double attackRange) {
        super(width, height, attackDamage);
        this.setAttackRange(attackRange);
    }

    public Melee(double width, double height) {
        super(width, height, 1);
        this.setAttackRange(100);
        this.setAttackDegree(30);
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
        
        boolean intersectDistant = false, intersectDegree = false;
        Point pos = object.getPosition();

        // Check Distant <= attackRange
        double rectX1 = pos.getX();
        double rectY1 = pos.getY();
        double rectX2 = pos.getX() + object.getWidth();
        double rectY2 = pos.getY() + object.getHeight();

        double centerX = getPosition().getX();
        double centerY = getPosition().getY();

        intersectDistant = intersectDistant || distant(rectX1, rectY1, centerX , centerY) <= this.getAttackRange();
        intersectDistant = intersectDistant || distant(rectX2, rectY1, centerX , centerY) <= this.getAttackRange();
        intersectDistant = intersectDistant || distant(rectX1, rectY2, centerX , centerY) <= this.getAttackRange();
        intersectDistant = intersectDistant || distant(rectX2, rectY2, centerX , centerY) <= this.getAttackRange();

        // Check angle <= attackDegree
        Point posResPlayer = this.getResolutionPostion();
		double mouseX = InputUtility.mouseX, mouseY = InputUtility.mouseY;

        double angle = Math.atan2(mouseY - posResPlayer.getY(), mouseX - posResPlayer.getX());

        if (angle < 0) {
            angle += 2 * Math.PI;
        }
        angle = Math.toDegrees(angle);

        Point posPlayer = this.getPosition();
        double angleObject = Math.atan2(pos.getY() - posPlayer.getY(), pos.getX() - posPlayer.getX());
        if (angleObject < 0) {
            angleObject += 2 * Math.PI;
        }
        angleObject = Math.toDegrees(angleObject);

        if(Math.max(angle, angleObject) >= 270 && Math.min(angle, angleObject) <= 90){
            intersectDegree = Math.abs(Math.max(angle, angleObject) - (Math.min(angle, angleObject) + 360)) <= this.getAttackDegree();
        }else{
            intersectDegree = Math.abs(angleObject - angle) <= this.getAttackDegree();
        }

        return intersectDistant && intersectDegree;
    }

    public double getAttackRange() {
        return attackRange;
    }

    public void setAttackRange(double attackRange) {
        this.attackRange = attackRange;
    }

    public double getAttackDegree() {
        return attackDegree;
    }

    public void setAttackDegree(double attackDegree) {
        this.attackDegree = attackDegree;
    }
    
}