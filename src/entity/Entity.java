package entity;

import Data.BaseObject;
import Data.DataEntity;
import Data.Point;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public abstract class Entity extends BaseObject {
	private String name;
	private DataEntity data;
	
	public Entity(String name, double height, double width, DataEntity data){
		this.setName(name);
		this.setData(data);
		this.setWidth(width);
		this.setHeight(height);
		this.setPosition(new Point(Math.random() * 640 , Math.random() * 480));
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

	
}
