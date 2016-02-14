package application;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Synthesizer;

import javafx.animation.AnimationTimer;
import javafx.animation.StrokeTransition;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Point2D;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.InnerShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.transform.Rotate;

public class Main extends Application {

	// panzer protagonist
	ArrayList<Circle> treeArrayList;
	ArrayList<Rectangle> buildingArrayList;
	ArrayList<Group> antagonistArrayList;
	Protagonist protagonist;

	/////
	private static final int STAR_COUNT = 200;

	private final Rectangle[] nodes = new Rectangle[STAR_COUNT];
	private final double[] angles = new double[STAR_COUNT];
	private final long[] start = new long[STAR_COUNT];

	private final Random random = new Random();
	///

	Random rand = random;

	static Stage primaryStage;
	static AnchorPane root;

	@Override

	public void start(Stage primaryStage) {
		try {

			///////
			for (int i = 0; i < STAR_COUNT; i++) {
				nodes[i] = new Rectangle(3, 3, Color.RED);
				angles[i] = 2.0 * Math.PI * random.nextDouble();
				start[i] = random.nextInt(2000000000);
			}

			/////

			AnchorPane root = new AnchorPane();
			root.setBackground(new Background(new BackgroundFill(Color.DARKKHAKI, CornerRadii.EMPTY, Insets.EMPTY)));
			Scene scene = new Scene(root, 400, 400);
			primaryStage.setScene(scene);
			primaryStage.show();
			primaryStage.setFullScreen(true);
			scene.setCursor(Cursor.CROSSHAIR);

			// add background and gridlines
			// addBackground(root);
			// addGrid(root);
			root.getChildren().addAll(nodes);

			Vegetation vegetation = new Vegetation();
			vegetation.addTrees(root); // adding trees
			treeArrayList = vegetation.getTreeList();
			Buildings buildings = new Buildings();
			buildings.addBuildings(root); // adding building
			buildingArrayList = buildings.getBuildingList();

			// Antagonist antagonist = new Antagonist();
			// antagonist.addAntagonist(root); // adding enemies

			// addProtagonist(root); // adding player
			Protagonist protagonist = new Protagonist();
			protagonist.addProtagonist(root);

			startTimer(primaryStage);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	////////////
	private void startTimer(Stage primaryStage) {
		new AnimationTimer() {
			public void handle(long now) {
				scrollMovement(primaryStage);
			}
		}.start();
	}
	/////////

	private void scrollMovement(Stage primaryStage) {

		// building scrolling
		for (int i = 0; i < buildingArrayList.size(); i++) {
			Rectangle build = buildingArrayList.get(i);
			build.setY(build.getY() + 1);
		}

		for (int i = 0; i < buildingArrayList.size(); i++) {
			Rectangle build = buildingArrayList.get(i);

			if (build.getY() > primaryStage.getHeight()) {
				build.setY(0 - build.getHeight());
			}

		}

		// "tree" scrolling
		for (int i = 0; i < treeArrayList.size(); i++) {
			Circle tree = treeArrayList.get(i);
			tree.setCenterY(tree.getCenterY() + 1);
		}

		for (int i = 0; i < treeArrayList.size(); i++) {
			Circle tree = treeArrayList.get(i);

			if (tree.getCenterY() > primaryStage.getHeight()) {
				tree.setRadius(rand.nextInt(20 - 5) + 5);
				tree.setCenterY(0 - tree.getRadius());

				int r = rand.nextInt(55);
				int g = rand.nextInt(55);
				int b = rand.nextInt(55);
				Color randomColor = Color.rgb(r, g, b);

				tree.setFill(randomColor);
			}

		}

		////// crazybackground
		//// needs root as input parameter
		// Color randcol =
		////// Color.rgb(rand.nextInt(155),rand.nextInt(55),rand.nextInt(55));
		// BackgroundFill bfill = new BackgroundFill(randcol, CornerRadii.EMPTY,
		////// Insets.EMPTY);
		// root.setBackground(new Background(bfill));

	}

	public static void main(String[] args) {
		launch(args);
	}

	private void addTurretController(AnchorPane root, Circle turret, Line foobarTurret) {
		// Mouse movement to be connected to "turret"
		root.setOnMouseMoved(e -> {

			// double angleToRotate = Math.atan2((turret.getCenterY() -
			// e.getY()), (turret.getCenterX() - e.getX()) - Math.PI / 2);
			double angleToRotate = (Math.atan2((e.getY() - turret.getCenterY()), (e.getX() - turret.getCenterX())));
			if (angleToRotate < 0)
				angleToRotate -= 360;

			foobarTurret.getTransforms().add(new Rotate(angleToRotate, 25, 30));

		});

	}

	private void addBackground(Pane root) {
		Rectangle background = new Rectangle();
		background.setX(0);
		background.setY(0);
		background.setHeight(root.getHeight());
		background.setWidth(root.getWidth());
		background.setFill(Color.DARKSLATEGREY);

		root.getChildren().add(background);
	}

}
