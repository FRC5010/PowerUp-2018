package org.usfirst.frc.team5010.robot.commands;

import org.usfirst.frc.team5010.robot.Robot;
import org.usfirst.frc.team5010.robot.RobotMap;
import org.usfirst.frc.team5010.robot.subsystems.BackRiser;

import edu.wpi.first.wpilibj.command.Command;

public class BackRiserMove extends Command {
	BackRiser riser = null;

	/**
	 * Default constructor.
	 */
	public BackRiserMove() {
		requires(RobotMap.backLifter);
		riser = RobotMap.backLifter;
	}

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	riser.move();
    }

	@Override
	protected boolean isFinished() {
    	if (riser.isAxisPressed())
    	{
    		return true;
    	}
        return false;
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
