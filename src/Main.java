import javafx.animation.AnimationTimer;

import javafx.application.Application;

import javafx.scene.*;
import javafx.scene.shape.*;
import javafx.stage.Stage;

import java.io.File;
import java.util.*;
import javafx.scene.image.*;
import javafx.scene.layout.*;
import javafx.scene.input.*;
import javafx.scene.control.*;
import javafx.geometry.Insets;
import javafx.scene.paint.*;
import javafx.scene.text.*;
import javafx.scene.media.*;

import java.util.HashMap;


public class Main extends Application {
	public static HashMap<KeyCode, Boolean> keys = new HashMap<>();
	private int levelOn = 0;
	Player player = new Player(new KeyCode[] {KeyCode.UP, KeyCode.LEFT, KeyCode.RIGHT});
	ArrayList<Level> levels = LevelGenerator.generateLevels();
	private Level currentLevel = levels.get(0);
	private Pane levelPane = new Pane();
	private Button
		playBtn = new Button(),
		howBtn = new Button(),
		backBtn = new Button(),
		restartBtn = new Button(),
		menuBtn = new Button(),
		menuBtn2 = new Button();
    private static float t;
    private static Text timeTxt = new Text(20, 30, "");
    private static float bestTime = (float) 12.269806;
	
	public static boolean isPressed(KeyCode key) {
		return keys.getOrDefault(key, false);
	}
	
	private void nextLevel() {
		levelOn++;
		currentLevel = levels.get(levelOn);
		player.startLevel(currentLevel);
		levelPane.getChildren().clear();
		levelPane.getChildren().add(currentLevel.getGroup());
	}
	
	public Scene getTitleScene() {
		Group root = new Group();
        Region bg = new Region();
        bg.setBackground(new Background(
    		new BackgroundImage(
				new Image("file:images/bg.png"),
				BackgroundRepeat.REPEAT,
				BackgroundRepeat.REPEAT,
				BackgroundPosition.CENTER,
				new BackgroundSize(100,100,true,true,true,true)
        )));
        bg.setMinWidth(800);
        bg.setMinHeight(500);
        ImageView title = new ImageView(new Image("file:images/title.png", 800, 500, true, true));
        
        playBtn.setGraphic(new ImageView(new Image("file:images/playBtn.png", 145, 100, true, true)));
        playBtn.setPadding(new Insets(0,0,0,0));
        
        howBtn.setGraphic(new ImageView(new Image("file:images/howBtn.png", 145, 100, true, true)));
        howBtn.setPadding(new Insets(0,0,0,0));
        
        GridPane btnPane = new GridPane();
        btnPane.setHgap(59);
        btnPane.add(playBtn, 0, 0);
        btnPane.add(howBtn, 1, 0);
        btnPane.setLayoutX(225);
        btnPane.setLayoutY(337);
        
		root.getChildren().addAll(bg, title, btnPane);
		Scene scene = new Scene(root, 800, 500);
		return scene;
	}
	
	public Scene getLevelScene() {
		Group root = new Group();
        Region bg = new Region();
        bg.setBackground(new Background(
    		new BackgroundImage(
				new Image("file:images/bg.png"),
				BackgroundRepeat.REPEAT,
				BackgroundRepeat.REPEAT,
				BackgroundPosition.CENTER,
				new BackgroundSize(100,100,true,true,true,true)
        )));
        bg.setMinWidth(800);
        bg.setMinHeight(500);
        
        Rectangle txtBg = new Rectangle(10, 5, 85, 35);
        txtBg.setArcWidth(10);
        txtBg.setArcHeight(10);
        txtBg.setFill(new Color(1, 1, 1, 0.9));
        
        menuBtn2.setGraphic(new ImageView(new Image("file:images/menuBtn.png", 70, 35, false, true)));
        menuBtn2.setPadding(new Insets(0,0,0,0));
        menuBtn2.setTranslateX(725);
        menuBtn2.setTranslateY(5);
        
		root.getChildren().addAll(bg, levelPane, player.getImgView(), player.getHealthBar(), txtBg, timeTxt, menuBtn2);
        Scene scene = new Scene(root, 800, 500);
        scene.setOnKeyPressed(e -> keys.put(e.getCode(), true));
        scene.setOnKeyReleased(e -> keys.put(e.getCode(), false));
		return scene;
	}
	
	public Scene getHowScene() {
		Group root = new Group();
        MediaPlayer bg = new MediaPlayer(new Media(new File("videos/demo.mp4").toURI().toString()));
        bg.setAutoPlay(true);
        bg.setCycleCount(MediaPlayer.INDEFINITE);
        
        Rectangle shading = new Rectangle(0, 0, 800, 500);
        shading.setFill(new Color(1, 1, 1, 0.8));
        
        backBtn.setTranslateX(550);
        backBtn.setTranslateY(350);
        backBtn.setGraphic(new ImageView(new Image("file:images/backBtn.png", 145, 100, true, true)));
        backBtn.setPadding(new Insets(0,0,0,0));
        
        Rectangle btnOutline = new Rectangle(550, 350, 145, 100);
        btnOutline.setStroke(Color.BLACK);
        btnOutline.setStrokeWidth(8);
        btnOutline.setArcHeight(10);
        btnOutline.setArcWidth(10);
        
        Rectangle instructionsBox = new Rectangle(90, 95, 620, 310);
        instructionsBox.setArcWidth(10);
        instructionsBox.setArcHeight(10);
        instructionsBox.setFill(shading.getFill());
        
        Text instructions = new Text(100, 120,
        	"Welcome to SpeedDraw!"
        	+ "\nThe rules are simple: Complete the levels as fast as you can!"
        	+ "\n\n - Move around with the arrow keys."
        	+ "\n - Look out for spikes!"
        	+ "\n    - Spikes don't kill you right away; they just damage you.\n      You die when your health runs out."
        	+ "\n - Use trampolines and springs to jump higher!"
        	+ "\n - Get to the portal to continue to the next level."
        	+ "\n - Keep an eye out for shortcuts! Every level has a more\n   difficult but potentially faster route."
        	+ "\n\nGood luck!");
        instructions.setFont(new Font("Monaco", 16));
        instructions.setFill(Color.BLACK);
        instructions.setWrappingWidth(700);
        instructions.setLineSpacing(1.5);
        
		root.getChildren().addAll(new MediaView(bg), shading, instructionsBox, instructions, btnOutline, backBtn);
		
        Scene scene = new Scene(root, 800, 500);
        return scene;
	}
	
