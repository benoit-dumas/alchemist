package model.ES.component.motion;

import util.entity.Comp;

public class PlanarMotionCapacity implements Comp {
	private final double maxSpeed;
	private final double maxRotationSpeed;
	private final double acceleration;
	private final double deceleration;
	
	public PlanarMotionCapacity(double maxSpeed,
			double maxRotationSpeed,
			double acceleration,
			double deceleration){
		this.maxRotationSpeed = maxRotationSpeed;
		this.maxSpeed = maxSpeed;
		this.acceleration = acceleration;
		this.deceleration = deceleration;
	}

	public double getMaxSpeed() {
		return maxSpeed;
	}

	public double getMaxRotationSpeed() {
		return maxRotationSpeed;
	}

	public double getAcceleration() {
		return acceleration;
	}

	public double getDeceleration() {
		return deceleration;
	}

}