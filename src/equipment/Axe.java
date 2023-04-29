package equipment;

import java.util.ArrayList;

import data.BaseObject;
import data.DataEntity;
import entity.Monster;

public class Axe extends Melee{

	public Axe(double width, double height, double attackDamage, double attackRange, double attackDegree) {
		super(width, height);

		this.setAttackDamage(attackDamage);
		this.setAttackRange(attackRange);
		this.setAttackDegree(attackDegree);
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

	@Override
    public Object clone() throws CloneNotSupportedException {
        Axe clone = (Axe) super.clone();
        return clone;
    }


}
