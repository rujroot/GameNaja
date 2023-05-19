package equipment;

import java.util.ArrayList;

import data.BaseObject;
import data.DataEntity;
import entity.Monster;
import entity.boss.BossEntity;
import javafx.scene.image.WritableImage;
import logic.RenderableHolder;

public class Axe extends Melee{
	private WritableImage image = new WritableImage(RenderableHolder.equipment1.getPixelReader(), 66, 4, (64-33)*2, (30-2)*2);

	public Axe() {
		super();
		this.setImage(image);
		this.setAttackDamage(100);
		this.setAttackRange(100);
		this.setAttackDegree(100);
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
