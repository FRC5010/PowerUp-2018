package org.usfirst.frc.team5010.robot.commands;

import org.usfirst.frc.team5010.robot.Robot;
import org.usfirst.frc.team5010.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class TeleopDefault extends Command {
	private double straightValue;
	private double turnValue;
	private double deadZone = .15;

	private double leftValue;
	private double rightValue;

	public TeleopDefault() {
		requires(RobotMap.drivetrain);
		requires(RobotMap.distance);
		requires(RobotMap.range);
		requires(RobotMap.fourbar);
		// requires(RobotMap.backLifter);
		// requires(RobotMap.frontLifter);
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
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

	// Called just before this Command runs the first time
	protected void initialize() {

	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		if (Robot.toa) {

			// arcade mode

			// leftValue = -scaleInputs(Robot.oi.joyDriver.getRawAxis(1));
			// rightValue = scaleInputs(Robot.oi.joyDriver.getRawAxis(2));
			leftValue = -scaleInputs(Robot.oi.joyDriver.getRawAxis(1));
			rightValue = scaleInputs(Robot.oi.joyDriver.getRawAxis(4));
			RobotMap.drivetrain.drive(leftValue + rightValue, leftValue - rightValue);
		} else if (!Robot.toa) {
			// tank mode
			leftValue = -scaleInputs(Robot.oi.joyDriver.getRawAxis(1));
			rightValue = -scaleInputs(Robot.oi.joyDriver.getRawAxis(5));
			// leftValue = -scaleInputs(Robot.oi.joyDriver.getRawAxis(1));
			// rightValue = -scaleInputs(Robot.oi.joyDriver2.getRawAxis(1));
			RobotMap.drivetrain.drive(leftValue, rightValue);
		}
		SmartDashboard.putNumber("encoder", RobotMap.distance.getDistance());
		SmartDashboard.putNumber("range front", RobotMap.range.getFrontDistance());
		SmartDashboard.putNumber("total Height", RobotMap.fourbar.getHeight());
		SmartDashboard.putNumber("range back", RobotMap.range.getBackDistance());

		// RobotMap.frontLifter.move();
		// RobotMap.backLifter.move();
		RobotMap.cubeIntake.devourCube();
		RobotMap.cubeIntake.spitCube();
		RobotMap.cubeIntake.checkForStop();
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return false;
	}

	// Called once after isFinished returns true
	protected void end() {
		RobotMap.drivetrain.drive(0, 0);

	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		end();
	}
}
