package equipment;

import java.util.ArrayList;

import Data.BaseObject;
import Data.DataEntity;
import Data.DataOre;
import Data.Point;
import animation.AnimationController;
import entity.Player;
import entity.Zombie;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;
import logic.Main;
import logic.RenderableHolder;
import ore.BaseOre;

public class Knife extends Melee{
	private WritableImage image = new WritableImage(RenderableHolder.Tileset.getPixelReader(), 607, 390, 637-607, 444-390);
	

	public Knife(double width, double height, double attackDamage, double attackRange,double attackDegree) {
		super(width, height, attackDamage, attackRange, attackDegree);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void attack() {
		// TODO Auto-generated method stub
		ArrayList<BaseObject> gameObjectContainer = Main.getLogic().getGameObjectContainer();
        ArrayList<BaseObject> instersectObject = new ArrayList<BaseObject>();

        // Attack Object section
        Player player = Player.getPlayer();
        double startAt = player.getMouseAngle();

        Point playerPosition = this.getPlayerPosition();
        Point attackPosition = new Point(playerPosition.getX() - (getAttackRange() / 2) + (player.getWidth() / 2), playerPosition.getY() - (getAttackRange() / 2) + (player.getHeight() / 2));

        AttackObject attackObject = new AttackObject(attackPosition, getAttackRange(), getAttackRange(), startAt - getAttackDegree() / 2, getAttackDegree());
        Main.logic.addObject(attackObject);
        AnimationController.animations.add(attackObject);

        for(BaseObject object : gameObjectContainer){
            if(object.isVisible() && this.intersectsCirclePart(object)){
                instersectObject.add(object);
            }
        }

        for(BaseObject object : instersectObject){
//            if(object instanceof BaseOre){
//                BaseOre ore = (BaseOre) object;
//                DataOre data = ore.getDataOre();
//                data.setDurability(0);
//            }else 
            	
            if(object instanceof Zombie){
                Zombie zombie = (Zombie) object;
                DataEntity data = zombie.getData();
                data.setHp(data.getHp() - this.getAttackDamage());
                //System.out.println("Att");
            }
        }
		
	}

	@Override
	public void draw(GraphicsContext gc) {
		// TODO Auto-generated method stub
		Point pos = this.getPosition();
		gc.drawImage(image, pos.getX()+15, pos.getY()+8, (int)(image.getWidth()*0.75) , (int)((image.getHeight()*0.75)));
//        gc.setFill(Color.GRAY);
//		gc.fillRect(pos.getX(), pos.getY(), this.getWidth(), this.getHeight());
	}

}
