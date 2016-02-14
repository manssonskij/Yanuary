package application;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class Buildings {
	ArrayList<Rectangle> buildingArrayList;

	public void addBuildings(Pane root) {

		buildingArrayList = new ArrayList<Rectangle>();
		Random rand = new Random();
		int noBuild = rand.nextInt(10 - 2) + 2;
		for (int i = 0; i < noBuild; i++) {
			int randomPosY = rand.nextInt((int) root.getHeight() - 200);
			int randomPosX = rand.nextInt((int) root.getWidth() - 200);

			Rectangle building = new Rectangle(randomPosY, randomPosX, rand.nextInt(200 - 100) + 100,
					rand.nextInt(200 - 100) + 100);
			building.setFill(Color.FIREBRICK);
			building.setStroke(Color.BLACK);
			
			buildingArrayList.add(building);
			root.getChildren().add(building);
		}
		System.out.println("Buildings: " + buildingArrayList.size());

		// implementera någon form av iterator
		Iterator<Rectangle> iterator = buildingArrayList.iterator();
		while (iterator.hasNext()) {
			Rectangle building = iterator.next();
			//FSystem.out.println("Building position, X:" + building.getX() + " Y:" + building.getY());
		}

	}
	
	//simple modifiable getter for arraylist
	public ArrayList<Rectangle> getBuildingList() {
		return buildingArrayList;
	}
}
