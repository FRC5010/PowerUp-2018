package org.usfirst.frc.team5010.robot.subsystems;

import org.usfirst.frc.team5010.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DistanceSensor extends Subsystem {

	public DistanceSensor() {
		RobotMap.encoder.reset();
		double dpp = RobotMap.encoder.getDistancePerPulse();
		SmartDashboard.getNumber("Distance per pulse", dpp);
		RobotMap.encoder.setDistancePerPulse(SmartDashboard.getNumber("Distance per pulse", 0.09));
	}

	public DistanceSensor(String name) {
		super(name);
	}
	public double getDistance() {
		return -RobotMap.encoder.getDistance();
	}
	public void reset() {
		RobotMap.encoder.reset();
	}
	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
	}

}
