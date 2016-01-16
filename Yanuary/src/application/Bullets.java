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
	Pane root;
	MouseEvent e;
	Group protagonist;
	Circle bullet;

	public void shootBullet(Group protagonist, Pane root, MouseEvent e) {
		root.getChildren().add(createBullet(e));

	}

	public Circle createBullet(MouseEvent e) {
		Circle bullet = new Circle(10);
		bullet.setFill(Color.BROWN);
		bullet.setCenterX((double) e.getX());
		bullet.setCenterY((double) e.getY());

		bullet.setFill(Color.YELLOW);
		bullet.setStroke(Color.YELLOW);
		bullet.setStrokeWidth(4);
		return bullet;
	}
}
