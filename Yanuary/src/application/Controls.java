package application;

import javafx.scene.Group;
import javafx.scene.layout.Pane;

public class Controls {

	public void addControlls(Pane root, Group protagonist) {
		Physics physics=new Physics();
		Bullets bullet = new Bullets();

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
			physics.collisionDetection(protagonist);

		});

		// ACTION EVENT SECTiON
		root.setOnMousePressed(e -> {
			bullet.shootBullet(protagonist, root, e);
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

}
