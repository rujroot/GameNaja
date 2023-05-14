package logic.pathfinding;

import data.Point;

public class Node {
    private Point pos;
    private int i, j;

    private double gCost = 0, hCost = 0 , Fcost = 0;
    private Node cameFrom;
    private boolean visitend = false;

    public Node(Point pos, int i, int j){
        this.setPos(pos);
        this.setI(i);
        this.setJ(j);
    }

    public void cal(Point start, Point end, double speed){
        
        double sqrtspeed = Math.sqrt(2) * speed;

        int Sx = (int) (start.getX() / speed);
        int Sy = (int) (start.getY() / speed);

        int Fx = (int) (end.getX() / speed);
        int Fy = (int) (end.getY() / speed);

        int Cx = (int) (pos.getX() / speed);
        int Cy = (int) (pos.getX() / speed);

        gCost = sqrtspeed * (Math.min(Cx - Sx, Cy - Sy)) + speed * (Math.max(Cx - Sx, Cy - Sy) - Math.min(Cx - Sx, Cy - Sy));
        hCost = sqrtspeed * (Math.min(Cx - Fx, Cy - Fy)) + speed * (Math.max(Cx - Fx, Cy - Fy) - Math.min(Cx - Fx, Cy - Fy));
        Fcost = gCost + hCost;
    }

    public void sum(){
        Fcost = gCost + hCost;
    }

    public Point getPos() {
        return pos;
    }

    public void setPos(Point pos) {
        this.pos = pos;
    }

    public double getgCost() {
        return gCost;
    }

    public void setgCost(double gCost) {
        this.gCost = gCost;
    }

    public double gethCost() {
        return hCost;
    }

    public void sethCost(double hCost) {
        this.hCost = hCost;
    }

    public double getFcost() {
        return Fcost;
    }

    public void setFcost(double fcost) {
        Fcost = fcost;
    }

    public Node getCameFrom() {
        return cameFrom;
    }

    public void setCameFrom(Node cameFrom) {
        this.cameFrom = cameFrom;
    }

    public boolean isVisitend() {
        return visitend;
    }

    public void setVisitend(boolean visitend) {
        this.visitend = visitend;
    }

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }

    public int getJ() {
        return j;
    }

    public void setJ(int j) {
        this.j = j;
    }

    

}
