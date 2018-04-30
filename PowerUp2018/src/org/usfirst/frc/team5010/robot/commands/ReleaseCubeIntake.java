package org.usfirst.frc.team5010.robot.commands;

import org.usfirst.frc.team5010.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ReleaseCubeIntake extends Command {

	double startTime;
	double runTime = 1000;
	
    public ReleaseCubeIntake() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	//requires(RobotMap.cubeIntake);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	startTime = System.currentTimeMillis();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	RobotMap.cubeIntake.setMotors(-.4);
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return System.currentTimeMillis() > startTime + runTime;
    }

    // Called once after isFinished returns true
    protected void end() {
    	RobotMap.cubeIntake.setMotors(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
