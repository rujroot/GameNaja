package equipment;

import java.util.ArrayList;

import data.BaseObject;
import data.DataEntity;
import entity.Monster;

public class Punch extends Melee {

	public Punch(double width, double height, double attackDamage, double attackRange, double attackDegree) {
		super(width, height, attackDamage, attackRange, attackDegree);
	}

	@Override
	public void attackAbility(ArrayList<BaseObject> intersectObjects) {

		for (BaseObject object : intersectObjects) {
			if (object instanceof Monster) {
				Monster monster = (Monster) object;
				DataEntity data = monster.getData();
				data.setHp(data.getHp() - this.getAttackDamage());
			}
		}
	}

}
