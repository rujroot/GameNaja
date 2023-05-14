package animation;

import java.io.File;

import data.Point;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;

public class ImageAnimation extends AnimationObject {

    private Point offSet;
    private Image[][] image;
    private int n = 0;

    public ImageAnimation(Point position, double width, double height, Point size) {
        super(position, width, height);
        this.setOffSet(offSet);
        this.image = new WritableImage[(int)(size.getY())][(int)(size.getX())];
    }  

    public void loadAnimation(File[] files){
        int i = 0;
        for (File file : files) {
            if (!file.isFile()) continue;
            System.out.println(file.getPath());
            this.image[n][i] = new Image(ClassLoader.getSystemResource(file.getPath()).toString());;
            i++;
        }
    }

    @Override
    public void draw(GraphicsContext gc) {
        
    }

    public Point getOffSet() {
        return offSet;
    }

    public void setOffSet(Point offSet) {
        this.offSet = offSet;
    }

}
