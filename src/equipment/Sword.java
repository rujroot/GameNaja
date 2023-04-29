package equipment;

import java.util.ArrayList;

import data.BaseObject;
import data.DataEntity;
import entity.Monster;
import javafx.scene.image.WritableImage;
import logic.RenderableHolder;

public class Sword extends Melee {

	private WritableImage image = new WritableImage(RenderableHolder.Tileset.getPixelReader(), 462, 164, 497-462, 252-164);

	public Sword(double width, double height, double attackDamage, double attackRange, double attackDegree) {
		super(width, height, attackDamage, attackRange, attackDegree);
		this.setImage(image);
	}

	@Override
	public void attackAbility(ArrayList<BaseObject> intersectObjects) {
		for(BaseObject object : intersectObjects){			
			if(object instanceof Monster){
				Monster monster = (Monster) object;
				DataEntity data = monster.getData();
				data.setHp(data.getHp() - this.getAttackDamage());
			}
		}
	}

	

}
