package org.usfirst.frc.team5010.robot.commands;

import org.usfirst.frc.team5010.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

public class EatCube extends Command {

	public EatCube() {
		requires(RobotMap.cubeIntake);
			
	}

	public void execute() {
		RobotMap.cubeIntake.devourCube();
	}
	@Override
	public void end() {
		RobotMap.cubeIntake.stop();
	}
	
	protected boolean isFinished() {
		
		return false;
	}

}
