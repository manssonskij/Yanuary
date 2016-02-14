package application;

import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class Controls {

	public void addControlls(Pane root, Group protagonist) {
		Physics physics = new Physics();
		Bullets bullet = new Bullets();

		protagonist.setOnKeyPressed(e -> {
			
			
			// WASD moves relative, ARROWS direct
			switch (e.getCode()) {
			case LEFT:
				protagonist.setTranslateX(protagonist.getTranslateX() - 1);
				break;
			case UP:
				protagonist.setTranslateY(protagonist.getTranslateY() - 1);
				break;
			case DOWN:
				protagonist.setTranslateY(protagonist.getTranslateY() + 1);
				break;
			case RIGHT:
				protagonist.setTranslateX(protagonist.getTranslateX() + 1);
				break;
				
			case A:
				protagonist.setRotate(protagonist.getRotate() - 1);
				break;
			case W:
				moveForward(protagonist);
				break;
			case D:
				protagonist.setRotate(protagonist.getRotate() + 1);
				break;
			case S:
				moveBackwards(protagonist);
				break;
			case F10:
				menuboxDisplay(root);
			default:
				break;
			}
		//	physics.collisionDetection(protagonist);

		});

		// ACTION EVENT SECTiON
		root.setOnMousePressed(e -> {
			bullet.shootBullet(protagonist, root, e);
		});

	}

	private void menuboxDisplay(Pane root) {
		StackPane stack = new StackPane();
		stack.setLayoutX(200);
		stack.setLayoutY(400);
		stack.setTranslateX(root.getHeight()/2);
		stack.setTranslateY(root.getWidth()/2);
		VBox menubox = new VBox();
		Button quit = new Button("Quit");
		Button save = new Button("Save");
		Button load = new Button("Load");
		menubox.getChildren().addAll(quit, save, load);
		stack.getChildren().add(menubox);
		root.getChildren().add(stack);
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
		//////
		double angle1 = agent.getRotate();

		agent.setTranslateX(Math.cos(angle1)+agent.getTranslateX());
        agent.setTranslateY(Math.sin(angle1)+agent.getTranslateY());
		/////
        /*
		double angle = Math.toRadians(agent.getRotate());
		System.out.println(angle);
		double newX = Math.sin(angle) * 10;
		double newY = Math.cos(angle) * 10;
		System.out.println(newX + " " + newY);
		agent.setTranslateX(agent.getTranslateX() - newX);
		agent.setTranslateY(agent.getTranslateY() - newY);
*/
	}
	

}
