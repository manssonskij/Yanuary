package application;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import javafx.scene.effect.BlurType;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Vegetation {
	ArrayList<Circle> treeArrayList;
	public void addTrees(Pane root) {

		// kanske borde göras till LinkedList
		DropShadow ds = new DropShadow();
		ds.blurTypeProperty();
		ds.setBlurType(BlurType.ONE_PASS_BOX);
		ds.setOffsetX(3);
		ds.setOffsetY(3);

		treeArrayList = new ArrayList<Circle>();
		Random rand = new Random();
		int noTrees = rand.nextInt(80 - 30) + 30;
		for (int i = 0; i < noTrees; i++) {
			int treeSize = rand.nextInt(20 - 5) + 5;
			Circle tree = new Circle(treeSize, Color.FORESTGREEN);
			int randX = rand.nextInt((int) root.getWidth());
			int randY = rand.nextInt((int) root.getHeight());
			// Circle tree = new Circle(10, Color.FORESTGREEN);
			tree.setCenterX(randX);
			tree.setCenterY(randY);
			// tree.setEffect(ds);
			treeArrayList.add(tree);
			root.getChildren().add(tree);
		}
		System.out.println("Trees: " + treeArrayList.size());

		Iterator<Circle> iterator = treeArrayList.iterator();
		while (iterator.hasNext()) {
			Circle tree = iterator.next();
			System.out.println("Tree position, X:" + tree.getCenterX() + " Y:" + tree.getCenterY());
		}

	}

}
