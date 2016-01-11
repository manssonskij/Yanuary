package application;

import javafx.application.Application;
import javafx.geometry.Point2D;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			Pane root = new Pane();
			Scene scene = new Scene(root, primaryStage.getWidth(), primaryStage.getHeight());
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setFullScreen(true);
			primaryStage.setScene(scene);
			primaryStage.show();

			addAntagonist(root);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}

	public void addAntagonist(Pane root) {
		Circle circle = new Circle();
		circle.setCenterX(root.getWidth() / 2);
		circle.setCenterY(root.getHeight() / 2);
		circle.setRadius(50);
		circle.setFill(Color.BISQUE);

		root.requestFocus();
		
		Point2D coordinate = new Point2D(0, 0);


		root.setOnMouseClicked(e -> {
			circle.setCenterX(e.getSceneX());
			circle.setCenterY(e.getSceneY());
			coordinate.add(e.getSceneX(),e.getSceneY());
		});
		
		root.getChildren().add(circle);

		double angle = circle.getRotate();

		Point2D coord1 = new Point2D(circle.getCenterX(), circle.getCenterY());
		Point2D newPosition= new Point2D(circle.getCenterX(), circle.getCenterY());
		newPosition.angle(coordinate);
		
		
		root.setOnKeyPressed(e -> {
			//Point2D coord2 =new Point2D(e.ge);
			switch (e.getCode()) {
			case UP:
				circle.setCenterY(circle.getCenterY() - 10);
				//circle.setCenterY(Math.atan(angle));
				circle.setCenterX(newPosition.getX());
				circle.setCenterY(newPosition.getY());
				
				break;
			case DOWN:
				circle.setCenterY(circle.getCenterY() + 10);
				break;
			case LEFT:
				circle.setCenterX(circle.getCenterX() - 10);
				circle.setRotate(-10);
				break;
			case RIGHT:
				circle.setCenterX(circle.getCenterX() + 10);
				circle.setRotate(+10);
				break;
			default:
				break;
			}
		});
	}
}
