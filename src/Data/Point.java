package data;

public class Point {
	private double X, Y;
	public Point(double X, double Y) {
		this.setX(X);
		this.setY(Y);
	}
	public double getX() {
		return X;
	}
	public void setX(double x) {
		X = x;
	}
	public double getY() {
		return Y;
	}
	public void setY(double y) {
		Y = y;
	}
	public String toString() {
		return "X = " + this.getX() + " " + "Y = " + this.getY() ;
	}
	public boolean equals(Point P){
		return this.getX() == P.getX() && this.getY() == P.getY();
	}

	public void unit(){
		double dis = Math.sqrt((this.X * this.X) + (this.Y * this.Y));
		this.setX(X / dis);
		this.setY(Y / dis);
	}
	public void multiply(double value){
		this.setX(X * value);
		this.setY(Y * value);
	}
	public double distant(Point P){
		double X = (this.X - P.getX());
		double Y = (this.Y - P.getY());
		return Math.sqrt((X * X) + (Y * Y));
	}

}
