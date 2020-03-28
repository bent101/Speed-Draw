import javafx.scene.image.Image;

public class SmallSpike extends Obstacle {
    /**
     * Constructor for SmallSpike
     * @param x x
     * @param y y
     */
    public SmallSpike(int x, int y) {
        super(x, y + 25, 50, 25, 5);
    }
    
	@Override
	protected Image getImg() {
		return new Image("file:images/smallSpike.png", 50, 30, false, true);
	}
}
