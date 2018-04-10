package org.usfirst.frc.team5010.robot.commands;

import org.usfirst.frc.team5010.robot.RobotMap;
import org.usfirst.frc.team5010.robot.jetson_autonomous.DoublePointer;
import org.usfirst.frc.team5010.robot.jetson_autonomous.MovementCalculator;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class VisionMovement extends Command {
	double x = -1;
	double y = -1;
	double size = -1;
	double res = -1;
	DoublePointer theta;
	DoublePointer mag;
	MovementCalculator calc;
	
	public VisionMovement() {
		theta = new DoublePointer();
		theta.val = -1;
		mag = new DoublePointer();
		mag.val = -1;
		calc = new MovementCalculator();
	}

	@Override
	protected boolean isFinished() {
		return false;
	}

	@Override
	public void execute() {
		x = RobotMap.visionIO.getX();
		SmartDashboard.putNumber("BoxX", x);
		y = RobotMap.visionIO.getY();
		SmartDashboard.putNumber("BoxY", y);
		size = RobotMap.visionIO.getSize();
		SmartDashboard.putNumber("BoxSize", size);
		res = RobotMap.visionIO.getSize();
		SmartDashboard.putNumber("BoxRes", res);
		
		calc.computeNextValues(theta, mag);
		SmartDashboard.putNumber("BoxTheta", theta.val);
		SmartDashboard.putNumber("BoxMag", mag.val);
	}
	
}
