package logic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javafx.scene.image.Image;

public class RenderableHolder {
	private static final RenderableHolder instance = new RenderableHolder();
	
	private List<IRenderable> objects;
	private Comparator<IRenderable> comparator;

	public static Image Tileset, baseFloor, ores;


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
		Tileset = new Image(ClassLoader.getSystemResource("res/image/Dungeon_Tileset.png").toString());
		baseFloor = new Image(ClassLoader.getSystemResource("res/image/baseFloor.png").toString());
		ores = new Image(ClassLoader.getSystemResource("res/image/ores.png").toString());
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
