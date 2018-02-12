package org.usfirst.frc.team5010.robot.commands.auto;

import org.usfirst.frc.team5010.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class LowerArms extends Command {

    public LowerArms() {
        requires(RobotMap.backLifter);
        requires(RobotMap.frontLifter);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	RobotMap.frontLifter.setHeight(1);
    	//RobotMap.backLifter.setHeight(10.9);
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
    	end();
    }
}
