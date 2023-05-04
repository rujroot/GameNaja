package entity;

import data.DataEntity;
import data.Point;
import equipment.BaseWeapon;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.WritableImage;
import logic.RenderableHolder;

public class Npc extends Entity {

    private WritableImage image = new WritableImage(RenderableHolder.Tileset.getPixelReader(), 644, 964, 59, 59);
    private String stage = "Follow";
    private BaseWeapon equipment;

    public Npc(String name, double width, double height, DataEntity data) {
        super(name, width, height, data);
        this.setWidth(image.getWidth());
        this.setHeight(image.getHeight());
    }

    @Override
    public void draw(GraphicsContext gc) {
        Point pos = this.getPosition();
		gc.drawImage(image, pos.getX(), pos.getY(), image.getWidth(), image.getHeight());
		this.drawHP(gc);
    }

    @Override
    public void attack() {
        
    }

    public void doBehavior(){
        if(stage.equals("Follow")){
            followPlayer();
        }
    }

    public void followPlayer() {
		Point pp = Player.getPlayer().getPosition();

		double px = pp.getX(), py = pp.getY();

		Point p = new Point(this.getPosition().getX() - px, this.getPosition().getY() - py);
		double distance = Math.sqrt(p.getX() * p.getX() + p.getY() * p.getY());

		DataEntity data = this.getData();

		if (distance > 0) {
			this.move(-p.getX() / distance * data.getSpd(), 0);
			this.move(0, -p.getY() / distance * data.getSpd());
		}

	}

    public void findNearestMonster(){

    }
    
}
