package org.usfirst.frc.team5010.robot.commands;

import org.usfirst.frc.team5010.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class UpperHeightJoystick extends Command {

    public UpperHeightJoystick() {
        requires(RobotMap.upperLifter);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	RobotMap.upperLifter.getPIDController().disable();
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	RobotMap.upperLifter.move();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	RobotMap.upperLifter.setHeight(RobotMap.upperLifter.getHeight());
    	RobotMap.upperLifter.getPIDController().enable();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}