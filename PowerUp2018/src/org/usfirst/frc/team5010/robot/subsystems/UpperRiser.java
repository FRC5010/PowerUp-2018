package org.usfirst.frc.team5010.robot.subsystems;

import org.usfirst.frc.team5010.robot.Robot;
import org.usfirst.frc.team5010.robot.RobotMap;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class UpperRiser extends Subsystem{
	/*
	 * Default constructor.
	 */
	private double deadZone = .15;
	public static double MAX_ANGLE = 180;
	private static final double ANGLE_RANGE = 100;
	public static double MIN_ANGLE = 0;
	private static double lastAngle = 0;
	
//	public static double MIN_HEIGHT = -3;
//	public static double MAX_HEIGHT = 46.5;
	
	private static double potOffset = 0;
	
	private PIDController PID;
	
	double lastOutput = 0;
	
	public UpperRiser() {
		lastAngle = MIN_ANGLE;
	}

	protected void initDefaultCommand() {
	}

	public void move() {
		// Handles raising and lowering
		// Reason: Raising and lowering will never be done at the same time
		if (Math.abs(Robot.oi.joyCoDriver.getRawAxis(1)) > 0.0) {
			double output = scaleInputs(Robot.oi.joyCoDriver.getRawAxis(1));
			RobotMap.upperRiserMotor.set(-output);
			SmartDashboard.putNumber("FrontRiser move:", -output);
		} else {
			RobotMap.upperRiserMotor.set(0);
		}
		getHeight();
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

	// armlength 29. //a1 19 //
	public double getHeight() {
		double potValue = getPotValue();
	
		if (potValue < MIN_ANGLE || potValue > MAX_ANGLE) {
			potValue = lastAngle;
		} else {
			lastAngle = potValue;
		}
		
		SmartDashboard.putNumber("Upper Riser Potentiometer", potValue);
		//double height = 19 - 29 * Math.cos(potValue * (Math.PI / 180));
//		double height = RobotMap.upperarmheight-(RobotMap.upperarmLength * Math.cos(potValue * (Math.PI / 180)));
//		SmartDashboard.putNumber("Upper Riser height:", height);
//		return height;
		return potValue;
	}
	
	public double getPotValue() {
		return RobotMap.upperRiserPotent.get();
	}

	public static void calibratePot() {
		double potValue = RobotMap.upperRiserPotent.get();
		while (potValue < 0 || potValue > 360) {
			potValue = RobotMap.upperRiserPotent.get();
		}
		MIN_ANGLE = potValue;
		MAX_ANGLE = potValue + ANGLE_RANGE;
		lastAngle = MIN_ANGLE;
		SmartDashboard.putNumber("Upper Riser Min Angle", MIN_ANGLE);
		SmartDashboard.putNumber("Upper Riser Max Angle", MAX_ANGLE);
//		potOffset = RobotMap.upperRiserPotent.get() - MIN_ANGLE;
//		SmartDashboard.putNumber("Upper Pot Offset", potOffset);
	}
}