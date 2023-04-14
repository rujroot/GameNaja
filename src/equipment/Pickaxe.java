package equipment;

import java.util.ArrayList;

import Data.BaseObject;
import Data.DataEntity;
import Data.Point;
import entity.Zombie;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import logic.Main;
import ore.BaseOre;

public class Pickaxe extends Melee{

    public Pickaxe(double width, double height) {
        super(width, height);
    }

    @Override
    public void attack() {
        
        ArrayList<BaseObject> gameObjectContainer = Main.getLogic().getGameObjectContainer();
        ArrayList<BaseObject> instersectObject = new ArrayList<BaseObject>();

        for(BaseObject object : gameObjectContainer){
            if(object.isVisible() && this.intersectsCirclePart(object)){
                //object.setVisible(false);
                //object.setDestroyed(true);
                instersectObject.add(object);
            }
        }

        for(BaseObject object : instersectObject){
            if(object instanceof BaseOre){
                object.setVisible(false);
                object.setDestroyed(true);
            }else if(object instanceof Zombie){
                Zombie zombie = (Zombie) object;
                DataEntity data = zombie.getData();
                data.setHp(data.getHp() - this.getAttackDamage());
            }
        }



    }

    @Override
    public void draw(GraphicsContext gc) {
        gc.setFill(Color.BROWN);
		
		Point pos = this.getPosition();
		gc.fillRect(pos.getX(), pos.getY(), this.getWidth(), this.getHeight());
    }
    
}
