import java.io.File;
import javafx.scene.media.*;

public class SoundPlayer {
	public static MediaPlayer
		boing = new MediaPlayer(new Media(new File("sounds/boing.mp3").toURI().toString())),
		oof = new MediaPlayer(new Media(new File("sounds/oof.mp3").toURI().toString())),
		portal = new MediaPlayer(new Media(new File("sounds/portal.mp3").toURI().toString())),
		music = new MediaPlayer(new Media(new File("sounds/mc.mp3").toURI().toString())),
		bruh = new MediaPlayer(new Media(new File("sounds/bruh.mp3").toURI().toString()));
	
	
	/**
	 * Plays an oof sound
	 */
	public static void playOof() {
		oof.stop();
		oof.play();
	}
	
	/**
	 * Plays a boing sound
	 */
	public static void playBoing() {
		boing.setVolume(0.05);
		boing.stop();
		boing.play();
	}
	
	/**
	 * Plays a whoosh sound
	 */
	public static void playPortal() {
		portal.setVolume(0.05);
		portal.stop();
		portal.play();
	}
	
	/**
	 * Plays music
	 */
	public static void playMusic() {
		music.setVolume(0.1);
		music.setAutoPlay(true);
		music.setCycleCount(MediaPlayer.INDEFINITE);
	}
	
	public static void playBruh() {
		bruh.stop();
		bruh.play();
	}
}
