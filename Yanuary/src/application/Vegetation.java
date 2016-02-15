package application;

import java.util.ArrayList;
import java.util.Random;

import javafx.scene.effect.MotionBlur;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Vegetation {

	private ArrayList<Circle> treeArrayList;
	boolean effects = false;

	public void addTrees(Pane root) {

		treeArrayList = new ArrayList<Circle>();

		Random rand = new Random();
		int noTrees = rand.nextInt(50 - 10) + 10;

		// cache as bitmap for performance
		Circle tree;

		for (int i = 0; i < noTrees; i++) {
			int treeSize = rand.nextInt(5 - 2) + 1;
			tree = new Circle(treeSize, Color.FORESTGREEN);

			tree.setCache(false);

			int randX = rand.nextInt((int) root.getWidth());
			int randY = rand.nextInt((int) root.getHeight());
			// Circle tree = new Circle(10, Color.FORESTGREEN);
			tree.setCenterX(randX);
			tree.setCenterY(randY);
			// tree.setEffect(ds);
			treeArrayList.add(tree);
			root.getChildren().add(tree);

			if (effects == true) {
				addEffect(tree);
			}

		}

		// debugging purpose
		System.out.println("Trees: " + treeArrayList.size());

		/*
		 * Iterator<Circle> iterator = treeArrayList.iterator(); while
		 * (iterator.hasNext()) { Circle tree = iterator.next();
		 * System.out.println("Tree position, X:" + tree.getCenterX() + "Y:" +
		 * tree.getCenterY()); }
		 */
	}

	// simple modifiable getter for arraylist
	public ArrayList<Circle> getTreeList() {
		return treeArrayList;
	}

	private void addEffect(Circle tree) {
		MotionBlur mb = new MotionBlur();
		mb.setRadius(5.0f);
		mb.setAngle(90.0f);
		tree.setEffect(mb);
	}
}
