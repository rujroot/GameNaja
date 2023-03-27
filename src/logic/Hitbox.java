package logic;

import Math.Point;

public class Hitbox {
	private Point Center;
	private double length, width;
	
	public Hitbox(Point Center, double length, double width) {
		this.setCenter(Center);
		this.setLength(length);
		this.setWidth(width);
	}
	
	public Point getTopleft() {	
		double X = Center.getX() - (width / 2);
		double Y = Center.getY() - (length / 2);
		Point TL = new Point(X, Y);
		return TL;
	}
	
	public boolean isIntersect(Hitbox A, Hitbox B) {
		Point TLA = A.getTopleft();
		double lengthA = A.getLength();
		double widthA = A.getWidth();
		
		Point TLB = B.getTopleft();
		double lengthB = B.getLength();
		double widthB = B.getWidth();
		
		// Intersect X
		if(Math.min( TLA.getX() + widthA, TLB.getX() + widthB) >= Math.max(TLA.getX(), TLB.getX())) {
			// Intersect Y
			if(Math.min( TLA.getY() + lengthA, TLB.getY() + lengthB) >= Math.max(TLA.getY(), TLB.getY())) {
				return true;
			}
			return false;
		}
		return false;
		
			
	}
	
	public double getLength() {
		return length;
	}

	public void setLength(double length) {
		this.length = length;
	}

	public double getWidth() {
		return width;
	}

	public void setWidth(double width) {
		this.width = width;
	}

	public Point getCenter() {
		return Center;
	}

	public void setCenter(Point center) {
		Center = center;
	}

}
