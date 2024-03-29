package org.usfirst.frc.team5010.robot.commands;

import org.usfirst.frc.team5010.robot.OI;
import org.usfirst.frc.team5010.robot.Robot;
import org.usfirst.frc.team5010.robot.RobotMap;
import org.usfirst.frc.team5010.robot.subsystems.LowerRiser;

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
		RobotMap.distance.reset();
	}

	public double scaleInputs(double input) {
		if (Math.abs(input) < deadZone) {
			input = 0;
		} else if (input > 0) {
			input = (input - deadZone) * 1 / (1 - deadZone);
		} else if (input < 0) {
			input = (input + deadZone) * 1 / (1 - deadZone);
		}

		return input * input * input;
	}

	// Called just before this Command runs the first time
	protected void initialize() {

	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		leftValue = -scaleInputs(Robot.oi.joyDriver.getRawAxis(1));
		rightValue = scaleInputs(Robot.oi.joyDriver.getRawAxis(4)) * 0.4f;
		RobotMap.drivetrain.drive(leftValue + rightValue, leftValue - rightValue);

	//	RobotMap.range.getFrontDistance();
		//RobotMap.range.getBackDistance();
		RobotMap.distance.getDistance();
		RobotMap.lowerLifter.getHeight();
		RobotMap.upperLifter.getHeight();
		//SmartDashboard.putNumber("Upper Lifter Angle", RobotMap.upperLifter.getHeight());
		//SmartDashboard.putNumber("Lower Lifter Angle", LowerRiser.getPotValue());

		SmartDashboard.putNumber("Gyro Angle", RobotMap.direction.angle());
		//SmartDashboard.putNumber("Range Back", RobotMap.range.getBackDistance());

		RobotMap.cubeIntake.devourCube();
		RobotMap.cubeIntake.spitCube();
		RobotMap.cubeIntake.checkForStop();
		

	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return false;// isCanceled();
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
