package equipment;

import java.util.ArrayList;

import data.BaseObject;
import data.DataEntity;
import entity.Npc;
import entity.Player;
import javafx.scene.canvas.GraphicsContext;

public class PunchMonster extends Melee {
    public PunchMonster() {
		super();
		this.setAttackDamage(1);
		this.setAttackRange(200);
		this.setAttackDegree(100);
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
			}else if (object instanceof Npc) {
				Npc npc = (Npc) object;
				DataEntity data = npc.getData();
				data.setHp(data.getHp() - this.getAttackDamage());
			}
		}
	}

}
