package org.usfirst.frc.team5010.robot.commands;

import org.usfirst.frc.team5010.robot.RobotMap;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.command.PIDCommand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class SetUpperHeight extends PIDCommand {

	PIDController PID;
	double height;
	boolean goUp = false;
	boolean bailOut = false;
	double lastHeight = 0;
	int lastHeightCount = 0;
	
	public SetUpperHeight(boolean goUp) {
		super(0.1, 0.0, 0.0);
		requires(RobotMap.upperLifter);
		PID = getPIDController();
		PID.setAbsoluteTolerance(2);
		PID.setInputRange(RobotMap.upperLifter.MIN_ANGLE, RobotMap.upperLifter.MAX_ANGLE);
		PID.setOutputRange(-.4, .8);
		this.goUp = goUp;
		PID.reset();
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		bailOut = false;
		if (goUp) {
			if (RobotMap.upperLifter.getHeight() < RobotMap.upperLifter.MAX_ANGLE / 3) {
				height = RobotMap.upperLifter.MAX_ANGLE / 2;
			} else {
				height = RobotMap.upperLifter.MAX_ANGLE;
			}
		} else {
			height = RobotMap.upperLifter.MIN_ANGLE;
		}
		
		lastHeight = RobotMap.upperLifter.getHeight();
		lastHeightCount = 0;
		
		if (RobotMap.lowerLifter.getHeight() > RobotMap.lowerLifter.MIN_ANGLE + 20 && !goUp) {
			bailOut = true;
		} else {
			PID.setSetpoint(height);
			PID.enable();
		}

		SmartDashboard.putBoolean("upper PID", PID.isEnabled());
		SmartDashboard.putBoolean("bailed out upper", bailOut);
		

	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		double currentHeight = RobotMap.upperLifter.getHeight();
		if (currentHeight == lastHeight) {
			lastHeightCount++;
		} else {
			lastHeightCount = 0;
		}
		lastHeight = currentHeight;
		if (lastHeightCount > 10) {
			bailOut = true;
		}
		
		SmartDashboard.putNumber("FrontRiser PID ERROR ", PID.getError());
		SmartDashboard.putNumber("FrontRiser PID SetPoint ", PID.getSetpoint());
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return PID.onTarget() || bailOut;
	}

	// Called once after isFinished returns true
	protected void end() {
		RobotMap.upperRiserMotor.set(0);
		PID.reset();
		SmartDashboard.putBoolean("upper PID", PID.isEnabled());
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		end();
	}

	@Override
	protected double returnPIDInput() {
		return RobotMap.upperLifter.getHeight();
	}

	@Override
	protected void usePIDOutput(double output) {
		SmartDashboard.putNumber("FrontRiser PID output:", output);
		RobotMap.upperRiserMotor.set(output);
	}
}
