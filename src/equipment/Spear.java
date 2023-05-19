package equipment;

import java.util.ArrayList;

import data.BaseObject;
import data.DataEntity;
import entity.Monster;
import javafx.scene.image.WritableImage;
import logic.RenderableHolder;

public class Spear extends Melee {
	private WritableImage image = new WritableImage(RenderableHolder.equipment1.getPixelReader(), 159*2, 95*2, (191-159)*2, (127-95)*2);
	
	public Spear() {
		super();
		this.setImage(image);
		this.setAttackDamage(100);
		this.setAttackRange(100);
		this.setAttackDegree(100);
	}
	
	public Spear( double attackDamage, double attackRange, double attackDegree) {
		super();
		this.setImage(image);
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
        Spear clone = (Spear) super.clone();
        return clone;
    }

}
