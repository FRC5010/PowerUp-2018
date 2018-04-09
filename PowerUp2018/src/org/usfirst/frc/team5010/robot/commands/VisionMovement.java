package org.usfirst.frc.team5010.robot.commands;

import org.usfirst.frc.team5010.robot.RobotMap;
import org.usfirst.frc.team5010.robot.jetson_autonomous.DoublePointer;
import org.usfirst.frc.team5010.robot.jetson_autonomous.MovementCalculator;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class VisionMovement extends Command {
	DoublePointer x;
	DoublePointer y;
	DoublePointer size;
	DoublePointer res;
	DoublePointer theta;
	DoublePointer mag;
	MovementCalculator calc;
	
	public VisionMovement() {
		x = new DoublePointer();
		y = new DoublePointer();
		size = new DoublePointer();
		res = new DoublePointer();
		theta = new DoublePointer();
		mag = new DoublePointer();
		calc = new MovementCalculator();
	}

	@Override
	protected boolean isFinished() {
		return false;
	}

	@Override
	public void execute() {
		RobotMap.visionIO.getX(x);
		SmartDashboard.putNumber("BoxX", x.val);
		RobotMap.visionIO.getY(y);
		SmartDashboard.putNumber("BoxY", y.val);
		RobotMap.visionIO.getSize(size);
		SmartDashboard.putNumber("BoxSize", size.val);
		RobotMap.visionIO.getSize(res);
		SmartDashboard.putNumber("BoxRes", res.val);
		
		calc.computeNextValues(theta, mag, x.val, y.val, size.val, res.val);
		SmartDashboard.putNumber("BoxTheta", theta.val);
		SmartDashboard.putNumber("BoxMag", mag.val);
	}
	
}
