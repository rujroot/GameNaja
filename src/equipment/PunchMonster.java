package equipment;

import java.util.ArrayList;

import data.BaseObject;
import data.DataEntity;
import entity.Monster;
import entity.Player;
import javafx.scene.canvas.GraphicsContext;

public class PunchMonster extends Melee {
    public PunchMonster() {
		super();
		this.setAttackDamage(1);
		this.setAttackRange(100);
		this.setAttackDegree(100);
	}
	
	public PunchMonster(double attackDamage, double attackRange, double attackDegree) {
		super();
		this.setAttackDamage(attackDamage);
		this.setAttackRange(attackRange);
		this.setAttackDegree(attackDegree);
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
