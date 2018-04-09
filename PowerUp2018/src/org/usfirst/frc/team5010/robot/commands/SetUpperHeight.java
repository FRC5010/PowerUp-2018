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
	boolean arbitraryAngle = false;
	boolean bailOut = false;
	double lastHeight = 0;
	int lastHeightCount = 0;

	public SetUpperHeight(boolean goUp) {
		super(0.1, 0.0, 0.0);
		requires(RobotMap.upperLifter);
		PID = getPIDController();
		PID.setAbsoluteTolerance(2);
		PID.setOutputRange(-.4, .6);
		this.goUp = goUp;
		PID.reset();
	}

	public SetUpperHeight(double angle) {
		super(0.1, 0.0, 0.0);
		requires(RobotMap.upperLifter);
		PID = getPIDController();
		PID.setAbsoluteTolerance(2);
		PID.setOutputRange(-.4, .8);
		goUp = true;
		arbitraryAngle = true;
		height = angle;
		PID.reset();
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		PID.setInputRange(RobotMap.upperLifter.MIN_ANGLE, RobotMap.upperLifter.MAX_ANGLE);
		bailOut = false;
		if (!arbitraryAngle) {
			if (goUp) {
				if (RobotMap.upperLifter.getHeight() < RobotMap.upperLifter.MIN_ANGLE + RobotMap.upperLifter.ANGLE_RANGE/ 3) {
					height = RobotMap.upperLifter.MIN_ANGLE + (RobotMap.upperLifter.MAX_ANGLE - RobotMap.upperLifter.MIN_ANGLE) / 2;
					SmartDashboard.putString("arm up?", "half");
				} else {
					height = RobotMap.upperLifter.MAX_ANGLE;
					SmartDashboard.putString("arm up?", "full");
				}
			} else {
				height = RobotMap.upperLifter.MIN_ANGLE;
			}
		}

		lastHeight = RobotMap.upperLifter.getHeight();
		lastHeightCount = 0;

		if (!arbitraryAngle && RobotMap.lowerLifter.getHeight() > RobotMap.lowerLifter.MIN_ANGLE + 30 && !goUp) {
			bailOut = true;
		} else {
			SmartDashboard.putNumber("upper height setpoint ", height);
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
		if (lastHeightCount > 40) {
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
