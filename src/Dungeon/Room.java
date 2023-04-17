package Dungeon;

import java.util.ArrayList;
import java.util.HashMap;

import Data.DataOre;
import Data.Point;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import logic.Hitbox;
import logic.IRenderable;
import logic.Main;
import logic.RenderableHolder;
import ore.BaseOre;
import ore.CoalOre;
import ore.DiamondOre;
import ore.GoldOre;
import ore.IronOre;
import ore.OreType;
import ore.StoneOre;

public class Room implements IRenderable {
	private double width, height;
	private Point position;
	private HashMap<Direction, Room> connectRoom;
	private Color color;
	private ArrayList<BaseOre> ores;
	
	Size[] allSize = {Size.SMALL, Size.MEDUIM, Size.LARGE};
	OreType[] allType = {OreType.STONE, OreType.COAL, OreType.DIAMOND, OreType.GOLD, OreType.IRON};
	
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
		ores = new ArrayList<BaseOre>();
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
		ores = new ArrayList<BaseOre>();

		Color[] allColor = {Color.BLACK, Color.BLUE, Color.CYAN, Color.GRAY, Color.GREEN, 
				Color.MAGENTA, Color.ORANGE, Color.PINK, Color.RED, Color.YELLOW};
		this.color = allColor[(int) (Math.random() * allColor.length)];
	}
	
	public void generateOre(int amountOre){

		for(int i = 0; i < amountOre; ++i){
			int chooseType = (int)(Math.random() * allType.length);
			OreType type = allType[chooseType];

			BaseOre baseOre;
			if(type.equals(OreType.STONE)){
				baseOre = new StoneOre(position, new DataOre(10, 10));
			}else if(type.equals(OreType.COAL)){
				baseOre = new CoalOre(position, new DataOre(10, 10));
			}else if(type.equals(OreType.IRON)){
				baseOre = new IronOre(position, new DataOre(10, 10));
			}else if(type.equals(OreType.GOLD)){
				baseOre = new GoldOre(position, new DataOre(10, 10));
			}else{
				baseOre = new DiamondOre(position, new DataOre(10, 10));
			}

			Point newPos = new Point(position.getX() + (Math.random() * (width - baseOre.getWidth()) ), 
									position.getY() + (Math.random() * (height - baseOre.getHeight()) ) );
			baseOre.setPosition(newPos);
			createOre(baseOre);
		}
		
	}

	public void createOre(BaseOre ore){
		// can't spawn ore due to intersection with other ore
		for(BaseOre thatOre : ores){
			if(!isLegal(thatOre, ore)) {
				return;
			}
		}
		// Can create ore
		ore.setVisible(false);
		ores.add(ore);
		Main.getLogic().addObject(ore);

	}

	private boolean isLegal(BaseOre oreA, BaseOre oreB){
		Hitbox A = new Hitbox(oreA.getPosition(), oreA.getWidth(), oreA.getHeight());
		Hitbox B = new Hitbox(oreB.getPosition(), oreB.getWidth(), oreB.getHeight());

		return !A.isIntersect(B);
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

	public ArrayList<BaseOre> getOres() {
		return ores;
	}

	@Override
	public void draw(GraphicsContext gc) {
		Image image = RenderableHolder.baseFloor;
		

		for(int i = 0; i < this.getWidth() / image.getWidth(); ++i){
			for(int j = 0; j < this.getHeight() / image.getHeight(); ++j){
				if(image.getWidth() * (i + 1) > this.getWidth() && image.getHeight() * (j + 1) > this.getHeight() ){
					gc.drawImage(
						image, position.getX() + image.getWidth() * i,
						position.getY() + image.getHeight() * j, 
						this.getWidth() - image.getWidth() * i, 
						this.getHeight() - image.getHeight() * j);	
				}else if(image.getWidth() * (i + 1) > this.getWidth()){
					gc.drawImage(
						image, position.getX() + image.getWidth() * i,
						position.getY() + image.getHeight() * j, 
						this.getWidth() - image.getWidth() * i, 
						image.getHeight());
				}else if(image.getHeight() * (j + 1) > this.getHeight()){
					gc.drawImage(
						image, position.getX() + image.getWidth() * i,
						position.getY() + image.getHeight() * j, 
						image.getWidth(), 
						this.getHeight() - image.getHeight() * j);
				}else{
					gc.drawImage(
						image, position.getX() + image.getWidth() * i,
						position.getY() + image.getHeight() * j, 
						image.getWidth(), 
						image.getHeight());	
				}

				
			}
		}

	}
	
	@Override
	public int getZ() {
		return 10;
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
