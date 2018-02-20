package org.usfirst.frc.team5010.robot.subsystems;

import org.usfirst.frc.team5010.robot.Robot;
import org.usfirst.frc.team5010.robot.RobotMap;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class UpperRiser extends PIDSubsystem {
	/*
	 * Default constructor.
	 */
	private double deadZone = .15;
	private static final double MAX_ANGLE = 140;
	private static final double MIN_ANGLE = 45;
	
	public static final double MIN_HEIGHT = -1;
	public static final double MAX_HEIGHT = 46.5;
	
	private static double potOffset = 0;
	
	private PIDController PID;
	
	double lastOutput = 0;
	
	public UpperRiser(String name) {
		super(name, 0.1, 0.0, 0.2);
		PID = getPIDController();
		PID.setAbsoluteTolerance(2);
		PID.setInputRange(MIN_HEIGHT, MAX_HEIGHT);
		PID.setOutputRange(-.5, .6);
		PID.setSetpoint(1);
		//PID.disable();
		PID.enable();
	}

	protected void initDefaultCommand() {
	}

	/**
	 * Function determines whether joystick controller is being pressed or not.
	 * 
	 * @return boolean
	 */
	public boolean isAxisPressed() {
		if (Math.abs(Robot.oi.joyCoDriver.getRawAxis(1)) > 0.0) {
			return true;
		}
		return false;
	}

	public void move() {
		// Handles raising and lowering
		// Reason: Raising and lowering will never be done at the same time
		if (Math.abs(Robot.oi.joyCoDriver.getRawAxis(1)) > 0.0) {
			double output = scaleInputs(Robot.oi.joyCoDriver.getRawAxis(1));
			RobotMap.upperRiserMotor.set(-output);
			SmartDashboard.putNumber("FrontRiser move:", output);
		}
		SmartDashboard.putNumber("Front Riser Potentiometer", RobotMap.upperRiserPotent.get());
		SmartDashboard.putNumber("FrontRiser getHeight", getHeight());

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

	// armlength 29. //a1 19 //
	public double getHeight() {
		double potValue = getPotValue();
		//double height = 19 - 29 * Math.cos(potValue * (Math.PI / 180));
		double height = RobotMap.upperarmheight-(RobotMap.upperarmLength * Math.cos(potValue * (Math.PI / 180)));
		return height;
	}

	protected double returnPIDInput() {
		return getHeight();
	}

	// @Override
	protected void usePIDOutput(double output) {
//		if(getPotValue() >= MAX_ANGLE) {
//			output = 0;
//		} else if(getPotValue() <= MIN_ANGLE) {
//			output = 0;
//		}
		
		SmartDashboard.putNumber("Upper Riser Potentiometer value", RobotMap.upperRiserPotent.get());
		SmartDashboard.putNumber("Upper Riser Angle", getPotValue());
		SmartDashboard.putNumber("Upper Riser getHeight", getHeight());

		
		if(PID.getError() < 0) {
		// momentary braking solution
		// Apply back EMF for just long enough to get resistance
			RobotMap.upperRiserMotor.set(0);
			try {
				Thread.sleep(5);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	// Alternating braking solution
	// Switch back and forth between braking and powered descent
	//		if(lastOutput != 0) {
	//			RobotMap.frontriser.set(0);
	//			lastOutput = 0;
	//		}else {
	//			lastOutput = output;
	//		}
		}
		SmartDashboard.putNumber("FrontRiser PID output:", output);
		SmartDashboard.putNumber("FrontRiser PID ERROR ", PID.getError());
		SmartDashboard.putNumber("FrontRiser PID SetPoint ", PID.getSetpoint());
		RobotMap.upperRiserMotor.set(output);
		
	}
	
	public double getPotValue() {
		return RobotMap.upperRiserPotent.get() - potOffset;
	}

	public static void calibratePot() {
		potOffset = RobotMap.upperRiserPotent.get() - MIN_ANGLE;
		SmartDashboard.putNumber("Upper Pot Offset", potOffset);
	}
}