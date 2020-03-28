import javafx.scene.image.Image;

public class Trampoline extends BouncePad {
    /**
     * Constructor for StrongBouncePad
     * @param x x
     * @param y y
     */
    public Trampoline(int x, int y) {
        super(x, y + 20, 50, 30, 23);
    }

	@Override
	protected Image getImg() {
		return new Image("file:images/trampoline.png", 50, 55, false, true);
	}
}
