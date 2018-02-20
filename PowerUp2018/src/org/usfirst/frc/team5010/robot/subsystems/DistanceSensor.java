package org.usfirst.frc.team5010.robot.subsystems;

import org.usfirst.frc.team5010.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DistanceSensor extends Subsystem {
	
	double forwardDPP = .16;
	
	
	public DistanceSensor() {
		RobotMap.forwardEncoder.reset();
		RobotMap.reverseEncoder.reset();
		double dpp = RobotMap.forwardEncoder.getDistancePerPulse();
		SmartDashboard.getNumber("Distance per pulse", dpp);
		RobotMap.forwardEncoder.setDistancePerPulse(forwardDPP);
		RobotMap.reverseEncoder.setDistancePerPulse(forwardDPP);
		
	}

	public DistanceSensor(String name) {
		super(name);
	}
	public double getDistance() {
		SmartDashboard.putNumber("right encoder raw", RobotMap.forwardEncoder.getRaw());
		SmartDashboard.putNumber("right encoder distance", RobotMap.forwardEncoder.getDistance());
		
		SmartDashboard.putNumber("left encoder raw", RobotMap.reverseEncoder.getRaw());
		SmartDashboard.putNumber("left encoder distance", RobotMap.reverseEncoder.getDistance());
		
		return RobotMap.forwardEncoder.getDistance();
	}
	public void reset() {
		RobotMap.forwardEncoder.reset();
	}
	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
	}

}
