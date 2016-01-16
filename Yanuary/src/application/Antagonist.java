package application;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import javafx.scene.Group;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;

public class Antagonist {
	Tank tank;
	ArrayList<Group> antagonistArrayList;

	public void addAntagonist(Pane root) {

		antagonistArrayList = new ArrayList<Group>();
		Random rand = new Random();
		int noEnemy = rand.nextInt(8 - 2) + 2;
		for (int i = 0; i < noEnemy; i++) {

			root.getChildren().add(tank.createTank(root));
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
