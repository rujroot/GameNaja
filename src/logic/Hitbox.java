package logic;

public class Hitbox {
	private int centerPoX, centerPoY, lenght, wide;
	
	public Hitbox(int centerPoX, int centerPoY, int lenght, int wide) {
		this.setCenterPoX(centerPoX);
		this.setCenterPoY(centerPoY);
		this.setLenght(lenght);
		this.setWide(wide);
	}

	public int getCenterPoX() {
		return centerPoX;
	}

	public void setCenterPoX(int centerPoX) {
		this.centerPoX = centerPoX;
	}

	public int getCenterPoY() {
		return centerPoY;
	}

	public void setCenterPoY(int centerPoY) {
		this.centerPoY = centerPoY;
	}

	public int getLenght() {
		return lenght;
	}

	public void setLenght(int lenght) {
		this.lenght = lenght;
	}

	public int getWide() {
		return wide;
	}

	public void setWide(int wide) {
		this.wide = wide;
	}
}
