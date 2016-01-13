package application;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import javafx.scene.Group;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;

public class Antagonist {
	ArrayList<Group> antagonistArrayList;
	public void addAntagonist(AnchorPane root) {

		antagonistArrayList = new ArrayList<Group>();
		Random rand = new Random();
		int noEnemy = rand.nextInt(8 - 2) + 2;
		for (int i = 0; i < noEnemy; i++) {

			Group antagonist = new Group();
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
			barrel.setTranslateX(antagonist.getTranslateX());
			barrel.setTranslateY(antagonist.getTranslateY());

			antagonist.getChildren().addAll(chassi, turret, hatch, barrel);
			antagonistArrayList.add(antagonist);

			int randomPosY = rand.nextInt((int) root.getHeight() - 200);
			int randomPosX = rand.nextInt((int) root.getWidth() - 200);
			antagonist.setTranslateX(randomPosX);
			antagonist.setTranslateY(randomPosY);
			root.getChildren().add(antagonist);
		}
		System.out.println("Buildings: " + antagonistArrayList.size());

		// implementera någon form av iterator
		Iterator<Group> iterator = antagonistArrayList.iterator();
		while (iterator.hasNext()) {
			Group antagonist = iterator.next();
			System.out
					.println("Building position, X:" + antagonist.getTranslateX() + " Y:" + antagonist.getTranslateY());
		}

	}
}
