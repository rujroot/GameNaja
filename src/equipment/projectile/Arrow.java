package equipment.projectile;

import java.util.ArrayList;
import java.util.HashMap;

import data.DataEntity;
import data.Point;
import dungeon.Direction;
import dungeon.GenerateDungeon;
import dungeon.Path;
import dungeon.Room;
import entity.Entity;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Arrow extends BaseProjectile {

	public Arrow(double width, double height, double damage, Point speed, Point Position) {
		super(width, height, damage, speed, Position);
	}

	public void makeDamge(Entity entity){
		DataEntity data = entity.getData();
		data.setHp(data.getHp() - this.getDamage());
	}

	@Override
	public void draw(GraphicsContext gc) {
		Point pos = this.getPosition();
		gc.setFill(Color.YELLOW);
		gc.fillRect(pos.getX(), pos.getY(), this.getWidth(), this.getHeight());
	}

	public boolean inRoom(){
		
		int currLevel = GenerateDungeon.getCurrLevel();
		ArrayList<Room> level = GenerateDungeon.getContainer().get(currLevel);
		double sum = 0;
		
		for(Room room : level) {
			
			Point posRoom = room.getPosition();
			Point newPos = this.getPosition();
			
			double rect1X1 = posRoom.getX();
		    double rect1Y1 = posRoom.getY();
		    double rect1X2 = posRoom.getX() + room.getWidth();
		    double rect1Y2 = posRoom.getY() + room.getHeight();
		    
		    double X1 = newPos.getX();
		    double Y1 = newPos.getY();
		    double X2 = newPos.getX() + this.getWidth();
		    double Y2 = newPos.getY() + this.getHeight();

			// calculate the coordinates of the intersection rectangle
			double xLeft = Math.max(rect1X1, X1);
			double yTop = Math.max(rect1Y1, Y1);
			double xRight = Math.min(rect1X2, X2);
			double yBottom = Math.min(rect1Y2, Y2);
			
			// calculate the area of the intersection rectangle
			double intersectionArea = 0.0;
			if (xRight > xLeft && yBottom > yTop) {
				intersectionArea = (xRight - xLeft) * (yBottom - yTop);
			}

			HashMap<Direction, Path> connectPath = room.getConnectPath();
			sum +=  getSumPath(connectPath.get(Direction.UP), newPos);
			sum +=  getSumPath(connectPath.get(Direction.DOWN), newPos);
			sum +=  getSumPath(connectPath.get(Direction.LEFT), newPos);
			sum +=  getSumPath(connectPath.get(Direction.RIGHT), newPos);
			sum += intersectionArea;
		}

		return Math.round(sum) == Math.round(this.getWidth() * this.getHeight());   
	}

	public double getSumPath(Path path, Point newPosPlayer){
		if(!path.isVisible()) return 0;
		
		Point posPath = path.getPosition();
		
		double rect1X1 = posPath.getX();
		double rect1Y1 = posPath.getY();
		double rect1X2 = posPath.getX() + path.getWidth();
		double rect1Y2 = posPath.getY() + path.getHeight();
		
		double X1 = newPosPlayer.getX();
		double Y1 = newPosPlayer.getY();
		double X2 = newPosPlayer.getX() + this.getWidth();
		double Y2 = newPosPlayer.getY() + this.getHeight();

		// calculate the coordinates of the intersection rectangle
		double xLeft = Math.max(rect1X1, X1);
		double yTop = Math.max(rect1Y1, Y1);
		double xRight = Math.min(rect1X2, X2);
		double yBottom = Math.min(rect1Y2, Y2);
		
		// calculate the area of the intersection rectangle
		double intersectionArea = 0.0;
		if (xRight > xLeft && yBottom > yTop) {
			intersectionArea = (xRight - xLeft) * (yBottom - yTop);
		}
		return intersectionArea;
	}

}
