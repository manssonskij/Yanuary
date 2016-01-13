package application;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import javafx.animation.AnimationTimer;
import javafx.animation.StrokeTransition;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.InnerShadow;
import javafx.scene.effect.SepiaTone;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.transform.Rotate;

public class Main extends Application {

	// panzer protagonist
	ArrayList<Circle> treeArrayList;
	//ArrayList<Rectangle> buildingArrayList;
	ArrayList<Group> antagonistArrayList;
	Group protagonist;

	AnimationTimer animTimer;

	@Override
	public void start(Stage primaryStage) {
		try {

			AnchorPane root = new AnchorPane();
			Scene scene = new Scene(root, 400, 400);
			primaryStage.setScene(scene);
			primaryStage.show();
			primaryStage.setFullScreen(true);
			scene.setCursor(Cursor.CROSSHAIR);

			SepiaTone sepiaTone = new SepiaTone();
			sepiaTone.setLevel(0.7);

			// root.setEffect(sepiaTone);

			// add background and gridlines
			addBackground(root);
			// addGrid(root);
			// lets add some fake trees and houses
			Vegetation vegetation =new Vegetation();
			vegetation.addTrees(root);
			Buildings buildings=new Buildings();
			buildings.addBuildings(root);

			Antagonist antagonist = new Antagonist();
			antagonist.addAntagonist(root);
			addProtagonist(root);

			startTimer();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}



	private void startTimer() {
		animTimer.start();

	}

	public static void main(String[] args) {
		launch(args);
	}



	private void collisionDetection() {

		for (Iterator<Circle> iterator = treeArrayList.iterator(); iterator.hasNext();) {
			Circle tree = (Circle) iterator.next();
			if (protagonist.getBoundsInParent().intersects(tree.getBoundsInLocal())) {
				System.out.println("collision at " + tree.getBoundsInLocal());
				tree.setVisible(false);
			}

		}
	}
	private void addProtagonist(AnchorPane root) {

		protagonist = new Group();
		Rectangle chassi = new Rectangle(50, 80);
		chassi.setFill(Color.DARKOLIVEGREEN);
		chassi.setStroke(Color.BLACK);
		Circle turret = new Circle(25, 30, 10);
		turret.setFill(Color.DARKGREEN);
		turret.setStroke(Color.BLACK);
		Rectangle hatch = new Rectangle(20, 50, 20, 10);
		hatch.setFill(Color.DARKOLIVEGREEN);
		hatch.setStroke(Color.BLACK);

		Line barrel = new Line(25, 30, 25, 10);
		System.out.println(barrel.getEndX() + " " + barrel.getEndY());
		barrel.setStrokeWidth(4);
		barrel.setFill(Color.DEEPSKYBLUE);
		barrel.setTranslateX(protagonist.getTranslateX());
		barrel.setTranslateY(protagonist.getTranslateY());

		// placing us in the center
		protagonist.setTranslateX((root.getWidth() / 2));
		protagonist.setTranslateY((root.getHeight() / 2));

		InnerShadow is = new InnerShadow();
		is.setBlurType(BlurType.ONE_PASS_BOX);
		hatch.setEffect(is);

		DropShadow ds = new DropShadow();
		ds.setBlurType(BlurType.ONE_PASS_BOX);
		ds.setOffsetX(3);
		ds.setOffsetY(3);

		chassi.setEffect(ds);
		turret.setEffect(ds);
		// barrel.setEffect(ds);

		protagonist.getChildren().addAll(chassi, turret, hatch, barrel);

		root.getChildren().addAll(protagonist);

		addStatusBox(root, protagonist);

		protagonist.requestFocus();

		addControlls(root, protagonist);
		// addTurretController(root, turret, foobarTurret);
		root.setOnMouseMoved(e -> {

			barrel.setRotate(0);
			double difX = e.getY() - turret.getCenterY();
			double difY = e.getX() - turret.getCenterX();

			double angleRad = Math.atan2(difX, difY);
			double angle = 90.0 - Math.toDegrees(angleRad);
			double angleToRotate = Math.toDegrees(
					Math.atan2((turret.getCenterY() - e.getY()), (turret.getCenterX() - e.getX()) - Math.PI / 2));

			System.out.println(angleToRotate);

			barrel.getTransforms().add(new Rotate(angle, 25, 30));
		});

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

	private void addControlls(Pane root, Group protagonist) {

		protagonist.setOnKeyPressed(e -> {
			switch (e.getCode()) {
			case LEFT:
				protagonist.setTranslateX(protagonist.getTranslateX() - 10);
				break;
			case UP:
				// protagonist.setTranslateY(protagonist.getTranslateY() - 10);
				moveForward(protagonist);

				break;
			case DOWN:
				moveBackwards(protagonist);
				// protagonist.setTranslateY(protagonist.getTranslateY() + 10);
				break;
			case RIGHT:
				protagonist.setTranslateX(protagonist.getTranslateX() + 10);
				break;
			case A:
				protagonist.setRotate(protagonist.getRotate() - 10);
				break;
			case W:
				moveForward(protagonist);
				//protagonist.setTranslateY(protagonist.getTranslateY());
				break;
			case D:
				protagonist.setRotate(protagonist.getRotate() + 10);
				break;
			case S:
				moveBackwards(protagonist);
				//protagonist.setTranslateY(protagonist.getTranslateY() + 10);
				break;
			default:
				break;
			}
			collisionDetection();

		});

		// ACTION EVENT SECTiON
		root.setOnMousePressed(e -> {
			shootBullet(protagonist, root, e);
		});

	}

	private void moveBackwards(Group agent) {
		double angle = Math.toRadians(agent.getRotate());
		System.out.println(angle);
		double newX = Math.sin(angle) * 10;
		double newY = Math.cos(angle) * 10;
		System.out.println(newX + " " + newY);
		double novoX = agent.getTranslateX() - newX;
		double novoY = agent.getTranslateY() - newY;
		agent.setTranslateX(novoX);
		agent.setTranslateY(novoY);

	}

	private void moveForward(Group agent) {

		double angle = Math.toRadians(agent.getRotate());
		System.out.println(angle);
		double newX = Math.sin(angle) * 10;
		double newY = Math.cos(angle) * 10;
		System.out.println(newX + " " + newY);
		agent.setTranslateX(agent.getTranslateX() - newX);
		agent.setTranslateY(agent.getTranslateY() - newY);

	}

	private void addStatusBox(AnchorPane root, Group protagonist) {
		Rectangle statusBox = new Rectangle(300, 100);
		statusBox.setTranslateX(10);
		statusBox.setTranslateY(10);
		statusBox.setFill(Color.LIGHTSTEELBLUE);
		statusBox.setOpacity(0.5);

		Text protPosition = new Text("X: " + Double.toString(protagonist.getTranslateX()) + "Y: "
				+ (Double.toString(protagonist.getTranslateY())));
		protPosition.setX(20);
		protPosition.setY(20);
		DropShadow ds = new DropShadow();
		ds.blurTypeProperty();
		ds.setOffsetX(10);
		statusBox.setEffect(ds);
		root.getChildren().addAll(statusBox, protPosition);

	}

	private void shootBullet(Group protagonist, Pane root, MouseEvent e) {
		Circle bullet = new Circle(10);
		bullet.setFill(Color.BROWN);
		bullet.setCenterX((double) e.getX());
		bullet.setCenterY((double) e.getY());
		protagonist.getRotate();

		bullet.setFill(Color.YELLOW);
		bullet.setStroke(Color.YELLOW);
		bullet.setStrokeWidth(4);

		root.getChildren().add(bullet);

		// copnpaste - check if can be fun for something
		StrokeTransition st = new StrokeTransition();
		st.setShape(bullet);
		st.setDuration(new Duration(2000));
		st.setToValue(Color.ORANGE);
		st.setCycleCount(Timeline.INDEFINITE);
		st.setAutoReverse(true);
		st.play();
		// root.getChildren().remove(bullet);

	}
/*
	private void addBuildings(Pane root) {

		buildingArrayList = new ArrayList<Rectangle>();
		Random rand = new Random();
		int noBuild = rand.nextInt(10 - 2) + 2;
		for (int i = 0; i < noBuild; i++) {
			int randomPosY = rand.nextInt((int) root.getHeight() - 200);
			int randomPosX = rand.nextInt((int) root.getWidth() - 200);

			Rectangle building = new Rectangle(randomPosY, randomPosX, rand.nextInt(200 - 100) + 100,
					rand.nextInt(200 - 100) + 100);
			building.setFill(Color.FIREBRICK);
			building.setStroke(Color.BLACK);
			buildingArrayList.add(building);
			root.getChildren().add(building);
		}
		System.out.println("Buildings: " + buildingArrayList.size());

		// implementera någon form av iterator
		Iterator<Rectangle> iterator = buildingArrayList.iterator();
		while (iterator.hasNext()) {
			Rectangle building = iterator.next();
			System.out.println("Building position, X:" + building.getX() + " Y:" + building.getY());
		}

	}
*/
	/*
	private void addTrees(Pane root) {

		// kanske borde göras till LinkedList
		DropShadow ds = new DropShadow();
		ds.blurTypeProperty();
		ds.setBlurType(BlurType.ONE_PASS_BOX);
		ds.setOffsetX(3);
		ds.setOffsetY(3);

		treeArrayList = new ArrayList<Circle>();
		Random rand = new Random();
		int noTrees = rand.nextInt(80 - 30) + 30;
		for (int i = 0; i < noTrees; i++) {
			int treeSize = rand.nextInt(20 - 5) + 5;
			Circle tree = new Circle(treeSize, Color.FORESTGREEN);
			int randX = rand.nextInt((int) root.getWidth());
			int randY = rand.nextInt((int) root.getHeight());
			// Circle tree = new Circle(10, Color.FORESTGREEN);
			tree.setCenterX(randX);
			tree.setCenterY(randY);
			// tree.setEffect(ds);
			treeArrayList.add(tree);
			root.getChildren().add(tree);
		}
		System.out.println("Trees: " + treeArrayList.size());

		Iterator<Circle> iterator = treeArrayList.iterator();
		while (iterator.hasNext()) {
			Circle tree = iterator.next();
			System.out.println("Tree position, X:" + tree.getCenterX() + " Y:" + tree.getCenterY());
		}

	}
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

	private void addGrid(Pane root) {
		// for (int i = 0; i < 12; i++) {
		// Line line = new Line();
		// line.startXProperty();
		// line.startXProperty().bind(i*50);
		// line.endXProperty().bind(root.widthProperty().divide(3));
		// line.endYProperty().bind(root.heightProperty());
		// line.setStroke(Color.GREEN);
		// }

		// supposed to be my background, NOTWORKING

		Line lineV1 = new Line();
		Line lineV2 = new Line();
		Line lineH1 = new Line();
		Line lineH2 = new Line();
		lineV1.setStroke(Color.INDIANRED);
		lineV2.setStroke(Color.INDIANRED);
		lineH1.setStroke(Color.AZURE);
		lineH2.setStroke(Color.AZURE);
		lineV1.setStrokeDashOffset(5);
		lineV1.setStrokeWidth(0.5);
		lineV2.setStrokeWidth(0.5);
		lineH1.setStrokeWidth(0.5);
		lineH2.setStrokeWidth(0.5);

		lineV2.startXProperty().bind(root.widthProperty().divide(3));
		lineV2.endXProperty().bind(root.widthProperty().divide(3));
		lineV2.endYProperty().bind(root.heightProperty());
		lineV1.startXProperty().bind(root.widthProperty().divide(1.5));
		lineV1.endXProperty().bind(root.widthProperty().divide(1.5));
		lineV1.endYProperty().bind(root.heightProperty());
		lineH1.startYProperty().bind(root.heightProperty().divide(3));
		lineH1.endXProperty().bind(root.widthProperty());
		lineH1.endYProperty().bind(root.heightProperty().divide(3));
		lineH2.startYProperty().bind((root.heightProperty().divide(1.5)));
		lineH2.endXProperty().bind(root.widthProperty());
		lineH2.endYProperty().bind(root.heightProperty().divide(1.5));
		root.getChildren().addAll(lineV1, lineV2, lineH1, lineH2);
	}

}
