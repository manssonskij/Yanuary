package application;

import javafx.application.Application;
import javafx.geometry.Point2D;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

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
		Rectangle agent = new Rectangle();
		agent.setWidth(40);
		agent.setHeight(80);
		agent.setTranslateX(root.getWidth() / 2);
		agent.setTranslateY(root.getHeight() / 2);
		agent.setFill(Color.DARKGREEN);

		root.requestFocus();

		root.setOnMouseClicked(e -> {
			agent.setTranslateX(e.getSceneX());
			agent.setTranslateY(e.getSceneY());
		});

		root.getChildren().add(agent);
		// Point2D agentlocation = new Point2D(agent.getTranslateX(),
		// agent.getTranslateY());
		// Point2D agentdirection;

		double angle = Math.toDegrees(agent.getRotate());

		double newX = Math.sin(angle) * 10;
		double newY = Math.cos(angle) * 10;

		// agentdirection = agentlocation
		// double angle = Math.toDegrees(agent.getRotate());
		root.setOnKeyPressed(e -> {

			switch (e.getCode()) {
			case UP: {
				moveForward(agent);
			}
				break;
			case DOWN:
				moveBackwards(agent);

				break;
			case LEFT:
				agent.setRotate(agent.getRotate() - 10);
				break;
			case RIGHT:
				agent.setRotate(agent.getRotate() + 10);
				break;
			default:
				break;
			}
		});
	}

	private void moveBackwards(Rectangle agent) {
		double angle = Math.toRadians(agent.getRotate());
		System.out.println(angle);
		double newX = Math.sin(angle) * 10;
		double newY = Math.cos(angle) * 10;
		System.out.println(newX + " " + newY);
		double novoX= agent.getTranslateX()-newX;
		double novoY = agent.getTranslateY()-newY;
		agent.setTranslateX(novoX);
		agent.setTranslateY(novoY);
		
	}

	private void moveForward(Rectangle agent) {

		double angle = Math.toRadians(agent.getRotate());
		System.out.println(angle);
		double newX = Math.sin(angle) * 10;
		double newY = Math.cos(angle) * 10;
		System.out.println(newX + " " + newY);
		agent.setTranslateX(agent.getTranslateX() - newX);
		agent.setTranslateY(agent.getTranslateY() - newY);

	}
}
