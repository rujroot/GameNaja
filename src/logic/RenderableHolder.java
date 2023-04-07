package logic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import equipment.projectile.Arrow;

public class RenderableHolder {
	private static final RenderableHolder instance = new RenderableHolder();
	
	private List<IRenderable> entities;
	private List<IRenderable> arrows;
	private Comparator<IRenderable> comparator;
	
	static {
		//Load Resource first time
		loadResource();
	}

	//set compare follow by Z
	public RenderableHolder() {
		entities = new ArrayList<IRenderable>();
		arrows = new ArrayList<IRenderable>();
		comparator = (IRenderable o1, IRenderable o2) -> {
			if (o1.getZ() > o2.getZ())
				return 1;
			return -1;
		};
	}
	
	//add entity to game
	public void add(IRenderable entity) {
		System.out.println("add");
		entities.add(entity);
		Collections.sort(entities, comparator);
	}

	//add not-entity to game
	public void add(IRenderable entity, String type) {
		System.out.println("add " + type);
		if(type == "Arrow"){
			arrows.add(entity);
			Collections.sort(entities, comparator);
		}
	}
	
	public static void loadResource() {
		///TODO
	}
	
	// Update when entity remove
	public void update() {
		for (int i = entities.size() - 1; i >= 0; i--) {
			if (entities.get(i).isDestroyed())
				entities.remove(i);
		}
		for (int i = arrows.size() - 1; i >= 0; i--) {
			if (arrows.get(i).isDestroyed())
				arrows.remove(i);
		}
	}
	
	// share instance to every calss
	public static RenderableHolder getInstance() {
		return instance;
	}

	public List<IRenderable> getEntities() {
		return entities;
	}

	public List<IRenderable> getArrows() {
		return arrows;
	}
	


}
