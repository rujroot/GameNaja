package logic.pathfinding;

import java.util.ArrayList;

import data.Point;
import entity.Entity;

public class PathFinding {

    private Entity entity;

    public PathFinding(Entity entity){
        this.setEntity(entity);
    }

    public Point walkTo(Point end){

        Point start = entity.getPosition();
        Node[][] node = init(start, end);

        double speed = entity.getData().getSpd();

        double Lx = start.getX() - end.getX();
        double Ly = start.getY() - end.getY();

        int n = (int) (Math.abs(Ly) / speed);
        int m = (int) (Math.abs(Lx) / speed);

        ArrayList<Node> toFind = new ArrayList<>();
        toFind.add(node[0][0]);

        Node prev = node[0][0];

        while(true){

            Node u = toFind.get(0);
            int size = toFind.size();
    
            //Choosing ndoe
            for(int i = 1; i < size; ++i){
                Node v = toFind.get(i);
                if(u.getFcost() < v.getFcost() || 
                  (u.getFcost() == v.getFcost() && v.gethCost() < u.gethCost())){
                    u = v;
                }
            }

            //if it is node that we looking
            if(u.getPos().equals(end)) break;

            //Looking to neighbor
            int i = u.getI(), j = u.getJ();

            for(int a = -1; a <= 1; ++a){
                for(int b = -1; b <= 1; ++b){
                    if(a == 0 && b == 0) continue;
                    if(i + a > n || i + a < 0 || j + b > m || j + b < 0) continue;

                    


                }
            }



        }

    }

    public Node[][] init(Point start, Point end){
        double speed = entity.getData().getSpd();

        double Lx = start.getX() - end.getX();
        double Ly = start.getY() - end.getY();

        int n = (int) (Math.abs(Ly) / speed);
        int m = (int) (Math.abs(Lx) / speed);

        Node[][] node = new Node[n + 5][m + 5];

        for(int i = 0; i < n; ++i){
            for(int j = 0; j < m; ++j){

                node[i][j] = new Node(new Point(start.getX() - ((Lx / m) * j), start.getY() - ((Ly / n) * i) ), i, j);

                node[i][j].setgCost( node[i][j].getPos().distant(start) );
                node[i][j].sethCost( node[i][j].getPos().distant(end) );
                node[i][j].sum();
            }
        }

        return node;

    }

    public Entity getEntity() {
        return entity;
    }

    public void setEntity(Entity entity) {
        this.entity = entity;
    }
    

}
