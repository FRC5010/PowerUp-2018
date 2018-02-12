package org.usfirst.frc.team5010.robot.commands;

import org.usfirst.frc.team5010.robot.Robot;
import org.usfirst.frc.team5010.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class SetBackHeight extends Command{

	double height = 11;
	
	public SetBackHeight() {
		requires(RobotMap.backLifter);
	}

	public void initialize() {
		//RobotMap.backLifter.setHeight(SmartDashboard.getNumber("Height to raise to",0));
	}
	
	public void execute() {
		height += Robot.oi.joyCoDriver.getRawAxis(5) / 3;
		height = Math.max(height = Math.min(height, 50), 11);
		
		RobotMap.backLifter.setHeight(height);
		SmartDashboard.putNumber("Height to raise back to", height);
	}

	@Override
	protected boolean isFinished() {
		return false;
	}
	
	

	

}
