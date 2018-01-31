package org.usfirst.frc.team5010.robot.commands;

import org.usfirst.frc.team5010.robot.RobotMap;
import org.usfirst.frc.team5010.robot.subsystems.Shift;

import edu.wpi.first.wpilibj.command.Command;

/**
 * @author Juan
 * @since 1/28/2016
 */
public class ShiftUp extends Command {
	Shift shift = null;
	long startTime = 0;

	/**
	 * Default constructor.
	 */
	public ShiftUp() {
    	requires(RobotMap.shift);
    	this.shift = RobotMap.shift;

    }
	
    // Called just before this Command runs the first time
    protected void initialize() {
    	startTime = System.currentTimeMillis();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	shift.shiftUp();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
		return (startTime + 1000 < System.currentTimeMillis());
    }

    // Called once after isFinished returns true
    protected void end() {
    	shift.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
