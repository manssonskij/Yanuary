package application;

import javafx.application.Application;
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

		// root.requestFocus();

		root.setOnMouseClicked(e -> {
			circle.setCenterX(e.getSceneX());
			circle.setCenterY(e.getSceneY());
		});
		root.getChildren().add(circle);

		root.setOnKeyPressed(e -> {
			switch (e.getCode()) {
			case UP:
				circle.setCenterY(circle.getCenterY() - 10);
				break;
			case DOWN:
				circle.setCenterY(circle.getCenterY() + 10);
				break;
			case LEFT:
				circle.setCenterX(circle.getCenterX() - 10);
				break;
			case RIGHT:
				circle.setCenterX(circle.getCenterX() + 10);
				break;
			default:
				break;
			}
		});
	}
}
