package application;

import javafx.animation.StrokeTransition;
import javafx.animation.Timeline;
import javafx.scene.Group;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

public class Bullets {
	public void shootBullet(Group protagonist, Pane root, MouseEvent e) {
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
}
