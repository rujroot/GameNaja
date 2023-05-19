package equipment;

import java.util.ArrayList;

import data.BaseObject;
import data.DataEntity;
import entity.Player;
import javafx.scene.canvas.GraphicsContext;

public class GoblinAttack extends Melee {
    public GoblinAttack() {
		super();
		this.setAttackDamage(0.5);
		this.setAttackRange(100);
		this.setAttackDegree(100);
        this.setCooldownTime(100);
	}

    @Override
    public void draw(GraphicsContext gc){
        
	}

	@Override
	public void attackAbility(ArrayList<BaseObject> intersectObjects) {
		for (BaseObject object : intersectObjects) {
			if (object instanceof Player) {
				Player player = (Player) object;
				DataEntity data = player.getData();
				data.setHp(data.getHp() - this.getAttackDamage());
			}
		}
	}
}
