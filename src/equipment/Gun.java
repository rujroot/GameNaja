package equipment;

import data.Point;
import entity.Entity;
import entity.Npc;
import entity.Player;
import equipment.projectile.Arrow;
import input.InputUtility;
import logic.Cooldownable;
import logic.Main;

public abstract class Gun extends BaseWeapon implements Cooldownable{

    private double cooldownTime;
	private double lastClickTime;
    private Arrow arrow;

    public Gun(double width, double height, double attackDamage) {
        super(width, height, attackDamage);
    }

    public void attack() {
		if(!onCooldown()) {
            Entity entity = this.getEntity();
            if(entity instanceof Player){
                // find direction to shoot
                Point pos = this.getResolutionPostion();
                double mouseX = InputUtility.mouseX, mouseY = InputUtility.mouseY;
                Point vector = new Point(mouseX - pos.getX(), mouseY - pos.getY());
                vector.unit();
                vector.multiply(10);

                // damge, speed, pos
                Arrow arrow = new Arrow(10.0 , 10.0 ,1, vector, this.getPosition());
                Main.getLogic().addObject(arrow);
            }else{
                // find direction to shoot
                Point vector = ((Npc) entity).getEntityVector();
                vector.multiply(30);

                // damge, speed, pos
                Arrow arrow = new Arrow(10.0 , 10.0 ,1, vector, this.getPosition());
                Main.getLogic().addObject(arrow);
            }
		}
	}

    @Override
	public boolean onCooldown() {
		long currentTime = System.currentTimeMillis();
		if(currentTime - lastClickTime > cooldownTime) {
			lastClickTime = currentTime;
			return false;
		}else {
			return true;
		}
	}
  
    // gatter setter

    public double getCooldownTime() {
        return cooldownTime;
    }

    public void setCooldownTime(double cooldownTime) {
        this.cooldownTime = cooldownTime;
    }

    public Arrow getArrow() {
        return arrow;
    }

    public void setArrow(Arrow arrow) {
        this.arrow = arrow;
    }

    

    
    
}
