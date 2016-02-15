package application;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;

public class Tank extends Vehicle {
	Group tank;

	public Tank() {
		tank = new Group();
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
		barrel.setTranslateX(tank.getTranslateX());
		barrel.setTranslateY(tank.getTranslateY());

		tank.getChildren().addAll(chassi, turret, hatch, barrel);

	}

	@Override
	public double getPositionX() {
		return this.getPositionX();
	}

	@Override
	public void setPositionX(double positionX) {
		tank.setLayoutX(positionX);
		//this.setPositionX(positionX);
	}

	@Override
	public double getPositionY() {
		return this.getPositionY();
	}

	@Override
	public void setPositionY(double positionY) {
		tank.setLayoutY(positionY);
		//this.setPositionY(positionY);
	}

	@Override
	public double getAngle() {
		return this.getAngle();
	}

	@Override
	public void setAngle(double angle) {
		this.setAngle(angle);
	}

	@Override
	public double getVelocity() {
		return this.getVelocity();
	}

	@Override
	public void setVelocity(double velocity) {
		this.setVelocity(velocity);
	}

	@Override
	public double getAcceleration() {
		return this.getAcceleration();
	}

	@Override
	public void setAcceleration(double acceleration) {
		this.setAcceleration(acceleration);
	}

}
