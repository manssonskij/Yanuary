package application;

import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.InnerShadow;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;

public class Protagonist extends Tank {
	Group protagonist;
	public Protagonist() {
	}

	public Group addProtagonist(AnchorPane root) {


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
		// System.out.println(barrel.getEndX() + " " + barrel.getEndY());
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

		UserInterface ui = new UserInterface();
		ui.addStatusBox(root, protagonist);

		protagonist.requestFocus();
		Controls controls = new Controls();
		controls.addControlls(root, protagonist);
		// addTurretController(root, turret, foobarTurret);
		root.setOnMouseMoved(e -> {

			Point2D protapoint = new Point2D(protagonist.getTranslateX(), protagonist.getTranslateY());
			Point2D turretpoint = new Point2D(turret.getCenterX(), turret.getCenterY());
			Point2D aimingpoint = new Point2D(e.getSceneX(), e.getSceneY());
			System.out.println(protapoint.toString() + " turret and aim " + aimingpoint.toString());
			barrel.setRotate(Math.toDegrees(
					Math.atan2(protapoint.getY() - aimingpoint.getY(), protapoint.getX() - aimingpoint.getX())));
					// double difX = e.getY() - turret.getCenterY();
					// double difY = e.getX() - turret.getCenterX();
					// turretpoint.angle(aimingpoint);
					// barrel.endXProperty().bind(e.getSceneX());
					// barrel.getTransforms().add(new
					// Rotate(Math.toDegrees(Math.atan2(protapoint.getY() -
					// aimingpoint.getY(), protapoint.getX() -
					// aimingpoint.getX())),25,30));
					// barrel.getTransforms().add((new
					// Rotate(Math.toDegrees(protapoint.angle(aimingpoint)),
					// 25,30)));

			// double angleRad = Math.atan2(difX, difY);
			// double angle = 90.0 - Math.toDegrees(angleRad);
			// double angleToRotate = Math.toDegrees(
			// Math.atan2((turret.getCenterY() - e.getY()), (turret.getCenterX()
			// - e.getX()) - Math.PI / 2));

			// System.out.println(angleToRotate);

			// barrel.getTransforms().add(new Rotate(angle, 25, 30));
		});

		return protagonist;
	}

	public double getLayoutX() {
		return protagonist.getLayoutX();

	}

	public double getLayoutY() {
		return protagonist.getLayoutY();
	}
}
