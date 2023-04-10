package Dungeon;

import java.util.HashMap;

import Data.Point;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import logic.IRenderable;

public class Room implements IRenderable {
	private double width, height;
	private Point position;
	private HashMap<Direction, Room> connectRoom;
	private Color color;
	
	Size[] allSize = {Size.SMALL, Size.MEDUIM, Size.LARGE};
	
	public Room(Room parentRoom, Direction direction) {
		Size sizeRoom = allSize[(int) (Math.random() * 3)];
		if(sizeRoom.equals(Size.SMALL)) {
			this.setWidth(640 + Math.random() * 300);
			this.setHeight(480 + Math.random() * 300);
		}else if(sizeRoom.equals(Size.MEDUIM)) {
			this.setWidth(960 + Math.random() * 300);
			this.setHeight(720 + Math.random() * 300);
		}else {
			this.setWidth(1920 + Math.random() * 300);
			this.setHeight(1440 + Math.random() * 300);
		}
		
		Point pos = parentRoom.getPosition();
		if(direction.equals(Direction.UP)) {
			this.setPosition(new Point(pos.getX() + (parentRoom.getWidth() - this.getWidth()) / 2 , pos.getY() - this.getHeight() ));
		}else if(direction.equals(Direction.DOWN)){
			this.setPosition(new Point(pos.getX() + (parentRoom.getWidth() - this.getWidth()) / 2 , pos.getY() + parentRoom.getHeight() ));
		}else if(direction.equals(Direction.LEFT)) {
			this.setPosition(new Point(pos.getX() - this.getWidth(), pos.getY() + (parentRoom.getHeight() - this.getHeight()) / 2));
		}else {
			this.setPosition(new Point(pos.getX() + parentRoom.getWidth(), pos.getY() + (parentRoom.getHeight() - this.getHeight()) / 2));
		}
		
		
		connectRoom = new HashMap<>();
		Color[] allColor = {Color.BLACK, Color.BLUE, Color.CYAN, Color.GRAY, Color.GREEN, 
				Color.MAGENTA, Color.ORANGE, Color.PINK, Color.RED, Color.YELLOW};
		this.color = allColor[(int) (Math.random() * allColor.length)];
	}

	public Room() {
		Size sizeRoom = allSize[(int) (Math.random() * 2)];
		if(sizeRoom.equals(Size.SMALL)) {
			this.setWidth(320);
			this.setHeight(240);
		}else if(sizeRoom.equals(Size.MEDUIM)) {
			this.setWidth(640);
			this.setHeight(480);
		}else {
			this.setWidth(960);
			this.setHeight(720);
		}
		
		this.setPosition(new Point(0,0));
		connectRoom = new HashMap<>();
		
		Color[] allColor = {Color.BLACK, Color.BLUE, Color.CYAN, Color.GRAY, Color.GREEN, 
				Color.MAGENTA, Color.ORANGE, Color.PINK, Color.RED, Color.YELLOW};
		this.color = allColor[(int) (Math.random() * allColor.length)];
	}
	
	public Point getPosition() {
		return position;
	}

	public void setPosition(Point position) {
		this.position = position;
	}

	public HashMap<Direction, Room> getConnectRoom() {
		return connectRoom;
	}

	public void setConnectRoom(HashMap<Direction, Room> connectRoom) {
		this.connectRoom = connectRoom;
	}

	public double getWidth() {
		return width;
	}

	public void setWidth(double width) {
		this.width = width;
	}

	public double getHeight() {
		return height;
	}

	public void setHeight(double height) {
		this.height = height;
	}

	@Override
	public void draw(GraphicsContext gc) {
		gc.setFill(color);
		gc.fillRect(position.getX(), position.getY(), width, height);
		//System.out.println(position.getX() + " " + position.getY() + " " + width + " " + height);
	}
	
	@Override
	public int getZ() {
		return 0;
	}

	@Override
	public boolean isDestroyed() {
		return false;
	}

	@Override
	public boolean isVisible() {
		return true;
	}
}
