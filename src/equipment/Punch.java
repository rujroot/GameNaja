package equipment;

import java.util.ArrayList;

import data.BaseObject;
import data.DataEntity;
import entity.Monster;
import javafx.scene.image.WritableImage;
import logic.RenderableHolder;

public class Punch extends Melee {
	private WritableImage image = new WritableImage(RenderableHolder.equipment1.getPixelReader(), 293, 0, 314-293, 31-0);
	public Punch(double width, double height, double attackDamage, double attackRange, double attackDegree) {
		super(width, height);

		this.setAttackDamage(attackDamage);
		this.setAttackRange(attackRange);
		this.setAttackDegree(attackDegree);
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

	@Override
    public Object clone() throws CloneNotSupportedException {
        Punch clone = (Punch) super.clone();
        return clone;
    }

}
