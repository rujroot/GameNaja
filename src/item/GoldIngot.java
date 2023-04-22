package item;

import Data.Point;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import logic.RenderableHolder;

public class GoldIngot extends Item {
	private WritableImage image = new WritableImage(RenderableHolder.ores.getPixelReader(), 32 * 6, 32 * 4, 32, 32);
    public GoldIngot(Point position, double width, double height) {
        super(position, width, height);
        this.setName("Gold Ingot");
    }

    public GoldIngot(Point position, double width, double height, int z) {
        super(position, width, height, z);
        this.setName("Gold Ingot");
    }

    @Override
    public void draw(GraphicsContext gc) {
    	Point pos = this.getPosition();
//        gc.setFill(Color.YELLOW);
//		gc.fillRect(pos.getX(), pos.getY(), this.getWidth(), this.getHeight());
		gc.drawImage(image, pos.getX()+5, pos.getY(), (int)(image.getWidth()*1.65) , (int)((image.getHeight()*1.65)));

        gc.setFont(new Font("Arial", 16));
        gc.setFill(Color.BLACK);
        gc.fillText(Integer.toString(getAmount()), pos.getX()-3 + this.getWidth(), pos.getY() + this.getHeight(), 100);
    }
}
