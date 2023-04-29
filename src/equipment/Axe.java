package equipment;

import java.util.ArrayList;

import data.BaseObject;
import data.DataEntity;
import entity.Monster;

public class Axe extends Melee{

	public Axe(double width, double height, double attackDamage, double attackRange, double attackDegree) {
		super(width, height, attackDamage, attackRange, attackDegree);
	}

	@Override
	public void attackAbility(ArrayList<BaseObject> interBaseObjects) {

        for(BaseObject object : interBaseObjects){
            if(object instanceof Monster){
            	Monster monster = (Monster) object;
                DataEntity data = monster.getData();
                data.setHp(data.getHp() - this.getAttackDamage());
            }
        }
		
	}


}