	public Scene getEndScene() {
		Group root = new Group();
        Region bg = new Region();
        bg.setBackground(new Background(
    		new BackgroundImage(
				new Image("file:images/clapping.jpg"),
				BackgroundRepeat.REPEAT,
				BackgroundRepeat.REPEAT,
				BackgroundPosition.CENTER,
				new BackgroundSize(100,100,true,true,true,true)
        )));
        bg.setMinWidth(800);
        bg.setMinHeight(500);
        
        Rectangle shading = new Rectangle(0, 0, 800, 500);
        shading.setFill(new Color(1, 1, 1, 0.6));
        
        Rectangle txtBox = new Rectangle(150, 145, 520, 185);
        txtBox.setArcWidth(10);
        txtBox.setArcHeight(10);
        txtBox.setFill(new Color(1, 1, 1, 0.7));
        
        Text header = new Text(50, 100, "You did it!");
        header.setTextAlignment(TextAlignment.CENTER);
        header.setWrappingWidth(700);
        header.setFont(new Font(90));
        header.setFill(Color.BLACK);
        
        int minutes = (int) Math.floor(t / 60);
        float seconds = t - 60 * minutes;
        int bestMinutes = (int) Math.floor(bestTime / 60);
        float bestSeconds = bestTime - 60 * bestMinutes;
        
        Text msg = new Text(160, 200,
        	  "Time:   " + minutes + ":" + (seconds < 10? "0": "") + seconds + 
        	"\nBest:   " + bestMinutes + ":" + (bestSeconds < 10? "0": "") + bestSeconds + 
        	"\nDeaths: " + player.getDeaths());
        msg.setFont(new Font("Monaco", 40));
        msg.setFill(new Color(0.2, 0.2, 0.2, 1.0));
        
        menuBtn.setGraphic(new ImageView(new Image("file:images/menuBtn.png", 145, 100, true, true)));
        menuBtn.setPadding(new Insets(0,0,0,0));
        menuBtn.setTranslateX(230);
        menuBtn.setTranslateY(350);
        
        restartBtn.setGraphic(new ImageView(new Image("file:images/playAgainBtn.png", 145, 100, true, true)));
        restartBtn.setPadding(new Insets(0,0,0,0));
        restartBtn.setTranslateX(425);
        restartBtn.setTranslateY(350);
        
		root.getChildren().addAll(bg, shading, txtBox, header, msg, restartBtn, menuBtn);
        Scene scene = new Scene(root, 800, 500);
        return scene;
	}
	
    
	public void start(Stage stage) {
		SoundPlayer.playMusic();
		
		timeTxt.setFont(new Font("Monaco", 20));
		
	    	AnimationTimer timer = new AnimationTimer() {
	    		
	        	@Override
	        	public void handle(long now) {
	        		player.update(levels.get(levelOn));
	        		if(player.finishedLevel()) nextLevel();
	        		if(levelOn == LevelGenerator.NUM_LEVELS) {
	        			levelOn = 0;
	        			stop();
	        			bestTime = bestTime == 0? t: Math.min(t,  bestTime);
	        			stage.setScene(getEndScene());
	        		}
	        		t += 1 / 63.0;
	        		timeTxt.setText(Double.toString(Math.floor(t * 100) / 100.0));
	        	}
	        	
	        	@Override
	        	public void start() {
	        		super.start();
	        		t = 0;
	        	}
        };
        
        playBtn.setOnAction(e -> {
	        	stage.setScene(getLevelScene());
	        	levelOn = 0;
	        	currentLevel = levels.get(0);
	        	levelPane.getChildren().clear();
	        	levelPane.getChildren().add(currentLevel.getGroup());
            player.startLevel(currentLevel);//starts the first level
            keys.clear();
            player.resetDeaths();
            timer.start();
        });
        
        howBtn.setOnAction(e -> {
        		stage.setScene(getHowScene());
        });
        
        backBtn.setOnAction(e -> {
        		stage.setScene(getTitleScene());
        });
        
        restartBtn.setOnAction(playBtn.getOnAction());
        
        menuBtn2.setOnAction(backBtn.getOnAction());
        
        menuBtn.setOnAction(backBtn.getOnAction());
        
        stage.setTitle("SpeedDraw");
        stage.setScene(getTitleScene());
        stage.show();
    }
    
    public static void main(String[] args) {
    		launch(args);
    }
}