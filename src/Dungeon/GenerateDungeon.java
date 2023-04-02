package Dungeon;

import java.util.ArrayList;

import javafx.util.Pair;

public class GenerateDungeon {
	private int level, currLevel = 0;
	private ArrayList<ArrayList<Room>> container = new ArrayList<ArrayList<Room>>();;
	
	public GenerateDungeon(int level){
		this.setLevel(level);
		
		// Generate state
		for(int i = 0; i < level; ++i) {
			ArrayList<Room> toPush = new ArrayList<Room>();
			
			int amountRoom = (int)(Math.random()*5 + 5 + level);
			
			// create first room
			Room rootRoom = new Room();
			toPush.add(rootRoom);
			
			for(int j = 1; j < amountRoom; ++j) {
				
				//Generate Room
				Pair<Room, Direction> toConnectRoom = findRoom(rootRoom);
				
				
				Room room = new Room(toConnectRoom.getKey(), toConnectRoom.getValue());
				while(!isLegalCreate(room, toPush)) {
					room = new Room(toConnectRoom.getKey(), toConnectRoom.getValue());
				}
				
				toConnectRoom.getKey().getConnectRoom().put(toConnectRoom.getValue(), room);
				toPush.add(room);
			}
			
			// Push the generate level to container
			container.add(toPush);
		}
	}

	public Pair<Room, Direction> findRoom(Room currRoom) {
		Direction[] d = {Direction.UP, Direction.DOWN, Direction.LEFT, Direction.RIGHT};
		Direction togo = d[(int) (Math.random() * 3)];
		
		if(currRoom.getConnectRoom().get(togo) == null) 
			return new Pair<>(currRoom, togo);
		
		return findRoom(currRoom.getConnectRoom().get(togo));
	}
	
	public boolean isLegalCreate(Room room, ArrayList<Room> level) {
		return true;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}
	
	public int getCurrLevel() {
		return currLevel;
	}

	public void setCurrLevel(int currLevel) {
		this.currLevel = currLevel;
	}
	
	public ArrayList<ArrayList<Room>> getContainer() {
		return container;
	}

	public void setContainer(ArrayList<ArrayList<Room>> container) {
		this.container = container;
	}


}


