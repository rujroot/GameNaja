package entity;

import Math.DataEntity;
import Math.Point;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import logic.IRenderable;

public abstract class Entity implements IRenderable {
	private String name;
	private double  Height, Width;
	private DataEntity data;
	private Point position;
	private int z;
	private boolean visible, destroyed;
	
	public Entity(String name, double Height, double Width, DataEntity data){
		this.setName(name);
		this.setData(data);
		this.setWidth(Width);
		this.setHeight(Height);
		this.setPosition(new Point(Math.random() * 640 , Math.random() * 480));
		this.visible = true;
		this.destroyed = false;
	}
	
	public abstract void attack();

	public void drawHP(GraphicsContext gc) {
		DataEntity data = this.getData();
		double maxHP = data.getMaxHP(), currHP = data.getHp();
		Point pos = this.getPosition();
		gc.setFill(Color.WHITE);
		gc.fillRect(pos.getX(), pos.getY() - 20, this.getWidth(), 10);
		gc.setFill(Color.LIGHTGREEN);
		gc.fillRect(pos.getX(), pos.getY() - 20, (currHP / maxHP) * (this.getWidth()), 10);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public DataEntity getData() {
		return data;
	}

	public void setData(DataEntity data) {
		this.data = data;
	}

	public void setDestroyed(boolean destroyed) {
		this.destroyed = destroyed;
	}

	public double getHeight() {
		return Height;
	}

	public void setHeight(double height) {
		Height = height;
	}

	public double getWidth() {
		return Width;
	}

	public void setWidth(double width) {
		Width = width;
	}

	@Override
	public boolean isDestroyed(){
		return destroyed;
	}
	
	@Override
	public boolean isVisible(){
		return visible;
	}
	
	@Override
	public int getZ(){
		return z;
	}

	public Point getPosition() {
		return position;
	}

	public void setPosition(Point position) {
		this.position = position;
	}
	
	
}
