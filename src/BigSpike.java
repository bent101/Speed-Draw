import javafx.scene.image.Image;

public class BigSpike extends Obstacle {
    /**
     * Constructor for TallSpike
     * @param x x
     * @param y y
     */
    public BigSpike(int x, int y) {
        super(x, y, 50, 50, 10);
    }
    
	@Override
	public Image getImg() {
		return new Image("file:images/bigSpike.png", 55, 55, false, true);
	}
}