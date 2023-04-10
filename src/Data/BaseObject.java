package Data;

import logic.IRenderable;

public abstract class BaseObject implements IRenderable{
    private double width, height;
    private Point position;
    private int z;
	private boolean visible = true, destroyed = false;

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
    public Point getPosition() {
        return position;
    }
    public void setPosition(Point position) {
        this.position = position;
    }

    public void setZ(int z) {
        this.z = z;
    }
    public void setVisible(boolean visible) {
        this.visible = visible;
    }
    public void setDestroyed(boolean destroyed) {
        this.destroyed = destroyed;
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

    
}
