package org.usfirst.frc.team5010.robot.commands;

import org.usfirst.frc.team5010.robot.RobotMap;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.command.PIDCommand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class SetLowerHeight extends PIDCommand {

	double height;
	private PIDController PID;
	private boolean bailOut = false;
	boolean goUp;
	
	public SetLowerHeight(boolean goUp) {
		super(0.2, 0.0, 0.0);
		requires(RobotMap.lowerLifter);
		this.goUp = goUp;
		PID = getPIDController();
		PID.setAbsoluteTolerance(2);
		PID.setInputRange(RobotMap.lowerLifter.MIN_ANGLE, RobotMap.lowerLifter.MAX_ANGLE);
		PID.setOutputRange(-.5, .8);
		PID.reset();
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		bailOut = false;
		if(goUp) {
			height = RobotMap.lowerLifter.MAX_ANGLE;
		}else {
			height = RobotMap.lowerLifter.MIN_ANGLE;
		}
		
		if((RobotMap.upperLifter.MIN_ANGLE + 30) < RobotMap.upperLifter.getHeight()) {
			bailOut = false;
			PID.setSetpoint(height);
			PID.enable();
		} else {
		   bailOut = true;
		}
		
		SmartDashboard.putBoolean("lower PID", PID.isEnabled());
		SmartDashboard.putBoolean("bailed out lower", bailOut);
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		SmartDashboard.putNumber("backRiser PID ERROR ", PID.getError());
		SmartDashboard.putNumber("backRiser PID SetPoint ", PID.getSetpoint());
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return PID.onTarget() || bailOut;
	}

	// Called once after isFinished returns true
	protected void end() {
		RobotMap.lowerRiserMotor.set(0);
		PID.reset();
		SmartDashboard.putBoolean("lower PID", PID.isEnabled());
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		end();
	}

	@Override
	protected double returnPIDInput() {
		return RobotMap.lowerLifter.getHeight();
	}

	@Override
	protected void usePIDOutput(double output) {
		SmartDashboard.putNumber("backRiser PID output:", output);
		RobotMap.lowerRiserMotor.set(-output);
	}
}
