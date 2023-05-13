package dungeon;

import java.util.ArrayList;
import java.util.HashMap;

import data.DataOre;
import data.Point;
import entity.MonsterType;
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
	private HashMap<Direction, Path> connectPath;
	private ArrayList<BaseOre> ores;
	private Size roomSize;

	private Image image = RenderableHolder.baseFloor;
	private Image sideWall = RenderableHolder.sideWall;
	private Image mainWall = RenderableHolder.mainWall;

	private double widthRoom = mainWall.getWidth();
	private double heightRoom = sideWall.getHeight();

	Size[] allSize = {Size.SMALL, Size.MEDUIM, Size.LARGE};
	OreType[] allType = {OreType.STONE, OreType.COAL, OreType.DIAMOND, OreType.GOLD, OreType.IRON};
	
	//create connect room
	public Room(Room parentRoom, Direction direction) {
		Size sizeRoom = allSize[(int) (Math.random() * 3)];
		if(sizeRoom.equals(Size.SMALL)) {
			this.setWidth(widthRoom * 10);
			this.setHeight(heightRoom * 10);
		}else if(sizeRoom.equals(Size.MEDUIM)) {
			this.setWidth(widthRoom * 15);
			this.setHeight(heightRoom * 15);
		}else {
			this.setWidth(widthRoom * 20);
			this.setHeight(heightRoom * 20);
		}
		
		Path path = parentRoom.getConnectPath().get(direction);
		Point pos = path.getPosition();
		if(direction.equals(Direction.UP)) {
			this.setPosition(new Point(pos.getX() + (path.getWidth() - this.getWidth()) / 2 , pos.getY() - this.getHeight() ));
		}else if(direction.equals(Direction.DOWN)){
			this.setPosition(new Point(pos.getX() + (path.getWidth() - this.getWidth()) / 2 , pos.getY() + path.getHeight() ));
		}else if(direction.equals(Direction.LEFT)) {
			this.setPosition(new Point(pos.getX() - this.getWidth(), pos.getY() + (path.getHeight() - this.getHeight()) / 2));
		}else {
			this.setPosition(new Point(pos.getX() + path.getWidth(), pos.getY() + (path.getHeight() - this.getHeight()) / 2));
		}
		
		this.setRoomSize(sizeRoom);
		connectRoom = new HashMap<>();
		connectPath = new HashMap<>();
		ores = new ArrayList<BaseOre>();
		generatePath();
	}

	//create spawn room
	public Room() {
		this.setWidth(widthRoom * 10);
		this.setHeight(heightRoom * 10);
		this.setPosition(new Point(0,0));

		this.setRoomSize(Size.SPAWN);
		connectPath = new HashMap<>();
		connectRoom = new HashMap<>();
		ores = new ArrayList<BaseOre>();
		generatePath();
	}

	public void generatePath(){
		connectPath.put(Direction.UP, new Path(this, Direction.UP));
		connectPath.put(Direction.LEFT, new Path(this, Direction.LEFT));
		connectPath.put(Direction.RIGHT, new Path(this, Direction.RIGHT));
		connectPath.put(Direction.DOWN, new Path(this, Direction.DOWN));
	}

	public void generateMonster(int amountMonster){
		ArrayList<MonsterType> types = MonsterType.getAllType();
		for(int i = 0; i < amountMonster; ++i){
			
		}
	}

	public void generateOre(int amountOre){

		for(int i = 0; i < amountOre; ++i){
			int chooseType = (int)(Math.random() * allType.length);
			OreType type = allType[chooseType];

			BaseOre baseOre;
			if(type.equals(OreType.STONE)){
				baseOre = new StoneOre(position, new DataOre(10, 1));
			}else if(type.equals(OreType.COAL)){
				baseOre = new CoalOre(position, new DataOre(10, 2));
			}else if(type.equals(OreType.IRON)){
				baseOre = new IronOre(position, new DataOre(10, 3));
			}else if(type.equals(OreType.GOLD)){
				baseOre = new GoldOre(position, new DataOre(10, 4));
			}else{
				baseOre = new DiamondOre(position, new DataOre(10, 5));
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

	@Override
	public void draw(GraphicsContext gc) {
		// Floor
		gc.drawImage(
			image, position.getX(),
			position.getY(), 
			this.getWidth(), 
			this.getHeight());
	
	}
	
	public void drawLeftWall(GraphicsContext gc){
		Point pos = this.getPosition();
		for(int i = 0; i < height / heightRoom; ++i){
			gc.drawImage(sideWall,
						pos.getX() - sideWall.getWidth(),
						pos.getY() + sideWall.getHeight() * i,
						sideWall.getWidth(),
						sideWall.getHeight());
		}
	}

	public void drawRightWall(GraphicsContext gc){
		Point pos = this.getPosition();
		for(int i = 0; i < height / heightRoom; ++i){
			gc.drawImage(sideWall,
						pos.getX() + width,
						pos.getY() + sideWall.getHeight() * i,
						sideWall.getWidth(),
						sideWall.getHeight());
		}
	}

	public void drawUpWall(GraphicsContext gc){
		Point pos = this.getPosition();
		for(int i = 0; i < width / widthRoom; ++i){
			gc.drawImage(mainWall,
						pos.getX() + mainWall.getWidth() * i,
						pos.getY() - mainWall.getHeight(),
						mainWall.getWidth(),
						mainWall.getHeight());
		}
		gc.drawImage(sideWall,
						pos.getX() - sideWall.getWidth(),
						pos.getY() - mainWall.getHeight(),
						sideWall.getWidth(),
						sideWall.getHeight());
		gc.drawImage(sideWall,
						pos.getX() + width,
						pos.getY() - mainWall.getHeight(),
						sideWall.getWidth(),
						sideWall.getHeight());
	}

	public void drawDownWall(GraphicsContext gc){
		Point pos = this.getPosition();
		for(int i = 0; i < width / widthRoom; ++i){
			gc.drawImage(mainWall,
						pos.getX() + mainWall.getWidth() * i,
						pos.getY() + height,
						mainWall.getWidth(),
						mainWall.getHeight());
		}
		gc.drawImage(mainWall,
						pos.getX() - sideWall.getWidth(),
						pos.getY() + height ,
						mainWall.getWidth(),
						mainWall.getHeight());
		gc.drawImage(mainWall,
						pos.getX() + + mainWall.getWidth() * (width / widthRoom - 1) + sideWall.getWidth(),
						pos.getY() + height,
						mainWall.getWidth(),
						mainWall.getHeight());
	}


	public double getWidthWall() {
		return sideWall.getWidth();
	}

	public double getHeightWall() {
		return mainWall.getHeight();
	}

	public Size getRoomSize() {
		return roomSize;
	}

	public void setRoomSize(Size roomSize) {
		this.roomSize = roomSize;
	}

	public HashMap<Direction, Path> getConnectPath() {
		return connectPath;
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
