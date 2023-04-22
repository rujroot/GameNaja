package equipment;

import Data.BaseObject;
import Data.Point;
import entity.Player;
public abstract class Melee extends BaseWeapon{

    private double attackRange, attackDegree;

    public Melee(double width, double height, double attackDamage, double attackRange, double attackDegree) {
        super(width, height, attackDamage);
        this.setAttackRange(attackRange);
        this.setAttackDegree(attackDegree);
    }

    public Melee(double width, double height) {
        super(width, height, 1);
        this.setAttackRange(150);
        this.setAttackDegree(60);
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

        double centerX = getPlayerPosition().getX() + Player.getPlayer().getWidth() / 2;
        double centerY = getPlayerPosition().getY() + Player.getPlayer().getHeight() / 2;

        intersectDistant = intersectDistant || distant(rectX1, rectY1, centerX , centerY) <= this.getAttackRange() / 2;
        intersectDistant = intersectDistant || distant(rectX2, rectY1, centerX , centerY) <= this.getAttackRange() / 2;
        intersectDistant = intersectDistant || distant(rectX1, rectY2, centerX , centerY) <= this.getAttackRange() / 2;
        intersectDistant = intersectDistant || distant(rectX2, rectY2, centerX , centerY) <= this.getAttackRange() / 2;

        // Check angle <= attackDegree
        Point posPlayer = this.getPlayerPosition();

        double angle = Player.getPlayer().getMouseAngle();
        double angleObject = Math.atan2(pos.getY() - posPlayer.getY(), pos.getX() - posPlayer.getX());

        if (angleObject < 0) {
            angleObject += 2 * Math.PI;
        }
        angleObject = 360 - Math.toDegrees(angleObject);

        if(Math.max(angle, angleObject) >= 270 && Math.min(angle, angleObject) <= 90){
            intersectDegree = Math.abs(Math.max(angle, angleObject) - (Math.min(angle, angleObject) + 360)) <= this.getAttackDegree();
        }else{
            intersectDegree = Math.abs(angleObject - angle) <= this.getAttackDegree();
        }
        
        //System.out.println(intersectDistant+" "+intersectDegree);

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
