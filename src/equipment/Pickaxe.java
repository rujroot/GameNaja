package equipment;

import java.util.ArrayList;

import data.BaseObject;
import data.DataEntity;
import data.DataOre;
import entity.Monster;
import javafx.scene.image.WritableImage;
import logic.RenderableHolder;
import ore.BaseOre;

public class Pickaxe extends Melee{
    private WritableImage image = new WritableImage(RenderableHolder.Tileset.getPixelReader(), 607, 390, 637-607, 444-390);

    public Pickaxe(double width, double height) {
        super(width, height);

        this.setImage(image);
    }

    @Override
    public void attackAbility(ArrayList<BaseObject> intersectObjects) {
        for(BaseObject object : intersectObjects){
            if(object instanceof BaseOre){
                BaseOre ore = (BaseOre) object;
                DataOre data = ore.getDataOre();
                data.setDurability(0);
            }else if(object instanceof Monster){
            	Monster monster = (Monster) object;
                DataEntity data = monster.getData();
                data.setHp(data.getHp() - this.getAttackDamage());
            }
        }
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        Pickaxe clone = (Pickaxe) super.clone();
        return clone;
    }
    
}
