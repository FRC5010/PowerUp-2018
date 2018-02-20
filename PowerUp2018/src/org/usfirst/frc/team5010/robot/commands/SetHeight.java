package org.usfirst.frc.team5010.robot.commands;

import org.usfirst.frc.team5010.robot.Robot;
import org.usfirst.frc.team5010.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class SetHeight extends Command {

	double height;
	double tolerance = 2.5;

	public SetHeight(double height) {
		requires(RobotMap.fourbar);
		this.height = height;
	}

	public void initialize() {
		RobotMap.fourbar.setHeight(height);
	}

	public void execute() {
	}

	@Override
	protected boolean isFinished() {
		return height >= RobotMap.fourbar.getHeight() - 5 && height <= RobotMap.fourbar.getHeight() + 5;
	}

}
