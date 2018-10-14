package org.usfirst.frc.team5010.robot.commands;

import org.usfirst.frc.team5010.robot.RobotMap;
import org.usfirst.frc.team5010.robot.subsystems.LowerRiser;
import org.usfirst.frc.team5010.robot.subsystems.UpperRiser;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class CalibratePots extends Command {

    public CalibratePots() {
    	
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	LowerRiser.calibratePot();
    	UpperRiser.calibratePot();
    	RobotMap.leftEncoder.reset();
    	RobotMap.rightEncoder.reset();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
