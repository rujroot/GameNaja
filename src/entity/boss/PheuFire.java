package entity.boss;

import animation.AnimationController;
import animation.ImageAnimation;
import data.DataEntity;
import data.Point;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import logic.Hitbox;
import logic.RenderableHolder;

public class PheuFire extends BossEntity{

    private ImageAnimation[] imageAnimation = new ImageAnimation[10];

    public PheuFire(String name, DataEntity data) {
        super(name, data, new Hitbox(new Point(310, 160), 260, 310));

        Image[][] demonSlime = RenderableHolder.imageAnimation.get("DemonSlime");

        imageAnimation[0] = new ImageAnimation(this.getPosition(), this.getWidth(), this.getHeight(), 
                            demonSlime[0], 10, 7);
        imageAnimation[1] = new ImageAnimation(this.getPosition(), this.getWidth(), this.getHeight(), 
                            demonSlime[1], 5, 16);
        AnimationController.animations.add(imageAnimation[0]);
        AnimationController.animations.add(imageAnimation[1]);

        imageAnimation[1].setStop(false);
    }

    @Override
    public void draw(GraphicsContext gc) {
        Image image = imageAnimation[1].getCurrImage();
        Point pos = this.getPosition();
        gc.drawImage(image, pos.getX(), pos.getY(), image.getWidth(), image.getHeight());
    }

    @Override
    public void attack() {
        
    }
    
}
