package equipment;

import java.util.ArrayList;

import data.BaseObject;
import data.DataEntity;
import data.DataOre;
import entity.Monster;
import ore.BaseOre;

public class Pickaxe extends Melee{

    public Pickaxe(double width, double height) {
        super(width, height);
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
