package Data;

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
	public void unit(){
		double dis = Math.sqrt((this.X * this.X) + (this.Y * this.Y));
		this.setX(X / dis);
		this.setY(Y / dis);
	}
	public void multiply(double value){
		this.setX(X * value);
		this.setY(Y * value);
	}

}
