package application;

import java.util.ArrayList;
import java.util.Random;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.effect.BlendMode;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.effect.InnerShadow;
import javafx.scene.effect.SepiaTone;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.RadialGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;

/* panzer protagonist
 * manssonskij @ github
 * 
 * project/nackademin
 * 
 */

public class Main extends Application {

	/*
	 * declaration of necessary variables
	 * 
	 */
	ArrayList<Circle> treeArrayList;
	ArrayList<Rectangle> buildingArrayList;
	ArrayList<Tank> antagonistArrayList;
	Protagonist protagonist;

	static Random rand;

	static Stage primaryStage;
	static AnchorPane root;
	static UserInterface ui;

	@Override

	public void start(Stage primaryStage) {
		try {
			/*
			 * root.setBackground( new Background(new
			 * BackgroundFill(Color.web(-fx-background-color:
			 * radial-gradient(center 50% 50% , radius 200px , #ffebcd,
			 * #008080)), CornerRadii.EMPTY, Insets.EMPTY)));
			 * 
			 * -fx-background-color: radial-gradient(center 50% 50% , radius
			 * 200px , #ffebcd, #008080);
			 */

			AnchorPane root = new AnchorPane();
			root.setBackground(
					new Background(new BackgroundFill(Color.web("#0d0d0d"), CornerRadii.EMPTY, Insets.EMPTY)));

			Scene scene = new Scene(root, 400, 400);
			primaryStage.setScene(scene);
			primaryStage.show();

			primaryStage.setFullScreen(true);
			scene.setCursor(Cursor.CROSSHAIR);

			rand = new Random();

			/*
			 * initializing the various objects populating the game world,
			 * objects contained in arrayLists. Should turn them into
			 * linkedlists for easy addition and subraction
			 */

			Vegetation vegetation = new Vegetation();
			vegetation.addTrees(root); // adding trees
			treeArrayList = vegetation.getTreeList();

			Buildings buildings = new Buildings();
			buildings.addBuildings(root); // adding building
			buildingArrayList = buildings.getBuildingList();

			Antagonist antagonist = new Antagonist();
			antagonist.addAntagonist(root); // adding enemies
			antagonistArrayList = antagonist.getAntagonist();

			Protagonist protagonist = new Protagonist();
			protagonist.addProtagonist(root);

			UserInterface ui = new UserInterface();
			ui.addStatusBox(root, protagonist);

			/*
			 * starting the game
			 */
			addEffect(root);

			startTimer(primaryStage);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/*
	 * animation timer to handle "time flow"
	 * 
	 */
	private void startTimer(Stage primaryStage) {
		new AnimationTimer() {
			public void handle(long now) {
				scrollMovement(primaryStage);
			}
		}.start();
	}

	/*
	 * method to control the scroll movement of various objects, such as
	 * "buildings" and "trees"
	 * 
	 */
	private void scrollMovement(Stage primaryStage) {

		/*
		 * "bug" when changing size of window to smaller, objects get clustered
		 * but does not spread out when window size is increased
		 * 
		 */

		// building scrolling
		for (int i = 0; i < buildingArrayList.size(); i++) {
			Rectangle build = buildingArrayList.get(i);
			build.setY(build.getY() + 1);
		}

		for (int i = 0; i < buildingArrayList.size(); i++) {
			Rectangle build = buildingArrayList.get(i);

			// ensure smooth fadein/fadeout during scroll
			if (build.getY() > primaryStage.getHeight()) {
				build.setY(0 - build.getHeight());
			}

		}

		// "tree" scrolling
		for (int i = 0; i < treeArrayList.size(); i++) {
			Circle tree = treeArrayList.get(i);
			tree.setCenterY(tree.getCenterY() + 1);
			// ensure smooth fadein/fadeout during scroll
		}

		for (int i = 0; i < treeArrayList.size(); i++) {
			Circle tree = treeArrayList.get(i);

			if ((tree.getCenterY() - tree.getRadius()) > primaryStage.getHeight()) {

				tree.setRadius(rand.nextInt(7 - 5) + 1);
				tree.setCenterY(0 - tree.getRadius());

				// generate new colors
				int r = rand.nextInt(55) + 200;
				int g = rand.nextInt(55) + 200;
				int b = rand.nextInt(55) + 200;
				Color randomColor = Color.rgb(r, g, b);

				tree.setFill(randomColor);
			}

		}

		for (int i = 0; i < antagonistArrayList.size(); i++) {
			Tank tank = antagonistArrayList.get(i);

			// tank.setPositionY((root.getWidth()-rand.nextInt((int)root.getHeight())));
			// tank.setPositionX((root.getWidth()-rand.nextInt((int)root.getWidth())));
			tank.setPositionX(300);
			tank.setPositionY(300);

		}

		// ui.proposix = Double.toString(protagonist.getLayoutY());
		// ui.proposiy = Double.toString(protagonist.getLayoutX());

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

	/*
	 * currently obsolete, replaced with initial setting of root background
	 * color
	 */
	private void addBackground(Pane root) {
		Rectangle background = new Rectangle();
		background.setX(0);
		background.setY(0);
		background.setHeight(root.getHeight());
		background.setWidth(root.getWidth());
		background.setFill(Color.DARKSLATEGREY);

		root.getChildren().add(background);
	}

	private void addEffect(Pane root) {
		/*
		 * good resource http://www.java2s.com/Tutorials/Java/JavaFX/
		 * 0110__JavaFX_Gradient_Color.htm
		 * 
		 */
		Rectangle frame = new Rectangle();
		frame.setHeight(root.getHeight());
		frame.setWidth(root.getWidth());

		//////////
		RadialGradient frameGradient = new RadialGradient(0, .1, (root.getWidth() / 2), (root.getHeight() / 2),
				root.getHeight(), false, CycleMethod.NO_CYCLE, new Stop(0, Color.rgb(85, 85, 52)),
				new Stop(1, Color.rgb(33, 33, 00)));

		LinearGradient linearGradient = new LinearGradient(0, 0, 0, root.getHeight(), false, CycleMethod.REFLECT,
				new Stop(0, Color.BLACK), new Stop(1, Color.WHEAT));

		Rectangle interlacing = new Rectangle();
		interlacing.setHeight(root.getHeight());
		interlacing.setWidth(root.getWidth());
		interlacing.setFill(linearGradient);
		interlacing.setOpacity(0.1);

		LinearGradient interpolatelines = new LinearGradient(0, 0, 0, 2, false, CycleMethod.REFLECT,
				new Stop(0, Color.BLACK), new Stop(1, Color.WHITE));

		Rectangle interpolate = new Rectangle();
		interpolate.setHeight(root.getHeight());
		interpolate.setWidth(root.getWidth());
		interpolate.setFill(interpolatelines);
		interpolate.setOpacity(0.1);

		///////////

		Rectangle rect = new Rectangle();
		rect.setHeight(root.getHeight() * 0.9);
		rect.setWidth(root.getWidth() * 0.9);
		rect.setX(50);
		rect.setY(50);
		rect.setArcHeight(rect.getHeight() * 0.10);
		rect.setArcWidth(rect.getWidth() * 0.10);
		// rect.blendModeProperty();

		final Shape framing = frame.subtract(frame, rect);
		framing.setFill(Color.rgb(32, 32, 32));
		framing.setStroke(Color.rgb(48, 48, 48));
		framing.setStrokeWidth(5);
		framing.setStrokeType(StrokeType.INSIDE);

		framing.setFill(frameGradient);

		Text soyoz = new Text("Фру́нзенская Спутник");
		soyoz.setX(root.getWidth() / 2);

		// soyoz.setStroke(frameGradient);
		soyoz.setFill(Color.BLACK);
		soyoz.setFont(Font.font("monospace"));
		soyoz.setScaleX(2);
		soyoz.setScaleY(1.5);
		soyoz.setTextAlignment(TextAlignment.CENTER);
		soyoz.setY(25);

		rect.setOpacity(50);

		root.getChildren().addAll(interlacing, interpolate, framing, soyoz);
		SepiaTone sp = new SepiaTone();
		root.setEffect(sp);

		// InnerShadow shadow = new InnerShadow();
		// shadow.setInput(new ColorAdjust(0, 0, -0.2, 0));
		// root.setEffect(shadow);
	}

}
