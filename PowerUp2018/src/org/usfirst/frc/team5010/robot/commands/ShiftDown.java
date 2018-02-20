package org.usfirst.frc.team5010.robot.commands;

import org.usfirst.frc.team5010.robot.RobotMap;
import org.usfirst.frc.team5010.robot.subsystems.Shift;

import edu.wpi.first.wpilibj.command.Command;

public class ShiftDown extends Command {
	Shift shift = null;

	public ShiftDown() {
    	requires(RobotMap.shift);
    	this.shift = RobotMap.shift;

    }

	// Called just before this Command runs the first time
	protected void initialize() {
		shift.shiftDown();
		
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
		//shift.stop();
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		end();
	}
}
