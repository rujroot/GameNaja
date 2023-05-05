package dungeon;

import java.util.ArrayList;
import java.util.HashMap;

import data.Point;
import javafx.util.Pair;
import ore.BaseOre;

public class GenerateDungeon {
	private static int level;
	private static int currLevel = 0;
	private static ArrayList<ArrayList<Room>> container = new ArrayList<ArrayList<Room>>();
	
	public GenerateDungeon(int level){
		this.setLevel(level);
		
		// Generate state
		for(int i = 0; i < level; ++i) {
			ArrayList<Room> toPush = new ArrayList<Room>();
			
			int amountRoom = 100;//(int)(Math.random()*5 + 5 + i);
			
			// create first room
			Room rootRoom = new Room();
			toPush.add(rootRoom);
			
			for(int j = 1; j < amountRoom; ++j) {
				
				//Generate Room
				Pair<Room, Direction> toConnectRoom = findRoom(rootRoom);
				Room room = new Room(toConnectRoom.getKey(), toConnectRoom.getValue());
				
				while(!isLegalCreate(room, toPush)) {
					toConnectRoom = findRoom(rootRoom);
					room = new Room(toConnectRoom.getKey(), toConnectRoom.getValue());
				}
				
				
				Direction direction = toConnectRoom.getValue();
				Room connectRoom = toConnectRoom.getKey();

				//visible path
				connectRoom.getConnectPath().get(direction).setVisible(true);

				//generate ore
				room.generateOre(10);

				// set connect room
				room.getConnectRoom().put(direction.getOpposite(), connectRoom);
				connectRoom.getConnectRoom().put(direction, room);
				toPush.add(room);
			}
			
			// Push the generate level to container
			container.add(toPush);
		}
		
		// generate ore first level
		for(Room room : container.get(0)){
			for(BaseOre ore : room.getOres()){
				ore.setVisible(true);
			}
		}
	}
	
	public void increaseFloor(int increase) {
		for(int i = 0; i < increase; ++i) {
			ArrayList<Room> toPush = new ArrayList<Room>();
			
			int amountRoom = 100;//(int)(Math.random()*5 + 5 + i);
			
			// create first room
			Room rootRoom = new Room();
			toPush.add(rootRoom);
			
			for(int j = 1; j < amountRoom; ++j) {
				
				//Generate Room
				Pair<Room, Direction> toConnectRoom = findRoom(rootRoom);
				Room room = new Room(toConnectRoom.getKey(), toConnectRoom.getValue());
				
				while(!isLegalCreate(room, toPush)) {
					toConnectRoom = findRoom(rootRoom);
					room = new Room(toConnectRoom.getKey(), toConnectRoom.getValue());
				}
				
				
				Direction direction = toConnectRoom.getValue();
				Room connectRoom = toConnectRoom.getKey();

				//visible path
				connectRoom.getConnectPath().get(direction).setVisible(true);

				//generate ore
				room.generateOre(10);

				// set connect room
				room.getConnectRoom().put(direction.getOpposite(), connectRoom);
				connectRoom.getConnectRoom().put(direction, room);
				toPush.add(room);
			}
			
			// Push the generate level to container
			container.add(toPush);
		}
		this.setLevel(this.getLevel()+increase);
	}

	public Pair<Room, Direction> findRoom(Room currRoom) {
		Direction[] d = {Direction.UP, Direction.DOWN, Direction.LEFT, Direction.RIGHT};
		Direction togo = d[(int) (Math.random() * 4)];
		
		if(currRoom.getConnectRoom().get(togo) == null) 
			return new Pair<>(currRoom, togo);
		
		return findRoom(currRoom.getConnectRoom().get(togo));
	}
	
	public boolean isLegalCreate(Room room, ArrayList<Room> level) {
		for(Room anotherRoom : level) {
			Point posR1 = room.getPosition();
			Point posR2 = anotherRoom.getPosition();
			
			double left1 = posR1.getX() - room.getWidthWall();
			double right1 = posR1.getX() + room.getWidth() + room.getWidthWall();
			double top1 = posR1.getY() - room.getHeightWall();
			double bottom1 = posR1.getY() + room.getHeight() + room.getHeightWall();
		    
			double left2 = posR2.getX() - anotherRoom.getWidthWall();
			double right2 = posR2.getX() + anotherRoom.getWidth() + anotherRoom.getWidthWall();
		    double top2 = posR2.getY() - anotherRoom.getHeightWall();
		    double bottom2 = posR2.getY() + anotherRoom.getHeight() + anotherRoom.getHeightWall();
		    
		    // Check for intersection
		    if (left1 < right2 && right1 > left2 && top1 < bottom2 && bottom1 > top2) {
		        return false;
		    }

			// Check Path
			HashMap<Direction, Path> connectPath = anotherRoom.getConnectPath();
			Path up = connectPath.get(Direction.UP);
			Path down = connectPath.get(Direction.DOWN);
			Path left = connectPath.get(Direction.LEFT);
			Path right = connectPath.get(Direction.RIGHT);

			if(checkPath(room, up)) return false;
			if(checkPath(room, down)) return false;
			if(checkPath(room, left)) return false;
			if(checkPath(room, right)) return false;
		}

		return true;
	}

	public boolean checkPath(Room room, Path path){
			Point posR1 = room.getPosition();
			Point posR2 = path.getPosition();

			double left1 = posR1.getX();
			double right1 = posR1.getX() + room.getWidth();
			double top1 = posR1.getY();
			double bottom1 = posR1.getY() + room.getHeight();
		    
			double left2 = posR2.getX();
			double right2 = posR2.getX() + path.getWidth();
		    double top2 = posR2.getY();
		    double bottom2 = posR2.getY() + path.getHeight();
		    
		    // Check for intersection
		    if (left1 < right2 && right1 > left2 && top1 < bottom2 && bottom1 > top2) {
		        return true;
		    }
			return false;
	}

	public static int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}
	
	public static int getCurrLevel() {
		return currLevel;
	}

	public static void setCurrLevel(int currLevel) {
		GenerateDungeon.currLevel = currLevel;
	}
	
	public static ArrayList<ArrayList<Room>> getContainer() {
		return container;
	}

	public void setContainer(ArrayList<ArrayList<Room>> container) {
		GenerateDungeon.container = container;
	}
}


