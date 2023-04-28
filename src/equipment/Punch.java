package equipment;

import java.util.ArrayList;

import animation.AnimationController;
import data.BaseObject;
import data.DataEntity;
import data.Point;
import entity.Monster;
import entity.Player;
import entity.Zombie;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import logic.Main;

public class Punch extends Melee {

	public Punch(double width, double height, double attackDamage, double attackRange, double attackDegree) {
		super(width, height, attackDamage, attackRange, attackDegree);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void attack() {
		// TODO Auto-generated method stub
		ArrayList<BaseObject> gameObjectContainer = Main.getLogic().getGameObjectContainer();
		ArrayList<BaseObject> instersectObject = new ArrayList<BaseObject>();

		// Attack Object section
		Player player = Player.getPlayer();
		double startAt = player.getMouseAngle();

		Point playerPosition = this.getPlayerPosition();
		Point attackPosition = new Point(playerPosition.getX() - (getAttackRange() / 2) + (player.getWidth() / 2),
				playerPosition.getY() - (getAttackRange() / 2) + (player.getHeight() / 2));

		AttackObject attackObject = new AttackObject(attackPosition, getAttackRange(), getAttackRange(),
				startAt - getAttackDegree() / 2, getAttackDegree());
		Main.getLogic().addObject(attackObject);
		AnimationController.animations.add(attackObject);

		for (BaseObject object : gameObjectContainer) {
			if (object.isVisible() && this.intersectsCirclePart(object)) {
				instersectObject.add(object);
			}
		}

		for (BaseObject object : instersectObject) {
//            if(object instanceof BaseOre){
//                BaseOre ore = (BaseOre) object;
//                DataOre data = ore.getDataOre();
//                data.setDurability(0);
//            }else 

			if (object instanceof Monster) {
				Monster monster = (Monster) object;
				DataEntity data = monster.getData();
				data.setHp(data.getHp() - this.getAttackDamage());
				// System.out.println("Att");
			}
		}
	}

	@Override
	public void draw(GraphicsContext gc) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		Point pos = this.getPosition();
		gc.setFill(Color.TAN);
		gc.fillRect(pos.getX(), pos.getY(), this.getWidth(), this.getHeight());
	}

}
