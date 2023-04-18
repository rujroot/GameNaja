package logic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;

public class RenderableHolder {
	private static final RenderableHolder instance = new RenderableHolder();
	
	private List<IRenderable> objects;
	private Comparator<IRenderable> comparator;

	public static Image Tileset, baseFloor, ores, character, atlas, sideWall, mainWall;


	static {
		//Load Resource first time
		loadResource();
	}

	//set compare follow by Z
	public RenderableHolder() {
		objects = new ArrayList<IRenderable>();
		
		comparator = (IRenderable o1, IRenderable o2) -> {
			if (o1.getZ() > o2.getZ())
				return 1;
			return -1;
		};
	}
	
	//add object to game
	public void add(IRenderable object) {
		//System.out.println("add ");
		objects.add(object);
		Collections.sort(objects, comparator);
	}
	
	public static void loadResource() {
		Tileset = new Image(ClassLoader.getSystemResource("res/image/DungeonTileset2.png").toString());
		baseFloor = new Image(ClassLoader.getSystemResource("res/image/floor_light.png").toString());
		//baseFloor = new Image(ClassLoader.getSystemResource("res/image/baseFloor.png").toString());
		ores = new Image(ClassLoader.getSystemResource("res/image/ores.png").toString());
		character = new Image(ClassLoader.getSystemResource("res/image/character2.png").toString());
		atlas = new Image(ClassLoader.getSystemResource("res/image/atlas2.png").toString());

		sideWall = new WritableImage(RenderableHolder.Tileset.getPixelReader(), 304 * 4, 12 * 4, 4 * 4, 32 * 4);
		mainWall = new WritableImage(RenderableHolder.Tileset.getPixelReader(), 1236, 176, 87, 79);
	}
	
	// Update when entity remove
	public void update() {
		for (int i = objects.size() - 1; i >= 0; i--) {
			if (objects.get(i).isDestroyed())
				objects.remove(i);
		}
	}
	
	// share instance to every calss
	public static RenderableHolder getInstance() {
		return instance;
	}

	public List<IRenderable> getObjects() {
		return objects;
	}

}
