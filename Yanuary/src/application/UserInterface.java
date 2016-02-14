package application;

import javafx.scene.Group;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

public class UserInterface {

	void addStatusBox(AnchorPane root, Group protagonist) {
		Rectangle statusBox = new Rectangle(300, 100);
		statusBox.setTranslateX(10);
		statusBox.setTranslateY(10);
		statusBox.setFill(Color.LIGHTSTEELBLUE);
		statusBox.setOpacity(0.5);

		Text title = new Text("в сторону Грозного");
		title.setTextAlignment(TextAlignment.CENTER);

		title.setX(root.getWidth()/2);
		title.setY(10);

		Text protPosition = new Text("X: " + Double.toString(protagonist.getTranslateX()) + "Y: "
				+ (Double.toString(protagonist.getTranslateY())));
		protPosition.setX(20);
		protPosition.setY(20);
		DropShadow ds = new DropShadow();
		ds.blurTypeProperty();
		ds.setOffsetX(10);
		statusBox.setEffect(ds);
		root.getChildren().addAll(statusBox, protPosition, title);

	}

	void addGrid(Pane root) {
		// for (int i = 0; i < 12; i++) {
		// Line line = new Line();
		// line.startXProperty();
		// line.startXProperty().bind(i*50);
		// line.endXProperty().bind(root.widthProperty().divide(3));
		// line.endYProperty().bind(root.heightProperty());
		// line.setStroke(Color.GREEN);
		// }

		// supposed to be my background, NOTWORKING

		Line lineV1 = new Line();
		Line lineV2 = new Line();
		Line lineH1 = new Line();
		Line lineH2 = new Line();
		lineV1.setStroke(Color.INDIANRED);
		lineV2.setStroke(Color.INDIANRED);
		lineH1.setStroke(Color.AZURE);
		lineH2.setStroke(Color.AZURE);
		lineV1.setStrokeDashOffset(5);
		lineV1.setStrokeWidth(0.5);
		lineV2.setStrokeWidth(0.5);
		lineH1.setStrokeWidth(0.5);
		lineH2.setStrokeWidth(0.5);

		lineV2.startXProperty().bind(root.widthProperty().divide(3));
		lineV2.endXProperty().bind(root.widthProperty().divide(3));
		lineV2.endYProperty().bind(root.heightProperty());
		lineV1.startXProperty().bind(root.widthProperty().divide(1.5));
		lineV1.endXProperty().bind(root.widthProperty().divide(1.5));
		lineV1.endYProperty().bind(root.heightProperty());
		lineH1.startYProperty().bind(root.heightProperty().divide(3));
		lineH1.endXProperty().bind(root.widthProperty());
		lineH1.endYProperty().bind(root.heightProperty().divide(3));
		lineH2.startYProperty().bind((root.heightProperty().divide(1.5)));
		lineH2.endXProperty().bind(root.widthProperty());
		lineH2.endYProperty().bind(root.heightProperty().divide(1.5));
		root.getChildren().addAll(lineV1, lineV2, lineH1, lineH2);
	}
}
