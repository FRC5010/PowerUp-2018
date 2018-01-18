package org.usfirst.frc.team5010.robot.commands;

import org.usfirst.frc.team5010.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class SwitchDriveMode extends Command {
	
	public SwitchDriveMode() {
		
	}
	
	protected void execute() {
		Robot.toa = !Robot.toa;
		System.out.println(Robot.toa);
	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return true;
	}
}
