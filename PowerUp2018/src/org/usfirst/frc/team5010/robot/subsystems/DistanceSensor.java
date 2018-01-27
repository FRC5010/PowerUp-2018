package org.usfirst.frc.team5010.robot.subsystems;

import org.usfirst.frc.team5010.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DistanceSensor extends Subsystem {

	public DistanceSensor() {
		RobotMap.encoder.reset();
		double dpp = RobotMap.encoder.getDistancePerPulse();
		SmartDashboard.getNumber("distance per pulse", dpp);
		RobotMap.encoder.setDistancePerPulse(SmartDashboard.getNumber("distance per pulse", 0.09));
	}

	public DistanceSensor(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}
	public double getDistance() {
		return -RobotMap.encoder.getDistance();
	}
	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		
		
		
		
		
		
		
		
		
		
		

	}

}
