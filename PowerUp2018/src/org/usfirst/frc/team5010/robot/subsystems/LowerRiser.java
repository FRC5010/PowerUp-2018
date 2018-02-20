package org.usfirst.frc.team5010.robot.subsystems;

import org.usfirst.frc.team5010.robot.Robot;
import org.usfirst.frc.team5010.robot.RobotMap;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class LowerRiser extends PIDSubsystem {
	/*
	 * Default constructor.
	 */
	private double deadZone = .15;
	private static final double MAX_ANGLE = 135;
	private static final double MIN_ANGLE = 35;

	public static final double MIN_HEIGHT = 10;
	public static final double MAX_HEIGHT = 50;

	private static double potOffset = 0;

	private PIDController PID;

	public LowerRiser(String name) {
		super(name, 0.2, .0, 0.0);
		PID = getPIDController();
		PID.setAbsoluteTolerance(2);
		PID.setInputRange(MIN_HEIGHT, MAX_HEIGHT);
		PID.setOutputRange(-.5, .8);
		PID.setSetpoint(11);
		// PID.disable();
		 PID.enable();
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
			RobotMap.lowerRiserMotor.set(output);
			SmartDashboard.putNumber("BackRiser speed:", output);
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

	public void setHeight(double height) {
		height = height < MIN_HEIGHT ? MIN_HEIGHT : height;
		height = height > MAX_HEIGHT ? MAX_HEIGHT : height;
		PID.setSetpoint(height);
	}

	// armlength 22 // //h1 30.25 //
	public double getHeight() {
		double potValue = getPotValue();
		SmartDashboard.putNumber("Back Riser Potentiometer", potValue);
		// double height = 30.25 - 22 * Math.cos(potValue * (Math.PI / 180));

		double height = RobotMap.lowerarmheight - (RobotMap.lowerarmLength * Math.cos(potValue * (Math.PI / 180)));
		SmartDashboard.putNumber("BackRiser height:", height);
		return height;
	}

	// /@Override
	protected double returnPIDInput() {
		return getHeight();
	}

	// @Override
	protected void usePIDOutput(double output) {
		SmartDashboard.putNumber("backRiser unaltered PID output:", output);
		SmartDashboard.putNumber("Lower Riser Potentiometer value", RobotMap.lowerRiserPotent.get());
		SmartDashboard.putNumber("Lower Riser Angle", getPotValue());
		SmartDashboard.putNumber("Lower Riser getHeight", getHeight());

//		if (PID.getError() < 0) {
//			// momentary braking solution
//			// Apply back EMF for just long enough to get resistance
//			RobotMap.upperRiserMotor.set(0);
//			try {
//				Thread.sleep(5);
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}

		// if(getPotValue() > MAX_ANGLE) {
		// output = 0;
		// } else if(getPotValue() < MIN_ANGLE) {
		// output = 0;
		// }
		SmartDashboard.putNumber("backRiser PID output:", output);
		SmartDashboard.putNumber("backRiser PID ERROR ", PID.getError());
		SmartDashboard.putNumber("backRiser PID SetPoint ", PID.getSetpoint());
		RobotMap.lowerRiserMotor.set(-output);
	}

	public double getPotValue() {
		return 360 - RobotMap.lowerRiserPotent.get() - potOffset;
	}

	public static void calibratePot() {
		potOffset = 360 - RobotMap.lowerRiserPotent.get() - MIN_ANGLE;
		SmartDashboard.putNumber("Lower Pot Offset", potOffset);
	}
}
