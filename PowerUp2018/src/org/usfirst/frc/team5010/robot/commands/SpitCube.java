package org.usfirst.frc.team5010.robot.commands;

import org.usfirst.frc.team5010.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

public class SpitCube extends Command {

	public SpitCube() {
		requires(RobotMap.cubeIntake);
	}
	
	public void execute() {
		RobotMap.cubeIntake.spitCube();
		RobotMap.cubeIntake.checkForStop();
	}
	
	public void end() {
		RobotMap.cubeIntake.stopIntakeMotors();
	}
	
	@Override
	protected boolean isFinished() {
		return false;
	}
	
	protected void interrupted() {
		end();
	}
}
