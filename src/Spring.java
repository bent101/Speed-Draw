import javafx.scene.image.Image;

public class Spring extends BouncePad {
    /**
     * Constructor for WeakBouncePad
     * @param x x
     * @param y y
     */
    public Spring(int x, int y) {
        super(x + 5, y + 10, 40, 40, 20);
    }

	@Override
	protected Image getImg() {
		return new Image("file:images/spring.png", 50, 50, false, true);
	}
}
