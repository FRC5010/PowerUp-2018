package org.usfirst.frc.team5010.robot.subsystems;

import org.usfirst.frc.team5010.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DistanceSensor extends Subsystem {
	
	double rightDPP = .16;
	//right encoder reversed?
	
	
	public DistanceSensor() {
		RobotMap.rightEncoder.reset();
		RobotMap.leftEncoder.reset();
		
		double dpp = RobotMap.rightEncoder.getDistancePerPulse();
		SmartDashboard.getNumber("Distance per pulse", dpp);
		
		RobotMap.rightEncoder.setDistancePerPulse(rightDPP);
		RobotMap.leftEncoder.setDistancePerPulse(rightDPP);
		
	}

	public DistanceSensor(String name) {
		super(name);
	}
	public double getDistance() {
		SmartDashboard.putNumber("right encoder raw", -RobotMap.rightEncoder.getRaw());
		SmartDashboard.putNumber("right encoder distance", -RobotMap.rightEncoder.getDistance());
		
		SmartDashboard.putNumber("left encoder raw", RobotMap.leftEncoder.getRaw());
		SmartDashboard.putNumber("left encoder distance", RobotMap.leftEncoder.getDistance());
		
		return -RobotMap.rightEncoder.getDistance();
	}
	
	public int getLeftRaw() {
		return RobotMap.leftEncoder.getRaw();
	}
	
	public int getRightRaw() {
		return -RobotMap.rightEncoder.getRaw();
		
	}
	public void reset() {
		RobotMap.rightEncoder.reset();
	}
	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
	}

}
