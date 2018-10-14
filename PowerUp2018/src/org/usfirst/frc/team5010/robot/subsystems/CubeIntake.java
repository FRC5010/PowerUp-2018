package org.usfirst.frc.team5010.robot.subsystems;

import org.usfirst.frc.team5010.robot.Robot;
import org.usfirst.frc.team5010.robot.RobotMap;

import edu.wpi.first.wpilibj.DoubleSolenoid;
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

	public void devourCube() { // Pretty self explanatory, if the user says to eat the cube, tell the motors to
								// eat the cube
		if (Robot.oi.joyDriver.getRawAxis(2) > deadZone) {
			RobotMap.leftIntakeMotor.set(scaleInputs(Robot.oi.joyDriver.getRawAxis(2)));
			RobotMap.rightIntakeMotor.set(-scaleInputs(Robot.oi.joyDriver.getRawAxis(2)));
		}
	}

	public void spitCube() { // same as last function, but for output instead
		if (Robot.oi.joyDriver.getRawAxis(3) > deadZone) {
			RobotMap.leftIntakeMotor.set(-scaleInputs(Robot.oi.joyDriver.getRawAxis(3)));
			RobotMap.rightIntakeMotor.set(scaleInputs(Robot.oi.joyDriver.getRawAxis(3)));
		}
	}

	//
	public void stopIntakeMotors() {
		RobotMap.leftIntakeMotor.set(0);
		RobotMap.rightIntakeMotor.set(0);
	}

	public void setMotors(double power) {
		RobotMap.leftIntakeMotor.set(-power);
		RobotMap.rightIntakeMotor.set(power);
	}

	public void checkForStop() { // If neither previous functions are fufilled, stop sending values
		if (Robot.oi.joyDriver.getRawAxis(3) < deadZone && Robot.oi.joyDriver.getRawAxis(2) < deadZone) {
			stopIntakeMotors();
		}
	}

	public double scaleInputs(double input) { // Makes the deadzone value zero on the speedcontroller
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
		RobotMap.intake.set(DoubleSolenoid.Value.kReverse);
		SmartDashboard.putBoolean("Intake closed", true);
	}

	/**
	 * Open pnuematics.
	 */
	public void openIntake() {
		RobotMap.intake.set(DoubleSolenoid.Value.kForward);
		SmartDashboard.putBoolean("Intake closed", false);
	}

}
