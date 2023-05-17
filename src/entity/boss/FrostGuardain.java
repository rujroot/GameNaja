package entity.boss;

import animation.AnimationController;
import animation.AnimationManager;
import animation.ImageAnimation;
import data.DataEntity;
import data.Point;
import entity.Player;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import logic.Hitbox;
import logic.RenderableHolder;

public class FrostGuardain extends BossEntity {
    
    private ImageAnimation[] imageAnimation = new ImageAnimation[10];
    private AnimationManager animationManager;
    private String status = "Idel";
    private boolean actionDone = true;

    private Point target;
    private int count = 0;

    private double cooldownTime = 2000;
	private double lastClickTime = 0;

    public FrostGuardain(String name, DataEntity data) {
        super(name, data, new Hitbox(new Point(150, 60), 250, 270));
        this.setWidth(260);
        this.setHeight(310);

        initAnimation();
    }

    public void initAnimation(){
        animationManager = new AnimationManager(position, 0, 0);

        Image[][] FrostGuardain = RenderableHolder.imageAnimation.get("FrostGuardain");

        // Idel Animation
        imageAnimation[0] = new ImageAnimation(this.getPosition(), this.getWidth(), this.getHeight(), 
                            FrostGuardain[0], 10, 7);
        imageAnimation[0].setLoop(true);

        // Attack animation
        imageAnimation[1] = new ImageAnimation(this.getPosition(), this.getWidth(), this.getHeight(), 
                                FrostGuardain[1], 10, 15);

        // set animation to manager
        animationManager.addAnimation(imageAnimation[0]);

        AnimationController.animations.add(animationManager);
        //AnimationController.animations.add(imageAnimation[1]);
    }

    @Override
    public void draw(GraphicsContext gc) {
        Image image = animationManager.getCurrAnimation().getCurrImage();
        Point pos = this.getPosition();
        Hitbox hitbox = this.getBossHitbox();
        gc.drawImage(image, pos.getX() - hitbox.getPosition().getX(), pos.getY() - hitbox.getPosition().getY(), image.getWidth(), image.getHeight());
    }

    @Override
    public void attack() {
        if(actionDone == false){
            playAction(status);
            return;
        }
        actionDone = false;
        // Random Action to play
        String[] action =  {"Attacking10", "Attacking20", "Attacking30"};
        status = "Attacking10";//action[(int)(Math.random() * 3)];
        imageAnimation[1].setStop(false);
        
    }

    private void playAction(String status) {
        follow();

        imageAnimation[1].setStop(false);
        animationManager.addAnimation(imageAnimation[1]);
    }

    public void playAttackP2(){

    }

    public void playAttackP3(){

    }

    public void playAttackP1(){

    }

    public double getDistant(){

        Point pp = Player.getPlayer().getPosition();
        double px = pp.getX() , py = pp.getY();

		Point p = new Point(this.getPosition().getX() - px - 100, this.getPosition().getY() - py + 100);
		double distance = Math.sqrt(p.getX() * p.getX() + p.getY() * p.getY());

        return distance;
    }

    public void follow() {

		Point pp = Player.getPlayer().getPosition();
        if(target != null) pp = target;

		double px = pp.getX() , py = pp.getY();

		Point p = new Point(this.getPosition().getX() - px - 50, this.getPosition().getY() - py + 220);
		double distance = Math.sqrt(p.getX() * p.getX() + p.getY() * p.getY());

		DataEntity data = this.getData();
        
		if (distance > 30) {
			this.move(-p.getX() / distance * data.getSpd(), 0);
			this.move(0, -p.getY() / distance * data.getSpd());
		}

	}

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
}
