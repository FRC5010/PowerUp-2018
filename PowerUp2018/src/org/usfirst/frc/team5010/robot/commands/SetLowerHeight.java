package org.usfirst.frc.team5010.robot.commands;

import org.usfirst.frc.team5010.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class SetLowerHeight extends Command {

	double height;
	double tolerance = 5;
	
    public SetLowerHeight(double height) {
       requires(RobotMap.lowerLifter);
       this.height = height;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	RobotMap.lowerLifter.setHeight(height);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	return height >= RobotMap.lowerLifter.getHeight() - tolerance && height <= RobotMap.lowerLifter.getHeight() + tolerance;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
