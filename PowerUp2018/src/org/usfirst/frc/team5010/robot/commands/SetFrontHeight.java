package org.usfirst.frc.team5010.robot.commands;

import org.usfirst.frc.team5010.robot.Robot;
import org.usfirst.frc.team5010.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class SetFrontHeight extends Command{

	double height = 1;
	
	public SetFrontHeight() {
		requires(RobotMap.frontLifter);
	}

	public void initialize() {
		//RobotMap.frontLifter.setHeight(SmartDashboard.getNumber("Height to raise to",0));
	}
	
	public void execute() {
		height += Robot.oi.joyCoDriver.getRawAxis(1) / 3;
		height = Math.max(height = Math.min(height, 45), 1);
		
		RobotMap.frontLifter.setHeight(height);
		SmartDashboard.putNumber("Height to raise front to", height);
	}

	@Override
	protected boolean isFinished() {
		return false;
	}
	
	

	

}
