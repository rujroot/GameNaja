package entity;

import java.util.ArrayList;

import data.BaseObject;
import data.DataEntity;
import data.Point;
import equipment.BaseWeapon;
import equipment.Knife;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.WritableImage;
import logic.RenderableHolder;

public class Npc extends Entity {

    private WritableImage image = new WritableImage(RenderableHolder.Tileset.getPixelReader(), 644, 964, 59, 59);
    private Entity followEntity;
    private double maxDistance = 200 ;
    private BaseWeapon equipment;

    public Npc(String name, double width, double height, DataEntity data) {
        super(name, width, height, data);
        this.setWidth(image.getWidth());
        this.setHeight(image.getHeight());

        this.setEquipment(new Knife(10, 10));
    }

    @Override
    public void draw(GraphicsContext gc) {
        Point pos = this.getPosition();
		gc.drawImage(image, pos.getX(), pos.getY(), image.getWidth(), image.getHeight());
		this.drawHP(gc);

        if (equipment != null) {
			equipment.draw(gc);
		}
    }

    @Override
    public void attack() {
        if(followEntity.equals(Player.getPlayer())) return;
        equipment.attack();
    }

    public void doBehavior(){
        follow(followEntity);
        attack();
    }

    public void follow(Entity entity) {
		Point pp = entity.getPosition();

		double px = pp.getX(), py = pp.getY();

		Point p = new Point(this.getPosition().getX() - px - 10, this.getPosition().getY() - py - 10);
		double distance = Math.sqrt(p.getX() * p.getX() + p.getY() * p.getY());

		DataEntity data = this.getData();

		if (distance > 0) {
			this.move(-p.getX() / distance * data.getSpd(), 0);
			this.move(0, -p.getY() / distance * data.getSpd());
		}

	}

    public double distance(Point pos1){
        Point pos2 = this.getPosition();
        return Math.sqrt(Math.pow(pos1.getX() - pos2.getX(), 2) + Math.pow(pos1.getY() - pos2.getY(), 2));
    }


    public void findNearestMonster(ArrayList<BaseObject> gameObjectContainer){
        Entity nearestMonster = Player.getPlayer();
        double minDistance = 1e9;

        for (int i = gameObjectContainer.size() - 1; i >= 0; i--) {
			BaseObject object = gameObjectContainer.get(i);
			if(object instanceof Monster && distance( object.getPosition()) <= maxDistance){
                if(distance( object.getPosition()) <= minDistance){
                    nearestMonster = (Entity) object;
                    minDistance = distance( object.getPosition());
                }
            }
        }

        followEntity = nearestMonster;
    }

    public Entity getFollowEntity() {
        return followEntity;
    }

    public void setFollowEntity(Entity followEntity) {
        this.followEntity = followEntity;
    }

    public double getMaxDistance() {
        return maxDistance;
    }

    public void setMaxDistance(double maxDistance) {
        this.maxDistance = maxDistance;
    }

    public BaseWeapon getEquipment() {
        return equipment;
    }

    public void setEquipment(BaseWeapon equipment) {
        this.equipment = equipment;
        equipment.setEntity(this);
    }

    
    
}
