package org.usfirst.frc.team5010.robot.subsystems;

import org.usfirst.frc.team5010.robot.Robot;
import org.usfirst.frc.team5010.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class LowerRiser extends Subsystem {
	/*
	 * Default constructor.
	 */
	private double deadZone = .15;
	public static double MAX_ANGLE = 360;
	private static final double ANGLE_RANGE = 100;
	public static double MIN_ANGLE = 0;
	private static double lastAngle = 0;
	
//	public static double MIN_HEIGHT = 9;
//	public static double MAX_HEIGHT = 50;

	private static double potOffset = 0;

	public LowerRiser() {
		lastAngle = MIN_ANGLE;
	}

	// @Override
	protected void initDefaultCommand() {
	}

	public void move() {
		// Allows for raising and lowering on same axis.
		if (Math.abs(Robot.oi.joyCoDriver.getRawAxis(5)) > 0.0) {
			// Raise and lower should be on the same axis, because they shouldn't be
			// triggered at the same time.
			double output = scaleInputs(Robot.oi.joyCoDriver.getRawAxis(5));

			RobotMap.lowerRiserMotor.set(-output);
			SmartDashboard.putNumber("BackRiser speed:", output);
		} else {
			RobotMap.lowerRiserMotor.set(0);
		}
		SmartDashboard.putNumber("Back Riser Potentiometer", getPotValue());
		SmartDashboard.putNumber("BackRiser getHeight", getHeight());
	}

	public double scaleInputs(double input) {
		if (Math.abs(input) < deadZone) {
			input = 0;
		} else if (input > 0) {
			input = (input - deadZone) * 1 / (1 - deadZone);
		} else if (input < 0) {
			input = (input + deadZone) * 1 / (1 - deadZone);
		}

		return input;
	}

	// armlength 22 // //h1 30.25 //
	public double getHeight() {
		double potValue = getPotValue();
//		if (potValue < MIN_ANGLE || potValue > MAX_ANGLE) {
//			potValue = lastAngle;
//		} else {
//			lastAngle = potValue;
//		}
		
		SmartDashboard.putNumber("Back Riser Potentiometer", potValue);
		return potValue;
	}

	// /@Override
	protected double returnPIDInput() {
		return getHeight();
	}

	// @Override
	protected void usePIDOutput(double output) {
	}

	public static double getPotValue() {
		return 360 - RobotMap.lowerRiserPotent.get();
	}

	public static void calibratePot() {
		double potValue = getPotValue();
		while (potValue < 0 || potValue > 360) {
			potValue = getPotValue();
		}
		MIN_ANGLE = potValue;
		MAX_ANGLE = potValue + ANGLE_RANGE;
		lastAngle = MIN_ANGLE;
		SmartDashboard.putNumber("Lower Riser Min Angle", MIN_ANGLE);
		SmartDashboard.putNumber("Lower Riser Max Angle", MAX_ANGLE);
		//potOffset = 360 - RobotMap.lowerRiserPotent.get() - MIN_ANGLE;
		//SmartDashboard.putNumber("Lower Pot Offset", potOffset);
	}
}
