package application;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import javafx.scene.Group;
import javafx.scene.layout.AnchorPane;

public class Antagonist {
	Tank tank;
	ArrayList<Group> antagonistArrayList;

	public void addAntagonist(AnchorPane root) {

		antagonistArrayList = new ArrayList<Group>();
		
		Random rand = new Random();
		int noEnemy = rand.nextInt(8 - 2) + 2;
		for (int i = 0; i < noEnemy; i++) {
			
			antagonistArrayList.add(tank.createTank(root));

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
