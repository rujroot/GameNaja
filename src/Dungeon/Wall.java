package dungeon;

public class Wall {
    private double Up, Down, Left, Right;

    public Wall(){
        Up = 0;
        Down = 0;
        Left = 0;
        Right = 0;
    }

    public void add(Direction direction, double width, double height){
        if(direction.equals(Direction.UP)){
            this.setUp(width);
        }else if(direction.equals(Direction.DOWN)){
            this.setDown(width);
        }else if(direction.equals(Direction.LEFT)){
            this.setLeft(height);
        }else{
            this.setRight(height);
        }
    }

    public double getUp() {
        return Up;
    }

    public void setUp(double up) {
        Up = up;
    }

    public double getDown() {
        return Down;
    }

    public void setDown(double down) {
        Down = down;
    }

    public double getLeft() {
        return Left;
    }

    public void setLeft(double left) {
        Left = left;
    }

    public double getRight() {
        return Right;
    }

    public void setRight(double right) {
        Right = right;
    }

    

    


}
