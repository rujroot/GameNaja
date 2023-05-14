package logic.pathfinding;

import java.util.ArrayList;

import data.Point;
import entity.Entity;
import javafx.util.Pair;

public class PathFinding {

    private Entity entity;

    public PathFinding(Entity entity){
        this.setEntity(entity);
    }

    // public Node walkTo(Point end){

    //     Point start = entity.getPosition();

    //     double speed = entity.getData().getSpd();
    //     double sqrtspeed = Math.sqrt(2) * speed;
       
    //     int Sx = (int) (start.getX() / speed);
    //     int Sy = (int) (start.getY() / speed);

    //     int Fx = (int) (end.getX() / speed);
    //     int Fy = (int) (end.getY() / speed);

    //     ArrayList<Pair<Integer, Integer>> visited = new ArrayList<>();
    //     ArrayList<Node> toFind = new ArrayList<>();
    //     Node startNode = new Node(start, Sx, Sy);
    //     startNode.cal(start, end, speed);
    //     toFind.add(startNode);
    //     System.out.println("Goal " + Fx + " " + Fy);

    //     while(toFind.size() != 0){
    //         Node u = toFind.get(0);
    //         toFind.remove(0);

    //         int i = u.getI(), j = u.getJ();
            
    //         if(checkVisited(visited, i, j)) continue;
    //         visited.add(new Pair<Integer,Integer>(i, j));

    //         int size = toFind.size();
    //         System.out.println(size);

    //         //Choosing node
    //         for(int k = 0; k < size; ++k){
    //             Node v = toFind.get(k);
    //             if(u.getFcost() > v.getFcost() || 
    //               (u.getFcost() == v.getFcost() && v.gethCost() < u.gethCost())){
    //                 u = v;
    //             }
    //         }

    //         //System.out.println(u.getI() + " " + u.getJ());
    //         //if(size > 100) System.out.println(toFind.get(10000));

    //         //if it is node that we looking
    //         System.out.println(u.getI() + " " + u.getJ() + " " + Fx + " " + Fy);
    //         if(u.getI() == Fx && u.getJ() == Fy){
    //             System.out.println("Found");
    //             return u;
    //         }

    //         //Looking to neighbor
    //         for(int a = -1; a <= 1; ++a){
    //             for(int b = -1; b <= 1; ++b){
    //                 if(a == 0 && b == 0) continue;

    //                 //Check visited
    //                 if(checkVisited(visited, i + a, j + b)) continue;
    //                 Point pos;
    //                 if(a == 1 && b == 1) pos = new Point(start.getX() + sqrtspeed, start.getY() + sqrtspeed);
    //                 else if(a == 1 && b == 0) pos = new Point(start.getX(), start.getY() + speed);
    //                 else if(a == 1 && b == -1) pos = new Point(start.getX() - sqrtspeed, start.getY() + sqrtspeed);
    //                 else if(a == 0 && b == 1) pos = new Point(start.getX() + speed, start.getY());
    //                 else if(a == 0 && b == -1) pos = new Point(start.getX() - speed, start.getY());
    //                 else if(a == -1 && b == 1) pos = new Point(start.getX() + sqrtspeed, start.getY() - sqrtspeed);
    //                 else if(a == -1 && b == 0) pos = new Point(start.getX(), start.getY() - speed);
    //                 else  pos = new Point(start.getX() - sqrtspeed, start.getY() - sqrtspeed);
                    
    //                 //System.out.println(i + " " + j + " " + a + " " + b + " " + n + " " + m);
    //                 Node newNode = new Node(pos
    //                                         , i + a
    //                                         , j + b);
    //                 newNode.cal(start, end, speed);
    //                 toFind.add(newNode);
    //                 newNode.setCameFrom(u);

    //             }
    //         }
    //     }

    //     return new Node(start, Fx, Fy);

    // }

    // public boolean checkVisited(ArrayList<Pair<Integer, Integer>> visited, int i, int j){
    //     int size = visited.size();
    //     for(int k = 0; k < size; ++k){
    //         Pair<Integer, Integer> ij = visited.get(k);
    //         if(ij.getKey() == i && ij.getValue() == j) return true;
    //     }
    //     return false;
    // }

    // public Node[][] init(Point start, Point end){
    //     double speed = entity.getData().getSpd();

    //     int Sx = (int) (start.getX() / speed);
    //     int Sy = (int) (start.getY() / speed);

    //     int Fx = (int) (end.getX() / speed);
    //     int Fy = (int) (end.getY() / speed);

    //     //Node[][] node = new Node[n + 5][m + 5];

    //     for(int i = 0; i <= n; ++i){
    //         for(int j = 0; j <= m; ++j){

    //             node[i][j] = new Node(new Point(start.getX() - ((Lx / m) * j), start.getY() - ((Ly / n) * i) ), i, j);

    //             node[i][j].setgCost( node[i][j].getPos().distant(start) );
    //             node[i][j].sethCost( node[i][j].getPos().distant(end) );
    //             node[i][j].sum();
    //         }
    //     }

    //     return node;

    // }

    public Entity getEntity() {
        return entity;
    }

    public void setEntity(Entity entity) {
        this.entity = entity;
    }
    

}
