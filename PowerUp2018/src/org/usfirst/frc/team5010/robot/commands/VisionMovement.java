package org.usfirst.frc.team5010.robot.commands;

import java.util.ArrayList;

import org.usfirst.frc.team5010.robot.RobotMap;
import org.usfirst.frc.team5010.robot.jetson_autonomous.DoublePointer;
import org.usfirst.frc.team5010.robot.jetson_autonomous.MovementCalculator;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class VisionMovement extends Command {
	private static final double GRAB_THRESHOLD = 0.5; //Threshold for grabbing the box
	private static final double ANGLE_TOLERANCE = 0.05;
	private ArrayList<Double> magnitudes, thetas; //History of previous vectors
	boolean reversing;
	
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
		magnitudes = new ArrayList<Double>();
		thetas = new ArrayList<Double>();
		reversing = false;
	}

	@Override
	protected boolean isFinished() {
		return magnitudes.size() == 0;
	}

	private void convertVectorToLeftRight(DoublePointer l, DoublePointer r, double mag, double theta) {
		double forward, sideways;
		forward = Math.cos(theta) * mag;
		sideways = Math.sin(theta) * mag;
		
		l.val = forward - sideways;
		r.val = forward + sideways;
	}
	
	@Override
	public void execute() {
		x = RobotMap.visionIO.getX();
		SmartDashboard.putNumber("BoxX", x);
		y = RobotMap.visionIO.getY();
		SmartDashboard.putNumber("BoxY", y);
		size = RobotMap.visionIO.getSize();
		SmartDashboard.putNumber("BoxSize", size);
		res = RobotMap.visionIO.getReserved();
		SmartDashboard.putNumber("BoxRes", res);
		
		calc.computeNextValues(theta, mag);
		SmartDashboard.putNumber("BoxTheta", theta.val);
		SmartDashboard.putNumber("BoxMag", mag.val);
		
		DoublePointer l, r;
		l = new DoublePointer();
		r = new DoublePointer();
		
		if (theta.val > ANGLE_TOLERANCE) {
			convertVectorToLeftRight(l, r, mag.val, theta.val);
			RobotMap.drivetrain.drive(l.val, r.val);
		} else {
			
		}
	}
	
}
