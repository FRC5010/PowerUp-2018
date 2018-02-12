package org.usfirst.frc.team5010.robot.commands;

import org.usfirst.frc.team5010.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

public class EatCube extends Command {

	public EatCube() {
		requires(RobotMap.cubeIntake);
			
	}

	public void execute() {
		RobotMap.cubeIntake.devourCube();
		RobotMap.cubeIntake.checkForStop();
	}
	
	@Override
	public void end() {
		RobotMap.cubeIntake.stopIntakeMotors();
	}
	
	protected boolean isFinished() {
		
		return false;
	}
	
	protected void interrupted() {
		end();
	}
}
