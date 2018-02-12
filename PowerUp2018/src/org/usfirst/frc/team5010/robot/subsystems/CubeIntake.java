package org.usfirst.frc.team5010.robot.subsystems;

import org.usfirst.frc.team5010.robot.Robot;
import org.usfirst.frc.team5010.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class CubeIntake extends Subsystem {
	private double deadZone = .15;

	/**
	 * Default constructor.
	 */
	public CubeIntake() {
		
	}

	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub

	}

	public void devourCube() { //Pretty self explanatory, if the user says to eat the cube, tell the motors to eat the cube
		if (Robot.oi.joyCoDriver.getRawAxis(2) > deadZone) {
			RobotMap.intakeMotorLeft.set(scaleInputs(Robot.oi.joyCoDriver.getRawAxis(2)) / 2);
			RobotMap.intakeMotorRight.set(-scaleInputs(Robot.oi.joyCoDriver.getRawAxis(2)) / 2);
		} 
	}

	public void spitCube() { //same as last function, but for output instead
		if (Robot.oi.joyCoDriver.getRawAxis(3) > deadZone) { 
			RobotMap.intakeMotorLeft.set(-scaleInputs(Robot.oi.joyCoDriver.getRawAxis(3)) / 2);
			RobotMap.intakeMotorRight.set(scaleInputs(Robot.oi.joyCoDriver.getRawAxis(3)) / 2);
		}
	}
	
	//
	public void stopIntakeMotors() {
		RobotMap.intakeMotorLeft.set(0);
		RobotMap.intakeMotorRight.set(0);
	}
	
	public void checkForStop() { //If neither previous functions are fufilled, stop sending values
		if(Robot.oi.joyCoDriver.getRawAxis(3) < deadZone && Robot.oi.joyCoDriver.getRawAxis(2) < deadZone) { 
			stopIntakeMotors();
		}
	}

	public double scaleInputs(double input) { //Makes the deadzone value zero on the speedcontroller
		if (Math.abs(input) < deadZone) {
			input = 0;
		} else if (input > 0) {
			input = (input - deadZone) * 1 / (1 - deadZone);
		} else if (input < 0) {
			input = (input + deadZone) * 1 / (1 - deadZone);
		}

		return input;
	}

	/**
	 * Close pnuematics.
	 */
	public void closeIntake() {
		RobotMap.intake.set(true);
		SmartDashboard.putBoolean("Intake open", true);
	}

	/**
	 * Open pnuematics.
	 */
	public void openIntake() {
		RobotMap.intake.set(false);
		SmartDashboard.putBoolean("Intake open", false);
	}

}
