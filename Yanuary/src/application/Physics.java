package application;

import java.util.ArrayList;
import java.util.Iterator;

import javafx.scene.Group;
import javafx.scene.shape.Circle;

public class Physics {
	
	ArrayList<Circle> treeArrayList;
	
	
	public void collisionDetection(Group protagonist) {

		for (Iterator<Circle> iterator = treeArrayList.iterator(); iterator.hasNext();) {
			Circle tree = (Circle) iterator.next();
			if (protagonist.getBoundsInParent().intersects(tree.getBoundsInLocal())) {
				System.out.println("collision at " + tree.getBoundsInLocal());
				tree.setVisible(false);
			}
		}
	}
}
