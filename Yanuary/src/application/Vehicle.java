package application;

public abstract class Vehicle {
	double positionX;
	double positionY;
	double angle;
	double velocity;
	double acceleration;
	
	public Vehicle(double positionX, double positionY, double angle, double velocity, double acceleration) {
		super();
		this.positionX = positionX;
		this.positionY = positionY;
		this.angle = angle;
		this.velocity = velocity;
		this.acceleration = acceleration;
	}
	
	public Vehicle(){
		super();
	}
	
	public double getPositionX() {
		return positionX;
	}

	public void setPositionX(double positionX) {
		this.positionX = positionX;
	}

	public double getPositionY() {
		return positionY;
	}

	public void setPositionY(double positionY) {
		this.positionY = positionY;
	}

	public double getAngle() {
		return angle;
	}

	public void setAngle(double angle) {
		this.angle = angle;
	}

	public double getVelocity() {
		return velocity;
	}

	public void setVelocity(double velocity) {
		this.velocity = velocity;
	}

	public double getAcceleration() {
		return acceleration;
	}

	public void setAcceleration(double acceleration) {
		this.acceleration = acceleration;
	}

}
