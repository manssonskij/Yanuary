package application;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import javafx.scene.layout.AnchorPane;

public class Antagonist {
	Tank tank;
	ArrayList<Tank> antagonistArrayList;

	public void addAntagonist(AnchorPane root) {

		antagonistArrayList = new ArrayList<Tank>();
		
		Random rand = new Random();
		int noEnemy = rand.nextInt(8 - 2) + 2;
		for (int i = 0; i < noEnemy; i++) {
			
			antagonistArrayList.add(tank = new Tank());
			tank.positionX=100;
			tank.positionY=100;

		}
		

		
		System.out.println("antagonist: " + antagonistArrayList.size());

		// implementera n�gon form av iterator
		Iterator<Tank> iterator = antagonistArrayList.iterator();
		
		while (iterator.hasNext()) {
			Tank antagonist = iterator.next();
			//System.out.println("Building position, X:" + antagonist.getTranslateX() + " Y:" + antagonist.getTranslateY());
		}

	}
	public ArrayList<Tank> getAntagonist() {
		return antagonistArrayList;
	}
}
