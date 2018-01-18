package org.usfirst.frc.team5010.robot.commands;

import org.usfirst.frc.team5010.robot.RobotMap;

import edu.wpi.first.wpilibj.command.PIDCommand;

public class TurnToAngle extends PIDCommand {
	
	private static double p = 0.06;
	private static double i = 0.04;
	private static double d = 0.04;

	public TurnToAngle() {
		super("TurnToAngle", p, i, d);
		// TODO Auto-generated constructor stub
		requires(RobotMap.drivetrain);
		requires(RobotMap.direction);
	}

	@Override
	protected double returnPIDInput() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	protected void usePIDOutput(double output) {
		// TODO Auto-generated method stub

	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}

}
