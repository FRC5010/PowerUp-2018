package org.usfirst.frc.team5010.robot.commands;

import org.usfirst.frc.team5010.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

public class SpitCube extends Command {

	public SpitCube() {
		requires(RobotMap.cubeIntake);
	}
	public void execute() {
		RobotMap.cubeIntake.spitCube();
	}
	public void end() {
		RobotMap.cubeIntake.stop();
	}
	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}

}
