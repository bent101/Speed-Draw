import javafx.scene.image.Image;

public class Portal extends Entity {
	/**
	 * Construcor for Portal
	 * @param x x
	 * @param y y
	 */
	public Portal(int x, int y) {
		super(x, y, 50, 50);
	}
	
	public Image getImg() {
		return new Image("file:images/portal.png", 50, 50, false, true);
	}
}
