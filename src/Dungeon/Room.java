package Dungeon;

import java.util.ArrayList;
import java.util.HashMap;

import Data.DataOre;
import Data.Point;
import drawing.GameScreen;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
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
	private ArrayList<BaseOre> ores;
	private Wall wall;

	private Image image = RenderableHolder.baseFloor;
	private Image sideWall = RenderableHolder.sideWall;
	private Image mainWall = RenderableHolder.mainWall;

	Size[] allSize = {Size.SMALL, Size.MEDUIM, Size.LARGE};
	OreType[] allType = {OreType.STONE, OreType.COAL, OreType.DIAMOND, OreType.GOLD, OreType.IRON};
	
	public Room(Room parentRoom, Direction direction) {
		Size sizeRoom = allSize[(int) (Math.random() * 3)];
		if(sizeRoom.equals(Size.SMALL)) {
			this.setWidth(640 + Math.random() * 0);
			this.setHeight(480 + Math.random() * 0);
		}else if(sizeRoom.equals(Size.MEDUIM)) {
			this.setWidth(960 + Math.random() * 0);
			this.setHeight(720 + Math.random() * 0);
		}else {
			this.setWidth(1440 + Math.random() * 0);
			this.setHeight(1080 + Math.random() * 0);
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
		wall = new Wall();
	}

	public Room() {
		this.setWidth(640);
		this.setHeight(480);
		this.setPosition(new Point(0,0));
		connectRoom = new HashMap<>();
		ores = new ArrayList<BaseOre>();
		wall = new Wall();
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
		// Floor
		gc.drawImage(
			image, position.getX(),
			position.getY(), 
			this.getWidth(), 
			this.getHeight());
		
		// anathor wall
		// this.drawUpWall(gc);
		// this.drawLeftWall(gc);
		// this.drawRightWall(gc);
		// this.drawDownWall(gc);
	}
	
	public void drawLeftWall(GraphicsContext gc){
		GameScreen.drawImage(sideWall, new Point(position.getX() - sideWall.getWidth() / 2, position.getY() - mainWall.getHeight() / 2), sideWall.getWidth() , height + mainWall.getHeight());

		// if(leftRoom == null){
		// 	GameScreen.drawImage(sideWall, new Point(position.getX(), position.getY()), sideWall.getWidth(), height);
		// }else if(leftRoom.getHeight() < height){
		// 	double length = height - leftRoom.getHeight();

		// 	Point start = new Point(position.getX() - sideWall.getWidth(), position.getY());
		// 	Point newpos = new Point(position.getX() - sideWall.getWidth(), position.getY() + height - length / 2);

		// 	GameScreen.drawImage(sideWall, start, sideWall.getWidth(), length / 2);
		// 	GameScreen.drawImage(sideWall, newpos, sideWall.getWidth(), length / 2);
		// }
	}

	public void drawRightWall(GraphicsContext gc){

		GameScreen.drawImage(sideWall, new Point(position.getX() + getWidth() - sideWall.getWidth() / 2, position.getY() - mainWall.getHeight() / 2), sideWall.getWidth(), height + mainWall.getHeight());
		// if(rightRoom == null){
		// 	GameScreen.drawImage(sideWall, new Point(position.getX()  + this.getWidth() , position.getY()), sideWall.getWidth(), height);
		// }else if(rightRoom.getHeight() < height){
		// 	double length = height - rightRoom.getHeight();

		// 	Point start = new Point(position.getX()  + this.getWidth() - sideWall.getWidth() , position.getY());
		// 	Point newpos = new Point(position.getX()  + this.getWidth() - sideWall.getWidth() , position.getY() + height - length / 2);

		// 	GameScreen.drawImage(sideWall, start, sideWall.getWidth(), length / 2);
		// 	GameScreen.drawImage(sideWall, newpos, sideWall.getWidth(), length / 2);
		// }

	}

	public void drawUpWall(GraphicsContext gc){
		GameScreen.drawImage(mainWall, new Point(position.getX(), position.getY() - mainWall.getHeight() / 2), width, mainWall.getHeight());
		// if(upRoom == null){
		// 	GameScreen.drawImage(mainWall, new Point(position.getX(), position.getY()), width, mainWall.getHeight());
		// }else if(upRoom.getWidth() < width){
		// 	double length = width - upRoom.getHeight();

		// 	Point start = new Point(position.getX(), position.getY());
		// 	Point newpos = new Point(position.getX() + width - length / 2, position.getY());

		// 	GameScreen.drawImage(mainWall, start, length / 2, mainWall.getHeight());
		// 	GameScreen.drawImage(mainWall, newpos, length / 2, mainWall.getHeight());
		// }
	}

	public void drawDownWall(GraphicsContext gc){
		Room downRoom = connectRoom.get(Direction.DOWN);
		GameScreen.drawImage(mainWall, new Point(position.getX(), position.getY() + height - mainWall.getHeight() / 2), width, mainWall.getHeight());
		// if(downRoom == null){
		// 	GameScreen.drawImage(mainWall, new Point(position.getX(), position.getY() + height - mainWall.getHeight()), width, mainWall.getHeight());
		// }else if(downRoom.getWidth() < width){
		// 	double length = width - downRoom.getHeight();

		// 	Point start = new Point(position.getX(), position.getY() + height - mainWall.getHeight());
		// 	Point newpos = new Point(position.getX() + width - length / 2 , position.getY() + height - mainWall.getHeight());

		// 	GameScreen.drawImage(mainWall, start, length / 2, mainWall.getHeight());
		// 	GameScreen.drawImage(mainWall, newpos, length / 2, mainWall.getHeight());
		// }
	}

	public Wall getWall() {
		return wall;
	}

	public void setWall(Wall wall) {
		this.wall = wall;
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
